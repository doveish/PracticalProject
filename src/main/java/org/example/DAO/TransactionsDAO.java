package org.example.DAO;

import org.example.model.Stocks;
import org.example.model.Transactions;
import org.example.utils.HibernateUtlis;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public class TransactionsDAO {


    public void getAllTransactions() {
        Session session = HibernateUtlis.getCurrentSessionFromConfig();
        Transaction trn = session.beginTransaction();
        try {
            SQLQuery query = session.createSQLQuery("SELECT * FROM project.transactions;");
            List<Object[]> trades = (List<Object[]>) query.list();
            System.out.println("All Your transactions");
            for (Object[] trade : trades) {

                String type = (String) trade[1];
                String symbol = (String) trade[2];
                String account = (String) trade[3];
                Date date = (Date) trade[4];
                double amount = (double) trade[5];
                double unitPrice = (double) trade[6];
                double commission = (double) trade[7];
                double trnSum = (double) trade[8];
                System.out.println("Trn type: " + type + ", stock: " + symbol + ", account: " + account +
                        ", date: " + date + ", amount: " + amount + ", unit price: " +
                        unitPrice + ", commission: " + commission + ", transaction sum: " + trnSum);
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


    public Transactions insertTrn(String trnType, String symbol, String accountId, String date,
                                  double amount, double unitPrice, double commission) {
        Session session = HibernateUtlis.getCurrentSessionFromConfig();
        session.beginTransaction();
        Transactions trn = new Transactions();
        trn.setTransactionType(trnType);
        trn.setStockSymbol(symbol);
        trn.setAccount(accountId);
        trn.setDate(LocalDate.parse(date));
        trn.setAmount(amount);
        trn.setUnitPrice(unitPrice);
        trn.setCommission(commission);

        session.persist(trn);

        session.getTransaction().commit();
        session.close();
        if(session.isOpen()){
            System.out.println("Something went wrong");
        } else {
            System.out.println("New transaction added");
        }
        return trn;
    }

    public List<Transactions> getTrnBySymbol(String stockSymbol) {
        Session session = HibernateUtlis.getCurrentSessionFromConfig();
        Transaction trn = session.beginTransaction();
        Query query = session.getNamedQuery("Transactions_By_Symbol");
        query.setParameter("stockSymbol", stockSymbol);
        List<Transactions> transactionsList = query.getResultList();
        trn.commit();
        return transactionsList;

    }



}

