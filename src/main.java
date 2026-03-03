import model.User;
import service.TradingPlatform;
import util.FileManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner sc=new Scanner(System.in);
        String filePath="data/users.ser";

        User user=(User)FileManager.loadUser(filePath);
        if(user==null){
            user=new User("Aadhi",100000);
        }

        TradingPlatform platform=new TradingPlatform(user);

        while(true){
            System.out.println("\n1.View Market\n2.Buy\n3.Sell\n4.Portfolio\n5.Transactions\n6.Exit");
            int choice=sc.nextInt();

            switch(choice){
                case 1: platform.showMarket(); break;
                case 2:
                    System.out.print("Symbol: ");
                    String sym=sc.next();
                    System.out.print("Qty: ");
                    int qty=sc.nextInt();
                    platform.buy(sym,qty);
                    break;
                case 3:
                    System.out.print("Symbol: ");
                    sym=sc.next();
                    System.out.print("Qty: ");
                    qty=sc.nextInt();
                    platform.sell(sym,qty);
                    break;
                case 4: platform.showPortfolio(); break;
                case 5: platform.showTransactions(); break;
                case 6:
                    FileManager.saveUser(user,filePath);
                    System.out.println("Data saved. Exiting...");
                    return;
            }
        }
    }
}