package pl.ambroziak.sensorapp;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import pl.ambroziak.sensorapp.Controller.DataController;
import pl.ambroziak.sensorapp.Controller.SensorController;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DataControllerTest {
    @InjectMocks
    SensorController sensorController;
    DataController dataController=new DataController();
    @Test
    public void shouldGetData() throws InterruptedException {
        when(sensorController.getTemperature(7)).thenReturn(new float[]{(float) 1.0, (float) 3.2});
        assertEquals(1.0,dataController.getData().getTemprature());
    }

    @Test
    public void shouldGetAvarangeHumidity(){
        ArrayList<Float> test= new ArrayList<>();
        test.add((float) 1.0);
        test.add((float) 2.0);
        test.add((float) 3.0);
        test.add((float) 4.0);
        float result= DataController.getAverageHumidity(test);
        assertEquals(2.5,result);
    }
    @Test
    public void shouldGetAvarangeTemperature(){
        ArrayList<Float> test= new ArrayList<>();
        test.add((float) 5.0);
        test.add((float) 2.3);
        test.add((float) 3.3);
        test.add((float) 4.4);
        float result=DataController.getAverageTemperature(test);
        assertEquals(3.75,result);
    }
}

