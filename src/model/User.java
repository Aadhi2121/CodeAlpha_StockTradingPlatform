package model;
import model.Transaction;
import java.io.Serializable;
import java.util.*;

public class User implements Serializable {

    private String name;
    private double balance;
    private Map<String,Integer> portfolio;
    private List<Transaction> transactions;

    public User(String name,double balance){
        this.name=name;
        this.balance=balance;
        this.portfolio=new HashMap<>();
        this.transactions=new ArrayList<>();
    }

    public String getName(){return name;}
    public double getBalance(){return balance;}
    public Map<String,Integer> getPortfolio(){return portfolio;}
    public List<Transaction> getTransactions(){return transactions;}

    public void debit(double amount){balance-=amount;}
    public void credit(double amount){balance+=amount;}

    public void addStock(String symbol,int qty){
        portfolio.put(symbol,portfolio.getOrDefault(symbol,0)+qty);
    }

    public void removeStock(String symbol,int qty){
        int current=portfolio.get(symbol);
        if(current==qty) portfolio.remove(symbol);
        else portfolio.put(symbol,current-qty);
    }
}