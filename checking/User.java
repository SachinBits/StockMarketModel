package checking;
import java.util.*;

public class User {
    private double balance;
    private Map<String, Integer> portfolio;  // Stock symbol -> Quantity
    private Map<String, Double> purchasePrices;  // Stock symbol -> Last purchase price
    private Map<String, Double> realizedProfit;  // Stock symbol -> Realized profit

    public User(double initialBalance) {
        this.balance = initialBalance;
        this.portfolio = new HashMap<>();
        this.purchasePrices = new HashMap<>();
        this.realizedProfit = new HashMap<>();
    }

    public double getBalance() {
        return balance;
    }

    public void adjustBalance(double amount) {
        this.balance += amount;
    }

    public void addStock(String stockSymbol, int quantity, double price) {
        portfolio.put(stockSymbol, portfolio.getOrDefault(stockSymbol, 0) + quantity);
        purchasePrices.put(stockSymbol, price);
    }

    public void removeStock(String stockSymbol, int quantity, double currentPrice) {
        int currentQuantity = portfolio.getOrDefault(stockSymbol, 0);
        if (currentQuantity >= quantity) {
            portfolio.put(stockSymbol, currentQuantity - quantity);
            adjustBalance(currentPrice * quantity);
            double profit = (currentPrice - purchasePrices.get(stockSymbol)) * quantity;
            realizedProfit.put(stockSymbol, realizedProfit.getOrDefault(stockSymbol, 0.0) + profit);
        }
    }

    public Map<String, Integer> getPortfolio() {
        return portfolio;
    }

    public Map<String, Double> getRealizedProfit() {
        return realizedProfit;
    }
}

