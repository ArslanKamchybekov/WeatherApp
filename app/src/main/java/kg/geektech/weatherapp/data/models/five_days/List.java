
package kg.geektech.weatherapp.data.models.five_days;

import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import kg.geektech.weatherapp.data.room.converting.WeatherFor5.CloudsCon;
import kg.geektech.weatherapp.data.room.converting.WeatherFor5.MainCon;
import kg.geektech.weatherapp.data.room.converting.WeatherFor5.SysCon;
import kg.geektech.weatherapp.data.room.converting.WeatherFor5.Weather_5Con;
import kg.geektech.weatherapp.data.room.converting.WeatherFor5.WindCon;

public class List {

    private Integer dt;
    @TypeConverters({MainCon.class})
    private Main main;
    @TypeConverters({Weather_5Con.class})
    private java.util.List<Weather_5> weather = null;
    @TypeConverters({CloudsCon.class})
    private Clouds clouds;
    @TypeConverters({WindCon.class})
    private Wind wind;
    private Integer visibility;
    private Double pop;
    @TypeConverters({SysCon.class})
    private Sys sys;
    @SerializedName("dt_txt")
    private String dtTxt;

    public List() {
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public java.util.List<Weather_5> getWeathers() {
        return weather;
    }

    public void setWeathers(java.util.List<Weather_5> weathers) {
        this.weather = weathers;
    }
    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Double getPop() {
        return pop;
    }

    public void setPop(Double pop) {
        this.pop = pop;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }

}