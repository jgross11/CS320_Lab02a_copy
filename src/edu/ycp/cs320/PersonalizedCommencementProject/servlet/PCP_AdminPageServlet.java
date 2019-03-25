package edu.ycp.cs320.PersonalizedCommencementProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.PersonalizedCommencementProject.controller.AdminController;
import edu.ycp.cs320.PersonalizedCommencementProject.controller.AdvisorController;
import edu.ycp.cs320.PersonalizedCommencementProject.controller.GraduateController;
import edu.ycp.cs320.PersonalizedCommencementProject.controller.UserController;
import edu.ycp.cs320.PersonalizedCommencementProject.model.Admin;
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
public class PCP_AdminPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// acts as a temporary 'database' for login verification
	private Advisor[] advisorLogins = new Advisor[1];
	private Graduate[] graduateLogins = new Graduate[2];
	private Admin[] AdminLogins = new Admin[1]; 
	
	// indicates that login matches a login stored in 'database'
	private boolean infoInDB;

	// indicates index of login match
	private int infoIndex;
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("PCP_AdvisorPage Servlet: doGet");
		
		// populate 'databases' with acceptable logins
		advisorLogins[0] = new Advisor(new User("agrove9", "agrove9", "advisor", "Alyssa", "Grove"));
		graduateLogins[0] = new Graduate(new User("dchism", "dchism", "student", "Dennis", "Chism"));
		graduateLogins[1] = new Graduate(new User("wabram", "wabram", "student", "Bill", "Abram"));
		AdminLogins[0] = new Admin(new User("jgross11","jgross11","admin","Josh","Gross")); 
				
		infoInDB = false;
		
		System.out.println("PCP_AdminPage Servlet: doGet");
		
		// create controller that holds Advisor controller
		AdminController controller = new AdminController();
		 
		
		// create Advisor model that holds Advisor information
		Admin Adminmodel = null;
		
		// attempt to find advisor based on username
		String username = req.getParameter("adminUserName");
		for(Admin ad : AdminLogins) {
			// found advisor
			if(ad.getUsername().equals(username)) {
				Adminmodel = ad;
				controller.setModel(Adminmodel);
				infoInDB = true;
				break;
			}
		}
		// no advisor found - redirect to login
		if(!infoInDB) {
			req.getRequestDispatcher("/_view/PCP_Index.jsp").forward(req, resp);
			System.out.println("Redirecting to login page due to no admin being found");
		}
		// advisor found set attributes and reload advisor page
		else {
			req.setAttribute("adminName",Adminmodel.getFirstName() + " " + Adminmodel.getLastName());
			req.setAttribute("advisorUsername", username);
			req.getRequestDispatcher("/_view/PCP_AdminPage.jsp").forward(req, resp);
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
		AdminLogins[0] = new Admin(new User("jgross11","jgross11","admin","Josh","Gross")); 
		infoInDB = false;
		
		System.out.println("PCP_AdvisorPage Servlet: doPost");
		
		// create controller that holds Advisor controller
		AdminController controller = new AdminController();
		
		// create Advisor model that holds Advisor information
		Admin adminModel;
		
		// correct input - obtain that student's information, redirect to student page
		if(req.getParameter("studentToView") != null) {
			// TODO: create student model based off of that student's information,
			// TODO: pass it's attributes to the student page and set mode to advisorView
		}
		// incorrect input - redirect to login page
		else {
			req.getRequestDispatcher("/_view/PCP_EventPage.jsp").forward(req, resp);
		}
	}
}
