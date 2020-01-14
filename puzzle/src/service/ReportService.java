package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.ReportDao;
import vo.Report;

public class ReportService {
	// ReportService
	// DAO 호출, 예외 처리, 트랜잭션
	
	// 기록 가져오기, 기록 리스트 리턴
		public List<Report> getReport(int reportCase, String memberId) {
			/*
			 * reportCase:
				1. 내 기록
				2. 오늘의 TOP10
				3. 이달의 TOP10
				4. 전체 TOP10
			 */
			System.out.println("ReportService.getReport param reportCase: "+reportCase);
			System.out.println("ReportService.getReport param memberId: "+memberId);
			
			Connection conn = null;
			List<Report> list = null;
			
			try {
				DBService dbService = new DBService();
				conn = dbService.getConnection();
				conn.setAutoCommit(false);
				
				ReportDao reportDao = new ReportDao();
				list = reportDao.getReport(conn, reportCase, memberId);
				conn.commit();
			} catch(Exception e) {
				try {
					e.printStackTrace();
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("rollback");
			}
			return list;
		}
		
	// 기록 저장
	public void addReport(Report report) {
		Connection conn = null;
		try {
			DBService dbService = new DBService();
			conn = dbService.getConnection();
			conn.setAutoCommit(false);
			
			ReportDao reportDao = new ReportDao();
			reportDao.addReport(conn, report);
			conn.commit();
		} catch (Exception e){
			try {
				e.printStackTrace();
				conn.rollback();
				System.out.println("rollback");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
