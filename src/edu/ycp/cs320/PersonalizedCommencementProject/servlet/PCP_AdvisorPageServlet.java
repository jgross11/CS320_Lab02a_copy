package edu.ycp.cs320.PersonalizedCommencementProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.PersonalizedCommencementProject.controller.AdvisorController;
import edu.ycp.cs320.PersonalizedCommencementProject.controller.GraduateController;
import edu.ycp.cs320.PersonalizedCommencementProject.controller.UserController;
import edu.ycp.cs320.PersonalizedCommencementProject.model.Advisor;
import edu.ycp.cs320.PersonalizedCommencementProject.model.Graduate;
import edu.ycp.cs320.PersonalizedCommencementProject.model.User;


/*
 * NOTE: THE FOLLOWING IS COPY-PASTE SKELETON CODE FROM INDEX SERVLET, NOTHING WORKS CORRECTLY
 * NOTE: THE FOLLOWING IS COPY-PASTE SKELETON CODE FROM INDEX SERVLET, NOTHING WORKS CORRECTLY
 * NOTE: THE FOLLOWING IS COPY-PASTE SKELETON CODE FROM INDEX SERVLET, NOTHING WORKS CORRECTLY
 * NOTE: THE FOLLOWING IS COPY-PASTE SKELETON CODE FROM INDEX SERVLET, NOTHING WORKS CORRECTLY
 * NOTE: THE FOLLOWING IS COPY-PASTE SKELETON CODE FROM INDEX SERVLET, NOTHING WORKS CORRECTLY
 * NOTE: THE FOLLOWING IS COPY-PASTE SKELETON CODE FROM INDEX SERVLET, NOTHING WORKS CORRECTLY
 */

/*
 * TODO: Everything
 * TODO: Everything
 * TODO: Everything
 */
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
		req.getRequestDispatcher("/_view/PCP_Index.jsp").forward(req, resp);
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
			
			// displays view version of student page
			req.setAttribute("mode", "advisorEdit");
			
			// informs student page jsp that the page needs to be loaded
			req.setAttribute("validLogIn", true);
			
			// sets page attribute to remember advisor's name
			req.setAttribute("advisorUsername", graduateModel.getAdvisor().getUsername());
			
			// sets page attribute to display graduate name
			req.setAttribute("studentName", graduateModel.getName());
			
			// sets page attribute to display graduate status
			req.setAttribute("studentStatus", graduateModel.getStatus());
			
			// sets page attribute to display graduate academic information
			req.setAttribute("studentAcademicInformation", "Major in Testing");
			
			// sets page attribute to display graduate's additional information
			req.setAttribute("studentExtraInformation", "excels at Testology");
			
			// TODO once the Infostate and ContentComponent classes are correctly implemented, there will need to be calls
			// TODO here setting the media page elements (video, photo, etc.) with the graduate's ContentComponents
			
			// redirect to the student page
			req.getRequestDispatcher("/_view/PCP_StudentPage.jsp").forward(req, resp);
		}
		// direct link to advisor page - redirect to login
		else {
			// create controller that holds Advisor controller
			AdvisorController controller = new AdvisorController();
			
			// create Advisor model that holds Advisor information
			Advisor advisorModel;
			
			req.getRequestDispatcher("/_view/PCP_Index.jsp").forward(req, resp);
		}
	}
}
