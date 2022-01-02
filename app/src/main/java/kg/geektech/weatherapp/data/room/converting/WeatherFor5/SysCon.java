package kg.geektech.weatherapp.data.room.converting.WeatherFor5;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import kg.geektech.weatherapp.data.models.five_days.Clouds;
import kg.geektech.weatherapp.data.models.five_days.Main;
import kg.geektech.weatherapp.data.models.five_days.Sys;

public class SysCon {

    @TypeConverter
    public String toString(Sys sys) {
        if (sys == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Sys>() {}.getType();
        return gson.toJson(sys, type);
    }

    @TypeConverter
    public Sys toClass(String value) {
        if (value == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Sys>() {}.getType();
        return gson.fromJson(value, type);
    }
}
