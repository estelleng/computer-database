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

@WebServlet("/EditComputer")
public class EditComputerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CompanyService companyService;
	private ComputerService computerService;

	private String computer_id = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditComputerController() {
		super();
		companyService = ServiceManager.INSTANCE.getCompanyService();
		computerService = ServiceManager.INSTANCE.getComputerService();
	}

	/**
	 * La methode doGet est executee lorsqu'un client execute l'URI
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Envoyer un objet dans la requete
		request.setAttribute("companies", companyService.getCompanies());

		//On recupere l'id de l'ordinateur que l'utilisateur souhaite modifier
		computer_id = request.getParameter("id");
		long computerId = Long.parseLong(computer_id);
		Computer computer = new Computer();
		computer = computerService.getComputer(computerId);
		
		//Envoyer un objet dans la requete
		request.setAttribute("computer", computer);

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/editComputer.jsp"));

		rd.forward(request, response);
	}

	/**
	 * La methode doPost est executee lorsqu'un client poste des informations
	 * (en general formulaire) 
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		long computerId = Long.parseLong(computer_id);
		boolean validation = true;

		String name = request.getParameter("name");
		
		// Si aucun nom n'a été saisi, ou s'il est vide (ne contient que des espaces)
		if (name == null || name.trim().isEmpty()) {
			//alors la saisie n'est pas valide
			validation = false;
		}
		
		
		SimpleDateFormat sdf = null;
		sdf = new SimpleDateFormat("yyyy-mm-dd");
		//Permettra de faire des tests sur le format de date
		sdf.setLenient(false);

		String introducedDate = request.getParameter("introducedDate");
		Date introduced = null;
		//Vérification de la date saisie
		try {
			introduced = (Date) sdf.parse(introducedDate);
		} catch (ParseException e) {
			//Mauvaise saisie
			validation = false;
			e.printStackTrace();
		}
		

		String discontinuedDate = request.getParameter("discontinuedDate");
		Date discontinued = null;
		//Vérification de la date saisie
		try {
			discontinued = (Date) sdf.parse(discontinuedDate);
		} catch (ParseException e) {
			//Mauvaise saisie
			validation = false;
			e.printStackTrace();
		}

		String company_id = request.getParameter("company_id");
		long companyId = Long.parseLong(company_id);


		// Si les champs ont été correctement remplis, on modifie les champs relatifs à l'ordinateur
		if (validation) {
			Company company = new Company();
			company = companyService.getCompany(companyId);

			Computer computer = computerService.getComputer(computerId);
			computer.setName(name);
			computer.setIntroduced(introduced);
			computer.setDiscontinued(discontinued);
			computer.setCompany(company);

			computerService.editComputer(computer);
			
			// Redirection vers la page qui liste les ordinateurs
			response.sendRedirect("ComputerList?page=1");
		}
		
		//Si un des champs a été mal rempli, l'utilisateur est redirigé vers la page d'édition 
		//de l'ordinateur.
		else response.sendRedirect("EditComputer?id="+ computerId);

		
	}
}
