package DAO;

import Util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
    public boolean createAccount(String email, String pin) {
        if (pin.length() != 4 || !pin.matches("\\d+")) {
            System.out.println("PIN must be 4 digits");
            return false;
        }
        if (accountExists(email)) {
            return false;
        }
        String query = "INSERT INTO accounts(email, security_pin) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, email);
            ps.setString(2, pin);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean accountExists(String email){
        String Query = "Select * from accounts where email = ?";
        Connection com = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            com = DBConnection.getConnection();
            ps = com.prepareStatement(Query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {

                if (com != null) com.close();
                if (ps != null) ps.close();
                if (rs != null)rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
    public double showBalance(String pin , String email)  {
        String Query = "Select balance from accounts where security_pin = ? and email = ? ";
        Connection com = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
             com = DBConnection.getConnection();
             ps = com.prepareStatement(Query);
            ps.setString(1,pin);
            ps.setString(2,email);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getDouble("balance");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            assert rs != null;
            try {
                rs.close();
                com.close();
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return -1;
    }
    public boolean depositMoney(String pin , String email , double amount){
        String Query = "Update accounts  Set balance = balance + ?  where security_pin = ? AND email = ? ";
        Connection com = null;
        PreparedStatement ps = null;
        try {
            com = DBConnection.getConnection();
            ps = com.prepareStatement(Query);
            ps.setDouble(1,amount);
            ps.setString(2,pin);
            ps.setString(3,email);
            int rs = ps.executeUpdate();
            if (rs>0){
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if (com != null) com.close();
                if (ps !=null) ps.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
    public boolean withdraw(String pin , String email , double amount) {
        String Query = "Update accounts  Set balance = balance - ?  where security_pin = ? AND email = ? ";
        Connection com = null;
        PreparedStatement ps = null;
        try {
            com = DBConnection.getConnection();
            ps = com.prepareStatement(Query);
            ps.setDouble(1, amount);
            ps.setString(2, pin);
            ps.setString(3, email);
            int rs = ps.executeUpdate();
            if (rs > 0) {
               return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
