package com.formation.jee.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.formation.jee.service.ComputerService;
import com.formation.jee.service.manager.ServiceManager;

@WebServlet("/ComputerList")
public class ComputerController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ComputerService computerService;

	private boolean first = true;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ComputerController() {
		super();
		computerService = ServiceManager.INSTANCE.getComputerService();
	}

	/**
	 * @author estelle
	 * 
	 *         La methode doGet est executee lorsqu'un client execute l'URI
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int currentPage;
		String valeurCherchee = request.getParameter("search");
		int nbElements = 25;
		int nbPage = 0;
		
		if(computerService.getComputerCount() % nbElements != 0){
			nbPage = (computerService.getComputerCount() / nbElements) + 1;
		}
		else {
			nbPage = computerService.getComputerCount() / nbElements;
		}
				
		System.out.println("NOMBRE DE PAGES : " + nbPage);	

		if (first) {
			currentPage = 1;
			first = false;
		} else {
			String page = request.getParameter("page");
			currentPage = Integer.parseInt(page);
		}

		if (valeurCherchee == null || valeurCherchee.trim().isEmpty()) {

			request.setAttribute("computers",
					computerService.getComputersPages(nbElements, currentPage));
			request.setAttribute("nPage", currentPage);
			request.setAttribute("nbPages", nbPage);
			request.setAttribute("computers_count", computerService.getComputerCount());
						
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					response.encodeURL("/WEB-INF/display.jsp"));
			rd.forward(request, response);

		}

		else if (valeurCherchee != null && !valeurCherchee.trim().isEmpty()) {

			request.setAttribute("computers",
					computerService.getComputersResearch(valeurCherchee));
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					response.encodeURL("/WEB-INF/display.jsp"));
			rd.forward(request, response);

		}

	}

	/**
	 * La methode doPost est executee lorsqu'un client poste des informations
	 * (en general formulaire)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
