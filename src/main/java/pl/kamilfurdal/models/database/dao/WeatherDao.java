package pl.kamilfurdal.models.database.dao;

import pl.kamilfurdal.models.WeatherModel;

import java.util.List;

public interface WeatherDao {
    void saveWeather(WeatherModel model);
    void removeWeather(String city);
    List<WeatherModel> loadWeather(String city);
    List<WeatherModel> loadWeather();
    List<WeatherModel> loadWeather(float temp);
}

