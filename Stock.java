package App;

import javax.swing.*;
import java.util.*;
import java.util.Timer;

public class Stock {
    private String name;
    int lengthofarray=15;
    public ArrayList<Double> stock_history = new ArrayList<>(lengthofarray);
    private double intitalPrice = 100;
    private static Map<String,Stock> registry = new HashMap<>();

    public Stock(String name, double intitalPrice) {
        this.name = name;
        this.intitalPrice =intitalPrice;
        setInitialValues();
        Timer timer = new Timer();
        registry.put(name, this);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run(){
                updateArray();
            }
        }, 0, 3000);
    }

    double nextValue(double currentPrice) {
        Random rand =new Random();
        int percent = rand.nextInt(1, 15);
        if(percent==1 ) {
            double priceChange = rand.nextDouble(-.5, .5);
            createnews(priceChange);
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
    int o=0;
    public void createnews(double priceChange){
        o++;
        if(o>3) {
            if (priceChange > 0.35) {
                Random_News randomNews = new Random_News();
                randomNews.positive_news(name);
            } else if (priceChange < -0.35) {
                Random_News randomNews = new Random_News();
                randomNews.negative_news(name);
            }
        }


    }

    public double getCurrentPrice() {
        return stock_history.get(lengthofarray-1);
    }

    public String getName() {
        return name;
    }

    public static Stock getInstance(String name) {
        return registry.get(name);
    }
}