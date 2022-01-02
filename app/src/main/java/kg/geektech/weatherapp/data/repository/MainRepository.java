package kg.geektech.weatherapp.data.repository;

import androidx.lifecycle.MutableLiveData;

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

    private WeatherApi api;
    private WeatherFor1Dao weatherFor1Dao;
    private WeatherFor5Dao weatherFor5Dao;

    public MainRepository(WeatherApi api, WeatherFor1Dao weatherFor1Dao, WeatherFor5Dao weatherFor5Dao) {
        this.api = api;
        this.weatherFor1Dao = weatherFor1Dao;
        this.weatherFor5Dao = weatherFor5Dao;
    }

    public MutableLiveData<Resource<Weather_for_1>> getWeather1(String city){
        MutableLiveData<Resource<Weather_for_1>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getWeather1(city, "f5dda35d65d096bd3824576f46d99487", "metric").enqueue(new Callback<Weather_for_1>() {
            @Override
            public void onResponse(Call<Weather_for_1> call, Response<Weather_for_1> response) {
                if (response.isSuccessful() && response.body() != null){
                    liveData.setValue(Resource.success(response.body()));
                    weatherFor1Dao.insert(response.body());
                }else {
                    liveData.setValue(Resource.error(null, response.message()));
                }
            }

            @Override
            public void onFailure(Call<Weather_for_1> call, Throwable t) {
                liveData.setValue(Resource.error(null, t.getLocalizedMessage()));
            }
        });
        return liveData;
    }
    public MutableLiveData<Resource<Weather_for_5>> getWeather5(String city){
        MutableLiveData<Resource<Weather_for_5>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getWeather5(city, "f5dda35d65d096bd3824576f46d99487", "metric").enqueue(new Callback<Weather_for_5>() {
            @Override
            public void onResponse(Call<Weather_for_5> call, Response<Weather_for_5> response) {
                if (response.isSuccessful() && response.body() != null){
                    liveData.setValue(Resource.success(response.body()));
                    weatherFor5Dao.insert(response.body());
                }else {
                    liveData.setValue(Resource.error(null, response.message()));
                }
            }

            @Override
            public void onFailure(Call<Weather_for_5> call, Throwable t) {
                liveData.setValue(Resource.error(null, t.getLocalizedMessage()));
            }
        });
        return liveData;
    }
}
