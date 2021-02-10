
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.sql.*;
public class Donors {
    Connect con = new Connect();
    Scanner s = new Scanner(System.in);

    String name;
    long MobNo;
    String city;
    String dist;
    String state;
    int mpin;
    String bloodgrp;
    boolean elgi=false,k=false;
    public boolean changectrl() throws SQLException, InterruptedException {
        Connection conn=con.setconnection();
        System.out.println("Are you a new member?(Y/N)");
        if(!s.next().equals("Y"))
        {
            return updateprofile(conn);
        }
        System.out.println("Being a donor you choose to donate blood(Y/N)?");
        if(!s.next().equals("Y"))
        {
            return false;
        }
        System.out.println("Before that you need to register yourself here...");
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
        System.out.println("What is your mobile number?");
        MobNo = s.nextLong();
        System.out.println("What is the last time you donated your blood(in months)?");
        if(s.nextInt()>6)
        {
            elgi = true;
        }
        System.out.println("Are you suffering from a communicable disease(Y/N)?");
        if(s.next().equals("N"))
        {
            elgi = true;
        }
        else{
            elgi = false;
        }
        System.out.println("Set your MPin _ _ _ _");
        mpin = s.nextInt();
        System.out.println("Congratulations!! You finally created your profile in YourBloodBank");
        if(elgi)
        {

            if(conn!=null)
            {
                System.out.println("connected!");
            }
            else {
                System.out.println("connection failed!");
                return false;
            }
            k=insertprofile(conn);
            printprofile();
        }
        return true;
    }

    public boolean updateprofile(Connection conn) throws SQLException {
        System.out.println("Enter your mobile number 10 digit");
        long mob = s.nextLong();
        String sql = "SELECT * FROM donors where mobno="+Long.toString(mob);
        int i=0,f=1;
        Statement smt = conn.createStatement();
        ResultSet r = smt.executeQuery(sql);
        while(r.next()) {
            name = r.getString("name");
            MobNo = r.getLong("mobno");
            city = r.getString("city");
            dist = r.getString("district");
            state = r.getString("state");
            mpin = r.getInt("mpin");
        }

        for(int j=0;j<3;j++) {
            System.out.println("enter your mpin");
            i = s.nextInt();
            if (i != mpin) {
                System.out.println("wrong mpin :(");
            }
            else{
                break;
            }
        }
        if(i != mpin)
        {
            System.out.println("Login failed");
            return false;
        }
        while(f==1) {
            System.out.println("What u want to update? name/mpin/state/city/district/mobno/nothing");
            switch (s.next()) {
                case "mpin":
                    System.out.println("Enter new MPIN");
                    mpin = s.nextInt();
                    break;
                case "name":
                    System.out.println("Enter new NAME");
                    name = s.next();
                    break;
                case "mobno":
                    System.out.println("Enter new MOBILE NO");
                    MobNo = s.nextLong();
                    break;
                case "state":
                    System.out.println("Enter new STATE");
                    state = s.next();
                    break;
                case "district":
                    System.out.println("Enter new DISTRICT");
                    dist = s.next();
                    break;
                case "city":
                    System.out.println("Enter new CITY");
                    city = s.next();
                    break;
                default:
                    f = 0;
                    break;
            }
        }
        System.out.println("UPDATING NOW...");
        sql = "UPDATE donors SET mobno="+Long.toString(MobNo)+",name='"+name+"',mpin="+Integer.toString(mpin)+",state='"+state+"',district='"+dist+"',city='"+city+"'";
        return smt.execute(sql);
    }

    public boolean insertprofile(Connection conn) throws SQLException {
        String sql = "insert into donors values ('"+name+"',"+Long.toString(MobNo)+",'"+state+"','"+city+"','"+dist+"','"+bloodgrp+"',"+Integer.toString(mpin)+")";
        Statement smt = conn.createStatement();
        return smt.execute(sql);
    }

    public void printprofile()
    {
        System.out.println("DONOR PROFILE");
        System.out.println("-------------------------");
        System.out.println("Name:"+name);
        System.out.println("State:"+state);
        System.out.println("District:"+dist);
        System.out.println("City:"+city);
        System.out.println("Blood Group:"+bloodgrp);
        System.out.println("Mobile Number:"+MobNo);
    }

}
