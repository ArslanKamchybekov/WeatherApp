package kg.geektech.weatherapp.ui.weather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import kg.geektech.weatherapp.App;
import kg.geektech.weatherapp.common.Resource;
import kg.geektech.weatherapp.data.models.Weather;

public class WeatherViewModel extends ViewModel {

    public LiveData<Resource<Weather>> liveData;

    public WeatherViewModel(){

    }

    public void getWeathers(){
        liveData = App.repository.getWeather();
    }
}
