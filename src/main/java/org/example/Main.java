package org.example;

import org.example.DAO.AccountsDAO;
import org.example.DAO.DividendsDAO;
import org.example.DAO.StockDAO;
import org.example.DAO.TransactionsDAO;

import java.util.Scanner;


public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static StockDAO stockDAO = new StockDAO();
    private static TransactionsDAO transactionsDAO = new TransactionsDAO();
    private static AccountsDAO accountsDAO = new AccountsDAO();
    private static DividendsDAO dividendsDAO = new DividendsDAO();

    public static void main(String[] args) {

        for (; ; ) {
            boolean quit = false;
            printActions();
            int choice;
            while (!quit) {
                System.out.println();
                System.out.println("Enter choice please!");
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 8:
                        System.out.println("Going back!");
                        quit = true;
                        break;
                    case 9:
                        System.out.println("Exiting the program!");
                        System.exit(0);
                    case 1:
                        readDataActions();
                        while (!quit) {
                            choice = sc.nextInt();
                            sc.nextLine();
                            switch (choice) {
                                case 8:
                                    System.out.println("Going back!");
                                    quit = true;
                                    break;
                                case 1:
                                    stockDAO.getFullPortfolio();
                                    readDataActions();
                                    break;
                                case 2:
                                    transactionsDAO.getAllTransactions();
                                    readDataActions();
                                    break;
                                case 3:
                                    accountsDAO.getAccounts();
                                    readDataActions();
                                    break;
                                case 4:
                                    dividendsDAO.getAllDividends();
                                    readDataActions();
                                    break;
                                case 5:
                                    getStockBySymbol();
                                    readDataActions();
                                    break;
                                case 6:
                                    getTradeBySymbol();
                                    readDataActions();
                                    break;
                                case 9:
                                    System.out.println("Exiting the program!");
                                    System.exit(0);
                                default:
                                    System.out.println("Wrong choice");
                                    readDataActions();
                            }
                        }
                        break;
                    case 2:
                        insertDataActions();
                        while (!quit) {
                            choice = sc.nextInt();
                            sc.nextLine();
                            switch (choice) {
                                case 8:
                                    System.out.println("Going back!");
                                    quit = true;
                                    break;
                                case 1:
                                    insertNewAccount();
                                    insertDataActions();
                                    break;
                                case 2:
                                    insertNewStock();
                                    insertDataActions();
                                    break;
                                case 3:
                                    insertNewTrade();
                                    insertDataActions();
                                    break;
                                case 4:
                                    insertNewDividend();
                                    insertDataActions();
                                    break;
                                case 5:
                                    insertDepositWithdraw();
                                    insertDataActions();
                                    break;
                                case 9:
                                    System.out.println("Exiting the program!");
                                    System.exit(0);
                                default:
                                    System.out.println("Wrong choice");
                                    insertDataActions();
                            }
                        }
                        break;
                    default:
                        System.out.println("Wrong choice");
                        printActions();
                }
            }
        }
    }


    private static void insertDepositWithdraw() {
        System.out.println("To which account do you want to deposit/withdrawal?");
        accountsDAO.getAccounts();
        System.out.println("\nInsert account name");
        String code = sc.nextLine();
        System.out.println("Insert sum ");
        double sum = sc.nextDouble();

        accountsDAO.makeDepositWithdraw(code, sum);
    }


    private static void getStockBySymbol() {
        System.out.println("List of stock");
        stockDAO.getStockList();
        System.out.println("\nInsert stock symbol what summary You need");
        String symbol = sc.next();
        System.out.println(stockDAO.getStockBySymbol(symbol));
    }

    private static void getTradeBySymbol() {
        System.out.println("List of stock");
        stockDAO.getStockList();

        System.out.println("\nInsert stock symbol what trades You need");
        String symbol = sc.nextLine();
        System.out.println(transactionsDAO.getTrnBySymbol(symbol));
    }

    private static void insertNewDividend() {
        System.out.println("Insert divided date");
        String date = sc.nextLine();
        System.out.println("Insert stock symbol");
        String symbol = sc.nextLine();
        System.out.println("Insert account for dividend");
        String account = sc.nextLine();
        System.out.println("Insert gross amount");
        double gross = sc.nextDouble();
        System.out.println("Insert withholding tax");
        double tax = sc.nextDouble();

        dividendsDAO.insertDividend(date, symbol, account, gross, tax);
    }

    private static void insertNewAccount() {
        System.out.println("Insert account name");
        String account = sc.nextLine();
        System.out.println("Insert account full name");
        String name = sc.nextLine();
        System.out.println("Insert account balance");
        double balance = sc.nextDouble();
        System.out.println("Insert account currency");
        String currency = sc.nextLine();

        accountsDAO.insertAccount(account, name, balance, currency);

    }

    private static void printActions() {
        System.out.println("Actions: press");
        System.out.println(
                "1 - read data \n" +
                "2 - insert data \n" +
                "8 - go back \n" +
                "9 - exit the program \n");
        System.out.println("Choose action");
    }

    private static void readDataActions() {
        System.out.println("Actions: press");
        System.out.println(
                "1 - view full portfolio \n" +
                "2 - view full transactions list \n" +
                "3 - view accounts list \n" +
                "4 - view received dividends \n" +
                "5 - view stock by symbol \n" +
                "6 - view transactions by stock symbol \n" +
                "8 - go back \n" +
                "9 - exit the program \n"
        );
        System.out.println("Choose action");
    }

    private static void insertDataActions() {
        System.out.println("Actions: press");
        System.out.println(
                "1 - insert new account \n" +
                "2 - insert new stock \n" +
                "3 - insert new trade \n" +
                "4 - insert received dividend \n" +
                "5 - insert deposit/withdraw \n" +
                "8 - go back \n" +
                "9 - exit the program \n"
        );
        System.out.println("Choose action");
    }

    private static void insertNewStock() {
        System.out.println("Insert stock symbol");
        String symbol = sc.nextLine();
        System.out.println("Insert stock name");
        String name = sc.nextLine();
        System.out.println("Insert account for stock");
        String account = sc.nextLine();
        System.out.println("Insert stock current price");
        double price = sc.nextDouble();

        stockDAO.insertStock(symbol, name, account, price);
    }

    private static void insertNewTrade() {
        System.out.println("Insert trade type");
        String type = sc.nextLine();
        System.out.println("Insert stock symbol");
        String symbol = sc.nextLine();
        System.out.println("Insert account for trade");
        String account = sc.nextLine();
        System.out.println("Insert trade date");
        String date = sc.nextLine();
        System.out.println("Insert trade amount");
        float amount = sc.nextFloat();
        System.out.println("Insert trade unit price");
        float price = sc.nextFloat();
        System.out.println("Insert trade commission");
        double commission = sc.nextDouble();

        transactionsDAO.insertTrn(type, symbol, account, date, amount, price, commission);


    }
}

