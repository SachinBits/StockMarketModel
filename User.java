package App;

import java.util.ArrayList;
import java.util.HashMap;

class StockDetails {
    public int quantity;
    public double price;
    StockDetails(int quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }
}

public class User {
    private String userName, password;
    private double intitial_balance, balance;
    private HashMap<Stock,StockDetails> ownedStocks;

    private ArrayList<String> trades; //[Company,Price Bought or Sold, Profit or Loss Incurred]

    public User(String userName, String password, double intitial_balance, double balance) {
        this.userName = userName;
        this.intitial_balance = intitial_balance;
        this.balance = balance;
    }

    public double getProfit() {
        return balance - intitial_balance;
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
                ownedStocks.put(stock, new StockDetails(ownedStocks.get(stock).quantity+amount,
                        ownedStocks.get(stock).price+stock.getCurrentPrice()*amount));
            } else {
                ownedStocks.put(stock, new StockDetails(amount, stock.getCurrentPrice()*amount));
            }
            return true;
        }
    }
    public boolean sellStock(Stock stock, int amount) {
        if(ownedStocks.containsKey(stock)) {
            if(ownedStocks.get(stock).quantity >= amount) {
                ownedStocks.put(stock, new StockDetails(ownedStocks.get(stock).quantity-amount,
                        ownedStocks.get(stock).price-stock.getCurrentPrice()*amount));
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

    public double getUnrealizedProfit() {
        double unr_profit = 0;
        for(Stock stock : ownedStocks.keySet()) {
            unr_profit += ownedStocks.get(stock).price;
        }
        return unr_profit;
    }
}
