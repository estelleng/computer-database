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
	<h1>Computer List</h1>
	<div id="actions">
		<form action="" method="GET">
			<input type="search" id="searchbox" name="search" value=""
				placeholder="Search name"> <input type="submit"
				id="searchsubmit" value="Filter by name" class="btn primary">
		</form>
		<a class="btn success" id="add" href="NewComputer">Add
			Computer</a>
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
				<c:forEach items="${requestScope.computers}" var="computer">
					<tr>
						<td>${computer.id}</td>
						<td>${computer.name}</td>
						<td>${computer.introduced}</td>
						<td>${computer.discontinued}</td>
						<td>${computer.company.name}</td>
					</tr>
				</c:forEach>
				<!-- <tr>

					<td><input type="submit" value="+" /></td>
					<td><input type="text" name="name"
						placeholder="Nom d'ordinateur..." /></td>
					<td><input type="text" name="name"
						placeholder="Date de mise en vente..." /></td>
					<td><input type="text" name="name"
						placeholder="Date de fin de mise en vente..." /></td>
					<td><input type="text" name="company"
						placeholder="marque de l'ordinateur..." /></td>
				</tr> -->
			</tbody>
		</table>
	</form>
	</section>
</body>
<jsp:include page="../include/footer.jsp" />
</html>