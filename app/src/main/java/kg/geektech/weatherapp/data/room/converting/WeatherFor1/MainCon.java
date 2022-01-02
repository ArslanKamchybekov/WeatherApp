package kg.geektech.weatherapp.data.room.converting.WeatherFor1;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import kg.geektech.weatherapp.data.models.one_day.Clouds;
import kg.geektech.weatherapp.data.models.one_day.Main;

public class MainCon {

    @TypeConverter
    public String toString(Main main) {
        if (main == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Main>() {}.getType();
        return gson.toJson(main, type);
    }
    @TypeConverter
    public Main toClass(String value) {
        if (value == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Main>() {}.getType();
        return gson.fromJson(value, type);
    }
}
