package pl.kamilfurdal.models;

public class WeatherModel {
    private int id;
    private String city;
    private float temp;

    public WeatherModel(int id, String city, float temp) {
        this.id = id;
        this.city = city;
        this.temp = temp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "WeatherModel{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", temp=" + temp +
                '}';
    }
}
