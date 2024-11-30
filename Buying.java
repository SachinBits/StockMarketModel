package App;

import java.util.Timer;
import java.util.TimerTask;

public class Buying {
    private int number;
    private String company;
    private double sell_no;
    Timer timer;
    double Buying_price;
    double Selling_price;
    double Current_Price;
    double Difference;
    static double Balance=10000;
    static double profit_loss;

    String TradeOption;

    public Buying(int a, String company) {
        this.company = company;
        this.number = a;
    }

    public void trade(int no, String Tradeoption){
        TradeOption=Tradeoption;

        number+=no;
        sell_no=no;
        if (timer != null) {
            timer.cancel();
        }


        timer=new Timer();
            if (company.equalsIgnoreCase("Apple") && TradeOption.equalsIgnoreCase("Buy")) {
                Buy(timer, company, Listing.generate_apple, Listing.generate_apple.b);
            }
            else if (company.equalsIgnoreCase("Apple") && TradeOption.equalsIgnoreCase("Sell")) {
                Sell(timer, company, Listing.generate_apple, Listing.generate_apple.b);
            }

            else if(company.equalsIgnoreCase("Microsoft") && TradeOption.equalsIgnoreCase("Buy")) {
                Buy(timer, company, Listing.generate_microsoft, Listing.generate_microsoft.b);
            }
            else if(company.equalsIgnoreCase("Microsoft") && TradeOption.equalsIgnoreCase("Sell")) {
                Sell(timer, company, Listing.generate_microsoft, Listing.generate_microsoft.b);
            }

            else if(company.equalsIgnoreCase("Lenovo") && TradeOption.equalsIgnoreCase("Buy")) {
                Buy(timer, company, Listing.generate_lenovo, Listing.generate_lenovo.b);
            }
            else if(company.equalsIgnoreCase("Lenovo") && TradeOption.equalsIgnoreCase("Sell")) {
                Sell(timer, company, Listing.generate_lenovo, Listing.generate_lenovo.b);
            }
            else if(company.equalsIgnoreCase("Philips") && TradeOption.equalsIgnoreCase("Buy")) {
                Buy(timer, company, Listing.generate_philips, Listing.generate_philips.b);
            }
            else if(company.equalsIgnoreCase("Philips") && TradeOption.equalsIgnoreCase("Sell")) {
                Sell(timer, company, Listing.generate_philips, Listing.generate_philips.b);
            }
            else if(company.equalsIgnoreCase("Google") && TradeOption.equalsIgnoreCase("Buy")) {
                Buy(timer, company, Listing.generate_google, Listing.generate_google.b);
            }
            else if(company.equalsIgnoreCase("Google") && TradeOption.equalsIgnoreCase("Sell")) {
                Sell(timer, company, Listing.generate_google, Listing.generate_google.b);
            }
            else{
                System.out.println("no sufficient amount");
            }
        System.out.println(profit_loss);


    }


    public void Buy(Timer timer ,String company,GraphsMain a,double[] b){
        Buying_price = a.getlatestvalue(b);
        double cost = number * Buying_price;
        if (Balance >= cost) {
            Balance=Balance-cost;
        }



        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                Current_Price = a.getlatestvalue(b);
                Difference = Current_Price - Buying_price;

                if (number == 0) {

                    Current_Price = 0;
                    Difference = 0;
                    timer.cancel();

                }

            }



        },0,300);
//        System.out.println(Balance);
    }
    public void Sell(Timer timer ,String company,GraphsMain a,double[] b){
        Selling_price = a.getlatestvalue(b);
        //        System.out.println(Buying_price);

        Balance=Balance+(-sell_no*getSelling_price());

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                Current_Price = a.getlatestvalue(b);
                Difference = Current_Price - Buying_price;

                if (number == 0) {

                    timer.cancel();
                    System.out.println("All stocks sold for " + company + ". Timer stopped.");
                }
            }


        },0,300);
        setProfit_loss(Difference);
    }
    public double getBuying_price(){
        return Buying_price;
    }
    public double getSelling_price(){
        return Selling_price;
    }

    public double getCurrent_Price() {
        return Current_Price;
    }
    public void setProfit_loss(double difference){
        profit_loss=profit_loss+(-sell_no*difference);
//        System.out.println(profit_loss);
    }

    public int getnumber(){
        return number;
    }
    public String getCompany(){
        return company;
    }
    public double getDifference(){
        return Difference;
    }


}














