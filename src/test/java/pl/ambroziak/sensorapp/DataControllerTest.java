package pl.ambroziak.sensorapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.configuration.injection.MockInjection;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.ambroziak.sensorapp.Controller.DataController;
import pl.ambroziak.sensorapp.Controller.SensorController;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SensorappApplicationTests.class)
public class DataControllerTest {

    SensorController sensorController;
    DataController dataController;

    @Before
    public void setup() {
        sensorController = mock(SensorController.class);
        dataController = mock(DataController.class);
    }

    @Test
    public void shouldGetData() throws InterruptedException {
        int[] floats=new int[]{1,2,3,4};
        assertNotEquals(sensorController.dataTransformation(floats,1),new float[]{1,2});
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

