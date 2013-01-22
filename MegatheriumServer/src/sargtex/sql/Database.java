/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sargtex.sql;

import java.sql.*;
import megatherium.util.ReportUtil;

/**
 *
 * @author Pathos
 */
public class Database {

	private static Database instance;
	private Connection connection;
	private String databaseName = "patrickthoma";
	private String username = "root";
	private String password = "AjXVFh9uNHmLX4hrPugp";
	private String host = "localhost:3306";

	/**
	 * Makes the constructor private to enable the singleton pattern. The
	 * constructor loads the sql driver.
	 */
	private Database() {
		try {
			// load driver
			Class.forName ("com.mysql.jdbc.Driver").newInstance(); 
		} catch (Exception ex) {
			ReportUtil.getInstance().add(ex);
		}
	}
	
	/**
	 * Returns the connection.
	 * If the connection is closed, a new one is opened.
	 * 
	 * @return the connection
	 */
	protected Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + databaseName, username, password);
			}
			
			return connection;
		} catch (SQLException ex) {
			ReportUtil.getInstance().add(ex);
		}
		
		// error occured - return null
		return null;
	}

	/**
	 * Returns the current instance of the database object. If there is none,
	 * one will be created and returned.
	 *
	 * @return the database object
	 */
	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}

		return instance;
	}

	/**
	 * Returns a result set after executing that query.
	 *
	 * @param query the executing query
	 * @return the result set
	 */
	public ResultSet fetch(String query) throws SQLException {
		Statement statement = getConnection().createStatement();
		return statement.executeQuery(query);
	}
	
	/**
	 * Returns a result set after executing the prepared statement.
	 * 
	 * @param query the executing query
	 * @param params the parameters, used for the prepared statement
	 * @return the result set
	 */
	public ResultSet fetch(String query, String[] params) throws SQLException {
		PreparedStatement statement = getConnection().prepareStatement(query);
		
		// set parameters
		if (params != null) {
			for (int i = 0; i < params.length; ++i) {
				statement.setString(i+1, params[i]);
			}
		}
		
		// return result list
		return statement.executeQuery();
	}

	/**
	 * Executes a manipulating query, e.g. containing INSERT, UPDATE or DELETE
	 * statements.
	 *
	 * @param query the query
	 * @param params the parameters, used for the prepared statement
	 * @return the last inserted key.
	 */
	public int execute(String query, String[] params) throws SQLException {
		PreparedStatement statement = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

		// set parameters
		if (params != null) {
			for (int i = 0; i < params.length; ++i) {
				statement.setString(i+1, params[i]);
			}
		}

		// execute statement
		ReportUtil.getInstance().add("Executing MySQL query: "+query);
		statement.executeUpdate();
		
		// fetch keys and return first one
		ResultSet keys = statement.getGeneratedKeys();
		if (keys.first()) return keys.getInt(1);
		return -1;
	}
}
