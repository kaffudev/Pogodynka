package pl.kamilfurdal.controllers;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import pl.kamilfurdal.models.WeatherModel;

import pl.kamilfurdal.models.database.dao.WeatherDao;
import pl.kamilfurdal.models.database.dao.impl.WeatherDaoImpl;
import pl.kamilfurdal.models.services.WeatherData;
import pl.kamilfurdal.models.services.WeatherObserver;
import pl.kamilfurdal.models.services.WeatherService;


import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable, WeatherObserver {



    @FXML
    Button btnSend;
    @FXML
    Button btnDelete;
    @FXML
    TextField txtCity;
    @FXML
    Label lblResult;





    private WeatherDao weatherDao = new WeatherDaoImpl();
    private WeatherService weatherService = WeatherService.getService();

    //serwisy to małe usługi ktore skladaja sie na cala aplikacje

    public void initialize(URL location, ResourceBundle resources) {
        weatherService.registerObserver(this);
        btnSend.setOnMouseClicked(e -> {
            if (e.isAltDown()){
                deleteWeather();
            }else{
                showWeather();
            }

        } );
        btnSend.setOnMouseClicked(e -> deleteWeather());

    }

    private void showWeather() {
        weatherService.makeCall(txtCity.getText());

    }

    private  void deleteWeather(){
        weatherDao.removeWeather(txtCity.getText());
    }


    @Override
    public void onWeatherUpdate(WeatherData data) {
        Platform.runLater(() -> {
            lblResult.setText("Temperatura : " + data.getTemp());
        });

        WeatherModel weatherModel = new WeatherModel(0, data.getCity(), data.getTemp());
        weatherDao.saveWeather(weatherModel);

    }
}
