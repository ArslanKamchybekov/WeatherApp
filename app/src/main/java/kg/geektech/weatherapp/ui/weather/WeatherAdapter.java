package kg.geektech.weatherapp.ui.weather;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.weatherapp.data.models.one_day.Weather_1;
import kg.geektech.weatherapp.databinding.Item5dWeatherBinding;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private List<kg.geektech.weatherapp.data.models.five_days.List> weathers = new ArrayList<>();

    public void setWeathers(List<kg.geektech.weatherapp.data.models.five_days.List> weathers) {
        this.weathers = weathers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Item5dWeatherBinding binding = Item5dWeatherBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(weathers.get(position));
    }

    @Override
    public int getItemCount() {
        return weathers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Item5dWeatherBinding binding;
        public ViewHolder(@NonNull Item5dWeatherBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(kg.geektech.weatherapp.data.models.five_days.List weather) {
            String icon = "https://openweathermap.org/img/wn/" + weather.getWeather().get(0).getIcon() + "@2x.png";
            Glide.with(binding.getRoot()).load(icon).centerCrop().into(binding.ivDay);
            String time = weather.getDtTxt();
            binding.tvWeekDay.setText(time);
            String maxTemp = weather.getMain().getTempMax() + "°C";
            binding.tvDayMax.setText(maxTemp);
            String minTemp = weather.getMain().getTempMin() + "°C";
            binding.tvDayMin.setText(minTemp);
        }
    }
}
