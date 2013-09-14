package com.formation.jee.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.formation.jee.domain.Computer;
import com.formation.jee.service.ComputerService;
import com.formation.jee.service.manager.ServiceManager;

@WebServlet("/DeleteComputer")
public class DeleteComputerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ComputerService computerService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteComputerController() {
		super();
		computerService = ServiceManager.INSTANCE.getComputerService();
	}

	/**
	 * La methode doGet est executee lorsqu'un client execute l'URI
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Envoyer un objet dans la requete
		request.setAttribute("computers", computerService.getComputers());

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/deleteComputer.jsp"));

		rd.forward(request, response);
	}

	/**
	 * La methode doPost est executee lorsqu'un client poste des informations
	 * (en general formulaire) sur l'URI UserServlet
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String computer_id = request.getParameter("computer_id");
		long computerId = Long.parseLong(computer_id);

		Computer computer = new Computer();
		computer = computerService.getComputer(computerId);
		computerService.deleteComputer(computer);

		// Redirection vers la page qui liste les ordinateurs
		response.sendRedirect("ComputerList");
	}

}
