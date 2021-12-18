package kg.geektech.weatherapp.ui.weather;

import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import kg.geektech.weatherapp.R;
import kg.geektech.weatherapp.common.Resource;
import kg.geektech.weatherapp.data.models.Weather;
import kg.geektech.weatherapp.databinding.FragmentWeatherBinding;

public class WeatherFragment extends Fragment {

    private FragmentWeatherBinding binding;
    private WeatherViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
        viewModel.getWeathers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWeatherBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.liveData.observe(getViewLifecycleOwner(), resource -> {
            switch (resource.status){
                case SUCCESS:{
                    String location = resource.data.getName() + ", " + resource.data.getSys().getCountry();
                    binding.tvLocation.setText(location);
                    String temp = new DecimalFormat("0").format(resource.data.getMain().getTemp());
                    binding.tvTemperature.setText(temp);
                    String increase = new DecimalFormat("0").format(resource.data.getMain().getTempMax()) + "°C";
                    binding.tvIncrease.setText(increase);
                    String decrease = new DecimalFormat("0").format(resource.data.getMain().getTempMin()) + "°C";
                    binding.tvDecrease.setText(decrease);
                    String humidity = resource.data.getMain().getHumidity() + "%";
                    binding.tvHumidityNum.setText(humidity);
                    String pressure = resource.data.getMain().getPressure() + "mBar";
                    binding.tvPressureNum.setText(pressure);
                    String windSpeed = resource.data.getWind().getSpeed() + "m/s";
                    binding.tvWindNum.setText(windSpeed);
                    binding.tvSunny.setText(resource.data.getWeather().get(0).getMain());
                    String date = getLiveTime(resource.data.getDt(), "EEE, dd MMM yyyy  |  HH:mm:ss", "GMT+6");
                    binding.tvDate.setText(date);
                    String sunset = getLiveTime(resource.data.getSys().getSunset(), "HH:MM", "GMT+6");
                    binding.tvSunsetNum.setText(sunset);
                    String sunrise = getLiveTime(resource.data.getSys().getSunrise(), "HH:MM", "GMT+6");
                    binding.tvSunriseNum.setText(sunrise);
                    Integer converting = resource.data.getSys().getSunset() - resource.data.getSys().getSunrise();
                    String daytime = getLiveTime(converting, "HH'h' MM'm'","GMT");
                    binding.tvDaytimeNum.setText(daytime);
                    String img = "https://openweathermap.org/img/wn/" + resource.data.getWeather().get(0).getIcon() + "@2x.png";
                    Glide.with(requireContext()).load(img).into(binding.ivSunny);
                    binding.tvDaytimeNum.setText(daytime);

                    Calendar c = Calendar.getInstance();
                    int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
                    if(timeOfDay >= 0 && timeOfDay < 12){
                        binding.ivStatus.setImageResource(R.drawable.img_day);
                    }else if(timeOfDay >= 12 && timeOfDay < 24){
                        binding.ivStatus.setImageResource(R.drawable.img_night);
                    }

                    success();
                    break;
                }
                case ERROR:{
                    Toast.makeText(requireContext(), "Couldn't load current weather", Toast.LENGTH_SHORT).show();
                    break;
                }
                case LOADING:{
                    loading();
                    break;
                }
            }
        });
    }
    private String getLiveTime(Integer timeInt, String timeFormat, String gmt){
        long time = timeInt * (long) 1000;
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat(timeFormat);
        format.setTimeZone(TimeZone.getTimeZone(gmt));
        return format.format(date);
    }
    private void success(){
        binding.progress.setVisibility(View.GONE);
        binding.n1.setVisibility(View.VISIBLE);
        binding.n2.setVisibility(View.VISIBLE);
        binding.n3.setVisibility(View.VISIBLE);
        binding.n4.setVisibility(View.VISIBLE);
        binding.n5.setVisibility(View.VISIBLE);
        binding.n6.setVisibility(View.VISIBLE);
        binding.n7.setVisibility(View.VISIBLE);
        binding.n8.setVisibility(View.VISIBLE);
        binding.n9.setVisibility(View.VISIBLE);
        binding.tvDate.setVisibility(View.VISIBLE);
        binding.tvLocation.setVisibility(View.VISIBLE);
        binding.ivLoc.setVisibility(View.VISIBLE);
    }
    private void loading(){
        binding.progress.setVisibility(View.VISIBLE);
        binding.n1.setVisibility(View.GONE);
        binding.n2.setVisibility(View.GONE);
        binding.n3.setVisibility(View.GONE);
        binding.n4.setVisibility(View.GONE);
        binding.n5.setVisibility(View.GONE);
        binding.n6.setVisibility(View.GONE);
        binding.n7.setVisibility(View.GONE);
        binding.n8.setVisibility(View.GONE);
        binding.n9.setVisibility(View.GONE);
        binding.tvDate.setVisibility(View.GONE);
        binding.tvLocation.setVisibility(View.GONE);
        binding.ivLoc.setVisibility(View.GONE);
    }
}