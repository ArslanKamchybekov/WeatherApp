
package kg.geektech.weatherapp.data.models.one_day;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

import kg.geektech.weatherapp.data.room.converting.WeatherFor1.CloudsCon;
import kg.geektech.weatherapp.data.room.converting.WeatherFor1.CoordCon;
import kg.geektech.weatherapp.data.room.converting.WeatherFor1.MainCon;
import kg.geektech.weatherapp.data.room.converting.WeatherFor1.SysCon;
import kg.geektech.weatherapp.data.room.converting.WeatherFor1.Weather_1Con;
import kg.geektech.weatherapp.data.room.converting.WeatherFor1.WindCon;

@Entity
public class Weather_for_1 {

    @TypeConverters({CoordCon.class})
    private Coord coord;
    @TypeConverters({Weather_1Con.class})
    private List<Weather_1> weather = null;
    private String base;
    @TypeConverters({MainCon.class})
    private Main main;
    private Integer visibility;
    @TypeConverters({WindCon.class})
    private Wind wind;
    @TypeConverters({CloudsCon.class})
    private Clouds clouds;
    private Integer dt;
    @TypeConverters({SysCon.class})
    private Sys sys;
    private Integer timezone;
    @PrimaryKey
    private Integer id;
    private String name;
    private Integer cod;

    public Weather_for_1() {
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather_1> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather_1> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

}
