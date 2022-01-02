
package kg.geektech.weatherapp.data.models.five_days;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather_5 {

    private Integer id;
    private String main;
    private String description;
    private String icon;

    public Weather_5() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}