package DAO;


import Util.DBConnection;

import java.sql.*;


public class UserDAO {

    public boolean registerUser(String email , String full_name , String password) {
        if (userExists(email)) {
            return false;
        }
        String query = "Insert into users(email , full_name , password) values (? , ? , ?)";
        Connection com =null;
        PreparedStatement ps = null;
        try {
            com = DBConnection.getConnection();
            ps = com.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, full_name);
            ps.setString(3, password);

            int rs = ps.executeUpdate();
            return rs>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try{
                if (ps != null) ps.close();
                if (com != null) com.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public boolean userExists(String email){
        String query = "Select * from users where email = ?";
        Connection com = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            com = DBConnection.getConnection();
            ps = com.prepareStatement(query);
            ps.setString(1,email);
            rs = ps.executeQuery();

            if (rs.next()){
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try{
                if (rs !=null) rs.close();
                if (ps !=null) ps.close();
                if (com !=null) com.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

        public boolean loginUser(String email, String password){
            String Query = "select * from users where email = ? AND password = ?";
            try{
                Connection com = DBConnection.getConnection();
                PreparedStatement ps = com.prepareStatement(Query);
                ps.setString(1,email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();

                while (rs.next()){
                    return  true;
                }
                com.close();
                ps.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return false;

        }

}
