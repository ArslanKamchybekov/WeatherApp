package kg.geektech.weatherapp.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import kg.geektech.weatherapp.data.models.five_days.Weather_for_5;
import kg.geektech.weatherapp.data.models.one_day.Weather_for_1;
import kg.geektech.weatherapp.data.room.converting.WeatherFor1.CloudsCon;
import kg.geektech.weatherapp.data.room.converting.WeatherFor1.CoordCon;
import kg.geektech.weatherapp.data.room.converting.WeatherFor1.MainCon;
import kg.geektech.weatherapp.data.room.converting.WeatherFor1.SysCon;
import kg.geektech.weatherapp.data.room.converting.WeatherFor1.Weather_1Con;
import kg.geektech.weatherapp.data.room.converting.WeatherFor1.WindCon;
import kg.geektech.weatherapp.data.room.converting.WeatherFor5.CityCon;
import kg.geektech.weatherapp.data.room.converting.WeatherFor5.ListCon;
import kg.geektech.weatherapp.data.room.converting.WeatherFor5.Weather_5Con;

@Database(entities = {Weather_for_1.class, Weather_for_5.class}, version = 1)

@TypeConverters({CloudsCon.class, CoordCon.class, MainCon.class, SysCon.class, Weather_1Con.class, WindCon.class,
        CityCon.class, kg.geektech.weatherapp.data.room.converting.WeatherFor5.CloudsCon.class, kg.geektech.weatherapp.data.room.converting.WeatherFor5.CoordCon.class,
        ListCon.class, kg.geektech.weatherapp.data.room.converting.WeatherFor5.MainCon.class, kg.geektech.weatherapp.data.room.converting.WeatherFor5.SysCon.class, Weather_5Con.class,
        kg.geektech.weatherapp.data.room.converting.WeatherFor5.WindCon.class})

public abstract class AppDatabase extends RoomDatabase {

    public abstract WeatherFor1Dao weather_for_1Dao();

    public abstract WeatherFor5Dao weather_for_5Dao();

}
