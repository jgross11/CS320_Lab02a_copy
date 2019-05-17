package edu.ycp.cs320.PersonalizedCommencementProject.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.PersonalizedCommencementProject.controller.LoginController;
import edu.ycp.cs320.PersonalizedCommencementProject.controller.UserController;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Admin;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Advisor;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Graduate;
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
		// HttpSesssion will store attributes as long as 
		// the project is running, useful for local persistence
		
		HttpSession session = null;
		
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
			// empty if no user found - bad data
			if(users != null) {
				for(User userInList : users) {
					if(userInList.getPassword().equals(password)) {
						user = userInList;
						System.out.println("USER FOUND");
						System.out.println(user.getName());
						System.out.println(user.getPassword());
						System.out.println(user.getType());
						System.out.println(user.getImage());
						session = req.getSession();
						
						// store User for use throughout session
						session.setAttribute("user", user);
						
						break;
					}
				}
			}
			else {
				errorMessage = "Username and password not found.";
			}
		}
		
		
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
				Graduate graduate = null;
				ArrayList<Graduate> graduates = controller.getGraduateByUsername(username);
				for(Graduate graduateInList : graduates) {
					// found graduate with same username
					if(graduateInList.getUsername().equals(user.getUsername())) {
						graduate = graduateInList;
						System.out.println("Graduate FOUND");
						System.out.println(graduate.getMajor());
						System.out.println(graduate.getAdvisor());
						System.out.println(graduate.getStatus());
						for(int i = 0; i < graduate.getCurrentInfo().getNumContents(); i++) {
							System.out.println(graduate.getCurrentInfo().getContentAtIndex(i).getContent());
							System.out.println(graduate.getPendingInfo().getContentAtIndex(i).getContent());
						}
						break;
					}
				}
				
				// check if no graduate was found
				if(graduate == null) {
					System.err.println("*** NO GRADUATE WAS FOUND***");
					resp.sendRedirect(req.getContextPath() + "/PCP_IndexPage");
				}
				
				// transfers data from userModel to gradModel 
				//Graduate gradModel = new Graduate(userModel);
				
				// displays view version of student page
				session.setAttribute("mode", "graduateView");
				
				session.setAttribute("graduate", graduate);
				
				// sets session attribute to display student status 
				session.setAttribute("studentStatus", graduate.getStatus() ? "Ready" : "Not Ready");
				
				//TODO: add logic determining layout of graduate's information, load appropriate value
				session.setAttribute("graduateLayout", "static slideshow");
				
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
				Advisor advisor = null;
				ArrayList<Advisor> advisors = controller.getAdvisorByUsername(username);
				for(Advisor advisorInList : advisors) {
					// found advisor with same username
					if(advisorInList.getUsername().equals(user.getUsername())) {
						advisor = advisorInList;
						System.out.println("Advisor FOUND");
						System.out.println("Retrieving advisor's graduate list");
						advisor.setGraduates(controller.getAdvisorGraduatesByAdvisorUsername(username));
						System.out.println("Finished retrieving advisor's graduate list");
						advisor.generatePendingAndCompletedGraduateList();
						advisor.calculateStatus();
						System.out.println(advisor.getAcademicInformation());
						System.out.println("Status: " + advisor.getStatus());
						System.out.println("Begin list of advisor's graduates\n");
						for(Graduate grad : advisor.getGraduates()) {
							System.out.println(grad.getName());
						}
						System.out.println("\nEnd list of advisor's graduates");
						session.setAttribute("advisor", advisor);
						break;
					}
				}
				
				// check if no advisor was found
				if(advisor == null) {
					System.err.println("*** NO ADVISOR WAS FOUND***");
					resp.sendRedirect(req.getContextPath() + "/PCP_IndexPage");
				}
				
				resp.sendRedirect(req.getContextPath() + "/PCP_AdvisorPage");
			}
			
			// redirect to admin page
			else if(loginType.equals("admin")){
				Admin admin = new Admin(user);
				ArrayList<Long> dates = controller.fetchEventDate();
				for(Long dateInList : dates) {
					// found dates
					admin.setDate(dateInList);
					System.out.println("name: " + admin.getName());
					System.out.println("date in ms: " + admin.getDate());
					System.out.println("date as date: " + new Date(admin.getDate()));
					session.setAttribute("admin", admin);
					resp.sendRedirect(req.getContextPath() + "/PCP_AdminPage");
					break;
				}
			}
			else {
				System.out.println("Unknown account type, redirecting to login.");
				req.getRequestDispatcher("/_view/PCP_Index.jsp").forward(req, resp);
			}
		
		}
		
		// incorrect login
		else {
			
			// redirect to login page again
			req.getRequestDispatcher("/_view/PCP_Index.jsp").forward(req, resp);
		}
	}
}