package kg.geektech.weatherapp.ui.weather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kg.geektech.weatherapp.App;
import kg.geektech.weatherapp.common.Resource;
import kg.geektech.weatherapp.data.models.Weather;
import kg.geektech.weatherapp.data.repository.MainRepository;

@HiltViewModel
public class WeatherViewModel extends ViewModel {

    public LiveData<Resource<Weather>> liveData;
    private MainRepository repository;

    @Inject
    public WeatherViewModel(MainRepository repository){
        this.repository = repository;
    }

    public void getWeathers(String city){
        liveData = repository.getWeather(city);
    }
}
