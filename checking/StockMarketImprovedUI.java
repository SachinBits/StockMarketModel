package checking;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

public class StockMarketImprovedUI extends Application {
    private User user;
    private Map<String, Stock> stocks;
    private List<String> botNames = Arrays.asList("Bot_Alpha", "Bot_Beta", "Bot_Gamma", "Bot_Delta");

    @Override
    public void start(Stage primaryStage) {
        user = new User(100000);  // Initialize the user with a balance of $100,000
        stocks = new HashMap<>();
        initializeStocks();

        // Create the UI layout and tabs as before
        // ...

        startBots();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), e -> updateTradingTab()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateTradingTab() {
        // This code should refresh the UI components, such as stock prices, portfolio, and balance.
        // Assuming you have a TableView for displaying stock data and other UI elements.
    
        Platform.runLater(() -> {
            // Iterate through all stocks and update the stock table or any relevant UI.
            for (Map.Entry<String, Stock> entry : stocks.entrySet()) {
                String stockName = entry.getKey();
                Stock stock = entry.getValue();
                
                // Update your UI components to reflect the new price and price history
                // For example:
                System.out.println("Updated price of " + stockName + ": " + stock.getCurrentPrice());
    
                // If you have a TableView or other UI elements, update them here
                // Example: tableView.refresh();
            }
    
            // Update user balance or portfolio view if necessary
            System.out.println("User balance: $" + user.getBalance());
            // Refresh UI elements such as balance labels, portfolio list views, etc.
        });
    }
    
    private void initializeStocks() {
        stocks.put("Apple", new Stock("Apple", 150.00));
        stocks.put("Google", new Stock("Google", 2800.00));
        stocks.put("Tesla", new Stock("Tesla", 750.00));
        // Add more stocks as needed
    }

    // Methods for handling UI actions and bot trades
    // ...

    private void handleBuy(String stockSymbol) {
        Stock stock = stocks.get(stockSymbol);
        if (stock != null && user.getBalance() >= stock.getCurrentPrice()) {
            user.adjustBalance(-stock.getCurrentPrice());
            user.addStock(stockSymbol, 1, stock.getCurrentPrice());
            Platform.runLater(() -> {
                // Update UI components as needed
            });
        }
    }

    private void handleSell(String stockSymbol) {
        Stock stock = stocks.get(stockSymbol);
        if (stock != null && user.getPortfolio().getOrDefault(stockSymbol, 0) > 0) {
            user.removeStock(stockSymbol, 1, stock.getCurrentPrice());
            Platform.runLater(() -> {
                // Update UI components as needed
            });
        }
    }

    // Bot trading and price update logic
    private void startBots() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (Stock stock : stocks.values()) {
                    double newPrice = stock.getCurrentPrice() + (new Random().nextDouble() * 10 - 5);
                    newPrice = Math.max(10, newPrice);  // Ensure price doesn't drop too low
                    stock.updatePrice(newPrice);
                }
                Platform.runLater(() -> {
                    // Update UI and stock table as needed
                });
            }
        }, 0, 2000);
    }
}

