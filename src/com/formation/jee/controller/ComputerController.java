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
	 * La methode doGet est exécutée lorsqu'un client exécute l'URI ComputerServlet.
	 * Elle définit les actions associées à tous les clics actifs de la page.
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		int currentPage;
		//Récupération de la valeur à chercher saisie par l'utilisateur
		String valeurCherchee = request.getParameter("search");
		//Définition à 25 du nombre d'ordinateurs à afficher par page
		int nbElements = 25;
		//Définition du nombre de pages.
		int nbPages = 0;
		
		//Calcul du nombre de pages en fonction du nombre d'éléments à afficher et 
		//du nombre total d'ordinateurs à afficher
		if(computerService.getComputerCount() % nbElements != 0){
			nbPages = (computerService.getComputerCount() / nbElements) + 1;
		}
		else {
			nbPages = computerService.getComputerCount() / nbElements;
		}
	
		//Si c'est la première fois qu'on atteint la page listant les ordinateurs, on se positionne
		//sur la première page
		if (first) {
			currentPage = 1;
			first = false;
		} else {
			//sinon on récupère la page courante
			String page = request.getParameter("page");
			currentPage = Integer.parseInt(page);
		}
		
		//Si l'utilisateur n'a rien saisi dans le champ de recherche,
		//on affiche tous les ordinateurs, avec un système de pagination
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
		
		//sinon, si l'utilisateur a renseigné le champ de recherche, on affiche tous les éléments
		//correspondant à la recherche
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
