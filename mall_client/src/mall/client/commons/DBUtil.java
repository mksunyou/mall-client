package mall.client.commons;

import java.sql.*;

public class DBUtil {
	//	1. DB 연결
	public Connection getConnection() {
		Connection conn = null;
		try { //이 두 영역을 실행하다가 예외가 나면 catch절로 진행.
			//Class.forName("org.mariadb.jdbc.Driver"); Listener를 통해 바로 시작.
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/mall","root","java1004");
		} catch(Exception e) {
			e.printStackTrace(); //예외가 나면 예외 메세지를 출력
		}
		
		return conn;
	}
	//	2. DB 자원(conn, stmt, rs) 해제
	public void close(ResultSet rs, PreparedStatement stmt,Connection conn ) { //해제될 순서.
		if(rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}


		}
		if(conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if(stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}