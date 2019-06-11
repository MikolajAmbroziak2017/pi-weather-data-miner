package pl.ambroziak.sensorapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.ambroziak.sensorapp.Controller.DataController;
import pl.ambroziak.sensorapp.Controller.SensorController;

@SpringBootApplication
public class SensorappApplication {

    public void run(){

    }
    public static void main(String[] args) {
        SpringApplication.run(SensorappApplication.class, args);
        System.out.println(new DataController().getData());
}
}
