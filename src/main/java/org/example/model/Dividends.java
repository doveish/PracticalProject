package org.example.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "`dividends`", schema = "`project`")
public class Dividends {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "`date`")
    private LocalDate date;
    @Column(name = "`stock_symbol`")
    private String stockSymbol;
    @Column(name = "`account_id`")
    private String accountId;
    @Column(name = "`gross_amount`")
    private double grossAmount;
    @Column(name = "`withholding_tax`")
    private double withholdingTax;
    @Column(name = "`net_amount`")
    private double netAmount;

    public Dividends() {
    }

    public Dividends(long id, LocalDate date, String stockSymbol, String accountId,
                     double grossAmount, double withholdingTax, double netAmount) {
        this.id = id;
        this.date = date;
        this.stockSymbol = stockSymbol;
        this.accountId = accountId;
        this.grossAmount = grossAmount;
        this.withholdingTax = withholdingTax;
        this.netAmount = netAmount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(double grossAmount) {
        this.grossAmount = grossAmount;
    }

    public double getWithholdingTax() {
        return withholdingTax;
    }

    public void setWithholdingTax(double withholdingTax) {
        this.withholdingTax = withholdingTax;
    }

    public double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(double netAmount) {
        this.netAmount = netAmount;
    }

    @Override
    public String toString() {
        return "Dividends{" +
                "id=" + id +
                ", date=" + date +
                ", stockSymbol='" + stockSymbol + '\'' +
                ", accountId='" + accountId + '\'' +
                ", grossAmount=" + grossAmount +
                ", withholdingTax=" + withholdingTax +
                ", netAmount=" + netAmount +
                '}';
    }
}