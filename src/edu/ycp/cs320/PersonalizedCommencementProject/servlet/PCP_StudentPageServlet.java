package edu.ycp.cs320.PersonalizedCommencementProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.PersonalizedCommencementProject.controller.GraduateController;
import edu.ycp.cs320.PersonalizedCommencementProject.model.Advisor;
import edu.ycp.cs320.PersonalizedCommencementProject.model.Graduate;
import edu.ycp.cs320.PersonalizedCommencementProject.model.User;

// TODO navigating between login / student pages is working 90% of the time
// TODO find / fix cases where it doesn't

// TODO finally, understand that the graduate and graduateController classes 
// TODO are not complete at this time (3/9) since they only contain a small 
// TODO amount of the attributes the Graduate class diagram has 

public class PCP_StudentPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// acts as a temporary 'database' for login verification
	private Graduate[] logins = new Graduate[2];
	private Advisor[] advLogin = new Advisor[1];
	
	// indicates that graduate information matches graduate information stored in 'database'
	private boolean infoInDB;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("PCP_StudentPage Servlet: doGet");
		
		// the supplied login information is valid,
		// call JSP to generate student page
		try{
			System.out.println("Attemping to navigate...");
			if(req.getAttribute("validLogIn").equals(true)) {
				System.out.println("Navigating to student page");
				resp.sendRedirect("http://localhost:8081/PersonalizedCommencementProject/PCP_StudentPage");
			}
			// the supplied login information is not valid,
			// return user to login page

			else {
				System.out.println("Navigating to login page due to bad data");
				resp.sendRedirect("http://localhost:8081/PersonalizedCommencementProject/PCP_Index");
				//req.getRequestDispatcher("/_view/PCP_Index.jsp").forward(req,  resp);
			}
		}
		// no information is supplied,
		// return user to login page
		catch(NullPointerException e){
			System.out.println("Navigating to login page due to no data");
			resp.sendRedirect("http://localhost:8081/PersonalizedCommencementProject/PCP_Index");
			//req.getRequestDispatcher("/_view/PCP_Index.jsp").forward(req,  resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// populate 'database' with acceptable graduates
		logins[0] = new Graduate(new User("wabram", "wabram", "student", "Bill", "Abram"));
		logins[1] = new Graduate(new User("dchism", "dchism", "student", "Dennis", "Chism"));
		logins[0].setStatus(true);
		logins[1].setStatus(false);
		infoInDB = false;
		
		System.out.println("PCP_StudentPage Servlet: doPost");
		
		// creates controller that holds provided graduate's Graduate model
		GraduateController controller = new GraduateController();

		// decode POSTed form parameters and dispatch to controller
		// stores inputed username and password
		String studentName = req.getParameter("studentName");
		
		// once the database is set up, this would iterate through every student in the database
		// and load information to the page only if the supplied information is valid (found in 
		// the database). Otherwise, it will redirect the user back to the login screen to 
		// supply valid information. One example why this is necessary is the case where someone
		// directly accesses the url for the student page without logging in - no login information 
		// would have been supplied, so no information should be displayed and the user should be redirected. 
		
		for(int i = 0; i < logins.length; i++) {
			// provided student name is equal to a student name in database --> input is probably valid
			// set the controller's model to the Graduate class of the user and indicate info was found. 
			if(studentName.equals(logins[i].getFirstName() + " " + logins[i].getLastName())) {
				controller.setModel(logins[i]);
				infoInDB = true;
				break;
			}	
		}
		
		// create reference to model
		Graduate model = controller.getModel();
		
		// this has to change once InfoState and ContentComponent are implemented correctly, but
		// for now it can be assumed that if the graduate name is correct, so is this information.
		String studentAcademicInformation = req.getParameter("studentAcademicInformation");
		String studentExtraInformation = req.getParameter("studentExtraInformation");
		
		// These can be used later on to differentiate the choice of the student
		String studentSaveChanges = req.getParameter("studentSaveChanges");
		String mode = req.getParameter("mode");
		
		if(mode.equals("graduateView")) {
			req.setAttribute("mode", "graduateEdit");
			System.out.println("Switching graduate mode from view to edit");
		}
		else if(mode.equals("graduateEdit")){
			req.setAttribute("mode", "graduateView");
			System.out.println("Switching graduate mode from edit to view");
			if(studentSaveChanges.equals("true")) {
				System.out.println("\n\nStudent wishes to save changes");
				// TODO: call function to save changes
				// TODO: call function to save changes
			}
			else if(studentSaveChanges.equals("false")){
				System.out.println("\n\nStudent wishes to discard changes");
				// TODO: call function to discard changes
				// TODO: call function to discard changes
			}
			else {
				System.out.println("Invalid Values, save: " + studentSaveChanges);
			}
		}
		else if(mode.equals("advisorView")){
			if(req.getParameter("advisorSwitch").equals("true")) {
				req.setAttribute("mode", "advisorEdit");
				System.out.println("Switching advisor mode from view to edit");
				req.setAttribute("advisorUsername", req.getParameter("advisorUsername"));
			}
			else if(req.getParameter("advisorGoBack").equals("true")) {
				System.out.println("Navigating back to advisor home page");
				req.setAttribute("advisorUsername", req.getParameter("advisorUsername"));
				advLogin[0] = new Advisor(new User("agrove9", "agrove9", "advisor", "Alyssa", "Grove"));
				Advisor advisorModel = advLogin[0];
				advisorModel.setStatus(false);
				advisorModel.setAcademicInformation("Department of Etestimology");
				Graduate[] graduateList = new Graduate[2];
				graduateList[0] = new Graduate(new User("wabram", "wabram", "student", "Bill", "Abram"));
				graduateList[1] = new Graduate(new User("dchism", "dchism", "student", "Dennis", "Chism"));
				graduateList[0].setStatus(true);
				graduateList[1].setStatus(false);
				advisorModel.setGraduates(graduateList);
				advisorModel.setNumGraduates(2);
				advisorModel.generatePendingAndCompletedGraduateList();

				req.setAttribute("model", advisorModel);
				req.setAttribute("advisorName", advisorModel.getFirstName() + " " + advisorModel.getLastName());
				req.setAttribute("academicInformation", advisorModel.getAcademicInformation());
				req.setAttribute("advisorStatus", advisorModel.getStatus());
				req.getRequestDispatcher("/_view/PCP_AdvisorPage.jsp").forward(req, resp);
			}
			
		}
		else if(mode.equals("advisorEdit")) {
			req.setAttribute("mode", "advisorView");
			req.setAttribute("advisorUsername", req.getParameter("advisorUsername"));
			System.out.println("Switching advisor mode from edit to view");
			System.out.println(req.getParameter("advisorSaveChanges"));
			// TODO: add logic to save dis/approved content
		}
		else {
			System.out.println("Invalid mode");
		}
		
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
		req.setAttribute("studentStatus", model.getStatus());
		req.setAttribute("studentSaveChanges", studentSaveChanges);
		
		// Redirect accordingly
		// correct graduate information is supplied
		if(infoInDB) {
			req.getRequestDispatcher("/_view/PCP_StudentPage.jsp").forward(req, resp);
		}
	}
}
