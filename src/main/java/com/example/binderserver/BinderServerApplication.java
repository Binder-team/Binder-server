package com.example.binderserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//import javax.persistence.EntityManagerFactory;

import java.sql.Connection;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication //(exclude = {DataSourceAutoConfiguration.class})
public class BinderServerApplication {

	public static void main(String[] args) {
		DBFunctions db=new DBFunctions();
		Connection conn=db.connect_to_db("binderdb","postgres","helloworld");
		//db.createTable(conn,"user_info");
		db.insert_row(conn,"user_info","gardocaves","Kawasaki","14134","08067861944",1,false);
	}
}
