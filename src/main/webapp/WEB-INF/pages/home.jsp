<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>DHL Foosball Tournament</title>
        <script type="text/javascript" src="resources/jquery-1.10.2.js"></script>		
        <script type="text/javascript" src="resources/jquery-ui-1.8.16.custom.min.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/jquery-ui-1.8.16.custom.css">
        <script type="text/javascript" src="resources/jquery-bracket-master/dist/jquery.bracket.min.js"></script>
        <script type="text/javascript" src="resources/jquery.json-2.2.min.js"></script>
        <link href="resources/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet" />
        <script src="resources/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>	
        <link rel="stylesheet" href="resources/font-awesome-4.4.0/css/font-awesome.min.css" />
        <link rel="stylesheet" type="text/css" href="resources/jquery-bracket-master/dist/jquery.bracket.min.css" />
        <link rel="icon" href="<c:url value="resources/favicon.ico"/>" type="image/x-icon" />
		<link rel="shortcut icon" href="<c:url value="resources/favicon.ico"/>" type="image/x-icon" />
        <style type="text/css">
            div.jQBracket .team div.label {font-family: Arial;font-size: 10px;font-weight: bold;color:black;margin-top: 4px;margin-left: 6px;}
            div.jQBracket .team {width: 110px;}
            .navbar-default {margin-bottom:0;background:#ffcc00;z-index:2000;border-bottom: 0 none #d40511;box-shadow: 0 0 2px #d40511;}
			.navbar > .container .navbar-brand, .navbar > .container-fluid .navbar-brand {
				margin-left: 12px;
				margin-top: -3px;
			}
			.navbar-default .navbar-nav > li > a {
				color: #D40511;
				font-weight: bold;
			}
			.navbar-default .navbar-nav > li > a:hover {
				color: rgba(212, 5, 17, 0.66);
				font-weight: bold;
			}			
			.navbar-default .navbar-nav > .active > a , .navbar-default .navbar-nav > .active > a:focus, .navbar-default .navbar-nav > .active > a:hover {
				color: #FFCC00;
				background-color: rgba(212, 5, 17, 0.66);
				font-weight: bold;
			}
			.navbar-default .navbar-nav>.open>a, .navbar-default .navbar-nav>.open>a:focus, .navbar-default .navbar-nav>.open>a:hover {
				color: #FFCC00;
				background-color: rgba(212, 5, 17, 0.66);
				font-weight: bold;
			}
			#login-nav input { margin-bottom: 15px;}
			ul.nav.navbar-nav {
				margin-left: 34px;
			}
			.alert-info {
				color: #6C6D71;
				background-color: #FFEA19;
				border-color: #F1E157;
			}
        </style>
    </head>
    <body>
		<div class="container-fluid">
		  <nav class="navbar navbar-default navbar-static-top" role="navigation">
               <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#"><img src="resources/dhl_logo.png">
                    </a>
                </div>
                <div class="collapse navbar-collapse" role="navigation">
					<ul class="nav navbar-nav">
						<li class="active"><a href="home">DHL Foosball Tournament Home</a></li>
						<li><a href="signup">Sign In</a></li>
				   </ul>
				</div>           
			</nav>
		</div>
		<br>
        <div class="container-fluid">		
            <div class="row">			
                <div class="col-sm-7 col-xs-offset-1">
					<div class="row">
						<h2><span class="label label-info">Tournament Progress</span></h2>
					</div>
					<br>
					<div class="row">
						<div id="minimal">
							<script>
								$(document).ready(function() {
									//Handles menu drop down
									$('.dropdown-menu').find('form').click(function (e) {
										e.stopPropagation();
									});
									
									$.ajax({
											type: 'GET',
											url: 'getfoosball.html',
											contentType: 'application/json; charset=utf-8',
											dataType: 'json',
											success: function(result) {
												var container = $('#minimal');
												container.bracket({
													init: result
													//save: saveFn
												});
											},error:function(jqXHR, textStatus, errorThrown){
												$('#minimal').append('<div class="alert alert-danger" role="alert" style="width:470px;">' +
												'<Strong>Failed to the load tournament bracket.' +
												'</strong> Contact the site admins.</div>');
											}							
									  });
								});
							</script>
						</div>
					</div>
				</div>		
            </div>
			<hr>
			<div class="row">
				<div class="col-sm-4 col-xs-offset-1">
					<div class="row">
						<h2><span class="label label-info">Teams</span></h2>
					</div>
					<br>
					<div class="row">
						<table class="table table-bordered table-striped table-hover">
							<thead>
								<tr>
									<th>Team</th>
									<th>Players</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Team 1</td>
									<td>John / Doe</td>
								</tr>
								<tr>
									<td>Team 2</td>
									<td>Tom / Cruise</td>
								</tr>
								<tr>
									<td>Team 3</td>
									<td>Brad / Pitt</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<hr>
        </div>		
		<footer class="pull-right">
			<p style="margin-top: 4px;margin-right:20px;color: lightgrey;font-size:11px">&copy; Development Team</p>
		</footer>
    </body>
</html>