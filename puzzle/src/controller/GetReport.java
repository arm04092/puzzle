package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ReportDao;
import service.ReportService;
import vo.Report;

@WebServlet("/GetReportList")
public class GetReport extends HttpServlet {
	ReportService reportService;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		int reportCase = Integer.parseInt(request.getParameter("reportCase"));
		String memberId = request.getParameter("memberId");
		System.out.println("GetReport.doPost param reportCase: "+reportCase);
		System.out.println("GetReport.doPost param memberId: "+memberId);
		/*
		 * reportCase:
			1. 내 기록
			2. 오늘의 TOP10
			3. 이달의 TOP10
			4. 전체 TOP10
		 */
		
		reportService = new ReportService();
		List<Report> list = reportService.getReport(reportCase, memberId);
		
		Gson gson = new Gson();
		String jsonList = gson.toJson(list);
		System.out.println("jsonList:" + jsonList);
		response.getWriter().write(jsonList);
	}

}
