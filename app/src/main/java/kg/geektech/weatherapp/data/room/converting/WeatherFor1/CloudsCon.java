package kg.geektech.weatherapp.data.room.converting.WeatherFor1;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import kg.geektech.weatherapp.data.models.one_day.Clouds;

public class CloudsCon {

    @TypeConverter
    public String toString(Clouds clouds) {
        if (clouds == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Clouds>() {}.getType();
        return gson.toJson(clouds, type);
    }
    @TypeConverter
    public Clouds toClass(String value) {
        if (value == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Clouds>() {}.getType();
        return gson.fromJson(value, type);
    }
}
