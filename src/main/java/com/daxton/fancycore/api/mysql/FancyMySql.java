package com.daxton.fancycore.api.mysql;

import com.daxton.fancycore.FancyCore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FancyMySql extends FancyMySqlValue {
	//登入資料庫帳號
	final private String user_name;
	//登入資料庫密碼
	final private String pass_word;
	//發送的URL
	final private String data_base_url;

	public FancyMySql(String host, int port, String data_base, String user_name, String pass_word) {
		super(user_name, pass_word, "jdbc:mysql://"+host+ ":" + port + "/" + data_base + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
		this.user_name = user_name;
		this.pass_word = pass_word;
		data_base_url = "jdbc:mysql://"+host+ ":" + port + "/" + data_base + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	}

	//連線
	public void connect () throws ClassNotFoundException, SQLException {
		//驅動
		String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		Class.forName(JDBC_DRIVER);
		Connection connection = DriverManager.getConnection(data_base_url, user_name, pass_word);
		connection.close();
		enable = true;
	}
	//在資料庫內建立表單
	public void createTable(MySqlComponent mySqlComponent) throws SQLException {
		if(!enable){
			return;
		}
		Connection connection = DriverManager.getConnection(data_base_url, user_name, pass_word);

		PreparedStatement preparedStatement = connection.prepareStatement(mySqlComponent.getCreateString());

		preparedStatement.execute();

		connection.close();
	}
	//在表單內插入新的值
	public void insertNew(MySqlComponent mySqlComponent) throws SQLException {
		if(!enable){
			return;
		}
		Connection connection = DriverManager.getConnection(data_base_url, user_name, pass_word);

		PreparedStatement preparedStatement = connection.prepareStatement(mySqlComponent.getInsertString());

		preparedStatement.execute();

		connection.close();
	}
	//更新表單內的值
	public void update(MySqlComponent mySqlComponent) throws SQLException {
		if(!enable){
			return;
		}
		Connection connection = DriverManager.getConnection(data_base_url, user_name, pass_word);

		PreparedStatement preparedStatement = connection.prepareStatement(mySqlComponent.getUpdateString());

		preparedStatement.execute();

		connection.close();
		FancyCore.sendLogger("更新資料庫!");
	}

	//建立
	public static class FancyMySqlBuilder{

		//位址
		String host = "localhost";
		//Port
		int port = 3306;
		//資料庫名稱
		String data_base = "fancy_core";
		//登入資料庫帳號
		String user_name = "";
		//登入資料庫密碼
		String pass_word = "";

		public static FancyMySqlBuilder getInstance(){
			return new FancyMySqlBuilder();
		}

		public FancyMySqlBuilder setHost(String host) {
			this.host = host;
			return this;
		}

		public FancyMySqlBuilder setPort(int port) {
			this.port = port;
			return this;
		}

		public FancyMySqlBuilder setDataBase(String data_base) {
			this.data_base = data_base;
			return this;
		}

		public FancyMySqlBuilder setUserName(String user_name) {
			this.user_name = user_name;
			return this;
		}

		public FancyMySqlBuilder setPassWord(String pass_word) {
			this.pass_word = pass_word;
			return this;
		}

		public FancyMySql build(){
			return new FancyMySql(host, port, data_base, user_name, pass_word);
		}

	}


}
