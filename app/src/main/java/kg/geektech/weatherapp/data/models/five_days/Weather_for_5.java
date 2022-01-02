
package kg.geektech.weatherapp.data.models.five_days;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import kg.geektech.weatherapp.data.room.converting.WeatherFor5.CityCon;
import kg.geektech.weatherapp.data.room.converting.WeatherFor5.ListCon;

@Entity
public class Weather_for_5 {

    @PrimaryKey(autoGenerate = true)
    private long roomId;
    private String cod;
    private Integer message;
    private Integer cnt;
    @TypeConverters({ListCon.class})
    private java.util.List<List> list = null;
    @TypeConverters({CityCon.class})
    private City city;

    public Weather_for_5() {
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public java.util.List<List> getList() {
        return list;
    }

    public void setList(java.util.List<List> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }
}