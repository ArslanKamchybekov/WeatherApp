package kg.geektech.weatherapp;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;
import kg.geektech.weatherapp.data.remote.RetrofitClient;
import kg.geektech.weatherapp.data.remote.WeatherApi;
import kg.geektech.weatherapp.data.repository.MainRepository;
@HiltAndroidApp
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
