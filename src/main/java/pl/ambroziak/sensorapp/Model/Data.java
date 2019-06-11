package pl.ambroziak.sensorapp.Model;

public class Data {
    private float temprature;
    private float humidity;

    public Data() {
    }

    public float getTemprature() {
        return temprature;
    }

    public void setTemprature(float temprature) {
        this.temprature = temprature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public Data(float temprature, float humidity) {
        this.temprature = temprature;
        this.humidity = humidity;
    }
}
