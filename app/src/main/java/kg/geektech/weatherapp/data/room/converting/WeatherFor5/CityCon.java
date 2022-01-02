package kg.geektech.weatherapp.data.room.converting.WeatherFor5;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import kg.geektech.weatherapp.data.models.five_days.City;

public class CityCon {

    @TypeConverter
    public String toString(City city) {
        if (city == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<City>() {}.getType();
        return gson.toJson(city, type);
    }

    @TypeConverter
    public City toClass(String value) {
        if (value == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<City>() {}.getType();
        return gson.fromJson(value, type);
    }

}
