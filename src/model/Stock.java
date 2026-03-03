package model;

import java.io.Serializable;

public class Stock implements Serializable {
    private String symbol;
    private double price;
    private int availableQuantity;

    public Stock(String symbol,double price,int availableQuantity){
        this.symbol=symbol;
        this.price=price;
        this.availableQuantity=availableQuantity;
    }

    public void updatePrice(){
        double change=(Math.random()-0.5)*0.1; // ±5%
        price+=price*change;
        if(price<1) price=1;
    }

    public String getSymbol(){return symbol;}
    public double getPrice(){return price;}
    public int getAvailableQuantity(){return availableQuantity;}

    public void reduceQuantity(int qty){availableQuantity-=qty;}
    public void increaseQuantity(int qty){availableQuantity+=qty;}

    @Override
    public String toString(){
        return symbol+" | ₹"+String.format("%.2f",price)+" | Available: "+availableQuantity;
    }
}