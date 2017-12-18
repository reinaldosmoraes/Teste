<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Filmografia</title>

<!-- CSS Bootstrap -->
<link rel="stylesheet" href="template/css/bootstrap.css">
<link rel="stylesheet" href="template/css/bootstrap-grid.css">

<!-- Script Bootstrap -->
<script src="template/js/bootstrap.js"></script>

<!-- Java -->
<%@page language="java" import="java.util.ArrayList"%>
<%@page language="java" import="modelo.Filme"%>


<% ArrayList<Filme> filmes = (ArrayList<Filme>) request.getAttribute("filmes"); %>
</head>
<body>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Filmografia</h3>
			</div>
			<ul class="list-group">
				<li class="list-group-item">
					<div class="row toggle" id="dropdown-detail-1"
						data-toggle="detail-1">
						<div class="col-xs-10">Lista</div>
						<div class="col-xs-2">
							<i class="fa fa-chevron-down pull-right"></i>
						</div>
					</div>
					<div id="detail-1">
						<hr></hr>
						<div class="container">
							<div class="fluid-row">
								<%
								if(request.getAttribute("filmes") != null){
									for (Filme listaFilmes : filmes){
								%>
								<div class="col-xs-2">
									<%= listaFilmes.getTitulo() %>
								</div>
								<div class="col-xs-2">
									<%= listaFilmes.getAno() %>
								</div>
								<div class="col-xs-2">
									<%= listaFilmes.getDiretor() %>
								</div>
								<div class="col-xs-2">
									<%= listaFilmes.getGenero() %>
								</div>
								<div class="col-xs-2">
									<%= listaFilmes.getPremios().get(0) %>
								</div>
								<div class="col-xs-2"></div>
								<div class="col-xs-1">
									<!--  <a href="listarFilmes.jsp?idAtor=" class="btn btn-link">Filmografia</a>-->
								<%
									}
								} 
								%>
								<div class="col-xs-12">
									<a href="index.jsp" class="btn btn-link">Retornar</a>
								</div>
							</div>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
</body>
</html>