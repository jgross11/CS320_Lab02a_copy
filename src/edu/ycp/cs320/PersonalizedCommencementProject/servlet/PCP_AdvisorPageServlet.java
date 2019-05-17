package edu.ycp.cs320.PersonalizedCommencementProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.PersonalizedCommencementProject.controller.AdvisorController;
import edu.ycp.cs320.PersonalizedCommencementProject.controller.GraduateController;
import edu.ycp.cs320.PersonalizedCommencementProject.controller.UserController;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Advisor;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Graduate;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.User;

public class PCP_AdvisorPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// acts as a temporary 'database' for login verification
	private Advisor[] advisorLogins = new Advisor[1];
	private Graduate[] graduateLogins = new Graduate[2];
	
	// indicates that login matches a login stored in 'database'
	private boolean infoInDB;

	// indicates index of login match
	private int infoIndex;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("PCP_AdvisorPage Servlet : doGet");
		// verify that user is an advisor
		Advisor advisor = null;
		try{
			advisor = (Advisor) req.getSession().getAttribute("advisor");
			advisor.getType();
		}
		catch(NullPointerException e) {
			// new users are null, and are redirected accordingly
			resp.sendRedirect(req.getContextPath() + "/PCP_Index");
		}
		if(advisor.getType().equals("advisor")) {
			advisor.generatePendingAndCompletedGraduateList();
			advisor.calculateStatus();
			req.getRequestDispatcher("/_view/PCP_AdvisorPage.jsp").forward(req, resp);
		}
		else {
			resp.sendRedirect(req.getContextPath() + "/PCP_Index");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// doPost called when advisor clicks on student button

		// check for valid studentToView input from JSP and
		// redirect accordingly
		
		infoInDB = false;
		
		System.out.println("PCP_AdvisorPage Servlet: doPost");
		
		// create reference to session for persistence
		HttpSession session = req.getSession();
		
		// look for graduate information
		String studentToView = req.getParameter("studentToView");
		
		// correct input - obtain that student's information, redirect to student page
		if(studentToView != null) {
			
			// create Graduate model that holds Graduate information
			Advisor advisor = (Advisor) session.getAttribute("advisor");
			
			// find graduate in graduate 'database'
			for(Graduate grad : advisor.getGraduates()) {
				if(grad.getUsername().equals(studentToView)) {
					
					// allows advisor to access student page
					session.setAttribute("studentToView", grad);
					break;
				}
			}
			
			// displays view version of student page
			session.setAttribute("mode", "advisorView");
			
			// redirect to the student page
			resp.sendRedirect(req.getContextPath() + "/PCP_StudentPage");
		}
		// direct link to advisor page - redirect to login
		// TODO: determine if this is even possible to achieve
		else {
			req.getRequestDispatcher("/_view/PCP_Index.jsp").forward(req, resp);
		}
	}
}
