package ab.stream;

import java.util.Arrays;
import java.util.List;

public class Transaction {

    public Transaction(Trader trader,int year,int value){
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public static List<Transaction> getTransaction(){
        Trader raoul = new Trader("Raoul","Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","China");

        return Arrays.asList(new Transaction(brian,2011,300),
                new Transaction(raoul,2012,1000),
                new Transaction(raoul,2011,400),
                new Transaction(mario,2012,710),
                new Transaction(mario,2012,700),
                new Transaction(alan,2012,950));
    }

    private Trader trader;
    private int year;
    private int value;

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toStringTransaction(){
        return this.getTrader().getName()+":"+this.getYear()+":"+this.getValue();
    }
}
