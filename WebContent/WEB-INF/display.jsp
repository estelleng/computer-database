<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.formation.jee.domain.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../include/header.jsp" />
<title>Computer List</title>
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>

	<section id="main">
	
	<!--Affichage du nombre d'ordinateurs figurant dans la liste-->
	<h1>${requestScope.computers_count}Computers Found</h1>
	
	<div id="actions">
	
		<form action="" method="GET">
		
			<input type="search" id="searchbox" name="search" value="" placeholder="Search name"> 
			<input type="submit" id="searchsubmit" value="Filter by name" class="btn primary">
			
		</form>
		
		<a class="btn success" id="add" href="NewComputer">Add Computer</a>
		<a class="btn success" id="delete" href="DeleteComputer">Delete	Computer</a>
		
	</div>
	
	<form action="ComputerServlet" method="POST">
		<table class="computers zebra-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nom</th>
					<th>Date de mise en vente</th>
					<th>Date de retrait de la vente</th>
					<th>Marque</th>
				</tr>
			</thead>
			<tbody>
				<!--Affichage de chaque ordinateur de la liste-->
				<c:forEach items="${requestScope.computers}" var="computer">
					<tr>
						<td>${computer.id}</td>
						<td>
							<!--Le nom des ordinateurs est un lien vers la page
							d'édition de l'ordinateur sélectionné -->
							<a href="<c:url value="EditComputer?id=${computer.id}" />" 
							onclick="">${computer.name}</a>
						</td>

						<td>${computer.introduced}</td>
						<td>${computer.discontinued}</td>
						<td>${computer.company.name}</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</form>
	
	<ul class="pagination">
		<!--Si la page courante n'est pas la première page, on affiche un lien vers la page précédente -->
		<c:if test="${nPage-1 != 0}">

			<li><a href="ComputerList?page=${nPage-1}">Previous</a></li>

		</c:if>
		
		<!--Affichage du numéro de la page courante-->
		<li><a href="ComputerList?page=${nPage}">${nPage}</a></li>
		
		<!--Si la page courante n'est pas la dernière page, on affiche un lien vers la page suivante -->
		<c:if test="${nPage+1 <= nbPages}">

			<li><a href="ComputerList?page=${nPage+1}">Next</a></li>

		</c:if>
	</ul>


	</section>
</body>
<jsp:include page="../include/footer.jsp" />
</html>