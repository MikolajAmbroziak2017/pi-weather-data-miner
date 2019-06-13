package pl.ambroziak.sensorapp.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import pl.ambroziak.sensorapp.Model.Data;

import java.util.ArrayList;

public class DataController {
    private ArrayList<Float> allTemperature;
    private ArrayList<Float> allHumidity;
    private final SensorController dht = new SensorController();


    public DataController() {
    }

    private void useSensor() throws Exception {
        for (int i = 0; i <60 ; i++) {
            Thread.sleep(2000);
            float[] result = dht.getTemperature(7);
            if (result[0] != 0 && result.length != 1) {
                allTemperature.add(result[0]);
                allHumidity.add(result[1]);
            }

        }
    }

    public static float getAverageHumidity(ArrayList<Float> humidity) {
        if(humidity.size()!=0){

            float average = (float) 0.0;
        for (Float e : humidity) {
            average =average+e;
        }
        return average / humidity.size();}
        else
            return (float) 0.0;
    }

    public static float getAverageTemperature(ArrayList<Float> temperature) {
        if(temperature.size()!=0){
        float average = (float) 0.0;
        for (Float e : temperature) {
            average =average+e;
        }
        return average / temperature.size();
    }
    else
    return (float) 0.0;
    }

    public Data getData() {
        allTemperature = new ArrayList<>();
        allHumidity = new ArrayList<>();
        try {
            useSensor();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Data data = new Data();
        data.setHumidity(getAverageHumidity(allHumidity));
        data.setTemprature(getAverageTemperature(allTemperature));
        return data;
    }

}
