package edu.ycp.cs320.PersonalizedCommencementProject.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.PersonalizedCommencementProject.controller.LoginController;
import edu.ycp.cs320.PersonalizedCommencementProject.controller.UserController;
import edu.ycp.cs320.PersonalizedCommencementProject.model.Admin;
import edu.ycp.cs320.PersonalizedCommencementProject.model.Advisor;
import edu.ycp.cs320.PersonalizedCommencementProject.model.Graduate;
import edu.ycp.cs320.PersonalizedCommencementProject.model.LoginModel;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.User;

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
		// Redirect according to user's username
		String username = "";
		try {
			username = req.getSession().getAttribute("username").toString();
		}
		catch(NullPointerException e) {
			// if null, no username supplied, so user hasn't logged in
		}
		// TODO: make this search userDB for session information's username and
		// TODO: redirect to the appropriate page based on that users' type
		
		// graduate search
		if(username.equals("dchism") || username.equals("wabram")) {
			req.getSession().setAttribute("mode", "graduateView");
			resp.sendRedirect(req.getContextPath() + "/PCP_StudentPage");
		}
		// advisor search
		else if(username.equals("agrove9")) {
			resp.sendRedirect(req.getContextPath() + "/PCP_AdvisorPage");
		}
		// admin search
		else if(username.equals("jgross11")) {
			resp.sendRedirect(req.getContextPath() + "/PCP_AdminPage");
		}
		else {
			req.getRequestDispatcher("/_view/PCP_Index.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("PCP_Index Servlet: doPost");

		// holds the error message text, if there is any
		String errorMessage = null;

		// create controller that holds User model
		LoginController controller = new LoginController();
		User user = null;
		
		// create user model that holds user information
		// User userModel = new User();
		// controller.setModel(userModel);

		// decode POSTed form parameters and dispatch to controller
		// stores inputted username and password
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		
		// check for errors in the form data before using is in a calculation
		if (username.equals("") || password.equals("") || username == null || password == null) {
			errorMessage = "Please enter a username and password";
		}
		// otherwise, data is good, store the user/pass
		// must create the controller each time, since it doesn't persist between POSTs
		// the view does not alter data, only controller methods should be used for that
		// thus, always call a controller method to operate on the data
		else {
			ArrayList<User> users = controller.getUserByUsername(username);
			for(User userInList : users) {
				if(userInList.getPassword().equals(password)) {
					user = userInList;
					System.out.println("USER FOUND");
					System.out.println(user.getName());
					System.out.println(user.getPassword());
					System.out.println(user.getType());
					System.out.println(user.getImage());
					break;
				}
			}
			// model still null if no user found, bad data
			if(user == null) {
				errorMessage = "Username and password not found.";
			}
		}
			
		// HttpSesssion will store attributes as long as 
		// the project is running, useful for local persistence
		HttpSession session = req.getSession();
		
		// store User for use throughout session
		session.setAttribute("user", user);
		
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		
		// Redirect accordingly
		// correct login
		
		
		if(user != null) {
			
			// get login account type
			String loginType = user.getType();
			
			// informs session that login is successful
			session.setAttribute("validLogIn", true);
			
			// redirect to student page
			if(loginType.equals("graduate")) {
				System.out.println("User supplied valid graduate data");
				
				// transfers data from userModel to gradModel 
				//Graduate gradModel = new Graduate(userModel);
				
				// displays view version of student page
				session.setAttribute("mode", "graduateView");
				
				// sets session attribute to display graduate name
				//session.setAttribute("studentName", gradModel.getName());
				
				// sets session attribute to display student status 
				// TODO: actually calculate status here instead of hardcoding value
				session.setAttribute("studentStatus", (infoIndex == 1) ? true : false);
				
				// sets session attribute to display graduate academic information
				// TODO: call graduate object to obtain this information
				session.setAttribute("studentAcademicInformation", "Major in Testing");
				
				// sets session attribute to display graduate's additional information
				// TODO: call graduate object to obtain this information
				session.setAttribute("studentExtraInformation", "excels at Testology");
				
				// sets session attribute to reference graduate model object when looking for information
				// TODO: implement way to eliminate the numerous attribute setting calls by having JSP 
				// TODO: reference the model's methods to obtain attributes as opposed to directly setting attributes
				//session.setAttribute("model", gradModel);
				
				//TODO: add logic determining layout of graduate's information, load appropriate value
				session.setAttribute("graduateLayout", "static slideshow");
				
				// TODO once the Infostate and ContentComponent classes are correctly implemented, there will need to be calls
				// TODO here setting the media page elements (video, photo, etc.) with the graduate's ContentComponents
				
				// redirect to the student page
				
				resp.sendRedirect(req.getContextPath() + "/PCP_StudentPage");
			}
			
			// redirect to advisor page
			else if(loginType.equals("advisor")) {
				//Advisor advisorModel = new Advisor(userModel);
				//advisorModel.setStatus(false);
				//advisorModel.setAcademicInformation("Department of Etestimology");
				/*
				 * TODO: this is where the Advisor's student list is populated, which will then be used to calculate the Advisor's
				 * TODO: status in order to set the initial value of the advisorStatus attribute
				 */
				Graduate[] graduateList = new Graduate[2];
				//graduateList[0] = new Graduate(new User("wabram", "wabram", "student", "Bill", "Abram"));
				//graduateList[1] = new Graduate(new User("dchism", "dchism", "student", "Dennis", "Chism"));
				//graduateList[0].setStatus(true);
				graduateList[1].setStatus(false);
				//advisorModel.setGraduates(graduateList);
				//advisorModel.setNumGraduates(2);
				//advisorModel.generatePendingAndCompletedGraduateList();

				// TODO: implement way to eliminate the numerous attribute setting calls by having JSP 
				// TODO: reference the model's methods to obtain attributes as opposed to directly setting attributes
				//session.setAttribute("model", advisorModel);
				//session.setAttribute("advisorName", advisorModel.getName());
				//session.setAttribute("academicInformation", advisorModel.getAcademicInformation());
				//session.setAttribute("advisorStatus", advisorModel.getStatus());
				resp.sendRedirect(req.getContextPath() + "/PCP_AdvisorPage");
			}
			
			// redirect to admin page
			else if(loginType.equals("admin")){
				//Admin adminModel = new Admin(userModel); 
				//session.setAttribute("adminName", adminModel.getName());
				resp.sendRedirect(req.getContextPath() + "/PCP_AdminPage");
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