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
public class ComputerController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private ComputerService computerService;
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
	 * La methode doGet est executee lorsqu'un client execute l'URI UserServlet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Envoyer un objet dans la requete 
		
		
		String valeurCherchee = request.getParameter("search");
		
		if (valeurCherchee == null || valeurCherchee.trim().isEmpty()){
			request.setAttribute("computers", computerService.getComputers());
			RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/WEB-INF/display.jsp"));
			rd.forward(request, response);
		}
			

		else if (valeurCherchee!=null && !valeurCherchee.trim().isEmpty()){
			request.setAttribute("computers", computerService.getComputersResearch(valeurCherchee));
			RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/WEB-INF/display.jsp"));
			rd.forward(request, response);
			//System.out.println(computerService.getComputersResearch());
		}
		
	}

	/**
	 * La methode doPost est executee lorsqu'un client poste des informations (en general formulaire) sur l'URI UserServlet
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
