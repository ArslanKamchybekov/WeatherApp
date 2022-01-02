package kg.geektech.weatherapp.data.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import kg.geektech.weatherapp.data.models.one_day.Weather_for_1;

@Dao
public interface WeatherFor1Dao {

    @Query("SELECT * FROM Weather_for_1")
    Weather_for_1 getWeatherFor1();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Weather_for_1 weather_for_1);

}
