package mall.client.model;

import java.sql.*;

import mall.client.commons.DBUtil;
import mall.client.vo.Client;

public class ClientDao {//dao의 모든 메소드는 DBUtil이 필요하다.
	private DBUtil dbUtil;

	//회원탈퇴
	public void deleteClient(String clientMail) {
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			// sql
			String sql = "DELETE FROM client WHERE client_mail = ?";
			// db처리
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, clientMail);

			//디버깅
			System.out.println(stmt+" <-- ClientDao에서 deleteClient()의 stmt");

			//삭제 실행
			stmt.executeUpdate();

		} catch (Exception e){
			e.printStackTrace();
		} finally {
			this.dbUtil.close(null, stmt, conn);
		}
	}
	
	//비밀번호 확인
	public boolean checkIdPw(Client client) {
		//
		boolean flag = false;
		
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			//sql
			String sql = "SELECT client_mail FROM client WHERE client_mail = ? AND client_pw = PASSWORD(?)";
			//db처리
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, client.getClientMail());
			stmt.setString(2, client.getClientPw());
			//디버깅
			System.out.println(stmt+" <-- ClientDao에서 selectClientOne()의 stmt");
			rs = stmt.executeQuery();
			
			//rs.next()가 있다면 아이디와 비밀번호가 일치함
			if(rs.next()) {
				flag = true;
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			this.dbUtil.close(rs, stmt, conn);
		}
		
		return flag;
	}
	
	//비밀번호 변경
	public int updateClientPw(Client client, String newPw) {
		this.dbUtil = new DBUtil();
		int rowCnt = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = this.dbUtil.getConnection();
			String sql = "UPDATE client SET client_pw = PASSWORD(?) WHERE client_mail=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, newPw);
			stmt.setString(2, client.getClientMail());
			
			//디버깅
			System.out.println("updateClientPw stmt : "+stmt);//디버깅코드
			
			//쿼리실행
			rowCnt = stmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbUtil.close(null, stmt, conn);
		}
		return rowCnt;
	}
	
	// 회원정보
		public Client selectClientOne(String clientMail) {
			this.dbUtil = new DBUtil();
			Client client = new Client();
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				conn = this.dbUtil.getConnection();
				String sql = "SELECT client_no clientNo, client_mail clientMail, client_date clientDate FROM client WHERE client_mail = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, clientMail);
				System.out.println(stmt+"고객 정보 출력 메서드");//디버깅코드
				rs = stmt.executeQuery();
				if(rs.next()) {
					client.setClientNo(rs.getInt("clientNo"));
					client.setClientMail(rs.getString("clientMail"));
					client.setClientDate(rs.getString("clientDate"));
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				this.dbUtil.close(rs, stmt, conn);
			}
			return client;
		}

	
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
			String sql = "SELECT client_no clientNo, client_mail clientMail FROM client WHERE client_mail=? AND client_pw=PASSWORD(?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, client.getClientMail());
			stmt.setString(2, client.getClientPw());
			System.out.println("login stmt : "+stmt); //디버깅
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnClient = new Client();
				returnClient.setClientNo(rs.getInt("clientNo"));
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
