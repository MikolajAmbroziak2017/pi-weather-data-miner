package pl.ambroziak.sensorapp.Model;

import java.util.Date;

public class SeriesData {
    private String x;
    private Date y;

    public SeriesData() {
    }

    public SeriesData(String x, Date y) {
        this.x = x;
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public Date getY() {
        return y;
    }

    public void setY(Date y) {
        this.y = y;
    }
}
