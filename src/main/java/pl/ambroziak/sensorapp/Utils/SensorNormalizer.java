package pl.ambroziak.sensorapp.Utils;

public class SensorNormalizer {
    public static float[] dataTransformation(int[] dat, int j) throws InterruptedException {
        if(dat.length==0)
            return new float[]{0};
        if (j >= 40 && checkParity(dat)) {
            float h = (float) ((dat[0] << 8) + dat[1]) / 10;
            if (h > 100) {
                h = dat[0]; // for DHT11
            }
            float c = (float) (((dat[2] & 0x7F) << 8) + dat[3]) / 10;
            if (c > 125) {
                c = dat[2]; // for DHT11
            }
            if ((dat[2] & 0x80) != 0) {
                c = -c;
            }
            final float f = c * 1.8f + 32;
            System.out.println("Humidity = " + h + " Temperature = " + c + "(" + f + "f)");
            return new float[]{c,h};
        } else {
            System.out.println("Data not good, skip");
            return new float[]{0};
        }

    }
    private static boolean checkParity(int[] dht11_dat) {
        return dht11_dat[4] == (dht11_dat[0] + dht11_dat[1] + dht11_dat[2] + dht11_dat[3] & 0xFF);
    }

}


