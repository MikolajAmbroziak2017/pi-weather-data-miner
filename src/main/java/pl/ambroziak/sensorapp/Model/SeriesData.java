package pl.ambroziak.sensorapp.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class SeriesData implements Serializable{
    private LocalDateTime gainDate;
    private String value;

    public SeriesData()
    {
    }

    public SeriesData( String value,LocalDateTime gainDate) {
        this.gainDate = gainDate;
        this.value = value;
    }

    public LocalDateTime getGainDate() {
        return gainDate;
    }

    public void setGainDate(LocalDateTime gainDate) {
        this.gainDate = gainDate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
