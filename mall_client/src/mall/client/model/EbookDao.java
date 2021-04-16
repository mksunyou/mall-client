package mall.client.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.mariadb.jdbc.internal.com.read.dao.Results;

import mall.client.commons.DBUtil;
import mall.client.vo.Ebook;

public class EbookDao {
	private DBUtil dbutil;
	
	//검색어 초기화
	public int totalCount(String searchWord, String categoryName ) {
		this.dbutil = new DBUtil();
		int totalRow = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//검색어와 카테고리 정렬이 없을때 모든 책의 수
		if(searchWord == null && categoryName == null) {
			try {
				conn = this.dbutil.getConnection();
				//sql
				String sql = "SELECT COUNT(*) cnt FROM ebook";
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();

				if(rs.next()) {
					totalRow = rs.getInt("cnt");
				}
			} catch (Exception e){
				e.printStackTrace();
			} finally {
				this.dbutil.close(rs, stmt, conn);
			}
		} else if(searchWord != null) { //검색어가 있을때
			try {
				conn = this.dbutil.getConnection();
				//sql
				String sql = "SELECT COUNT(*) cnt FROM ebook WHERE ebook_title LIKE ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+searchWord+"%");
				System.out.println(sql + "<---EbookDao 에서 totalCount의 stmt");
				rs = stmt.executeQuery();

				if(rs.next()) {
					totalRow = rs.getInt("cnt");
				}
			} catch (Exception e){
				e.printStackTrace();
			} finally {
				this.dbutil.close(rs, stmt, conn);
			}
		} else if (categoryName != null) {
			try {
				conn = this.dbutil.getConnection();
				//sql
				String sql = "SELECT COUNT(*) cnt FROM ebook WHERE category_name = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, categoryName);
				System.out.println(sql + "<---EbookDao 에서 totalCount의 stmt");
				rs = stmt.executeQuery();

				if(rs.next()) {
					totalRow = rs.getInt("cnt");
				}
			} catch (Exception e){
				e.printStackTrace();
			} finally {
				this.dbutil.close(rs, stmt, conn);
			}
		}

		//리턴
		return totalRow;
	}
	
	//Category별 ebooklist
	public List<Ebook> selectEbookListByCategory(int beginRow, int rowPerPage, String categoryName) {
		List<Ebook> list = new ArrayList<>();
		this.dbutil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//선택된 카테고리가 없다면 모든 책 셀렉트
				if(categoryName == null) {
					try {
						conn = this.dbutil.getConnection();
						//sql
						String sql = "SELECT ebook_no ebookNo, ebook_title ebookTitle, ebook_price ebookPrice FROM ebook ORDER BY ebook_date DESC LIMIT ?, ?";
						stmt = conn.prepareStatement(sql);
						stmt.setInt(1, beginRow);
						stmt.setInt(2, rowPerPage);
						//디버깅
						System.out.println(sql + "<---EbookDao 에서 selectEbookListByPage의 stmt");

						rs = stmt.executeQuery();
						//반복문 돌면서 리스트에 ebook객체 정보담기
						while(rs.next()) {
							Ebook ebook = new Ebook();
							ebook.setEbookNo(rs.getInt("ebookNo"));
							ebook.setEbookTitle(rs.getString("ebookTitle"));
							ebook.setEbookPrice(rs.getInt("ebookPrice"));
							list.add(ebook);
						}
					} catch(Exception e) {
						e.printStackTrace();
					} finally {
						this.dbutil.close(rs, stmt, conn);
					}
				} else {//선택된 카테고리가 있다면 그 카테고리만 셀렉트
					try {
						conn = this.dbutil.getConnection();
						//sql
						String sql = "SELECT ebook_no ebookNo, ebook_title ebookTitle, ebook_price ebookPrice FROM ebook WHERE category_name = ? ORDER BY ebook_date DESC LIMIT ?, ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, categoryName);
						stmt.setInt(2, beginRow);
						stmt.setInt(3, rowPerPage);
						//디버깅
						System.out.println(sql + "<---EbookDao 에서 selectEbookListByPage의 stmt");

						rs = stmt.executeQuery();
						//반복문 돌면서 리스트에 ebook객체 정보담기
						while(rs.next()) {
							Ebook ebook = new Ebook();
							ebook.setEbookNo(rs.getInt("ebookNo"));
							ebook.setEbookTitle(rs.getString("ebookTitle"));
							ebook.setEbookPrice(rs.getInt("ebookPrice"));
							list.add(ebook);
						}
					} catch(Exception e) {
						e.printStackTrace();
					} finally {
						this.dbutil.close(rs, stmt, conn);
					}
				}
		
		return list;
	}
	
	//Ebook 상세정보
	public Ebook selectEbookOne(int ebookNo) {
		this.dbutil = new DBUtil();
		Ebook ebook = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.dbutil.getConnection();
			String sql = "SELECT ebook_no ebookNo, ebook_isbn ebookISBN, category_name categoryName, ebook_title ebookTitle, ebook_author ebookAuthor, ebook_company ebookCompany, ebook_page_count ebookPageCount, ebook_price ebookPrice, ebook_summary ebookSummary, ebook_img ebookImg, ebook_date ebookDate, ebook_state ebookState FROM ebook WHERE ebook_no=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ebookNo);
			rs = stmt.executeQuery();
			if(rs.next()) {
				ebook = new Ebook();
				ebook.setEbookNo(rs.getInt("ebookNo"));
				ebook.setEbookISBN(rs.getString("ebookISBN"));
				ebook.setCategoryName(rs.getString("categoryName"));
				ebook.setEbookTitle(rs.getString("ebookTitle"));
				ebook.setEbookAuthor(rs.getString("ebookAuthor"));
				ebook.setEbookCompany(rs.getString("ebookCompany"));
				ebook.setEbookPageCount(rs.getInt("ebookPageCount"));
				ebook.setEbookPrice(rs.getInt("ebookPrice"));
				ebook.setEbookImg(rs.getString("ebookImg"));
				ebook.setEbookSummary(rs.getString("ebookSummary"));
				ebook.setEbookDate(rs.getString("ebookDate"));
				ebook.setEbookState(rs.getString("ebookState"));
			}
	} catch (Exception e) {
		e.printStackTrace();
		
	} finally { //try든, catch든 무조건 끝날때는 finally 실행
		this.dbutil.close(rs, stmt, conn);
	}
	return ebook;
	}
	
	//Ebook List 페이징
	public List<Ebook> selectEbookListByPage(int beginRow, int rowPerPage, String searchWord) {
		List<Ebook> list = new ArrayList<>();
		this.dbutil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
		conn = this.dbutil.getConnection();
		String sql = "SELECT ebook_no ebookNo, ebook_title ebookTitle, ebook_price ebookPrice FROM ebook WHERE ebook_title LIKE ? ORDER BY ebook_date DESC LIMIT ?,?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+searchWord+"%");
		stmt.setInt(2, beginRow);
		stmt.setInt(3, rowPerPage);
		
		//디버깅
		System.out.println("EbookListPage 검색어 추가 stmt : " + stmt);
		
		rs = stmt.executeQuery();
		while(rs.next()) {
			Ebook ebook = new Ebook();
			ebook.setEbookNo(rs.getInt("ebookNo"));
			ebook.setEbookTitle(rs.getString("ebookTitle"));
			ebook.setEbookPrice(rs.getInt("ebookPrice"));
			//ebook.setEbookImg(rs.getString("ebookImg"));
			list.add(ebook);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally { //try든, catch든 무조건 끝날때는 finally 실행
			this.dbutil.close(rs, stmt, conn);
		}
		return list;
	}
}
