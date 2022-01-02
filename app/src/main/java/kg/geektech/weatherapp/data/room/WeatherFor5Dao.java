package kg.geektech.weatherapp.data.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import kg.geektech.weatherapp.data.models.five_days.Weather_for_5;

@Dao
public interface WeatherFor5Dao {

    @Query("SELECT * FROM Weather_for_5")
    Weather_for_5 getWeatherFor5();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Weather_for_5 weather_for_5);

}
