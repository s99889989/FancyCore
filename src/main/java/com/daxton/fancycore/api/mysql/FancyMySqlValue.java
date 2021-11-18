package com.daxton.fancycore.api.mysql;

import java.sql.*;

public class FancyMySqlValue {

	//登入資料庫帳號
	final String user_name;
	//登入資料庫密碼
	final String pass_word;
	//發送的URL
	final String data_base_url;

	//連線狀態
	boolean enable = false;

	public FancyMySqlValue(String user_name, String pass_word, String data_base_url) {
		this.user_name = user_name;
		this.pass_word = pass_word;
		this.data_base_url = data_base_url;
	}

	public String getString(MySqlComponent mySqlComponent, String key) throws SQLException {
		String value = "";
		if(enable){
			Connection connection = DriverManager.getConnection(data_base_url, user_name, pass_word);

			String executeString = "Select "+key+" From "+mySqlComponent.table+" Where "+mySqlComponent.mainKey+"="+mySqlComponent.mainValue;
			PreparedStatement preparedStatement = connection.prepareStatement(executeString);

			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()){
				value = rs.getString(key);
			}

			connection.close();
		}
		return value;
	}

	public boolean getBoolean(MySqlComponent mySqlComponent, String key) throws SQLException {
		boolean value = false;
		if(enable){
			Connection connection = DriverManager.getConnection(data_base_url, user_name, pass_word);

			String executeString = "Select "+key+" From "+mySqlComponent.table+" Where "+mySqlComponent.mainKey+"="+mySqlComponent.mainValue;
			PreparedStatement preparedStatement = connection.prepareStatement(executeString);

			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()){
				value = rs.getBoolean(key);
			}

			connection.close();
		}
		return value;
	}

	public byte getByte(MySqlComponent mySqlComponent, String key) throws SQLException {
		byte value = 0;
		if(enable){
			Connection connection = DriverManager.getConnection(data_base_url, user_name, pass_word);

			String executeString = "Select "+key+" From "+mySqlComponent.table+" Where "+mySqlComponent.mainKey+"="+mySqlComponent.mainValue;
			PreparedStatement preparedStatement = connection.prepareStatement(executeString);

			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()){
				value = rs.getByte(key);
			}

			connection.close();
		}
		return value;
	}

	public short getShort(MySqlComponent mySqlComponent, String key) throws SQLException {
		short value = 0;
		if(enable){
			Connection connection = DriverManager.getConnection(data_base_url, user_name, pass_word);

			String executeString = "Select "+key+" From "+mySqlComponent.table+" Where "+mySqlComponent.mainKey+"="+mySqlComponent.mainValue;
			PreparedStatement preparedStatement = connection.prepareStatement(executeString);

			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()){
				value = rs.getShort(key);
			}

			connection.close();
		}
		return value;
	}

	public int getInt(MySqlComponent mySqlComponent, String key) throws SQLException {
		int value = 0;
		if(enable){
			Connection connection = DriverManager.getConnection(data_base_url, user_name, pass_word);

			String executeString = "Select "+key+" From "+mySqlComponent.table+" Where "+mySqlComponent.mainKey+"="+mySqlComponent.mainValue;
			PreparedStatement preparedStatement = connection.prepareStatement(executeString);

			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()){
				value = rs.getInt(key);
			}

			connection.close();
		}
		return value;
	}

	public long getLong(MySqlComponent mySqlComponent, String key) throws SQLException {
		long value = 0;
		if(enable){
			Connection connection = DriverManager.getConnection(data_base_url, user_name, pass_word);

			String executeString = "Select "+key+" From "+mySqlComponent.table+" Where "+mySqlComponent.mainKey+"="+mySqlComponent.mainValue;
			PreparedStatement preparedStatement = connection.prepareStatement(executeString);

			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()){
				value = rs.getLong(key);
			}

			connection.close();
		}
		return value;
	}

	public float getFloat(MySqlComponent mySqlComponent, String key) throws SQLException {
		float value = 0;
		if(enable){
			Connection connection = DriverManager.getConnection(data_base_url, user_name, pass_word);

			String executeString = "Select "+key+" From "+mySqlComponent.table+" Where "+mySqlComponent.mainKey+"="+mySqlComponent.mainValue;
			PreparedStatement preparedStatement = connection.prepareStatement(executeString);

			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()){
				value = rs.getFloat(key);
			}

			connection.close();
		}
		return value;
	}

	public double getDouble(MySqlComponent mySqlComponent, String key) throws SQLException {
		double value = 0;
		if(enable){
			Connection connection = DriverManager.getConnection(data_base_url, user_name, pass_word);

			String executeString = "Select "+key+" From "+mySqlComponent.table+" Where "+mySqlComponent.mainKey+"="+mySqlComponent.mainValue;
			PreparedStatement preparedStatement = connection.prepareStatement(executeString);

			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()){
				value = rs.getDouble(key);
			}

			connection.close();
		}
		return value;
	}

}
