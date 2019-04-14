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
import edu.ycp.cs320.PersonalizedCommencementProject.model.Advisor;
import edu.ycp.cs320.PersonalizedCommencementProject.model.Graduate;
import edu.ycp.cs320.PersonalizedCommencementProject.model.User;

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
		String username = "";
		try{
			username = req.getSession().getAttribute("username").toString();
		}
		catch(NullPointerException e) {
			// new users are null, and are redirected accordingly
		}
		// TODO: make this search userDB for session informations usernames' type
		// TODO: and redirect to advisor page only if type is advisor
		if(username.equals("agrove9")) {
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
		
		// populate 'databases' with acceptable logins
		advisorLogins[0] = new Advisor(new User("agrove9", "agrove9", "advisor", "Alyssa", "Grove"));
		graduateLogins[0] = new Graduate(new User("dchism", "dchism", "student", "Dennis", "Chism"));
		graduateLogins[1] = new Graduate(new User("wabram", "wabram", "student", "Bill", "Abram"));
		infoInDB = false;
		
		System.out.println("PCP_AdvisorPage Servlet: doPost");
		
		// create reference to session for persistence
		HttpSession session = req.getSession();
		
		// look for graduate information
		String studentToView = req.getParameter("studentToView");
		
		// correct input - obtain that student's information, redirect to student page
		if(studentToView != null) {
			
			// create controller that holds Graduate controller
			GraduateController graduateController = new GraduateController();
			
			// create Graduate model that holds Graduate information
			Graduate graduateModel = new Graduate();
			
			// find graduate in graduate 'database'
			for(Graduate grad : graduateLogins) {
				grad.setAdvisor(advisorLogins[0]);
				if(grad.getUsername().equals(studentToView)) {
					graduateModel = grad;
					break;
				}
			}
			
			// allows advisor to access student page
			session.setAttribute("studentToView", studentToView);
			
			// displays view version of student page
			session.setAttribute("mode", "advisorView");
			
			// sets page attribute to display graduate name
			session.setAttribute("studentName", graduateModel.getName());
			
			// sets page attribute to display graduate status
			session.setAttribute("studentStatus", graduateModel.getStatus());
			
			// sets page attribute to display graduate academic information
			session.setAttribute("studentAcademicInformation", "Major in Testing");
			
			// sets page attribute to display graduate's additional information
			session.setAttribute("studentExtraInformation", "excels at Testology");
			
			// TODO: fix these statements that only serve to correct a null 
			// TODO: pointer when the student servlet looks for these values

			
			// TODO once the Infostate and ContentComponent classes are correctly implemented, there will need to be calls
			// TODO here setting the media page elements (video, photo, etc.) with the graduate's ContentComponents
			
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
