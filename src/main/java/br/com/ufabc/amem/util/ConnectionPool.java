package br.com.ufabc.amem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ConnectionPool {

	// TODO should exceptions be treated here?
	private static String ip;
	private static String port;
	private static String base;
	private static String username;
	private static String password;
	private static ConnectionPool instance;
	private static int INITIAL_POOL_SIZE = 5;
	private static List<Connection> connectionPool;
	private List<Connection> usedConnections = new ArrayList<>();

	private ConnectionPool() {
		
	}

	static {

		instance       = new ConnectionPool();
		connectionPool = new ArrayList<>(INITIAL_POOL_SIZE);
			
		ResourceBundle resourceBundle = ResourceBundle.getBundle("properties.db");

		ip       = resourceBundle.getString("jdbc.ip");
		port     = resourceBundle.getString("jdbc.port");
		username = resourceBundle.getString("jdbc.username");
		password = resourceBundle.getString("jdbc.password");
		base     = resourceBundle.getString("jdbc.base");

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

			System.out.println("JDBC Driver not found");
			System.out.println(e.getMessage());
		}

		try {

			for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
				
				Connection connection = DriverManager.getConnection(getUrl(), username, password);
				connectionPool.add(connection);
				
				if (connection == null) {

					throw new SQLException();
				} 
			}

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			System.out.println(e.getMessage());
		}
	}

	public static ConnectionPool getInstance() {

		return instance;
	}

	public Connection getConnection() {

		Connection connection = connectionPool.remove(connectionPool.size() - 1);
		usedConnections.add(connection);
		return connection;
	}

	public void releaseConnection(Connection connection) {

		connectionPool.add(connection);
		usedConnections.remove(connection);
	}

	public static String getUrl() {

		return "jdbc:oracle:thin:@" + ip + ":" + port + ":" + base;
	}

	public String getPassword() {
		return password;
	}

	public int getSize() {
		return connectionPool.size() + usedConnections.size();
	}

	public String getIp() {
		return ip;
	}

	public static void setIp(String ipNew) {
		ip = ipNew;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String portNew) {
		port = portNew;
	}

	public String getUser() {
		return username;
	}

	public static void setUser(String usernameNew) {
		username = usernameNew;
	}

	public String getPass() {
		return password;
	}

	public static void setPassword(String passwordNew) {
		password = passwordNew;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String baseNew) {
		base = baseNew;
	}
}