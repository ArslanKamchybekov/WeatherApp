package kg.geektech.weatherapp.data.remote;

import kg.geektech.weatherapp.data.models.Weather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("data/2.5/weather")
    Call<Weather> getWeather (@Query("q") String cityName, @Query("appid") String appId, @Query("units") String units);

}
