package kg.geektech.weatherapp.data.room.converting.WeatherFor5;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import kg.geektech.weatherapp.data.models.five_days.Clouds;
import kg.geektech.weatherapp.data.models.five_days.Sys;
import kg.geektech.weatherapp.data.models.five_days.Weather_5;

public class Weather_5Con {

    @TypeConverter
    public static List<Weather_5> toList(String value){
        if (value == null){
            return null;
        }
        Type listType = new TypeToken<List<Weather_5>>(){}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String toString(List<Weather_5> list){
        if (list == null){
            return null;
        }
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
