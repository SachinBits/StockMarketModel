package App;

import java.util.*;

public class Stock {
    private String name;
    int lengthofarray=15;
    public ArrayList<Double> stock_history = new ArrayList<>(lengthofarray);
    private double intitalPrice = 100;

    public Stock(String name, double intitalPrice) {
        this.name = name;
        this.intitalPrice =intitalPrice;
        setInitialValues();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run(){
                updateArray();
            }
        }, 0, 1000);
    }

    double nextValue(double currentPrice) {
        Random rand =new Random();
        int percent = rand.nextInt(1, 20);
        if(percent==1) {
            double priceChange = rand.nextDouble(-.5, .5);
            return currentPrice+(currentPrice*priceChange);
        }
        else {
            double priceChange = rand.nextDouble(-.05, .05);
            return currentPrice+(currentPrice*priceChange);
        }
    }

    public void setInitialValues() {
        stock_history.add(intitalPrice);
        for(int i=1;i<lengthofarray;i++) {
            stock_history.add(nextValue(stock_history.get(i-1)));
        }
    }

    public void updateArray() {

        stock_history.add(nextValue(stock_history.get(lengthofarray-1)));
        stock_history.remove(0);
    }





}