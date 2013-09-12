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
	 * La methode doGet est executee lorsqu'un client execute l'URI UserServlet
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Envoyer un objet dans la requete 
		request.setAttribute("computers", computerService.getComputers());
		request.setAttribute("companies", companyService.getCompanies());

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/addComputer.jsp"));
		
		rd.forward(request, response);
	}

	/**
	 * La methode doPost est executee lorsqu'un client poste des informations
	 * (en general formulaire) sur l'URI UserServlet
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		
		System.out.println("nom" + name);
		
		SimpleDateFormat sdf = null;
		sdf = new SimpleDateFormat("yyyy-mm-dd");
		
		

		String introducedDate = request.getParameter("introducedDate");
		Date introduced = null;
		try {
			introduced = (Date) sdf.parse(introducedDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("date "+introducedDate);
		
		SimpleDateFormat sdf2 = null;
		sdf2 = new SimpleDateFormat("yyyy-mm-dd");
		
		String discontinuedDate = request.getParameter("discontinuedDate");
		Date discontinued = null;
		try {
			discontinued = (Date) sdf2.parse(discontinuedDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("date "+discontinuedDate);



		//String company_name = request.getParameter("company");

		// Test de validite des champs login et password
		if (name != null && !name.isEmpty()){
			computerService.create(new Computer.Builder().name(name).
					introduced(introduced).discontinued(discontinued).
					build());
			
		}
				
		

		// Redirection vers la page
		doGet(request, response);
	}

}
