package mall.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import mall.client.commons.DBUtil;
import mall.client.vo.Category;

public class CategoryDao {
	private DBUtil dbutil;
	
	//Category 별 List
	public ArrayList<Category> selectCategoryList() {
		this.dbutil = new DBUtil();
		ArrayList<Category> returnCategoryList = new ArrayList<Category>(); 
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			//db연결
			conn = this.dbutil.getConnection();
			//sql 혹시나 나중에 가중치를 사용할지도 모르기때문에 weight도 category객체에 넣어둔다.
			String sql = "SELECT category_name categoryName, category_weight categoryWeight FROM category";

			//쿼리실행
			stmt = conn.prepareStatement(sql);
			System.out.println(stmt + "<-- CategoryDao.java에서 selectCategoryList()에서 stmt");
			rs = stmt.executeQuery();
			while(rs.next()) {
				Category category = new Category();
				category.setCategoryName(rs.getString("categoryName"));
				category.setCategoryWeight(rs.getInt("categoryWeight"));
				returnCategoryList.add(category);
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbutil.close(rs, stmt, conn);
		}

		return returnCategoryList;
	}
}
