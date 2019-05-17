package edu.ycp.cs320.PersonalizedCommencementProject.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.PersonalizedCommencementProject.controller.AdminController;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Admin;
import edu.ycp.cs320.PersonalizedCommencementProject.model.Advisor;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Graduate;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.User;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.DatabaseProvider;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.DerbyDatabase;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.IDatabase;

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
		
		System.out.println("PCP_AdminPage Servlet: doGet");
		
		// verify user attempting to 
		// navigate here is an admin
		User user = null;
		try{
			user = (User) req.getSession().getAttribute("user");
			user.getType();
		}
		catch(NullPointerException e) {
				// new users will be null, and will be directed to login
			resp.sendRedirect(req.getContextPath() + "/PCP_Index");
		}
		// TODO: make this search userDB for session informations usernames' type
		// TODO: and redirect to admin page only if type is admin
		if(user.getType().equals("admin")) {
			req.setAttribute("mode", "home");
			System.out.println("mode: " + req.getParameter("mode"));
			req.getRequestDispatcher("/_view/PCP_AdminPage.jsp").forward(req, resp);
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
		
		// create array of Graduate objects to demonstrate event
		// creates DB instance
		
		System.out.println("PCP_AdminPage Servlet: doPost");
		String choice = req.getParameter("adminChoice");
		HttpSession session = req.getSession();
		ArrayList<Graduate> grads = null;
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		List<Graduate> gradList = db.findGraduateByUsername("dchism");
		if (gradList.isEmpty()) {
			System.out.println("No graduates found");
		}
		else {
			grads = new ArrayList<Graduate>();
			for (Graduate grad : gradList) {
				grads.add(grad);
				System.out.println("Adding " + grad.getName());
			}			
		}
		gradList = db.findGraduateByUsername("acastro");
		if (gradList.isEmpty()) {
			System.out.println("No graduates found");
		}
		else {
			for (Graduate grad : gradList) {
				grads.add(grad);
				System.out.println("Adding " + grad.getName());
			}			
		}
		gradList = db.findGraduateByUsername("wabram");
		if (gradList.isEmpty()) {
			System.out.println("No graduates found");
		}
		else {
			for (Graduate grad : gradList) {
				grads.add(grad);
				System.out.println("Adding " + grad.getName());
			}			
		}
		System.out.println("mode: " + req.getParameter("mode"));
		if(req.getParameter("mode").equals("home")) {
			if(choice.equals("start event")) {
				session.setAttribute("gradList", grads);
				session.setAttribute("leftGrad", grads.get(0));
				session.setAttribute("rightGrad", grads.get(1));
				req.setAttribute("mode", "event");
				System.out.println("choice: " + choice);
				req.getRequestDispatcher("/_view/PCP_AdminPage.jsp").forward(req, resp);
			} 
			else if(choice.equals("update deadline")){
				System.out.println("\n\n\nSubmitted date: " + req.getParameter("newDate"));
				Admin admin = (Admin)req.getSession().getAttribute("admin");
				AdminController controller = new AdminController();
				controller.setModel(admin);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				format.format(new Date());
				try {
					controller.setDate(format.parse(req.getParameter("newDate")).getTime());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				req.setAttribute("mode", "home");
				req.getRequestDispatcher("/_view/PCP_AdminPage.jsp").forward(req, resp);
			}			
		}
		else {
			if(choice.equals("find grad")) {
				String gradUsername = req.getParameter("gradToFind");
				for(Graduate grad : grads) {
					if(grad.getUsername().equals(gradUsername)) {
						session.setAttribute("rightGrad", session.getAttribute("leftGrad"));
						session.setAttribute("leftGrad", grad);
						break;
					}
				}
				req.setAttribute("mode", "event");
				req.setAttribute("choice", "none");
			}
			else {
				req.setAttribute("mode", "home");
				req.setAttribute("choice", "none");				
			}
			req.getRequestDispatcher("/_view/PCP_AdminPage.jsp").forward(req, resp);
		}
	}
}
