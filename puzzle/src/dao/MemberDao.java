package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Member;

public class MemberDao {
	// 회원탈퇴
	public int exit(Connection conn, Member member) throws Exception{
		System.out.println("MemberDao.exit param member: "+member.toString());
		int checkRow = 0;
		String sql ="DELETE FROM member WHERE member_id=? AND member_pw=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			checkRow = stmt.executeUpdate();
			System.out.println("Deleted Row: "+ checkRow);
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return checkRow;
	}
	// 비밀변호 변경
	public void updatePw(Connection conn, Member member) throws Exception{
		System.out.println("MemberDao.updatePw param member: "+member.toString());
		
		String sql ="UPDATE member SET member_pw=? WHERE member_id=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberPw());
			stmt.setString(2, member.getMemberId());
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
	// 회원가입
	public void join(Connection conn, Member member) throws Exception{
		System.out.println("MemberDao.login param member.memberId: " + member.getMemberId());
		System.out.println("MemberDao.login param member.memberPw: " + member.getMemberPw());
		
		String sql ="INSERT INTO member (member_id, member_pw) values(?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
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
	 
	/*
	 * SELECT member_id
	 * FROM member 
	 * WHERE member_id='sample@goodee.com' AND member_pw='1234' AND member_level='Y'
	 */
	// 로그인
	public Member login(Connection conn, Member member) throws Exception{
		System.out.println("MemberDao.login param member.memberId: " + member.getMemberId());
		System.out.println("MemberDao.login param member.memberPw: " + member.getMemberPw());
		
		Member returnMember = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT member_id FROM member WHERE member_id=? AND member_pw=? AND member_level='Y'";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnMember = new Member();
				returnMember.setMemberId(rs.getString(1));
			}
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		System.out.println("returnMember:" + returnMember.toString());
		
		return returnMember;
	}
}
