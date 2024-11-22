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
    private Timer timer;
    private ActionEvent e;

//    DefaultTableModel tablemodel;
//
//    Buying(){
//        String[] columns={"No","Stock","Quantity","Buying Price","Current Price","Difference"};
//        Object[][] data={{1,;}};
//    }
    public Buying(int no, String company, ActionEvent e) {
        this.company = company;
        this.number = no; // Initialize the quantity
        this.timer = new Timer();
        this.e=e;


        if(e.getSource()==Buy){
            this.number=+no;
        }
        else if(e.getSource()==Sell){
            this.number=-no;
        }



            timer=new Timer();
            if (company.equalsIgnoreCase("Apple")) {
                getportfolio(timer, number, company, Listing.generate_apple, Listing.generate_apple.b);
            }

            else if(company.equalsIgnoreCase("Microsoft")) {
                getportfolio(timer, number, company, Listing.generate_microsoft, Listing.generate_microsoft.b);
            }
            else if(company.equalsIgnoreCase("Lenovo")) {
                getportfolio(timer, number, company, Listing.generate_lenovo, Listing.generate_lenovo.b);
            }
            else if(company.equalsIgnoreCase("Philips")) {
                getportfolio(timer, number, company, Listing.generate_philips, Listing.generate_philips.b);
            }
            else if(company.equalsIgnoreCase("Google")) {
                getportfolio(timer, number, company, Listing.generate_google, Listing.generate_google.b);
            }

    }
    public void getportfolio(Timer timer,int number ,String company,GraphsMain a,double[] b){
        double Buying_price = a.getlatestvalue(b);

        System.out.println(Buying_price);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                double Current_Price = a.getlatestvalue(b);
                double Difference = Buying_price - Current_Price;
                System.out.println("Company:" + company + " Buying Price:" + Buying_price + " Current Price:" + Current_Price
                        + " Difference:" + Difference+"number:"+number);
            }

        },0,200);
    }
}














