package nl.wouterwally.afstuderen.backend;

public class CounterResponseMessage {
    private int counter;
    private String hoeGek;

    public CounterResponseMessage(int counter) {
        this.counter = counter;
        this.hoeGek = "keiGek";
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getHoeGek() {
        return hoeGek;
    }

    public void setHoeGek(String hoeGek) {
        this.hoeGek = hoeGek;
    }
}
