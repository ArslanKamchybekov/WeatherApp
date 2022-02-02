package kg.geektech.weatherapp.data.remote;

import kg.geektech.weatherapp.data.models.five_days.Weather_for_5;
import kg.geektech.weatherapp.data.models.one_day.Weather_for_1;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("data/2.5/weather")
    Call<Weather_for_1> getWeather1(@Query("q") String cityName, @Query("lat") Double lat, @Query("lon") Double lon, @Query("appid") String appId, @Query("units") String units);

    @GET("data/2.5/forecast")
    Call<Weather_for_5> getWeather5(@Query("q") String cityName, @Query("lat") Double lat, @Query("lon") Double lon, @Query("appid") String appId, @Query("units") String units);

}
