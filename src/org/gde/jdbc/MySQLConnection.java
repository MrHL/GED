package org.gde.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.gde.interfaces.IDBConnection;

public class MySQLConnection implements IDBConnection{
	
	private final String driver = "com.mysql.jdbc.Driver";
	private String ip = "localhost";
	private String port = "3306";
	private String dbInstance = "test";
	private String dbUrl = "jdbc:mysql://"+ip+":"+port+"/"+dbInstance+"?charset=utf-8";
	private String user = "";
	private String pass = "";

	public MySQLConnection(String ip,String user,String pass){
		this.ip = ip;
		this.user = user;
		this.pass = pass;
	}
	@Override
	public Connection getConnInstance(String ip, String user, String pass) {
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(dbUrl,user,pass);
			if(conn!=null)
				return conn;
			else
				throw new SQLException("connection object is null");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Connection getConnInstance() {
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(dbUrl,user,pass);
			if(conn!=null)
				return conn;
			else
				throw new SQLException("connection object is null");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}	

}
