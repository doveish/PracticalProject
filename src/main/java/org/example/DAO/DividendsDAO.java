package org.example.DAO;

import org.example.model.Accounts;
import org.example.model.Dividends;
import org.example.utils.HibernateUtlis;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class DividendsDAO {
    public Dividends insertDividend(String date, String symbol, String account, double gross, double tax) {
        Session session = HibernateUtlis.getCurrentSessionFromConfig();
        session.beginTransaction();
        Dividends dividends = new Dividends();
        dividends.setDate(LocalDate.parse(date));
        dividends.setStockSymbol(symbol);
        dividends.setAccountId(account);
        dividends.setGrossAmount(gross);
        dividends.setWithholdingTax(tax);


        session.persist(dividends);

        session.getTransaction().commit();
        session.close();
        if (session.isOpen()) {
            System.out.println("Something went wrong");
        } else {
            System.out.println("Dividend added");
        }
        return dividends;
    }

    public void getAllDividends() {
        Session session = HibernateUtlis.getCurrentSessionFromConfig();
        Transaction trn = session.beginTransaction();
        try {
            SQLQuery query = session.createSQLQuery("SELECT * FROM project.dividends;");
            List<Object[]> dividends = (List<Object[]>) query.list();
            System.out.println("Dividends You have got");

            for (Object[] div : dividends) {
                Date date = (Date) div[1];
                String symbol = (String) div[2];
                String account = (String) div[3];
                double gross = (double) div[4];
                double tax = (double) div[5];
                double net = (double) div[6];
                System.out.println("Date: " + date +
                        ", ticker: " + symbol + ", account: " + account + ", gross amount: " +
                        gross + ", withholding tax: " + tax + ", net amount " + net);
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
}


