package App;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Generating_Values {
    double Micro_val=100;
    double Apple_val=100;
    double Google_val=100;
    double Lenovo_val=100;
    double Philips_val=100;
    public static ArrayList<Double> Microsoft_Stock=new ArrayList<>();
    public static ArrayList<Double> Apple_Stock=new ArrayList<>();
    public static ArrayList<Double> Google_Stock=new ArrayList<>();
    public static ArrayList<Double> Lenovo_Stock=new ArrayList<>();
    public static ArrayList<Double> Philips_Stock=new ArrayList<>();

     public void Microsoft_Generating_Values(){

        Random rand =new Random();

        Timer timer =new Timer();

         Microsoft_Stock.add(Micro_val);

        timer.scheduleAtFixedRate(new TimerTask(){
           @Override
            public void run() {


               for (int i = 0; i < 1; i++) {
                   Double number = rand.nextDouble(1, 5);

                   boolean increase = rand.nextBoolean();

                   if (increase) {
                       Micro_val += number;
                   } else if (!increase) {
                       Micro_val -= number;
                   } else if (Micro_val < 0) {
                       Micro_val = 0;
                   }

                   Microsoft_Stock.add(Micro_val);
//                   for (Double z : Microsoft_Stock) {
//                       System.out.println(z);
//                   }


               }
           }

        },0,2000);
    }
    public void Apple_Generating_Values(){

        Random rand =new Random();

        Timer timer =new Timer();

        Apple_Stock.add(Micro_val);

        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {


                for (int i = 0; i < 1; i++) {
                    Double number = rand.nextDouble(1, 5);

                    boolean increase = rand.nextBoolean();

                    if (increase) {
                        Apple_val += number;
                    } else if (!increase) {
                        Apple_val -= number;
                    } else if (Apple_val < 0) {
                        Apple_val = 0;
                    }

                    Apple_Stock.add(Apple_val);
//                   for (Double z : Apple_Stock) {
//                       System.out.println(z);
//                   }


                }
            }

        },0,2000);
    }
    public void Google_Generating_Values(){

        Random rand =new Random();

        Timer timer =new Timer();

        Google_Stock.add(Google_val);

        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {


                for (int i = 0; i < 1; i++) {
                    Double number = rand.nextDouble(1, 5);

                    boolean increase = rand.nextBoolean();

                    if (increase) {
                        Google_val += number;
                    } else if (!increase) {
                        Google_val -= number;
                    } else if (Google_val < 0) {
                        Google_val = 0;
                    }

                    Google_Stock.add(Google_val);
//                   for (Double z : Google_Stock) {
//                       System.out.println(z);
//                   }


                }
            }

        },0,2000);
    }
    public void Lenovo_Generating_Values(){

        Random rand =new Random();

        Timer timer =new Timer();

        Lenovo_Stock.add(Lenovo_val);

        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {


                for (int i = 0; i < 1; i++) {
                    Double number = rand.nextDouble(1, 5);

                    boolean increase = rand.nextBoolean();

                    if (increase) {
                        Lenovo_val += number;
                    } else if (!increase) {
                        Lenovo_val -= number;
                    } else if (Lenovo_val < 0) {
                        Lenovo_val = 0;
                    }

                    Lenovo_Stock.add(Lenovo_val);
//                   for (Double z : Lenovo_Stock) {
//                       System.out.println(z);
//                   }


                }
            }

        },0,2000);
    }
    public void Philips_Generating_Values(){

        Random rand =new Random();

        Timer timer =new Timer();

        Philips_Stock.add(Micro_val);

        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {


                for (int i = 0; i < 1; i++) {
                    Double number = rand.nextDouble(1, 5);

                    boolean increase = rand.nextBoolean();

                    if (increase) {
                        Philips_val += number;
                    } else if (!increase) {
                        Philips_val -= number;
                    } else if (Philips_val < 0) {
                        Philips_val = 0;
                    }

                    Philips_Stock.add(Philips_val);
//                   for (Double z : Philips_Stock) {
//                       System.out.println(z);
//                   }


                }
            }

        },0,2000);
    }






}
