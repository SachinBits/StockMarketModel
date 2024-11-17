package com.example.stocks;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

public class StockMarketImprovedUI extends Application {

    private double userBalance = 100000;  // Increased initial balance
    private Map<String, Integer> userPortfolio = new HashMap<>();  // User's stock portfolio
    private Map<String, Double> userPurchasePrices = new HashMap<>();  // Purchase prices of stocks
    private Map<String, Double> stockPrices = new HashMap<>();  // Current stock prices
    private Map<String, List<Double>> priceHistory = new HashMap<>();  // Price history for graphs
    private Random random = new Random();
    private List<String> botNames = Arrays.asList("Bot_Alpha", "Bot_Beta", "Bot_Gamma", "Bot_Delta", "Bot_Epsilon",
            "Bot_Zeta", "Bot_Eta", "Bot_Theta", "Bot_Iota", "Bot_Kappa");
    private Label profitLossLabel = new Label("Profit/Loss: $0");
    private Label profitPercentageLabel = new Label("Profit Percentage: 0%");
    private ListView<String> activityLog = new ListView<>();
    private TableView<Map.Entry<String, Double>> stockTable = new TableView<>();
    private Map<String, Double> realizedProfit = new HashMap<>();
    private Map<String, Double> presetStockPurchases = new HashMap<>(); // Track realized profit for each stock
    private Map<String, Double> stockPurchasePrices = new HashMap<>();

    // New declarations
    private VBox tradingTabLayout = new VBox(10);
    private TableView<PortfolioEntry> portfolioTable = new TableView<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Sample data for stock prices
        stockPrices.put("Apple", 150.00);
        stockPrices.put("Google", 2800.00);
        stockPrices.put("Tesla", 750.00);
        stockPrices.put("Amazon", 3300.00);
        stockPrices.put("Microsoft", 290.00);
        stockPrices.put("Meta", 350.00);
        stockPrices.put("Netflix", 500.00);
        stockPrices.put("Intel", 55.00);
        stockPrices.put("Nvidia", 650.00);
        stockPrices.put("AMD", 100.00);

        // Initialize price history for graphs
        for (String company : stockPrices.keySet()) {
            priceHistory.put(company, new ArrayList<>());
            priceHistory.get(company).add(stockPrices.get(company));  // Add the initial price
        }

        // Create the UI components
        BorderPane root = new BorderPane();

        // Create the tabs
        TabPane tabPane = new TabPane();

        // Create the Trading Tab
        Tab tradingTab = new Tab("Trading");
        tradingTab.setContent(createTradingTab());
        tradingTab.setClosable(false); // Prevent closing the tab

        // Create the Analytics Tab
        Tab analyticsTab = new Tab("Analytics");
        analyticsTab.setContent(createAnalyticsTab());
        analyticsTab.setClosable(false);

        // Add the tabs to the tab pane
        tabPane.getTabs().addAll(tradingTab, analyticsTab);
        root.setCenter(tabPane);


        // Set the scene
        Scene scene = new Scene(root, 1000, 700);
        scene.getStylesheets().add("file:/C:/Users/sachi/IdeaProjects/Stocks/src/main/java/com/example/stocks/style.css");


