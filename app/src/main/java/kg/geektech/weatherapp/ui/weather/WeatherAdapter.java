package kg.geektech.weatherapp.ui.weather;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import kg.geektech.weatherapp.databinding.Item5dWeatherBinding;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private List<kg.geektech.weatherapp.data.models.five_days.List> list;

    public WeatherAdapter() {
        list = new ArrayList<>();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<kg.geektech.weatherapp.data.models.five_days.List> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Item5dWeatherBinding binding = Item5dWeatherBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final Item5dWeatherBinding binding;

        public ViewHolder(@NonNull Item5dWeatherBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(kg.geektech.weatherapp.data.models.five_days.List list) {
            String icon = "https://openweathermap.org/img/wn/" + list.getWeathers().get(0).getIcon() + "@2x.png";
            Glide.with(binding.getRoot()).load(icon).centerCrop().into(binding.ivDay);
            String date = getLiveTime(list.getDt(), "EEE, dd | HH:mm", "GMT+6");
            binding.tvWeekDay.setText(date);
            String maxTemp = list.getMain().getTempMax() + "°C";
            binding.tvDayMax.setText(maxTemp);
            String minTemp = list.getMain().getTempMin() + "°C";
            binding.tvDayMin.setText(minTemp);
        }

        private String getLiveTime(Integer timeInt, String timeFormat, String gmt) {
            long time = timeInt * (long) 1000;
            Date date = new Date(time);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat(timeFormat);
            format.setTimeZone(TimeZone.getTimeZone(gmt));
            return format.format(date);
        }
    }
}
