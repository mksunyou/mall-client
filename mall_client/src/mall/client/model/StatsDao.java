package mall.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import mall.client.commons.DBUtil;
import mall.client.vo.Stats;

public class StatsDao {
	private DBUtil dbutil;

	public Stats selectStatsByToday() {

		Stats stats = null;
		
		this.dbutil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = this.dbutil.getConnection();
			String sql = "SELECT stats_day statsDay, stats_count statsCount FROM stats WHERE stats_day = DATE(NOW())";
			stmt = conn.prepareStatement(sql);
			System.out.println(stmt + "<--selectStatsByToday");

			rs = stmt.executeQuery();
			if (rs.next()) {
				stats = new Stats();
				stats.setStatsCount(rs.getLong("statsCount"));
				stats.setStatsDay(rs.getString("statsDay"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbutil.close(rs, stmt, conn);
		}

		return stats;

	}

	public void insertStats() {

		this.dbutil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = this.dbutil.getConnection();
			String sql = "INSERT INTO stats(stats_day, stats_count) VALUES (DATE(NOW()),1)";
			stmt = conn.prepareStatement(sql);

			System.out.println(stmt + "<--insertStats()");

			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbutil.close(null, stmt, conn);
		}
	}

	public void updateStats() {

		this.dbutil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = this.dbutil.getConnection();
			String sql = "UPDATE stats SET stats_count = stats_count+1 WHERE stats_day = DATE(NOW())";
			stmt = conn.prepareStatement(sql);

			System.out.println(stmt + "<--updateStats()");

			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbutil.close(null, stmt, conn);
		}
	}

	public long selectStatsTotal() {

		this.dbutil = new DBUtil();

		long total = 0;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.dbutil.getConnection();
			String sql = "SELECT SUM(stats_count) total FROM stats";
			stmt = conn.prepareStatement(sql);
			System.out.println(stmt + "<--selectStatsTotal()");
			rs = stmt.executeQuery();
			if (rs.next()) {
				total = rs.getLong("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbutil.close(rs, stmt, conn);
		}
		return total;
	}

}