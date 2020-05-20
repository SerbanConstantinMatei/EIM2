package ro.pub.cs.systems.eim.test2;

import androidx.annotation.NonNull;

public class WeatherInformation {
    public String getWindSpeed() {
        return windSpeed;
    }

    public String getPressure() {
        return pressure;
    }

    public String getCondition() {
        return condition;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    private String temperature;
    private String windSpeed;
    private String pressure;
    private String condition;
    private String humidity;

    public WeatherInformation() {
        this.temperature = null;
        this.windSpeed = null;
        this.pressure = null;
        this.condition = null;
        this.humidity = null;
    }

    @NonNull
    @Override
    public String toString() {
        return "Weather info - temperature: " + temperature
                + " wind speed: " + windSpeed +  " pressure: "
                + "condition: " + condition + " humidity: " + humidity;
    }
}
