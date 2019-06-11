package pl.ambroziak.sensorapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.ambroziak.sensorapp.Controller.DataController;
import pl.ambroziak.sensorapp.Controller.SensorController;
import pl.ambroziak.sensorapp.Model.Data;
import pl.ambroziak.sensorapp.Model.SeriesData;
import pl.ambroziak.sensorapp.Services.RabbitServices;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class SensorappApplication {
    @Autowired
private RabbitServices rabbitServices;

    public SensorappApplication(){}

    public static void main(String[] args) throws InterruptedException {
        SensorappApplication app=new SensorappApplication();
        SpringApplication.run(SensorappApplication.class, args);
        do{
        Data data=new DataController().getData();
        System.out.println(data.getTemprature()+" "+data.getHumidity());

        Date date=new Date(System.currentTimeMillis());
        app.rabbitServices.sentMessage(new SeriesData(String.valueOf(data.getTemprature()),date.toString()),new SeriesData(String.valueOf(data.getHumidity()),date.toString()));
            TimeUnit.MINUTES.sleep(3);
        }while (1==1);
    }
}
