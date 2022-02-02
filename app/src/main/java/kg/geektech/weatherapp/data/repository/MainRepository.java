package kg.geektech.weatherapp.data.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import kg.geektech.weatherapp.common.Resource;
import kg.geektech.weatherapp.data.models.five_days.Weather_for_5;
import kg.geektech.weatherapp.data.models.one_day.Weather_for_1;
import kg.geektech.weatherapp.data.remote.WeatherApi;
import kg.geektech.weatherapp.data.room.WeatherFor1Dao;
import kg.geektech.weatherapp.data.room.WeatherFor5Dao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private final WeatherApi api;
    private final WeatherFor1Dao weatherFor1Dao;
    private final WeatherFor5Dao weatherFor5Dao;

    @Inject
    public MainRepository(WeatherApi api, WeatherFor1Dao weatherFor1Dao, WeatherFor5Dao weatherFor5Dao) {
        this.api = api;
        this.weatherFor1Dao = weatherFor1Dao;
        this.weatherFor5Dao = weatherFor5Dao;
    }

    public MutableLiveData<Resource<Weather_for_1>> getWeather1(String cityName, Double lat, Double lon) {
        MutableLiveData<Resource<Weather_for_1>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getWeather1(cityName, lat, lon, "f5dda35d65d096bd3824576f46d99487", "metric").enqueue(new Callback<Weather_for_1>() {
            @Override
            public void onResponse(@NonNull Call<Weather_for_1> call, @NonNull Response<Weather_for_1> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                    weatherFor1Dao.insert(response.body());
                } else {
                    liveData.setValue(Resource.error(null, response.message()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Weather_for_1> call, @NonNull Throwable t) {
                liveData.setValue(Resource.error(null, t.getLocalizedMessage()));
            }
        });
        return liveData;
    }

    public MutableLiveData<Resource<Weather_for_5>> getWeather5(String cityName, Double lat, Double lon) {
        MutableLiveData<Resource<Weather_for_5>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getWeather5(cityName, lat, lon, "f5dda35d65d096bd3824576f46d99487", "metric").enqueue(new Callback<Weather_for_5>() {
            @Override
            public void onResponse(@NonNull Call<Weather_for_5> call, @NonNull Response<Weather_for_5> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                    weatherFor5Dao.insert(response.body());
                } else {
                    liveData.setValue(Resource.error(null, response.message()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Weather_for_5> call, @NonNull Throwable t) {
                liveData.setValue(Resource.error(null, t.getLocalizedMessage()));
            }
        });
        return liveData;
    }
}
