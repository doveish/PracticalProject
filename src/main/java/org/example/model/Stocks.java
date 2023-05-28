package org.example.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "Stocks_By_Symbol",
                query = "SELECT s FROM Stocks s WHERE s.symbol = :symbol"),

//        @NamedQuery(name = "Full_Portfolio",
//                query = "SELECT a.stockSymbol as Ticker , b.stockName as CompanyName, " +
//                        "b.currentPrice as CurrentPrice, sum(a.amount) as TotalAmount, " +
//                        "avg((a.transactionSum / a.amount)) as AverageBuyPrice, sum(a.transactionSum) as CurrentValue, " +
//                        "((sum(a.amount) * b.currentPrice) - sum(a.transactionSum)) as ProfitLoss " +
//                        "from Transactions a join Stocks b on a.stockSymbol = b.symbol group by a.stockSymbol"),

//        @NamedQuery(name = "Stock_List",
//                query = "SELECT s.symbol, s.name FROM Stocks s "),

})

@Entity
@Table(name = "`stocks`", schema = "`project`")
public class Stocks {
    @Id
    @Column(name = "`symbol`")
    private String symbol;
    @Column(name = "`name`")
    private String stockName;
    @Column(name = "`accout_name`")
    private String accountName;
    @Column(name = "`currect_price`")
    private double currentPrice;
    @Column(name = "`total_amount`")
    private double totalAmount;
    @Column(name = "`average_price`")
    private double averagePrice;
    @Column(name = "`current_value`")
    private double currentValue;
    @Column(name = "`profit_loss`")
    private double profitLoss;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "stocks_trn",
            joinColumns = {@JoinColumn(name = "stock_symbol")},
            inverseJoinColumns = {@JoinColumn(name = "symbol")}
    )
    private List<Transactions> transactions;

    public Stocks() {
    }

    public Stocks(String symbol, String stockName, String accountName,
                  double currentPrice, double totalAmount, double averagePrice,
                  double currentValue, double profitLoss) {
        this.symbol = symbol;
        this.stockName = stockName;
        this.accountName = accountName;
        this.currentPrice = currentPrice;
        this.totalAmount = totalAmount;
        this.averagePrice = averagePrice;
        this.currentValue = currentValue;
        this.profitLoss = profitLoss;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public double getProfitLoss() {
        return profitLoss;
    }

    public void setProfitLoss(double profitLoss) {
        this.profitLoss = profitLoss;
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Stocks{" +
                "symbol='" + symbol + '\'' +
                ", stockName='" + stockName + '\'' +
                ", accountName='" + accountName + '\'' +
                ", currentPrice=" + currentPrice +
                ", totalAmount=" + totalAmount +
                ", averagePrice=" + averagePrice +
                ", currentValue=" + currentValue +
                ", profitLoss=" + profitLoss +

                '}';
    }
}