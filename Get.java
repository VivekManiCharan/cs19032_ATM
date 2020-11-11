package com.company;

import java.sql.*;
import java.util.Date;
import java.time.format.DateTimeFormatter;

public class Get {

    public String GetName(String AccountNumber) {
        Connection conn = DbConnection.connect();
        ResultSet rs = null;

        String tempo = null;

        try {
            String sql = "Select  Name from Accounts where AccountNumber =  " + AccountNumber;

            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            tempo = rs.getString("Name");


        } catch (SQLException e) {
            tempo = null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("qwerty");
            }
        }
        return tempo;


    }

    public long GetBalance(String AccountNumber) {
        Connection conn = DbConnection.connect();
        ResultSet rs = null;

        long tempo = 0;
        System.err.println(AccountNumber);

        try {
            String sql = "Select  Balance from Accounts where AccountNumber =  "+ AccountNumber;

            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            tempo = rs.getLong("Balance");


        } catch (SQLException e) {
            System.out.println("Invalid Balance");
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("qwerty");
            }
        }
        return tempo;


    }

    public String GetPin(String AccountNumber) {
        Connection conn = DbConnection.connect();
        ResultSet rs = null;

        String tempo = null;

        try {
            String sql = "Select  Pin from Accounts where AccountNumber =  " + AccountNumber;

            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            tempo = rs.getString("Pin");


        } catch (SQLException e) {
            System.out.println("Invalid Pin");
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("qwerty");
            }
        }
        return tempo;


    }


    public void Update(String a, Long Balance) throws SQLException {
        Connection conn = DbConnection.connect();



        long tempo = 0;

        try {
            String sql = "UPDATE Accounts Set Balance = "+Balance+ " WHERE AccountNumber = " + a;
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("qwerty");
            }
        }


    }
    public void ChangePin(String a, String Pin) throws SQLException {
        Connection conn = DbConnection.connect();

        try {
            String sql = "UPDATE Accounts Set Pin = '"+ Pin +"'"+ "  where AccountNumber = " + a;
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("qwerty");
            }
        }


    }
    public void insert(String Name ,long Amount,String Type) {

        Connection  conn = DbConnection.connect();
        Date date = new Date();
        try{
            String sql = "INSERT INTO " +Name+"( Type, Amount,Date) VALUES(?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Type);
            pstmt.setLong(2, Amount);
            pstmt.setString(3, date.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public void Transactions(String Name){
        Connection  conn = DbConnection.connect();
        try{
            String sql = "Select * FROM "+Name;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            System.out.println("\nType        Amount      Date\n");
            while(rs.next()){
                String Type = rs.getString("Type");
                long Amount = rs.getLong("Amount");
                String Date = rs.getString("Date");

                System.out.println(Type+"     "+Amount+ "    "+ Date);
            }
        }catch (SQLException e){
            System.out.println(e.toString());
        }
    }
    public  int  TransactionCount(String Name) throws SQLException {
        Connection  conn = DbConnection.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT count(*) from "+Name);
        rs.next();
        return rs.getInt("count(*)");

    }
    public  String GetRow(String Name) throws SQLException {
        String str = null;
        Connection  conn = DbConnection.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * from "+Name+ " ORDER BY id DESC LIMIT 1;");
        if(rs.next()){
            String  Type = rs.getString("Type");
            long Amount = rs.getLong("Amount");
            String Date = rs.getString("Date");
            str = Type+Amount+"  "+Date;
        }

        return  str;
    }
}