        primaryStage.setTitle("Stock Market Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Start bots for simulated stock market fluctuations and trading
        startBots();

        // Refresh Trading tab every 5 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), e -> updateTradingTab()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    // PortfolioEntry class
    public static class PortfolioEntry {
        private final SimpleStringProperty stockSymbol;
        private final SimpleIntegerProperty quantity;
        private final SimpleDoubleProperty purchasePrice;
        private final SimpleDoubleProperty currentPrice;
        private final SimpleDoubleProperty priceDifference;

        public PortfolioEntry(String stockSymbol, int quantity, double purchasePrice, double currentPrice) {
            this.stockSymbol = new SimpleStringProperty(stockSymbol);
            this.quantity = new SimpleIntegerProperty(quantity);
            this.purchasePrice = new SimpleDoubleProperty(purchasePrice);
            this.currentPrice = new SimpleDoubleProperty(currentPrice);
            this.priceDifference = new SimpleDoubleProperty(currentPrice - purchasePrice);
        }

        public String getStockSymbol() { return stockSymbol.get(); }
        public int getQuantity() { return quantity.get(); }
        public double getPurchasePrice() { return purchasePrice.get(); }
        public double getCurrentPrice() { return currentPrice.get(); }
        public double getPriceDifference() { return priceDifference.get(); }
    }

    // Method to create the Trading Tab
    private VBox createTradingTab() {
        // Initialize tradingTabLayout
        tradingTabLayout = new VBox(10);

        HBox balanceBox = new HBox(10);
        Label balanceLabel = new Label("Balance: $" + String.format("%.2f", userBalance));
        balanceBox.getChildren().add(balanceLabel);

        // Company selection for buying/selling
        ComboBox<String> companySelector = new ComboBox<>();
        companySelector.getItems().addAll(stockPrices.keySet());
        companySelector.setPromptText("Select a Company");

        // Buttons for buy/sell actions
        Button buyButton = new Button("Buy");
        Button sellButton = new Button("Sell");

        // Portfolio and activity log
        ListView<String> portfolioView = new ListView<>();
        updatePortfolioView(portfolioView); // Update portfolio view with quantities
        activityLog.setPrefHeight(200);

        buyButton.setOnAction(e -> handleBuy(companySelector, portfolioView, balanceLabel));
        sellButton.setOnAction(e -> handleSell(companySelector, portfolioView, balanceLabel));

        // Preset Purchase Rule Button
        Button presetPurchaseButton = new Button("Set Preset Purchase");
        presetPurchaseButton.setOnAction(e -> openPresetPurchaseWindow());

        // Layout for Trading Tab
        tradingTabLayout.getChildren().addAll(balanceBox, companySelector, buyButton, sellButton, portfolioView, activityLog, presetPurchaseButton);

        // Create columns for the portfolio table
        TableColumn<PortfolioEntry, String> symbolCol = new TableColumn<>("Stock Symbol");
        symbolCol.setCellValueFactory(new PropertyValueFactory<>("stockSymbol"));

        TableColumn<PortfolioEntry, Integer> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<PortfolioEntry, Double> purchasePriceCol = new TableColumn<>("Purchase Price");
        purchasePriceCol.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));

        TableColumn<PortfolioEntry, Double> currentPriceCol = new TableColumn<>("Current Price");
        currentPriceCol.setCellValueFactory(new PropertyValueFactory<>("currentPrice"));

        TableColumn<PortfolioEntry, Double> differenceCol = new TableColumn<>("Difference");
        differenceCol.setCellValueFactory(new PropertyValueFactory<>("priceDifference"));

        // Add columns to the table
        portfolioTable.getColumns().addAll(symbolCol, quantityCol, purchasePriceCol, currentPriceCol, differenceCol);

        // Set table properties
        portfolioTable.setPrefHeight(200);
        portfolioTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Add the table to the layout
        tradingTabLayout.getChildren().addAll(new Label("Portfolio Details:"), portfolioTable);

