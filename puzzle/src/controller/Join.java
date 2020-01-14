package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
import vo.Member;

@WebServlet("/Join")
public class Join extends HttpServlet {
	MemberService memberService;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId1 = request.getParameter("memberId1");
		String memberId2 = request.getParameter("memberId2");
		String memberPw = request.getParameter("memberPw");
		
		System.out.println("Join.doPost param memberId1: "+memberId1);
		System.out.println("Join.doPost param memberId1: "+memberId2);
		System.out.println("Join.doPost param memberPw: "+memberPw);
		
		String memberId = memberId1 + "@" + memberId2;
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		memberService = new MemberService();
		memberService.join(member);
		
		
		
	}
}
