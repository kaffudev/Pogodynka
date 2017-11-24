package pl.kamilfurdal.models.services;

import org.json.JSONObject;
import pl.kamilfurdal.models.utils.Config;
import pl.kamilfurdal.models.utils.HttpUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WeatherService {
    private static WeatherService ourInstance = new WeatherService();

    public static WeatherService getService() {
        return ourInstance;
    }

    private List<WeatherObserver> observers;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    private WeatherService() {
        observers = new ArrayList<>();
    }

    public void registerObserver(WeatherObserver observer){
        observers.add(observer);
    }

    private void notifyObservers(WeatherData data ){
        for (WeatherObserver observer : observers) {
            observer.onWeatherUpdate(data);
        }
    }

    public void makeCall(String city){
        executorService.execute(() -> {
        //metoda zwraca text pobranej string builderem strony
        parseJsonData(HttpUtils.makeHttpRequest(Config.APP_URL + city + "&appid=" + Config.APP_ID));
        });

        executorService.shutdown();
    }

    private void parseJsonData(String text){
        JSONObject root = new JSONObject(text);
        JSONObject main = root.getJSONObject("main");
        int temp = main.getInt("temp");
        String name = root.getString("name");
        WeatherData data = new WeatherData();
        data.setTemp(temp);
        data.setCity(name);

        notifyObservers(data);

        /*System.out.println("Temperatura to: " + temp);
        //zachmurzenie
        JSONObject clouds = root.getJSONObject("clouds");
        int all = clouds.getInt("all");
        System.out.println("Zachmurzenie wynosi : " + all + " %");
        JSONObject sys = root.getJSONObject("sys");
        String country = sys.getString("country");
        System.out.println("Dane z kraju: " +country);*/

    }
}
