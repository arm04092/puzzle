package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.MemberService;
import vo.Member;

@WebServlet("/Exit")
public class Exit extends HttpServlet {
	MemberService memberService;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		System.out.println("UpdatePw.doPost param memberId: " + memberId);
		System.out.println("UpdatePw.doPost param memberPw: " + memberPw);
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		int checkRow = 0;
		memberService = new MemberService();
		checkRow = memberService.exit(member);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(checkRow);
		response.getWriter().write(jsonStr);
		
	}

}
