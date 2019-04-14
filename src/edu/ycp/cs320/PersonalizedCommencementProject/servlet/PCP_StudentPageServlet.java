package edu.ycp.cs320.PersonalizedCommencementProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.PersonalizedCommencementProject.controller.GraduateController;
import edu.ycp.cs320.PersonalizedCommencementProject.model.Advisor;
import edu.ycp.cs320.PersonalizedCommencementProject.model.Graduate;
import edu.ycp.cs320.PersonalizedCommencementProject.model.User;

public class PCP_StudentPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// acts as a temporary 'database' for login verification
	private Graduate[] logins = new Graduate[2];
	private Advisor[] advLogin = new Advisor[1];
	
	// indicates that graduate information matches graduate information stored in 'database'
	private boolean infoInDB;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("PCP_AdvisorPage Servlet : doGet");
		// validate user should be allowed to access this page by checking
		// the type of user associated with the provided username
		String username = "";
		try{
			username = req.getSession().getAttribute("username").toString();
		}
		catch(NullPointerException e) {

		}
		// TODO: make this search userDB for session informations usernames' type
		// TODO: and redirect to student page only if type is student or advisor
		
		// allow students to always access this page
		if(username.equals("dchism") || username.equals("wabram")) {
			req.getRequestDispatcher("/_view/PCP_StudentPage.jsp").forward(req, resp);
		}
		
		// allow advisors to access this page so long
		// as they have a student's information to view
		else if(username.equals("agrove9") && req.getSession().getAttribute("studentToView") != null){
			req.getRequestDispatcher("/_view/PCP_StudentPage.jsp").forward(req, resp);
		}
		// direct access w/o logging in, or admin account type
		else {
			resp.sendRedirect(req.getContextPath() + "/PCP_Index");
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
		
		// HttpSession allows for local persistence while 
		// the user is interacting with the project
		HttpSession session = req.getSession();
		
		// store name of student for use across session
		String studentName = session.getAttribute("studentName").toString();
		
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
		String studentAcademicInformation = session.getAttribute("studentAcademicInformation").toString();
		String studentExtraInformation = session.getAttribute("studentExtraInformation").toString();
		
		// determines whether or not student wishes
		// to save changes to their media selections
		String studentSaveChanges = "";
		
		// mode the JSP should display
		String mode = "";
		
		// attempt to obtain the student's action
		// null if student was just redirected to page
		try{
			studentSaveChanges = req.getParameter("studentSaveChanges").toString();
			mode = session.getAttribute("mode").toString();
		}
		catch(NullPointerException e) {
			// only null for the first post, safe to assume
			// user wants to edit information
			mode = "graduateView";
			System.out.println("Switching graduate mode from view to edit - First time");
		}
		
		// student wishes to edit their media
		if(mode.equals("graduateView")) {
			session.setAttribute("mode", "graduateEdit");
			System.out.println("Switching graduate mode from view to edit");
			req.getRequestDispatcher("/_view/PCP_StudentPage.jsp").forward(req, resp);
		}
		
		// student wishes to either save or discard their
		// changes to their media
		else if(mode.equals("graduateEdit")){
			System.out.println(req.getParameter("graduateLayoutChange"));
			if(req.getParameter("graduateLayoutChange").toString().equals("true")){
				// TODO: set graduate's layout mode to the chosen value
				// TODO: this is temporary; will eventually change model
				session.setAttribute("graduateLayout", req.getParameter("graduateLayout"));
				session.setAttribute("graduateLayoutChange", "false");
				session.setAttribute("mode", "graduateEdit");
				System.out.println("Returning to graduate edit mode after modifying layout");
			}
			else {
				session.setAttribute("mode", "graduateView");
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
			req.getRequestDispatcher("/_view/PCP_StudentPage.jsp").forward(req, resp);
		}
		// advisor wishes to dis/approve a student's media selections
		else if(mode.equals("advisorView")){
			try {
				// attempt to get requested values and update session values
				session.setAttribute("advisorSwitch", req.getParameter("advisorSwitch").toString());
				session.setAttribute("advisorGoBack", req.getParameter("advisorGoBack").toString());
				System.out.println(session.getAttribute("advisorGoBack").toString() + ", and " + session.getAttribute("advisorSwitch").toString());
			}
			catch(NullPointerException e) {
				// first time post case
				session.setAttribute("advisorGoBack", "true");
				session.setAttribute("advisorSwitch", "false");
				System.out.println(session.getAttribute("advisorGoBack").toString() + ", or " + session.getAttribute("advisorSwitch").toString());
			}
			if(session.getAttribute("advisorSwitch").toString().equals("true")) {
				session.setAttribute("mode", "advisorEdit");
				System.out.println("Switching advisor mode from view to edit");
				req.getRequestDispatcher("/_view/PCP_StudentPage.jsp").forward(req, resp);
			}
			else if(session.getAttribute("advisorGoBack").equals("true")) {
				System.out.println("Navigating back to advisor home page");
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
				resp.sendRedirect(req.getContextPath() + "/PCP_AdvisorPage");
			}
			
		}
		
		// advisor wishes to save or discard their decisions
		// regarding student's media choices
		else if(mode.equals("advisorEdit")) {
			session.setAttribute("mode", "advisorView");
			System.out.println("Switching advisor mode from edit to view");
			System.out.println(req.getParameter("advisorSaveChanges"));
			// TODO: add logic to save dis/approved content
			
			req.getRequestDispatcher("/_view/PCP_StudentPage.jsp").forward(req, resp);
		}
		else {
			System.out.println("Invalid mode");
		}
		
		req.setAttribute("studentName", studentName);
		req.setAttribute("studentAcademicInformation", studentAcademicInformation);
		req.setAttribute("studentExtraInformation", studentExtraInformation);
		req.setAttribute("model", model);
		req.setAttribute("studentStatus", model.getStatus());
		req.setAttribute("studentSaveChanges", studentSaveChanges);
	}
}
