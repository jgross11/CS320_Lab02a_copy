package edu.ycp.cs320.PersonalizedCommencementProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.PersonalizedCommencementProject.controller.GuessingGameController;
import edu.ycp.cs320.PersonalizedCommencementProject.model.GuessingGame;

public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Index Servlet: doGet");

		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Index Servlet: doPost");

		// create GuessingGame model - model does not persist between requests
		// must recreate it each time a Post comes in
		GuessingGame model = new GuessingGame();

		// create GuessingGame controller - controller does not persist between requests
		// must recreate it each time a Post comes in
		GuessingGameController controller = new GuessingGameController();

		// assign model reference to controller so that controller can access model
		controller.setModel(model);

		// check if user is starting a new game and call controller method
		if (req.getParameter("addNumbers") != null) {
			req.getRequestDispatcher("/_view/addNumbers.jsp").forward(req, resp);
		} else if (req.getParameter("multiplyNumbers") != null) {
			req.getRequestDispatcher("/_view/multiplyNumbers.jsp").forward(req, resp);
		} else if (req.getParameter("guessingGame") != null) {
			req.getRequestDispatcher("/_view/guessingGame.jsp").forward(req, resp);
		}
	}
}
