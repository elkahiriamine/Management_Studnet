<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css">

</head>
<body>
<%@include file="header.jsp" %>
	<div class="container-fluid">
		<div class="row ">
			<div class="col-8 offset-2 ">
				<div class="card bg-info shadow">
					<div class="card-header ">
						<div class="col-8">
							<h3 class="mb-0 text-white">Édition du etudiant</h3>
						</div>
					</div>
					<div class="card-body bg-info">

						<div class="row">
							<div class="col">
								<form action="" method="post">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">

												<label class="form-control-label">Nom:</label> <input
													type="text" name="nom" class="form-control" autofocus="autofocus"
													value='<%=(request.getParameter("nom") == null) ? "" : request.getParameter("nom")%>' />
												<span style="color: red;">${erreur.nom}</span>
											</div>

											<div class="form-group">
												<label class="form-control-label">Prenom:</label> <input
													type="text" name="prenom" class="form-control"
													value='<%=(request.getParameter("prenom") == null) ? "" : request.getParameter("prenom")%>' />
												<span style="color: red;">${erreur.prenom}</span>
											</div>

											<div class="form-group">
												<label class="form-control-label">Age:</label> <input
													class="form-control" type="number" name="age"
													value='<%=(request.getParameter("age") == null) ? "" : request.getParameter("age")%>' />
												<span style="color: red;">${erreur.age}</span>
											</div>


										</div>
									</div>
									<div class="row">
										<div class="col-md-6 offset-5">
											<input type="submit" value="Enregistrer"
												class="btn btn-block btn-sm btn-success">
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>