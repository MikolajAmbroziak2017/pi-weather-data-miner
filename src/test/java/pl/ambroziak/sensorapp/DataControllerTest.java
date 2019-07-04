package pl.ambroziak.sensorapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import pl.ambroziak.sensorapp.Controller.DataController;
import pl.ambroziak.sensorapp.Controller.SensorController;
import pl.ambroziak.sensorapp.Utils.SensorNormalizer;


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

    @Test
    public void shouldGetWrongData() throws InterruptedException {
        int[] testData = {63, 0, 28, 1,0};
        int[] emptyData = {};
        int emptyJ=0;
        int j = 40;
        assertArrayEquals(new float[]{0},SensorNormalizer.dataTransformation(testData,j));
        assertArrayEquals(new float[]{0},SensorNormalizer.dataTransformation(emptyData,j));
        assertArrayEquals(new float[]{0},SensorNormalizer.dataTransformation(testData,emptyJ));
    }
    @Test
    public void shouldGetData() throws InterruptedException {
        int[] testData = {63, 0, 28, 1,92};
        int j = 40;
        assertArrayEquals(new float[]{(float) 28.0, (float) 63.0},SensorNormalizer.dataTransformation(testData,j));
    }

    @Test
    public void shouldGetAvarangeHumidity() {
        ArrayList<Float> test = new ArrayList<>();
        test.add((float) 1.0);
        test.add((float) 2.0);
        test.add((float) 3.0);
        test.add((float) 4.0);
        float result = DataController.getAverageHumidity(test);
        assertEquals(2.5, result);
        assertNotEquals(2.75, result);
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
        assertNotEquals(2.5, result);

    }
    @Test
    public void shouldGetWrongAvarangeTemperature() {
        ArrayList<Float> test = new ArrayList<>();
        test.add((float) 0);
        test.add((float) 0);
        test.add((float) 0);
        test.add((float) 0);
        assertEquals(0.0,DataController.getAverageTemperature(test));
        assertEquals(0.0,DataController.getAverageTemperature(new ArrayList<>()));

    }
    @Test
    public void shouldGetWrongAvarangeHumidity() {
        ArrayList<Float> test = new ArrayList<>();
        test.add((float) 0);
        test.add((float) 0);
        test.add((float) 0);
        test.add((float) 0);
        assertEquals(0.0,DataController.getAverageTemperature(test));
        assertEquals(0.0,DataController.getAverageTemperature(new ArrayList<>()));
    }
}

