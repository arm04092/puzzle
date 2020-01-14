package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.MemberDao;
import vo.Member;

public class MemberService {
	// MemberService
	// DAO 호출, 예외 처리, 트랜잭션
	
	// 로그인 처리, 멤버id 리턴
	public String login(Member member) {
		Connection conn = null;
		Member returnMember = null;
		try {
			// db 연결
			DBService dbService = new DBService();
			conn = dbService.getConnection();
			// 오토커밋 끄기
			conn.setAutoCommit(false);
			
			MemberDao memberDao = new MemberDao();
			// before
			returnMember = memberDao.login(conn, member);
			// after
			// 완료되면 커밋
			conn.commit();
		} catch (Exception e) {
			try {
				// 예외 발생 시 롤백
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
		return returnMember.getMemberId();
	}
	
	// 회원 탈퇴, 처리된 행의 수(0,1) 리턴
	public int exit(Member member) {
		Connection conn = null;
		int checkRow = 0;
		try {
			DBService dbService = new DBService();
			conn = dbService.getConnection();
			conn.setAutoCommit(false);
		
			MemberDao memberDao = new MemberDao();
			checkRow = memberDao.exit(conn, member);
			conn.commit();
		} catch(Exception e) {
			try {
				e.printStackTrace();
				conn.rollback();
				System.out.println("rollback");
			} catch(Exception e1) {
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
		
		return checkRow;
	}
	
	// 비밀변호 변경
	public void updatePw(Member member) {
		Connection conn = null;
		try {
			DBService dbService = new DBService();
			conn = dbService.getConnection();
			conn.setAutoCommit(false);
		
			MemberDao memberDao = new MemberDao();
			memberDao.updatePw(conn, member);
			conn.commit();
		} catch(Exception e) {
			try {
				e.printStackTrace();
				conn.rollback();
				System.out.println("rollback");
			} catch(Exception e1) {
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
	// 회원가입 처리
	public void join(Member member) {
		Connection conn = null;
		try {
			DBService dbService = new DBService();
			conn = dbService.getConnection();
			conn.setAutoCommit(false);
		
			MemberDao memberDao = new MemberDao();
			memberDao.join(conn, member);
			conn.commit();
		} catch(Exception e) {
			try {
				e.printStackTrace();
				conn.rollback();
				System.out.println("rollback");
			} catch(Exception e1) {
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
