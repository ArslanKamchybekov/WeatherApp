package kg.geektech.weatherapp.ui.weather;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.icu.util.Calendar;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.weatherapp.R;
import kg.geektech.weatherapp.data.models.one_day.Weather_for_1;
import kg.geektech.weatherapp.data.room.WeatherFor1Dao;
import kg.geektech.weatherapp.data.room.WeatherFor5Dao;
import kg.geektech.weatherapp.databinding.FragmentWeatherBinding;

@AndroidEntryPoint
public class WeatherFragment extends Fragment implements LocationListener {

    private FragmentWeatherBinding binding;
    private WeatherViewModel viewModel;
    private WeatherAdapter adapter;
    private BroadcastReceiver receiver;
    @Inject
    WeatherFor1Dao weatherFor1Dao;
    @Inject
    WeatherFor5Dao weatherFor5Dao;
    private final String[] PERMS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private LocationManager locationManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new WeatherAdapter();
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
        locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        ActivityCompat.requestPermissions(requireActivity(), PERMS, 1);
        getData();
        getLocData();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWeatherBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();
        broadcastReceiver();
    }

    private void getData() {
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            String cityName = bundle.getString("keyName");
            Double lat = bundle.getDouble("key1");
            Double lon = bundle.getDouble("key2");
            viewModel.getWeathers(cityName, lat, lon);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                getLocData();
            } else {
                ActivityCompat.requestPermissions(requireActivity(), PERMS, 1);
            }
        }
    }

    private void getLocData() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    1000, 0, this
            );
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        if (getArguments() == null) {
            viewModel.getWeathers(null, location.getLatitude(), location.getLongitude());
            broadcastReceiver();
        }
    }

    private void broadcastReceiver() {
        receiver = new BroadcastReceiver() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onReceive(Context context, Intent intent) {
                try {
                    if (isOnline(context)) {
                        checkWIFI(true);
                    } else {
                        checkWIFI(false);
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        };
        requireActivity().registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void checkWIFI(boolean b) {
        binding.rvWeathers.setAdapter(adapter);
        if (b) {
            checkGPS();
            initAllData();
        } else {
            Toast.makeText(requireContext(), "No internet connection...", Toast.LENGTH_SHORT).show();
            error();
            adapter.setList(weatherFor5Dao.getWeatherFor5().getList());
            setData(weatherFor1Dao.getWeatherFor1());
        }
    }

    private void checkGPS() {
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            alertGPS();
        }
    }

    private void alertGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialogInterface, i) -> startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)))
                .setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel());
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private boolean isOnline(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initAllData() {
        viewModel.liveData1.observe(getViewLifecycleOwner(), resource -> {
            switch (resource.status) {
                case SUCCESS: {
                    setData(resource.data);
                    success();
                    break;
                }
                case ERROR: {
                    Toast.makeText(requireContext(), "Couldn't load current weather", Toast.LENGTH_SHORT).show();
                    error();
                    break;
                }
                case LOADING: {
                    loading();
                    break;
                }
            }
        });
        viewModel.liveData5.observe(getViewLifecycleOwner(), resource -> {
            switch (resource.status) {
                case SUCCESS: {
                    adapter.setList(resource.data.getList());
                    success();
                    break;
                }
                case ERROR: {
                    Toast.makeText(requireActivity(), "Couldn't load weather for 5 days", Toast.LENGTH_SHORT).show();
                    error();
                    break;
                }
                case LOADING: {
                    loading();
                    break;
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setData(Weather_for_1 weather_for_1) {
        String location = weather_for_1.getName() + ", " + weather_for_1.getSys().getCountry();
        binding.tvLocation.setText(location);
        String temp = new DecimalFormat("0").format(weather_for_1.getMain().getTemp());
        binding.tvTemperature.setText(temp);
        String increase = new DecimalFormat("0").format(weather_for_1.getMain().getTempMax()) + "°C";
        binding.tvIncrease.setText(increase);
        String decrease = new DecimalFormat("0").format(weather_for_1.getMain().getTempMin()) + "°C";
        binding.tvDecrease.setText(decrease);
        String humidity = weather_for_1.getMain().getHumidity() + "%";
        binding.tvHumidityNum.setText(humidity);
        String pressure = weather_for_1.getMain().getPressure() + "mBar";
        binding.tvPressureNum.setText(pressure);
        String windSpeed = weather_for_1.getWind().getSpeed() + "m/s";
        binding.tvWindNum.setText(windSpeed);
        binding.tvSunny.setText(weather_for_1.getWeather().get(0).getMain());
        String date = getLiveTime(weather_for_1.getDt(), "EEE, dd MMM yyyy  |  HH:mm:ss", "GMT+6");
        binding.tvDate.setText(date);
        String sunset = getLiveTime(weather_for_1.getSys().getSunset(), "HH:MM", "GMT+6");
        binding.tvSunsetNum.setText(sunset);
        String sunrise = getLiveTime(weather_for_1.getSys().getSunrise(), "HH:MM", "GMT+6");
        binding.tvSunriseNum.setText(sunrise);
        Integer converting = weather_for_1.getSys().getSunset() - weather_for_1.getSys().getSunrise();
        String daytime = getLiveTime(converting, "HH'h' MM'm'", "GMT");
        binding.tvDaytimeNum.setText(daytime);
        String img = "https://openweathermap.org/img/wn/" + weather_for_1.getWeather().get(0).getIcon() + "@2x.png";
        Glide.with(requireContext()).load(img).into(binding.ivSunny);
        binding.tvDaytimeNum.setText(daytime);

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if (timeOfDay >= 0 && timeOfDay < 17) {
            binding.ivStatus.setImageResource(R.drawable.img_day);
        } else if (timeOfDay >= 17 && timeOfDay < 24) {
            binding.ivStatus.setImageResource(R.drawable.img_night);
        }
    }

    private void success() {
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

    @SuppressLint("SetTextI18n")
    private void error() {
        binding.tvLocation.setText("Try again");
    }

    private void loading() {
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

    private void initListeners() {
        binding.ivCity.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.cityFragment);
        });
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });
    }

    private String getLiveTime(Integer timeInt, String timeFormat, String gmt) {
        long time = timeInt * (long) 1000;
        Date date = new Date(time);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat(timeFormat);
        format.setTimeZone(TimeZone.getTimeZone(gmt));
        return format.format(date);
    }

    private void unRegisterNetwork() {
        try {
            requireActivity().unregisterReceiver(receiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        unRegisterNetwork();
    }
}