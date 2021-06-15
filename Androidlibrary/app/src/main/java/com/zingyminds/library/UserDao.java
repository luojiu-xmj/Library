package com.zingyminds.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public boolean login(String name,String password){

        String sql = "select * from user where name = ? and password = ?";

        Connection  con = MysqlConnection.getConn();
        try {
            PreparedStatement pst=con.prepareStatement(sql);

            pst.setString(1,name);
            pst.setString(2,password);

            if(pst.executeQuery().next()){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            MysqlConnection.close(con);
        }
        return false;
    }

    public boolean register(User user){

        String sql = "insert into user(name,password) values (?,?)";

        Connection  con = MysqlConnection.getConn();

        try {
            PreparedStatement pst=con.prepareStatement(sql);

            pst.setString(1,user.getName());
            pst.setString(2,user.getPassword());

            int value = pst.executeUpdate();

            if(value>0){
                return true;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            MysqlConnection.close(con);
        }
        return false;
    }

    public User findUser(String name){

        String sql = "select * from user where name = ?";

        Connection  con = MysqlConnection.getConn();
        User user = null;
        try {
            PreparedStatement pst=con.prepareStatement(sql);

            pst.setString(1,name);

            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                String namedb = rs.getString(1);
                String passworddb  = rs.getString(2);
                user = new User(namedb,passworddb);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            MysqlConnection.close(con);
        }

        return user;
    }

}