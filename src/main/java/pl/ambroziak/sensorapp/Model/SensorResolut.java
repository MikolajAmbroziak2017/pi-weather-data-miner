package pl.ambroziak.sensorapp.Model;

public class SensorResolut {
    private int[] dht11;
    private int j;

    public SensorResolut(int[] dht11, int j) {
        this.dht11 = dht11;
        this.j = j;
    }

    public int[] getDht11() {
        return dht11;
    }

    public void setDht11(int[] dht11) {
        this.dht11 = dht11;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
