package pl.ambroziak.sensorapp;

import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.GpioUtil;

import java.util.concurrent.TimeUnit;

public class SensorController {
    private static final int maxtimes = 85;
    private final int[] dht11_dat = {0, 0, 0, 0, 0};
    //private final int pin=3;
    private static int counter= 0;
    public SensorController() {
        if (Gpio.wiringPiSetup() == -1) {
            System.out.println("setup was filed");
            return;
        }
        GpioUtil.export(3, GpioUtil.DIRECTION_OUT);
    }

    public void getTemperature(final int pin) throws InterruptedException {
        int j=0;
        int lastState= Gpio.HIGH;
        dht11_dat[0] = dht11_dat[1] = dht11_dat[2] = dht11_dat[3] = dht11_dat[4] = 0;

        //wysłanie sygnału uruchamiającego czyli low na 18milisekund a następnie up na 40 mikrosekund
        Gpio.pinMode(pin,Gpio.OUTPUT);
        Gpio.analogWrite(pin,Gpio.LOW);
        Thread.sleep(18);
        Gpio.analogWrite(pin,Gpio.HIGH);
        TimeUnit.MICROSECONDS.sleep(40);
        Gpio.pinMode(pin,Gpio.INPUT);

        //80 razy bedzie sprawdzac sygnał
        for(int i=0;i<maxtimes;i++){
            counter=0;
            while(Gpio.digitalRead(pin)==lastState)
            {
                counter++;
                Gpio.delayMicroseconds(1); //z 1 na 7
                if(counter==255)
                {
                    break;
                }
            }
            lastState = Gpio.digitalRead(pin);
            if (counter==255)
            {
                break;
            }
            /* ignore first 3 transitions */
            if (i >= 4 && i % 2 == 0) {
                /* shove each bit into the storage bytes */
                dht11_dat[j / 8] <<= 1;
                if (counter > 30) { //z 16 na 30
                    dht11_dat[j / 8] |= 1;
                }
                j++;
            }
        }
        if (j >= 40 && checkParity()) {
            float h = (float) ((dht11_dat[0] << 8) + dht11_dat[1]) / 10;
            if (h > 100) {
                h = dht11_dat[0]; // for DHT11
            }
            float c = (float) (((dht11_dat[2] & 0x7F) << 8) + dht11_dat[3]) / 10;
            if (c > 125) {
                c = dht11_dat[2]; // for DHT11
            }
            if ((dht11_dat[2] & 0x80) != 0) {
                c = -c;
            }
            final float f = c * 1.8f + 32;
            System.out.println("Humidity = " + h + " Temperature = " + c + "(" + f + "f)");
        } else {
            System.out.println("Data not good, skip");
        }
    }
    private boolean checkParity() {
        return dht11_dat[4] == (dht11_dat[0] + dht11_dat[1] + dht11_dat[2] + dht11_dat[3] & 0xFF);
    }

}
