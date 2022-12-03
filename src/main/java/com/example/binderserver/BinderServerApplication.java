package com.example.binderserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication //(exclude = {DataSourceAutoConfiguration.class})
public class BinderServerApplication {

	public static void main(String[] args) {
		DBFunctions db=new DBFunctions();
		Connection conn=db.connect_to_db("binderdb","postgres","helloworld");
		db.create_user_info_table(conn, "user_info");
		db.create_user_books_table(conn,"user_books");
		db.create_reputation_table(conn, "reputation");
		db.create_trade_table(conn,"trade");
		db.insert_row(conn,"user_info","gardocaves","Kawasaki","14134","08067861944",1,false);
	}
}
