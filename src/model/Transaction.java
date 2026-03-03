package model;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {
    private String stockSymbol;
    private int quantity;
    private double price;
    private String type;
    private LocalDateTime timestamp;

    public Transaction(String stockSymbol,int quantity,double price,String type){
        this.stockSymbol=stockSymbol;
        this.quantity=quantity;
        this.price=price;
        this.type=type;
        this.timestamp=LocalDateTime.now();
    }

    @Override
    public String toString(){
        return type+" | "+stockSymbol+" | Qty: "+quantity+" | ₹"+price+" | "+timestamp;
    }
}