package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ReportService;
import vo.Report;

@WebServlet("/AddReport")
public class AddReport extends HttpServlet {
	ReportService reportService;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		int timer = Integer.parseInt(request.getParameter("timer"));
		int count = Integer.parseInt(request.getParameter("count"));
		System.out.println("AddReport.doPost param memberId: " + memberId);
		System.out.println("AddReport.doPost param timer: "+ timer);
		System.out.println("AddReport.doPost param count: "+ count);
		
		Report report = new Report();
		report.setMemberId(memberId);
		report.setTimer(timer);
		report.setCount(count);
		
		reportService = new ReportService();
		reportService.addReport(report);
		
	}

}
