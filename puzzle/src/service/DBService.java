package service;

import java.sql.Connection;
import java.sql.DriverManager;

class DBService {
	// Connection 받는 메소드
	public Connection getConnection() throws Exception {
		// mariadb 드라이버 로딩, db 연결
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/puzzle", "root", "java1234");
		return conn;
	}
}
