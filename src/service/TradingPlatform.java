package service;

import model.*;
import java.util.*;

public class TradingPlatform {

    private List<Stock> market;
    private User user;

    public TradingPlatform(User user){
        this.user=user;
        this.market=new ArrayList<>();
        initMarket();
    }

    private void initMarket(){
        market.add(new Stock("AAPL",180,1000));
        market.add(new Stock("TSLA",250,1000));
        market.add(new Stock("INFY",1500,1000));
    }

    public void updateMarket(){
        for(Stock s:market) s.updatePrice();
    }

    public void showMarket(){
        updateMarket();
        for(Stock s:market) System.out.println(s);
    }

    private Stock findStock(String symbol){
        for(Stock s:market){
            if(s.getSymbol().equalsIgnoreCase(symbol)) return s;
        }
        return null;
    }

    public void buy(String symbol,int qty){
        Stock s=findStock(symbol);
        if(s==null){System.out.println("Stock not found");return;}
        if(qty>s.getAvailableQuantity()){System.out.println("Not enough stock available");return;}

        double cost=qty*s.getPrice();
        if(cost>user.getBalance()){System.out.println("Insufficient balance");return;}

        user.debit(cost);
        user.addStock(symbol,qty);
        s.reduceQuantity(qty);
        user.getTransactions().add(new Transaction(symbol,qty,s.getPrice(),"BUY"));

        System.out.println("Stock bought successfully");
    }

    public void sell(String symbol,int qty){
        if(!user.getPortfolio().containsKey(symbol)){
            System.out.println("You don't own this stock");
            return;
        }

        int owned=user.getPortfolio().get(symbol);
        if(qty>owned){System.out.println("Not enough shares");return;}

        Stock s=findStock(symbol);
        double value=qty*s.getPrice();

        user.credit(value);
        user.removeStock(symbol,qty);
        s.increaseQuantity(qty);
        user.getTransactions().add(new Transaction(symbol,qty,s.getPrice(),"SELL"));

        System.out.println("Stock sold successfully");
    }

    public void showPortfolio(){
        System.out.println("Balance: ₹"+user.getBalance());
        for(String sym:user.getPortfolio().keySet()){
            int qty=user.getPortfolio().get(sym);
            Stock s=findStock(sym);
            double currentValue=qty*s.getPrice();
            System.out.println(sym+" | Qty: "+qty+" | Current Value: ₹"+currentValue);
        }
    }

    public void showTransactions(){
        for(Transaction t:user.getTransactions()) System.out.println(t);
    }
}