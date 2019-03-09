package edu.ycp.cs320.PersonalizedCommencementProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.PersonalizedCommencementProject.controller.GraduateController;
import edu.ycp.cs320.PersonalizedCommencementProject.model.Graduate;
import edu.ycp.cs320.PersonalizedCommencementProject.model.User;

// TODO add parameter 'switchToEditMode' into the student page jsp file
// TODO this will determine whether or not the user wants to edit their info

// TODO also, figure out why the Student page jsp css is broken

// TODO also, create test cases for graduate and graduateController

// TODO also, navigating between login / student pages is working 90% of the time
// TODO find / fix cases where it doesn't

// TODO finally, understand that the graduate and graduateController classes 
// TODO are not complete at this time (3/9) since they only contain a small 
// TODO amount of the attributes the Graduate class diagram has. 

public class PCP_StudentPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// acts as a temporary 'database' for login verification
	private Graduate[] logins = new Graduate[1];
	
	// indicates that graduate information matches graduate information stored in 'database'
	private boolean infoInDB;
	
	// indicates whether or not user wants to edit their information
	private boolean switchToEditMode;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("PCP_StudentPage Servlet: doGet");
		
		// the supplied login information is valid,
		// call JSP to generate student page
		try{
			System.out.println("Attemping to navigate...");
			if(req.getAttribute("validLogIn").equals(false)) {
				System.out.println("Navigating to student page");
				req.getRequestDispatcher("/_view/PCP_StudentPage.jsp").forward(req, resp);
			}
			// the supplied login information is not valid,
			// return user to login page

			else {
				System.out.println("Navigating to login page due to bad data");
				req.getRequestDispatcher("/_view/PCP_Index.jsp").forward(req,  resp);
			}
		}
		// no information is supplied,
		// return user to login page
		catch(NullPointerException e){
			System.out.println("Navigating to login page due to no data");
			req.getRequestDispatcher("/_view/PCP_Index.jsp").forward(req,  resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// populate 'database' with acceptable graduates
		logins[0] = new Graduate(new User("wabram", "wabram", "student", "Bill", "Abram"));
		logins[1] = new Graduate(new User("dchism", "dchism", "student", "Dennis", "Chism"));
		infoInDB = false;
		
		System.out.println("PCP_StudentPage Servlet: doPost");
		
		// creates controller that holds provided graduate's Graduate model
		GraduateController controller = new GraduateController();

		// decode POSTed form parameters and dispatch to controller
		// stores inputted username and password
		String studentName = req.getParameter("studentName");
		
		// once the database is set up, this would iterate through every student in the database
		// and load information to the page only if the supplied information is valid. Otherwise,
		// it will redirect the user back to the login screen to supply valid information. 
		// One example why this is necessary is the case where someone directly accesses the
		// url for the student page without logging in - no login information would have been 
		// supplied, so no information should be displayed and the user should be redirected. 
		
		for(int i = 0; i < logins.length; i++) {
			// provided student name is equal to a student name in database -> input is probably valid
			System.out.println("FFFF" + studentName);
			if(studentName.equals(logins[i].getFirstName() + " " + logins[i].getLastName())) {
				System.out.println("Found user");
				controller.setModel(logins[i]);
				infoInDB = true;
				break;
			}	
		}
		// create 'dummy' graduate if supplied information is missing / incorrect
		// used to ensure that user is redirected to the login screen
		if(!infoInDB) {
			controller.setModel(new Graduate(new User("", "", "", "", "")));
		}
		
		// create reference to model
		Graduate model = controller.getModel();
		
		// this has to change once InfoState and ContentComponent are implemented correctly, but
		// for now it can be assumed that if the graduate name is correct, so is this information.
		String studentAcademicInformation = req.getParameter("studentAcademicInformation");
		String studentExtraInformation = req.getParameter("studentExtraInformation");
		
		// TODO add this parameter into the student page 
		// TODO jsp file and then uncomment the following line 
		// switchToEditMode = (req.getParameter("switchToEditMode").equals("true")) ? true : false;
		
		// Add parameters as request attributes
		// this creates attributes named "first" and "second for the response, and grabs
		// the
		// values that were originally assigned to the request attributes, also named
		// "first" and "second"
		// they don't have to be named the same, but in this case, since we are passing
		// them back
		// and forth, it's a good idea
		req.setAttribute("studentName", studentName);
		req.setAttribute("studentAcademicInformation", studentAcademicInformation);
		req.setAttribute("studentExtraInformation", studentExtraInformation);
		req.setAttribute("model", model);
		
		// Redirect accordingly
		// correct graduate information is supplied
		if(infoInDB) {
			req.getRequestDispatcher("/_view/PCP_StudentPage.jsp").forward(req, resp);
		}
		// incorrect information is supplied
		else {
			// redirect to login page
			req.getRequestDispatcher("/_view/PCP_Index.jsp").forward(req, resp);
		}
	}

	/* 
	 * NOT REQUIRED FOR OUR PROJECT - WILL MOST LIKELY BE DELETED AT SOME POINT
	 * STILL HERE FOR REFERENCE IF IT WOULD BE NEEDED FOR WHATEVER REASON
	 */
	// gets double from the request with attribute named s
	private Double getDoubleFromParameter(String s) {
		if (s == null || s.equals("")) {
			return null;
		} else {
			return Double.parseDouble(s);
		}
	}
}
