package edu.ycp.cs320.PersonalizedCommencementProject.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		ArrayList<Graduate> grads = null;
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
		req.setAttribute("gradList", grads);
		
		System.out.println("PCP_AdvisorPage Servlet: doPost");
		if(req.getParameter("startEvent").equals("true")) {
			// TODO: consider making this its own servlet to get rid 
			// TODO: of the extra /_view/ created by the dispatch
			req.getRequestDispatcher("/_view/PCP_EventPage.jsp").forward(req, resp);
		}
		else {
			req.getRequestDispatcher("/_view/PCP_AdminPage.jsp").forward(req, resp);
		}
	}
}
