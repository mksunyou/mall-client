package mall.client.model;

import java.util.*;
import java.sql.*;
import mall.client.commons.DBUtil;
import mall.client.vo.*;

public class CartDao {
	private DBUtil dbUtil;
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
			System.out.println("CartDaostmt : "+stmt);
			
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
