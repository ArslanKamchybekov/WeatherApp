package kg.geektech.weatherapp.data.di;

import android.content.Context;

import androidx.room.Room;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import kg.geektech.weatherapp.data.models.five_days.Weather_for_5;
import kg.geektech.weatherapp.data.models.one_day.Weather_for_1;
import kg.geektech.weatherapp.data.remote.WeatherApi;
import kg.geektech.weatherapp.data.repository.MainRepository;
import kg.geektech.weatherapp.data.room.AppDatabase;
import kg.geektech.weatherapp.data.room.WeatherFor1Dao;
import kg.geektech.weatherapp.data.room.WeatherFor5Dao;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    @Provides
    public static MainRepository provideMainRepository(WeatherApi api, WeatherFor1Dao weatherFor1Dao, WeatherFor5Dao weatherFor5Dao){
        return new MainRepository(api, weatherFor1Dao, weatherFor5Dao);
    }

    @Provides
    public static WeatherApi provideApi(Retrofit retrofit){
        return retrofit.create(WeatherApi.class);
    }

    @Provides
    public static OkHttpClient provideOkHttpClient(Interceptor loggingInterceptor){
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Provides
    public static Interceptor provideLoggingInterceptor(){
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    public static Retrofit provideRetrofit(OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    public static AppDatabase provideAppDatabase(@ApplicationContext Context context){
        return Room.databaseBuilder(context, AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    public static Weather_for_1 provideWeatherFor1Dao(AppDatabase appDatabase){
        return appDatabase.weather_for_1Dao();
    }

    @Provides
    public static Weather_for_5 provideWeatherFor5Dao(AppDatabase appDatabase){
        return appDatabase.weather_for_5Dao();
    }
}
