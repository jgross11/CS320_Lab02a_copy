package edu.ycp.cs320.PersonalizedCommencementProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.PersonalizedCommencementProject.controller.UserController;
import edu.ycp.cs320.PersonalizedCommencementProject.model.Admin;
import edu.ycp.cs320.PersonalizedCommencementProject.model.Advisor;
import edu.ycp.cs320.PersonalizedCommencementProject.model.Graduate;
import edu.ycp.cs320.PersonalizedCommencementProject.model.User;

public class PCP_IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// acts as a temporary 'database' for login verification
	private User[] logins = new User[4];
	
	// indicates that login matches a login stored in 'database'
	private boolean infoInDB;

	// indicates index of login match
	private int infoIndex;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("PCP_Index Servlet: doGet");

		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/PCP_Index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// populate 'database' with acceptable logins
		logins[0] = new Admin(new User("jgross11", "jgross11", "admin", "Josh", "Gross"));
		logins[1] = new Graduate(new User("wabram", "wabram", "student", "Bill", "Abram"));
		logins[2] = new Advisor(new User("agrove9", "agrove9", "advisor", "Alyssa", "Grove"));
		logins[3] = new Graduate(new User("dchism", "dchism", "student", "Dennis", "Chism"));
		infoInDB = false;
		
		System.out.println("PCP_Index Servlet: doPost");

		// holds the error message text, if there is any
		String errorMessage = null;

		// create controller that holds User model
		UserController controller = new UserController();
		
		// create user model that holds user information
		User userModel = new User();
		controller.setModel(userModel);

		// decode POSTed form parameters and dispatch to controller
			// stores inputted username and password
			String username = req.getParameter("username");
			String password = req.getParameter("password");

			// check for errors in the form data before using is in a calculation
			if (username.equals("") || password.equals("")) {
				errorMessage = "Please enter a username and password";
				controller.setUsername("");
				controller.setPassword("");
			}
			// otherwise, data is good, store the user/pass
			// must create the controller each time, since it doesn't persist between POSTs
			// the view does not alter data, only controller methods should be used for that
			// thus, always call a controller method to operate on the data
			else {
				controller.setUsername(username);
				controller.setPassword(password);
				// check if user/pass is in 'database'
				for(int i = 0; i < logins.length; i++) {
					if(username.equals(logins[i].getUsername()) && password.equals(logins[i].getPassword())) {
						infoInDB = true;
						infoIndex = i;
						controller.setFirstName(logins[i].getFirstName());
						controller.setLastName(logins[i].getLastName());
						break;
					}
				}
				if(!infoInDB) {
					errorMessage = "Username and password not found.";
				}
			}
			
		// Add parameters as request attributes
		// this creates attributes named "first" and "second for the response, and grabs
		// the
		// values that were originally assigned to the request attributes, also named
		// "first" and "second"
		// they don't have to be named the same, but in this case, since we are passing
		// them back
		// and forth, it's a good idea
		req.setAttribute("username", userModel.getUsername());
		req.setAttribute("password", userModel.getPassword());
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);

		req.setAttribute("model", userModel);
		
		// Redirect accordingly
		// correct login
		if(infoInDB) {
			// get login account type
			String loginType = logins[infoIndex].getType();
			
			// redirect to student page
			if(loginType.equals("student")) {
				System.out.println("User supplied valid graduate data");
				
				// transfers data from userModel to gradModel 
				Graduate gradModel = new Graduate(userModel);
				
				// displays view version of student page
				req.setAttribute("mode", "graduateView");
				
				// informs student page jsp that the page needs to be loaded
				req.setAttribute("validLogIn", true);
				
				// sets page attribute to display graduate name
				req.setAttribute("studentName", gradModel.getName());
				
				req.setAttribute("studentStatus", (infoIndex == 1) ? true : false);
				
				// sets page attribute to display graduate academic information
				req.setAttribute("studentAcademicInformation", "Major in Testing");
				
				// sets page attribute to display graduate's additional information
				req.setAttribute("studentExtraInformation", "excels at Testology");
				
				// TODO once the Infostate and ContentComponent classes are correctly implemented, there will need to be calls
				// TODO here setting the media page elements (video, photo, etc.) with the graduate's ContentComponents
				
				// redirect to the student page
				req.getRequestDispatcher("/_view/PCP_StudentPage.jsp").forward(req, resp);
			}
			
			// redirect to advisor page
			else if(loginType.equals("advisor")) {
				Advisor advisorModel = new Advisor(userModel);
				advisorModel.setStatus(false);
				advisorModel.setAcademicInformation("Department of Etestimology");
				/*
				 * TODO: this is where the Advisor's student list is populated, which will then be used to calculate the Advisor's
				 * TODO: status in order to set the initial value of the advisorStatus attribute
				 */
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
			
			// redirect to admin page
			else if(loginType.equals("admin")){
				Admin Adminmodel = new Admin(userModel); 
				req.setAttribute("adminName", Adminmodel.getFirstName() + " " + Adminmodel.getLastName());
				req.getRequestDispatcher("/_view/PCP_AdminPage.jsp").forward(req, resp);
			}
			else {
				System.out.println("Unknown account type, redirecting to login.");
			}
		}
		
		// incorrect login
		else {
			
			// redirect to login page again
			req.getRequestDispatcher("/_view/PCP_Index.jsp").forward(req, resp);
		}
	}
}