        return tradingTabLayout;
    }

    // Method to update the trading tab with purchase price and live difference
    private void updateTradingTab() {
        Platform.runLater(() -> {
            // Update balance label
            HBox balanceBox = (HBox) tradingTabLayout.getChildren().get(0);
            Label balanceLabel = (Label) balanceBox.getChildren().get(0);
            balanceLabel.setText("Balance: $" + String.format("%.2f", userBalance));

            // Update portfolio table
            portfolioTable.getItems().clear();
            for (Map.Entry<String, Integer> entry : userPortfolio.entrySet()) {
                String stockSymbol = entry.getKey();
                int quantity = entry.getValue();

                if (quantity > 0) {
                    double purchasePrice = stockPurchasePrices.getOrDefault(stockSymbol, 0.0);
                    double currentPrice = stockPrices.getOrDefault(stockSymbol, 0.0);
                    portfolioTable.getItems().add(new PortfolioEntry(stockSymbol, quantity, purchasePrice, currentPrice));
                }
            }
        });
    }

    // Method to create the Analytics Tab
    private VBox createAnalyticsTab() {
        VBox analyticsLayout = new VBox(10);

        // Profit/Loss and Percentage Labels
        analyticsLayout.getChildren().addAll(profitLossLabel, profitPercentageLabel);

        // Stock Price Table for live updates
        stockTable.setItems(FXCollections.observableArrayList(stockPrices.entrySet()));
        stockTable.setEditable(false);

        // Create columns for the table
        TableColumn<Map.Entry<String, Double>, String> nameCol = new TableColumn<>("Company");
        nameCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getKey()));

        TableColumn<Map.Entry<String, Double>, String> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(data -> new ReadOnlyStringWrapper("$" + String.format("%.2f", data.getValue().getValue())));

        stockTable.getColumns().addAll(nameCol, priceCol);

        // Graph button
        Button showGraphButton = new Button("Show Graphs");
        showGraphButton.setOnAction(e -> showStockGraphs());

        // Layout for Analytics Tab
        analyticsLayout.getChildren().addAll(stockTable, showGraphButton);

        return analyticsLayout;
    }

    // Method to handle stock purchase
    private void handleBuy(ComboBox<String> companySelector, ListView<String> portfolioView, Label balanceLabel) {
        String stock = companySelector.getValue();
        if (stock != null) {
            double price = stockPrices.get(stock);
            if (userBalance >= price) {
                userBalance -= price;
                userPortfolio.put(stock, userPortfolio.getOrDefault(stock, 0) + 1);

                // Update purchase price in stockPurchasePrices
                stockPurchasePrices.put(stock, price); // Ensure the latest purchase price is updated

                // Store the purchase price if it's the first time buying
                if (!userPurchasePrices.containsKey(stock)) {
                    userPurchasePrices.put(stock, price);
                }

                activityLog.getItems().add("Bought 1 share of " + stock + " at $" + String.format("%.2f", price));
                balanceLabel.setText("Balance: $" + String.format("%.2f", userBalance));
                updatePortfolioView(portfolioView);
                updateProfitLoss();
                updateTradingTab();
            } else {
                showAlert("Insufficient funds!");
            }
        }
    }


    // Method to handle stock sale
    private void handleSell(ComboBox<String> companySelector, ListView<String> portfolioView, Label balanceLabel) {
        String stock = companySelector.getValue();
        if (stock != null) {
            int quantity = userPortfolio.getOrDefault(stock, 0);
            if (quantity > 0) {
                double currentPrice = stockPrices.get(stock);
                double purchasePrice = userPurchasePrices.get(stock); // Retrieve the purchase price

                // Calculate profit/loss for this transaction
                double profitLoss = (currentPrice - purchasePrice); // Profit if positive, loss if negative
                double transactionProfitLoss = profitLoss; // If sold, consider the profit/loss from this transaction

                // Update portfolio
                userPortfolio.put(stock, quantity - 1);
                userBalance += currentPrice;

                // Record realized profit
                realizedProfit.put(stock, realizedProfit.getOrDefault(stock, 0.0) + transactionProfitLoss);

                // Log the transaction
                activityLog.getItems().add("Sold 1 share of " + stock + " at $" + String.format("%.2f", currentPrice) + " | Profit/Loss: $" + String.format("%.2f", transactionProfitLoss));
                balanceLabel.setText("Balance: $" + String.format("%.2f", userBalance));

                updatePortfolioView(portfolioView);
                updateProfitLoss();
                updateTradingTab();
            } else {
                showAlert("No shares to sell!");
            }
        }
    }

    // Method to calculate and update profit and loss
    private void updateProfitLoss() {
        double totalProfitLoss = 0;
        double totalRealizedProfit = 0;
        double totalUnrealizedProfit = 0;

        for (String stock : userPortfolio.keySet()) {
            int quantity = userPortfolio.get(stock);
            if (quantity > 0) {
                double purchasePrice = userPurchasePrices.get(stock);
                double currentPrice = stockPrices.get(stock);
                totalUnrealizedProfit += (currentPrice - purchasePrice) * quantity;
            }
        }

        // Calculate realized profit
        for (Double profit : realizedProfit.values()) {
            totalRealizedProfit += profit;
        }

        totalProfitLoss = totalRealizedProfit + totalUnrealizedProfit;

        double profitPercentage = (totalProfitLoss / 100000) * 100;
        profitLossLabel.setText("Profit/Loss: $" + String.format("%.2f", totalProfitLoss));
        profitPercentageLabel.setText("Profit Percentage: " + String.format("%.2f", profitPercentage) + "%");
    }

    // Method to update portfolio view with quantities (if needed elsewhere)
    private void updatePortfolioView(ListView<String> portfolioView) {
        List<String> portfolio = new ArrayList<>();
        double totalAmountFromSelling = 0;

        for (String stock : userPortfolio.keySet()) {
            int quantity = userPortfolio.get(stock);
            if (quantity > 0) {
                portfolio.add(stock + " - " + quantity + " shares");
                totalAmountFromSelling += stockPrices.get(stock) * quantity;
            }
        }

        portfolio.add("Total amount from selling all stocks: $" + String.format("%.2f", totalAmountFromSelling));

        portfolioView.setItems(FXCollections.observableArrayList(portfolio));
    }

    // Method to show stock graphs
    private void showStockGraphs() {
        Stage graphStage = new Stage();
        VBox graphLayout = new VBox(10);

        // Create a ComboBox to select which graph to show
        ComboBox<String> stockSelector = new ComboBox<>();
        stockSelector.getItems().addAll(stockPrices.keySet());
        stockSelector.setPromptText("Select a stock to view");

        Button showSelectedGraphButton = new Button("Show Selected Graph");
        showSelectedGraphButton.setOnAction(e -> {
            String selectedStock = stockSelector.getValue();
            if (selectedStock != null) {
                graphLayout.getChildren().clear();  // Clear the previous graph
                graphLayout.getChildren().add(createStockChart(selectedStock));
            }
        });

        // Show all graphs button
        Button showAllGraphsButton = new Button("Show All Graphs");
        showAllGraphsButton.setOnAction(e -> {
            graphLayout.getChildren().clear();  // Clear the previous graphs
            for (String company : stockPrices.keySet()) {
                graphLayout.getChildren().add(createStockChart(company));
            }
        });

        // Add components to the graph layout
        graphLayout.getChildren().addAll(stockSelector, showSelectedGraphButton, showAllGraphsButton);

        Scene graphScene = new Scene(graphLayout, 800, 600);
        graphStage.setTitle("Stock Price Graphs");
        graphStage.setScene(graphScene);
        graphStage.show();
    }

    // Method to create a line chart for a single stock (no dots)
    private LineChart<Number, Number> createStockChart(String company) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(company);
        List<Double> history = priceHistory.get(company);

        // Add historical data points to the series (no dots)
        for (int i = 0; i < history.size(); i++) {
            series.getData().add(new XYChart.Data<>(i, history.get(i)));
        }

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Time");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Price");

        // Creating the LineChart
        LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
        chart.getData().add(series);

        // Make sure the chart only has a line, no dots
        chart.setCreateSymbols(false);

        return chart;
    }

    // Method to start bots for simulated stock market fluctuations and trading
    private void startBots() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Simulate bot trades
                String randomBot = botNames.get(random.nextInt(botNames.size()));
                String randomStock = getRandomStock();
                double price = stockPrices.get(randomStock);
                Platform.runLater(() -> activityLog.getItems().add(randomBot + " traded " + randomStock + " at $" + String.format("%.2f", price)));

                // Update stock prices randomly
                for (String stock : stockPrices.keySet()) {
                    double oldPrice = stockPrices.get(stock);
                    double newPrice = oldPrice + (random.nextDouble() * 10 - 5);
                    newPrice = Math.max(10, newPrice);
                    stockPrices.put(stock, newPrice);
                    priceHistory.get(stock).add(newPrice);
                }

                // Update UI
                Platform.runLater(() -> {
                    updateStockTable();
                    updateTradingTab(); // Refresh Trading Tab
                });
            }
        }, 0, 2000);
    }

    // Method to get a random stock for bots to trade
    private String getRandomStock() {
        List<String> stocks = new ArrayList<>(stockPrices.keySet());
        return stocks.get(random.nextInt(stocks.size()));
    }

    // Method to update the stock table with live data
    private void updateStockTable() {
        Platform.runLater(() -> {
            stockTable.getItems().clear();
            stockTable.getColumns().clear();

            TableColumn<Map.Entry<String, Double>, String> nameCol = new TableColumn<>("Company");
            nameCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getKey()));

            TableColumn<Map.Entry<String, Double>, String> priceCol = new TableColumn<>("Price");
            priceCol.setCellValueFactory(data -> new ReadOnlyStringWrapper("$" + String.format("%.2f", data.getValue().getValue())));

            TableColumn<Map.Entry<String, Double>, String> realizedProfitCol = new TableColumn<>("Realized Profit");
            realizedProfitCol.setCellValueFactory(data -> new ReadOnlyStringWrapper("$" + String.format("%.2f", realizedProfit.getOrDefault(data.getValue().getKey(), 0.0))));

            TableColumn<Map.Entry<String, Double>, String> unrealizedProfitCol = new TableColumn<>("Unrealized Profit");
            unrealizedProfitCol.setCellValueFactory(data -> new ReadOnlyStringWrapper("$" + String.format("%.2f", (data.getValue().getValue() - userPurchasePrices.getOrDefault(data.getValue().getKey(), 0.0)))));

            stockTable.getColumns().addAll(nameCol, priceCol, realizedProfitCol, unrealizedProfitCol);
            stockTable.setItems(FXCollections.observableArrayList(stockPrices.entrySet()));
        });
    }

    // Method to show alerts (e.g., insufficient funds)
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Additional methods (e.g., openPresetPurchaseWindow) remain unchanged
    private void openPresetPurchaseWindow() {
        Stage presetPurchaseStage = new Stage();
        VBox presetPurchaseLayout = new VBox(10);

        ComboBox<String> companySelector = new ComboBox<>();
        companySelector.getItems().addAll(stockPrices.keySet());
        companySelector.setPromptText("Select a Company");

        TextField priceField = new TextField();
        priceField.setPromptText("Enter Price");

        Button setButton = new Button("Set Purchase Rule");
        setButton.setOnAction(e -> {
            String stock = companySelector.getValue();
            String priceText = priceField.getText();
            if (stock != null && !priceText.isEmpty()) {
                try {
                    double presetPrice = Double.parseDouble(priceText);
                    if (presetPrice > 0) {
                        presetStockPurchases.put(stock, presetPrice);
                        showAlert("Purchase rule set for " + stock + " at $" + String.format("%.2f", presetPrice));
                        presetPurchaseStage.close();
                    } else {
                        showAlert("Price must be greater than 0.");
                    }
                } catch (NumberFormatException ex) {
                    showAlert("Invalid price input.");
                }
            } else {
                showAlert("Please select a stock and enter a valid price.");
            }
        });

        presetPurchaseLayout.getChildren().addAll(companySelector, priceField, setButton);

        Scene presetPurchaseScene = new Scene(presetPurchaseLayout, 300, 200);
        presetPurchaseStage.setTitle("Set Preset Purchase Rule");
        presetPurchaseStage.setScene(presetPurchaseScene);
        presetPurchaseStage.show();
    }
}
