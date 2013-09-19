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
	 * La methode doGet est ex�cut�e lorsqu'un client ex�cute l'URI ComputerServlet.
	 * Elle d�finit les actions associ�es � tous les clics actifs de la page.
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		int currentPage;
		//R�cup�ration de la valeur � chercher saisie par l'utilisateur
		String valeurCherchee = request.getParameter("search");
		//D�finition � 25 du nombre d'ordinateurs � afficher par page
		int nbElements = 25;
		//D�finition du nombre de pages.
		int nbPages = 0;
		
		//Calcul du nombre de pages en fonction du nombre d'�l�ments � afficher et 
		//du nombre total d'ordinateurs � afficher
		if(computerService.getComputerCount() % nbElements != 0){
			nbPages = (computerService.getComputerCount() / nbElements) + 1;
		}
		else {
			nbPages = computerService.getComputerCount() / nbElements;
		}
	
		//Si c'est la premi�re fois qu'on atteint la page listant les ordinateurs, on se positionne
		//sur la premi�re page
		if (first) {
			currentPage = 1;
			first = false;
		} else {
			//sinon on r�cup�re la page courante
			String page = request.getParameter("page");
			currentPage = Integer.parseInt(page);
		}
		
		//Si l'utilisateur n'a rien saisi dans le champ de recherche,
		//on affiche tous les ordinateurs, avec un syst�me de pagination
		if (valeurCherchee == null || valeurCherchee.trim().isEmpty()) {
			
			request.setAttribute("computers",
					computerService.getComputersPages(nbElements, currentPage));
			request.setAttribute("nPage", currentPage);
			request.setAttribute("nbPages", nbPages);
			request.setAttribute("computers_count", computerService.getComputerCount());
						
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					response.encodeURL("/WEB-INF/display.jsp"));
			rd.forward(request, response);

		}
		
		//sinon, si l'utilisateur a renseign� le champ de recherche, on affiche tous les �l�ments
		//correspondant � la recherche
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
