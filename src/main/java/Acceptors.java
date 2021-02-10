import java.sql.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class Acceptors {
    Connect con = new Connect();
    Scanner s = new Scanner(System.in);
    String name;
    long MobNo;
    String city;
    String dist;
    String state;
    String bloodgrp;
    boolean k=false;


    public boolean changectrl() throws InterruptedException, SQLException {
        Connect con = new Connect();
        System.out.println("Hi Welcome to YourBloodBank Org");
        System.out.println("YourBloodBank Org is the fastest growing BloodBank in India.");
        System.out.println("We are here to help you!");
        System.out.println("Before we show you list of donors you need to register yourself here...");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("What is your good name");
        name = s.next();
        System.out.println("What is your city?");
        city = s.next();
        System.out.println("What is your district?");
        dist = s.next();
        System.out.println("What is your state?");
        state = s.next();
        System.out.println("What is your bloodgroup?");
        bloodgrp = s.next();
        Connection conn=con.setconnection();
        if(conn!=null)
        {
            System.out.println("connected!");
        }
        else {
            System.out.println("connection failed!");
            return false;
        }
        k=fetchdata(conn);
        if(k)
        {
            return true;
        }
        return false;
    }

    public boolean fetchdata(Connection conn) throws SQLException {
        String sa;
        long mo;
        int co = 1;
        String sql1 = "Select name,mobno from donors where bloodgrp='" + bloodgrp + "' and city='" + city + "' union Select name,mobno from donors where bloodgrp='" + bloodgrp + "' and district='" + dist + "' union Select name,mobno from donors where bloodgrp='" + bloodgrp + "' and state='" + state + "'";
        System.out.println(sql1);
        Statement smt = conn.createStatement();
        ResultSet rs1 = smt.executeQuery(sql1);
        int n=0,j=1;
        System.out.println("Below are the ones nearest to you:");
        while (rs1.next()) {
            sa = rs1.getString("name");
            mo = rs1.getLong("mobno");
            System.out.println(co + ":" + sa +"   "+mo);
            co++;
        }


        if (rs1 != null){
            System.out.println("Send message to the following donors and ask them to update the status as well");

        }

        System.out.println("Congratulations we hope u will get your donor! Please wait for them to respond");
        return true;
    }


}
