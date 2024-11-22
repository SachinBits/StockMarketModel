package App;

import java.util.ArrayList;
import java.util.HashMap;


public class User {
    private String userName, password;
    private double intitial_balance, balance;
    private HashMap<Stock,Integer> ownedStocks;
    private ArrayList<String> trades; //[Company,Price Bought or Sold, Profit or Loss Incurred]

    public User(String userName, String password, double intitial_balance, double balance) {
        this.userName = userName;
        this.intitial_balance = intitial_balance;
        this.balance = balance;
    }

    public double getProfit() {
        return intitial_balance - balance;
    }

    public String getUserName() {
        return userName;
    }

    public double getBalance() {
        return balance;
    }

    //In buy and sell operations trua implies a successful transaction.
    public boolean buyStock(Stock stock, int amount) {
        if(this.balance < stock.getCurrentPrice()*amount) {
            return false;
        }
        else {
            this.balance -= stock.getCurrentPrice()*amount;
            if (ownedStocks.containsKey(stock)) {
                ownedStocks.put(stock, ownedStocks.get(stock) + amount);
            } else {
                ownedStocks.put(stock, amount);
            }
            return true;
        }
    }
    public boolean sellStock(Stock stock, int amount) {
        if(ownedStocks.containsKey(stock)) {
            if(ownedStocks.get(stock) >= amount) {
                ownedStocks.put(stock, ownedStocks.get(stock) - amount);
                this.balance += amount*stock.getCurrentPrice();
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
}
