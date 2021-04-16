package mall.client.model;

import java.util.*;
import java.sql.*;
import mall.client.commons.DBUtil;
import mall.client.vo.*;

public class CartDao {
	private DBUtil dbUtil;
	
	//회원탈퇴 전 장바구니 비우기
		public void deleteCartByClient (String clientMail) {
			this.dbUtil = new DBUtil();
			Connection conn = null;
			PreparedStatement stmt = null;

			try {
				// sql
				String sql = "DELETE FROM cart WHERE client_mail = ?";
				// db처리
				conn = this.dbUtil.getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, clientMail);

				//디버깅
				System.out.println(stmt+" <-- CartDao에서 deleteCartByClient()의 stmt");

				//삭제 실행
				stmt.executeUpdate();

			} catch (Exception e){
				e.printStackTrace();
			} finally {
				this.dbUtil.close(null, stmt, conn);
			}
		}
	
	//장바구니 삭제
	public int deleteCart(String clientMail, int ebookNo) {
		int rowCnt = 0;	
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = this.dbUtil.getConnection();
			String sql = "DELETE FROM cart WHERE client_Mail=? AND ebook_no = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, clientMail);
			stmt.setInt(2, ebookNo);
			
			//디버깅
			System.out.println("DeleteCart stmt : "+stmt);
			rowCnt = stmt.executeUpdate();
		
		} catch (Exception e){
			e.printStackTrace();
			
		} finally { //try든, catch든 무조건 끝날때는 finally 실행
			this.dbUtil.close(null, stmt, conn);
		}
		return rowCnt; 
	}
	
	//중복확인
	public boolean selectClientMail(Cart cart) {
		boolean flag = true; //중복없음
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.dbUtil.getConnection();
			//sql
			String sql = "SELECT * FROM cart WHERE client_mail = ? AND ebook_no = ?";		
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cart.getClientMail());
			stmt.setInt(2, cart.getEbookNo());
			//디버깅
			System.out.println(stmt + "<------CartDao에서 stmt");
			rs=stmt.executeQuery();
			if(rs.next()) {
				flag = false; //중복있음.
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(rs, stmt, conn);
		}
		return flag;
	}
	
	//장바구니 추가
	public int insertCart(Cart cart) {
		int rowCnt = 0;	
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = this.dbUtil.getConnection();
			String sql = "INSERT INTO cart(client_mail, ebook_no, cart_date) VALUES(?,?,NOW())";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cart.getClientMail());
			stmt.setInt(2, cart.getEbookNo());
			
			//디버깅
			System.out.println("insertCart stmt : "+stmt);
			rowCnt = stmt.executeUpdate();
		
		} catch (Exception e){
			e.printStackTrace();
			
		} finally { //try든, catch든 무조건 끝날때는 finally 실행
			this.dbUtil.close(null, stmt, conn);
		}
		return rowCnt; //제발 please...
	}
	
	//장바구니 리스트
	public List<Map<String, Object>> selectCartList(String clientMail) {
		
		//리턴할 리스트 추가 조인된 테이블을 사용하기 때문에 map 사용.
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			conn = this.dbUtil.getConnection();
			String sql = "SELECT c.cart_no cartNo, e.ebook_no ebookNo, e.ebook_title ebookTitle, c.cart_date cartDate FROM cart c INNER JOIN ebook e ON c.ebook_no = e.ebook_no WHERE client_mail = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, clientMail);
			//디버깅
			System.out.println("selectCartList : "+stmt);
			
			rs = stmt.executeQuery();
			/*
			SELECT c.cart_no, e.ebook_no, e.ebook_title, c.cart_date
			FROM cart c INNER JOIN ebook e
			ON c.ebook_no = e.ebook_no
			 */
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("cartNo",rs.getInt("cartNo"));
				map.put("ebookNo", rs.getInt("ebookNo"));
				map.put("ebookTitle", rs.getString("ebookTitle"));
				map.put("cartDate", rs.getString("cartDate"));
				list.add(map);
		}
		
		} catch (Exception e){
			e.printStackTrace();
			
		} finally { //try든, catch든 무조건 끝날때는 finally 실행
			this.dbUtil.close(rs, stmt, conn);
		}
		return list; //제발 please...
	}
}
