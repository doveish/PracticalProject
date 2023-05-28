package org.example.DAO;

import org.example.model.Accounts;
import org.example.model.Stocks;
import org.example.utils.HibernateUtlis;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.List;

public class AccountsDAO {
    public Accounts insertAccount(String account, String name, double balance, String currency) {
        Session session = HibernateUtlis.getCurrentSessionFromConfig();
        session.beginTransaction();
        Accounts accounts = new Accounts();
        accounts.setAccount(account);
        accounts.setAccountName(name);
        accounts.setBalance(balance);
        accounts.setCurrency(currency);


        session.persist(accounts);

        session.getTransaction().commit();
        session.close();
        if (session.isOpen()) {
            System.out.println("Something went wrong");
        } else {
            System.out.println("New account added");
        }
        return accounts;
    }

    public void getAccounts() {
        Session session = HibernateUtlis.getCurrentSessionFromConfig();
        Transaction trn = session.beginTransaction();
        try {
            SQLQuery query = session.createSQLQuery("SELECT * FROM project.accounts;");
            List<Object[]> list = (List<Object[]>) query.list();
            System.out.println("Accounts");
            for (Object[] l : list) {
                String code = (String) l[0];
                String name = (String) l[1];
                double balance = (double) l[2];
                String currency = (String) l[3];
                System.out.println(" Symbol: " + code + ", name: " + name +
                        ", balance: " + balance + ", currency: " + currency);

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

    public void makeDepositWithdraw(String code, double sum) {
        Session session = HibernateUtlis.getCurrentSessionFromConfig();
        Transaction trn = session.beginTransaction();
        Query query = session.createQuery("UPDATE Accounts a " +
                "SET a.balance = a.balance + :sum " +
                "WHERE a.account = :acc");
        query.setParameter("sum", sum);
        query.setParameter("acc", code);
        int result = query.executeUpdate();
        if (result > 0 && sum > 0) {
            System.out.println("Deposit made");
        } else if (result > 0 && sum < 0) {
            System.out.println("Withdraw made");
        } else {
            System.out.println("Something went wrong");
        }

        trn.commit();

    }
}
