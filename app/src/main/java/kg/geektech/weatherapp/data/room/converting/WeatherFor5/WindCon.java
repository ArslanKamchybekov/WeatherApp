package kg.geektech.weatherapp.data.room.converting.WeatherFor5;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import kg.geektech.weatherapp.data.models.five_days.Clouds;
import kg.geektech.weatherapp.data.models.five_days.Sys;
import kg.geektech.weatherapp.data.models.five_days.Wind;

public class WindCon {

    @TypeConverter
    public String toString(Wind wind) {
        if (wind == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Wind>() {}.getType();
        return gson.toJson(wind, type);
    }

    @TypeConverter
    public Wind toClass(String value) {
        if (value == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Wind>() {}.getType();
        return gson.fromJson(value, type);
    }

}
