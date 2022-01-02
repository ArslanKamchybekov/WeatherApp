package kg.geektech.weatherapp.data.room.converting.WeatherFor1;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import kg.geektech.weatherapp.data.models.one_day.Clouds;
import kg.geektech.weatherapp.data.models.one_day.Coord;

public class CoordCon {

    @TypeConverter
    public String toString(Coord coord) {
        if (coord == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Coord>() {}.getType();
        return gson.toJson(coord, type);
    }
    @TypeConverter
    public Coord toClass(String value) {
        if (value == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Coord>() {}.getType();
        return gson.fromJson(value, type);
    }
}
