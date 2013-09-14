<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.formation.jee.domain.*"%>
<jsp:include page="../include/header.jsp" />
<section id="main">

	<h1>Add Computer</h1>

	<form action="DeleteComputer" method="POST">
		<fieldset>
			<div class="clearfix">
				<label for="computer">Computer Name:</label>
				<div class="input">
					<select name="computer_id">
					
						<c:forEach items="${requestScope.computers}" var="computer">

							<option value = "${computer.id}">${computer.name}</option>


						</c:forEach>


					</select>
				</div>
			</div>

		</fieldset>
		<div class="actions">
			<input type="submit" value="Delete" class="btn primary"> or <a
				href="ComputerList" class="btn">Cancel</a>
		</div>
	</form>
</section>

<jsp:include page="../include/footer.jsp" />