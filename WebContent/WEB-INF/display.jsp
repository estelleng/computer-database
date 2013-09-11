<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.formation.jee.domain.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Computer List</title>
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<section id="main">
		<form action="ComputerServlet" method="POST">
			<table class="computers zebra-striped">
				<thead>
					<tr>
						<th>id</th>
						<th>name</th>
						<th>introduced</th>
						<th>discontinued</th>
						<th>company id</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.computers}" var="computer">
						<tr>
							<td>${computer.id}</td>
							<td>${computer.name}</td>
							<td>${computer.introduced}</td>
							<td>${computer.discontinued}</td>
							<td>${computer.company_id}</td>
						</tr>
					</c:forEach>
					<tr>

						<td><input type="submit" value="+" /></td>
						<td><input type="text" name="name"
							placeholder="Entrer un nouvel ordinateur :" /></td>
						<td><input type="text" name="company"
							placeholder="Entrer la marque de l'ordinateur :" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</section>
</body>
</html>