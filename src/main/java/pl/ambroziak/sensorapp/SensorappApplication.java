package pl.ambroziak.sensorapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.ambroziak.sensorapp.Controller.DataController;
import pl.ambroziak.sensorapp.Model.Data;
import pl.ambroziak.sensorapp.Model.SeriesData;
import pl.ambroziak.sensorapp.Services.RabbitServices;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class SensorappApplication {

    private static RabbitServices rabbitServices;

    @Autowired
    public SensorappApplication(RabbitServices rabbitServices) {
        this.rabbitServices = rabbitServices;
    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(SensorappApplication.class, args);
        do {
            Data data = new DataController().getData();
            System.out.println(data.getTemprature() + " " + data.getHumidity());
            LocalDateTime date = LocalDateTime.now();
            SeriesData temeratureSeries = new SeriesData(String.valueOf(data.getTemprature()), date);
            SeriesData humiditySeries = new SeriesData(String.valueOf(data.getHumidity()), date);
            rabbitServices.sentMessage(temeratureSeries, humiditySeries);
            TimeUnit.MINUTES.sleep(5);
        } while (1 == 1);
    }
}
