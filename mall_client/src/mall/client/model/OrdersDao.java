package mall.client.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mall.client.commons.DBUtil;
import mall.client.vo.Orders;

public class OrdersDao {
	private DBUtil dbUtil;
	
	//주문리스트
	public List<Map<String, Object>> selectOrderListByClient(int clientNo) {
		
		//리턴할 리스트 추가 조인된 테이블을 사용하기 때문에 map 사용.
		List<Map<String, Object>> list = new ArrayList<>();
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dbUtil.getConnection();
			String sql = "SELECT o.orders_no ordersNo, o.ebook_no ebookNo, o.orders_date ordersDate, o.orders_state ordersState, e.ebook_title ebookTitle, e.ebook_price ebookPrice FROM orders o INNER JOIN ebook e ON o.ebook_no=e.ebook_no WHERE o.client_No=?";;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, clientNo);

			//디버깅
			System.out.println("selectOrdersList : "+stmt);
			
			rs = stmt.executeQuery();
		
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("ordersNo",rs.getInt("ordersNo"));
				map.put("ebookNo", rs.getInt("ebookNo"));
				map.put("ordersDate", rs.getString("ordersDate"));
				map.put("ordersState", rs.getString("ordersState"));
				map.put("ebookTitle", rs.getString("ebookTitle"));
				map.put("ebookPrice", rs.getInt		("ebookPrice"));
				list.add(map);
		}
		
		} catch (Exception e){
			e.printStackTrace();
			
		} finally { //try든, catch든 무조건 끝날때는 finally 실행
			this.dbUtil.close(rs, stmt, conn);
		}
		return list; //제발 please...
	}
	
	//주문 추가
	public int insertOrders(Orders orders) {
		int rowCnt = 0;
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			//sql
			String sql = "INSERT INTO orders(ebook_no, client_no, orders_date, orders_state) VALUES(?, ?, NOW(), '주문완료')";
			
			//db 연결 및 쿼리 진행
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, orders.getEbookNo());
			stmt.setInt(2, orders.getClientNo());
			
			//디버깅
			System.out.println("insertOrders stmt : "+stmt);
			
			rowCnt = stmt.executeUpdate();
			
		} catch(Exception e){
			//예외 발생시 시스템을 멈추고 함수(메서드)호출스택구조를 콘솔 출력하겠다.
			e.printStackTrace();
		} finally {
			this.dbUtil.close(null, stmt, conn);
		}
		return rowCnt;
	}
}
