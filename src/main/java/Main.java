
import java.sql.SQLException;
import java.util.*;
public class Main {

    public static void main(String[] args) throws SQLException, InterruptedException {
        Donors dd = new Donors();
        Acceptors aa = new Acceptors();
        // write your code here
        int f=1;
        String str="";
        Scanner s = new Scanner(System.in);
        System.out.println("Hi Welcome to YourBloodBank Org");
        System.out.println("YourBloodBank Org is the fastest growing BloodBank in India.");
        System.out.println("We are here to help you!");
        while(f==1) {
            System.out.println("Are you a Donor or Acceptor?");
            str = s.next();
            switch (str) {
                case "Donor":
                    boolean k = dd.changectrl();
                    f=0;
                    if(!k)
                    {
                        System.out.println("We are waiting for you till you come back to us!!");
                    }
                    else{
                        System.out.println("done!!");
                    }
                    System.out.println("Do you want to exit?(Y/N)");
                    if(s.next().equals("N"))
                    {
                        f=1;
                    }
                    break;
                case "Acceptor":
                    k = aa.changectrl();
                    f=0;
                    if(!k)
                    {
                        System.out.println("We are waiting for you till you come back to us!!");
                    }
                    else{
                        System.out.println("done!!");
                    }
                    System.out.println("Do you want to exit?(Y/N)");
                    if(s.next().equals("N"))
                    {
                        f=1;
                    }
                    break;
                default:
                    System.out.println("Please check your input");
                    break;
            }
        }

        System.out.println("Thank You for trusting YourBloodBank!");

    }
}
