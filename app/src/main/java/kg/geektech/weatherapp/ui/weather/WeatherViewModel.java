package kg.geektech.weatherapp.ui.weather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kg.geektech.weatherapp.common.Resource;
import kg.geektech.weatherapp.data.models.five_days.Weather_for_5;
import kg.geektech.weatherapp.data.models.one_day.Weather_for_1;
import kg.geektech.weatherapp.data.repository.MainRepository;

@HiltViewModel
public class WeatherViewModel extends ViewModel {

    public LiveData<Resource<Weather_for_1>> liveData1;
    public LiveData<Resource<Weather_for_5>> liveData5;
    private MainRepository repository;

    @Inject
    public WeatherViewModel(MainRepository repository){
        this.repository = repository;
    }

    public void getWeathers(Double lat, Double lon){
        liveData1 = repository.getWeather1(lat, lon);
        liveData5 = repository.getWeather5(lat, lon);
    }
}
