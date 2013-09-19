package com.formation.jee.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.formation.jee.domain.Company;
import com.formation.jee.domain.Computer;
import com.formation.jee.service.CompanyService;
import com.formation.jee.service.ComputerService;
import com.formation.jee.service.manager.ServiceManager;

@WebServlet("/NewComputer")
public class AddComputerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ComputerService computerService;
	private CompanyService companyService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComputerController() {
		super();
		computerService = ServiceManager.INSTANCE.getComputerService();
		companyService = ServiceManager.INSTANCE.getCompanyService();
	}

	/**
	 * La methode doGet est executee lorsqu'un client execute l'URI
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Envoyer un objet dans la requete
		request.setAttribute("companies", companyService.getCompanies());

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/addComputer.jsp"));

		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		boolean validation = true;
		String name = request.getParameter("name");

		// Si aucun nom n'a été saisi, ou s'il est vide (ne contient que des
		// espaces)
		if (name == null || name.trim().isEmpty()) {
			// alors la saisie n'est pas valide
			validation = false;
		}

		SimpleDateFormat sdf = null;
		sdf = new SimpleDateFormat("yyyy-mm-dd");
		//Permet les tests sur les formats de date
		sdf.setLenient(false);

		String introducedDate = request.getParameter("introducedDate");
		Date introduced = null;
		//Vérification de la date saisie
		try {
			introduced = (Date) sdf.parse(introducedDate);
		} catch (ParseException e) {
			//mauvaise saisie
			validation = false;
			e.printStackTrace();
		}

		String discontinuedDate = request.getParameter("discontinuedDate");
		Date discontinued = null;
		//Vérification de la date saisie
		try {
			discontinued = (Date) sdf.parse(discontinuedDate);
		} catch (ParseException e) {
			//mauvaise saisie
			validation = false;
			e.printStackTrace();
		}

		String company_id = request.getParameter("company_id");
		
		// Test de validite des champs entrés par l'utilisateur
		if (validation) {
			//Si l'utilisateur a saisi une compagnie
			if (!company_id.isEmpty()) {
				long companyId = Long.parseLong(company_id);
				Company company = new Company();
				company = companyService.getCompany(companyId);

				computerService.create(new Computer.Builder().name(name)
						.introduced(introduced).discontinued(discontinued)
						.company(company).build());
			}

			else {
				computerService.create(new Computer.Builder().name(name)
						.introduced(introduced).discontinued(discontinued)
						.build());
			}

			// Redirection vers la page qui liste les ordinateurs
			response.sendRedirect("ComputerList?page=1");
		}

		// Si un des champs a été mal rempli, l'utilisateur est redirigé
		// vers la page d'ajout d'un ordinateur.
		else
			response.sendRedirect("NewComputer");

	}
}
