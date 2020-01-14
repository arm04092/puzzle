package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Report;

public class ReportDao {
	// 기록 가져오기
	public List<Report> getReport(Connection conn, int reportCase, String memberId) throws Exception{
		System.out.println("ReportDao.getReport param reportCase: "+reportCase);
		System.out.println("ReportDao.getReport param memberId: "+memberId);
		
		/* 동적쿼리
		 * reportCase:
			1. 내 기록
			2. 오늘의 TOP10
			3. 이달의 TOP10
			4. 전체 TOP10
		 */
		String sql = "";
		if(reportCase == 1) {
			sql = "SELECT report_id, member_id, report_date, count, timer FROM report WHERE member_id=? ORDER BY timer";
		}
		if(reportCase == 2) {
			sql = "SELECT report_id, member_id, report_date, count, timer FROM report WHERE DATE(report_date) = DATE(NOW()) ORDER BY timer ASC LIMIT 10";
		}
		if(reportCase == 3) {
			sql = "SELECT report_id, member_id, report_date, count, timer FROM report WHERE YEAR(report_date) = YEAR(NOW()) AND MONTH(report_date) = MONTH(NOW()) ORDER BY timer ASC LIMIT 10";
		}
		if(reportCase == 4) {
			sql = "SELECT report_id, member_id, report_date, count, timer FROM report ORDER BY timer ASC LIMIT 10;";
		}
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Report> list = new ArrayList<Report>();
		
		try {
			stmt = conn.prepareStatement(sql);
			if(reportCase == 1) {
				stmt.setString(1, memberId);
			}
			rs = stmt.executeQuery();
			while(rs.next()) {
				Report report = new Report();
				report.setReportId(rs.getInt("report_id"));
				report.setMemberId(rs.getString("member_id"));
				report.setReportDate(rs.getString("report_date"));
				report.setCount(rs.getInt("count"));
				report.setTimer(rs.getInt("timer"));
				list.add(report);
			}
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	// 기록 저장
	public void addReport(Connection conn, Report report) throws Exception {
		String sql = "INSERT INTO report (member_id, report_date, count, timer) values(?,NOW(),?,?)";
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, report.getMemberId());
			stmt.setInt(2, report.getCount());
			stmt.setInt(3, report.getTimer());
			stmt.executeUpdate();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
