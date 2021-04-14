package mall.client.model;

import java.sql.*;

import mall.client.commons.DBUtil;
import mall.client.vo.Client;

public class ClientDao {//dao의 모든 메소드는 DBUtil이 필요하다.
	private DBUtil dbUtil;

	//회원가입
	public int insertClient(Client client) {
		this.dbUtil = new DBUtil();
		int rowCnt = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		//예외 발생 안했을 시 try 진행
		try {
			conn = this.dbUtil.getConnection();
			String sql = "INSERT INTO client(client_mail, client_pw, client_date) VALUES(?,PASSWORD(?),now())";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, client.getClientMail());
			stmt.setString(2, client.getClientPw());
			
			//디버깅
			System.out.println("insertClient stmt : " + stmt);
			
			//쿼리 실행
			rowCnt = stmt.executeUpdate();
		
			//예외 발생시 catch 진행
		} catch (Exception e){
			e.printStackTrace(); //예외 발생 에러코드 출력
			
			//무조건 실행.
		} finally {
			this.dbUtil.close(null, stmt, conn); //연결 제거
		}
		//반환.
		return rowCnt; //입력 성공시 1, 실패시 0
	}
	
	//회원가입 Mail 중복검사
	public String selectClientMail(String clientMail) {
		this.dbUtil = new DBUtil();
		String returnClientMail = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dbUtil.getConnection();
			String sql = "SELECT client_mail FROM client WHERE client_mail = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, clientMail);
			
			//디버깅
			System.out.println("selectClientMail stmt : " + stmt);
			
			//쿼리실행
			rs = stmt.executeQuery();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.dbUtil.close(rs, stmt, conn); // 연결 제거
		}
		//반환
		return returnClientMail;
	}
	
	public Client login(Client client) {
		this.dbUtil = new DBUtil();
		Client returnClient = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.dbUtil.getConnection();
			String sql = "SELECT client_mail clientMail FROM client WHERE client_mail=? AND client_pw=PASSWORD(?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, client.getClientMail());
			stmt.setString(2, client.getClientPw());
			System.out.println("login stmt : "+stmt); //디버깅
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnClient = new Client();
				returnClient.setClientMail(rs.getString("clientMail"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(rs, stmt, conn);
		}
		return returnClient;
	}
}
