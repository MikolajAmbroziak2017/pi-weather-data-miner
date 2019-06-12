package pl.ambroziak.sensorapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import pl.ambroziak.sensorapp.Controller.DataController;
import pl.ambroziak.sensorapp.Controller.SensorController;
import pl.ambroziak.sensorapp.Model.SensorResolut;
import static org.mockito.Mockito.when;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Configuration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SensorappApplicationTests.class)
public class DataControllerTest {

    SensorController sensorController;
    DataController dataController;



    @Before
    public void setup() {
        this.sensorController = mock(SensorController.class);
        this.dataController = mock(DataController.class);
    }
// @Test
//    public void shouldntGetData() throws InterruptedException {
//        SensorResolut sensorResolut= new SensorResolut( new int[]{63, 0, 28, 1},40);
//        when(sensorDataNormalize.setSensorResolut()).thenReturn(true);
//        sensorDataNormalize.setSensorTest(sensorResolut);
//        assertArrayEquals(sensorDataNormalize.dataTransformation(), new float[]{28, 60});
//        // test kurde
//    }

//    @Test
//    public void shouldGetData() throws InterruptedException {
//        int[] testData = {63, 0, 28, 1};
//        int j = 40;
//        float[] expected = {(float) 28.0, (float) 63.0};
//        float[] result;
//        when(result=sensorController.dataTransformation(testData, j)).thenCallRealMethod();
//        assertArrayEquals(expected, result);
//    }

    @Test
    public void shouldGetAvarangeHumidity() {
        ArrayList<Float> test = new ArrayList<>();
        test.add((float) 1.0);
        test.add((float) 2.0);
        test.add((float) 3.0);
        test.add((float) 4.0);
        float result = DataController.getAverageHumidity(test);
        assertEquals(2.5, result);
    }

    @Test
    public void shouldGetAvarangeTemperature() {
        ArrayList<Float> test = new ArrayList<>();
        test.add((float) 5.0);
        test.add((float) 2.3);
        test.add((float) 3.3);
        test.add((float) 4.4);
        float result = DataController.getAverageTemperature(test);
        assertEquals(3.75, result);
    }
}

