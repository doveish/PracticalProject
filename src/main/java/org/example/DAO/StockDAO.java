package org.example.DAO;

import org.example.model.Stocks;
import org.example.model.Transactions;
import org.example.utils.HibernateUtlis;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class StockDAO {

    public Stocks insertStock(String symbol, String stockName, String accountName, double currentPrice) {

        Session session = HibernateUtlis.getCurrentSessionFromConfig();
        session.beginTransaction();

        Stocks stocks = new Stocks();

        stocks.setSymbol(symbol);
        stocks.setStockName(stockName);
        stocks.setAccountName(accountName);
        stocks.setCurrentPrice(currentPrice);
        session.persist(stocks);
        session.getTransaction().commit();
        session.close();
        if (session.isOpen()) {
            System.out.println("Something went wrong");
        } else {
            System.out.println("Stock added to list");
        }
        return stocks;

    }

    public void getFullPortfolio() {
        Session session = HibernateUtlis.getCurrentSessionFromConfig();
        Transaction trn = session.beginTransaction();
        try {
            SQLQuery query = session.createSQLQuery("SELECT * FROM project.stocks;");
            List<Object[]> list = (List<Object[]>) query.list();
            System.out.println("Your full portfolio");
            for (Object[] l : list) {
                String sym = (String) l[0];
                String name = (String) l[1];
                String account = (String) l[2];
                double currentPrice = (double) l[3];
                double amount = (double) l[4];
                double ave = (double) l[5];
                double value = (double) l[6];
                double pl = (double) l[7];
                System.out.println(" Symbol: " + sym + ", stock name: " + name + ", account: " + account +
                        ", market price: " + currentPrice + ", amount: " + amount + ", average buy price: " +
                        ave + ", total buy value: " + value + ", profit/loss: " + pl);
            }
            System.out.println();
            trn.commit();
        } catch (HibernateException e) {
            if (trn != null) trn.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Stocks> getStockBySymbol(String symbol) {
        Session session = HibernateUtlis.getCurrentSessionFromConfig();
        Transaction trn = session.beginTransaction();
        Query query = session.getNamedQuery("Stocks_By_Symbol");
        query.setParameter("symbol", symbol);
        List<Stocks> stocks = query.getResultList();
        trn.commit();
        return stocks;
    }

    public void getStockList() {
        Session session = HibernateUtlis.getCurrentSessionFromConfig();
        Transaction trn = session.beginTransaction();
        try {
            SQLQuery query = session.createSQLQuery("SELECT symbol, name FROM project.stocks;");
            List<Object[]> list = (List<Object[]>) query.list();
            for (Object[] l : list) {
                String s = (String) l[0];
                String n = (String) l[1];
                System.out.println(" Symbol " + s + " for " + n);
            }
            trn.commit();
        } catch (HibernateException e) {
            if (trn != null) trn.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
