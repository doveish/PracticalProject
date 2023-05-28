package org.example.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "`accounts`", schema = "`project`")
public class Accounts {
    @Id
    @Column(name = "account")
    private String account;
    @Column(name = "`account_name`")
    private String accountName;
    @Column(name = "`balance`")
    private double balance;
    @Column(name = "`currecny`")
    private String currency;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "account")
    private List<Transactions> transactions;

    public Accounts() {
    }

    public Accounts(String account, String accountName, double balance, String currency) {
        this.account = account;
        this.accountName = accountName;
        this.balance = balance;
        this.currency = currency;
    }



    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "account='" + account + '\'' +
                ", accountName='" + accountName + '\'' +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", transactions=" + transactions +
                '}';
    }
}
