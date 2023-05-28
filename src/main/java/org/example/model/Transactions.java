package org.example.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@NamedQueries({
        @NamedQuery(name = "Transactions_By_Symbol",
                query = "SELECT t FROM Transactions t WHERE t.stockSymbol = :stockSymbol"),

        @NamedQuery(name = "Full_Portfolio",
                query = "SELECT a.stockSymbol as Ticker , b.stockName as CompanyName, " +
                        "b.currentPrice as CurrentPrice, sum(a.amount) as TotalAmount, " +
                        "avg((a.transactionSum / a.amount)) as AverageBuyPrice, sum(a.transactionSum) as CurrentValue, " +
                        "((sum(a.amount) * b.currentPrice) - sum(a.transactionSum)) as ProfitLoss " +
                        "from Transactions a join Stocks b on a.stockSymbol = b.symbol group by a.stockSymbol"),


        //same as in MySQL workbench
//        //SELECT cat.* FROM cat_club.cat, cat_club.cat_owner WHERE cat.cat_owner_id = cat_owner.id AND cat_owner.code = '111111111';
//
//        @NamedQuery(name = "Cat_By_CatOwner_Name",
//                query = "SELECT c FROM Cat c, CatOwner o WHERE c.catOwner.id = o.id and o.name LIKE :name"),
//
//        @NamedQuery(name = "Get_Cat_By_Gender",
//                query = "SELECT c FROM Cat c where c.gender = :gender"),
//
//        @NamedQuery(name = "Get_Cats_By_Breeds",
//                query = "SELECT c FROM Cat c WHERE c.breed IN :breeds")

})
@Entity
@Table(name = "`transactions`", schema = "`project`")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "`trn_type`")
    private String transactionType;
    @Column(name = "`stock_symbol`")
    private String stockSymbol;
    @Column(name = "account_id")
    private String account;
    @Column(name = "`date`")
    private LocalDate date;
    @Column(name = "`amount`")
    private double amount;
    @Column(name = "`unit_price`")
    private double unitPrice;
    @Column(name = "`trn_commission`")
    private double commission;
    @Column(name = "`trn_sum`")
    private double transactionSum;
    @ManyToMany(mappedBy = "transactions", fetch = FetchType.EAGER)
    private List<Stocks> stocks;
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "account", referencedColumnName = "account_id")
//    private List<Accounts> accounts;


    public Transactions() {
    }

    public Transactions(long id, String transactionType, String stockSymbol, String account,
                        LocalDate date, double amount, double unitPrice, double commission, double transactionSum) {
        this.id = id;
        this.transactionType = transactionType;
        this.stockSymbol = stockSymbol;
        this.account = account;
        this.date = date;
        this.amount = amount;
        this.unitPrice = unitPrice;
        this.commission = commission;
        this.transactionSum = transactionSum;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public double getTransactionSum() {
        return transactionSum;
    }

    public void setTransactionSum(double transactionSum) {
        this.transactionSum = transactionSum;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "id=" + id +
                ", transactionType='" + transactionType + '\'' +
                ", stockSymbol='" + stockSymbol + '\'' +
                ", account='" + account + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", unitPrice=" + unitPrice +
                ", commission=" + commission +
                ", transactionSum=" + transactionSum +

                '}';
    }
}
