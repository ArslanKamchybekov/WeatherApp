package kg.geektech.weatherapp.data.room.converting.WeatherFor1;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import kg.geektech.weatherapp.data.models.one_day.Weather_1;

public class Weather_1Con {

    @TypeConverter
    public static List<Weather_1> toList(String value){
        if (value == null){
            return null;
        }
        Type listType = new TypeToken<List<Weather_1>>(){}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String toString(List<Weather_1> list){
        if (list == null){
            return null;
        }
        Gson gson = new Gson();
        return gson.toJson(list);
    }

}
