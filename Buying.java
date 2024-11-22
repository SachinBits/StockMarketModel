package App;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import static App.Transaction.Buy;
import static App.Transaction.Sell;

public class Buying {
    private int number;
    private String company;
    Timer timer;
    double Buying_price;
    double Current_Price;
    double Difference;

    public Buying(int a, String company) {
        this.company = company;
        this.number = a; // Initialize the quantity

    }

    public void gostuff(int no){

        number+=no;
        if (timer != null) {
            timer.cancel();
        }

        timer=new Timer();


            if (company.equalsIgnoreCase("Apple")) {
                getportfolio(timer, company, Listing.generate_apple, Listing.generate_apple.b);
            }

            else if(company.equalsIgnoreCase("Microsoft")) {
                getportfolio(timer, company, Listing.generate_microsoft, Listing.generate_microsoft.b);
            }
            else if(company.equalsIgnoreCase("Lenovo")) {
                getportfolio(timer, company, Listing.generate_lenovo, Listing.generate_lenovo.b);
            }
            else if(company.equalsIgnoreCase("Philips")) {
                getportfolio(timer, company, Listing.generate_philips, Listing.generate_philips.b);
            }
            else if(company.equalsIgnoreCase("Google")) {
                getportfolio(timer, company, Listing.generate_google, Listing.generate_google.b);
            }

    }


    public void getportfolio(Timer timer ,String company,GraphsMain a,double[] b){
        Buying_price = a.getlatestvalue(b);
        //        System.out.println(Buying_price);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                Current_Price = a.getlatestvalue(b);
                Difference = Buying_price - Current_Price;

                if (number == 0) {
                    timer.cancel();
                    System.out.println("All stocks sold for " + company + ". Timer stopped.");
                }
            }


        },0,300);
    }
    public double getBuying_price(){
        return Buying_price;
    }

    public double getCurrent_Price() {
        return Current_Price;
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














