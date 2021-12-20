package kg.geektech.weatherapp.data.repository;

import androidx.lifecycle.MutableLiveData;

import kg.geektech.weatherapp.App;
import kg.geektech.weatherapp.common.Resource;
import kg.geektech.weatherapp.data.models.Weather;
import kg.geektech.weatherapp.data.remote.WeatherApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private WeatherApi api;

    public MainRepository(WeatherApi api) {
        this.api = api;
    }

    public MutableLiveData<Resource<Weather>> getWeather (String city){
        MutableLiveData<Resource<Weather>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getWeather(city, "f5dda35d65d096bd3824576f46d99487", "metric").enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (response.isSuccessful() && response.body() != null){
                    liveData.setValue(Resource.success(response.body()));
                }else {
                    liveData.setValue(Resource.error(null, response.message()));
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                liveData.setValue(Resource.error(null, t.getLocalizedMessage()));
            }
        });
        return liveData;
    }
}
