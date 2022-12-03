package com.example.binderserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBFunctions {
    public Connection connect_to_db(String dbname, String username, String password){
        Connection conn=null;
        try {
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,username,password);
            if (conn !=null){
                System.out.println("Connection Established");
            } else {
                System.out.println("Connection Failed");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return conn;
    }
    public void createTable(Connection conn, String table_name) {
        Statement statement;
        try{
            String query="create table "+table_name+" (id SERIAL,username varchar(200),city varchar(200),postal_code varchar(200),phone_number varchar(200),reputation float,isBanned boolean,primary key(id));";
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created!");
        }catch (Exception e) {
            System.out.println(e + "🔥");
        }
    }

    public void insert_row(Connection conn, String table_name,String username, String city, String postal_code, String phone_number, float reputation, boolean isBanned){
        Statement statement;
        try{
            String query=String.format("insert into %s(username,city,postal_code,phone_number,reputation,isBanned) values ('%s','%s');",table_name,username,city,postal_code,phone_number,reputation,isBanned);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Inserted!");
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
