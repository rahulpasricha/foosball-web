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
				background-color: #FFFD9C;
				border-color: #F1E157;
			}
			a#logout {
			    font-size: 12px;
			    line-height: 1.5;
			    border-radius: 3px;
			    color: #fff;
			    background-color: #337ab7;
			    border-color: #2e6da4;
			    display: inline-block;
			    padding: 6px 12px;
			    margin-bottom: 0;
			    font-size: 14px;
			    font-weight: 400;
			    line-height: 1.42857143;
			    text-align: center;
			    white-space: nowrap;
			    vertical-align: middle;
			    -ms-touch-action: manipulation;
			    touch-action: manipulation;
			    cursor: pointer;
			    -webkit-user-select: none;
			    -moz-user-select: none;
			    -ms-user-select: none;
			    user-select: none;
			    background-image: none;
			    border: 1px solid transparent;
			    border-radius: 4px;
			    margin-right: 8px;
			}
			a#logout:hover {
			    color: #fff;
			    background-color: #286090;
			    border-color: #204d74;
			}
			#welcomeUser {
				margin-top: 15px;
			    font-size: 20px;
			    margin-right: 10px;
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
						<ul class="nav navbar-nav pull-right" style="margin-top:4px;">
							<li><h4 id="welcomeUser"><small>Welcome <strong><sec:authentication property="principal.username" /></strong></small></h4></li>
							<li>&nbsp;&nbsp;&nbsp;</li>
							<li><a href=<c:url value="j_spring_security_logout" /> class="btn btn-primary btn-sm" id="logout" style="margin-top:7px;">Logout</a></li>
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
													$('#minimal').append('<div class="alert alert-danger" role="alert" style="width:430px;">' +
													'<Strong>Failed to load tournament bracket.' +
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
						<h2><span class="label label-info">Rate Your Opponents</span></h2>
					</div>
					<br>
					<div class="row">
						<form role="form" id="playerRatingsForm">
							<h4><small>Rate other players based on their skill level. This will be taken into account when the teams are drawn and will make
							sure the teams are well balanced.</small></h4>
							<hr>
							<table class="table table-bordered table-striped table-hover table-condensed">
								<thead>
									<tr>
										<th>Player</th>
										<th>Rating</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>John Doe</td>
										<td>
											<select id="playerRatings" name="playerRatings" class="form-control input-sm">
												<option value=""></option>
												<option value="BEGINNER">BEGINNER</option>
												<option value="INTERMEDIATE">INTERMEDIATE</option>
												<option value="ADVANCED">ADVANCED</option>
												<option value="EXPERT">EXPERT</option>
											</select>
										</td>
									</tr>
									<tr>
										<td>Mark Firefox</td>
										<td>
											<select id="playerRatings" name="playerRatings" class="form-control input-sm">
												<option value=""></option>
												<option value="BEGINNER">BEGINNER</option>
												<option value="INTERMEDIATE">INTERMEDIATE</option>
												<option value="ADVANCED">ADVANCED</option>
												<option value="EXPERT">EXPERT</option>
											</select>
										</td>
									</tr>
									<tr>
										<td>Bob Chrome</td>
										<td>
											<select id="playerRatings" name="playerRatings" class="form-control input-sm">
												<option value=""></option>	
												<option value="BEGINNER">BEGINNER</option>
												<option value="INTERMEDIATE">INTERMEDIATE</option>
												<option value="ADVANCED">ADVANCED</option>
												<option value="EXPERT">EXPERT</option>
											</select>
										</td>
									</tr>
								</tbody>
							</table>
							<div class="row">
								<div class="col-xs-12 col-md-6"><input id="ratePlayersButton" type="submit" value="Rate Players" class="btn btn-success btn-block btn-lg"></div>
							</div>
						</form>
					</div>
				</div>
				<div class="col-sm-5 col-xs-offset-1">
					<div class="row">
						<h2><span class="label label-info">Submit Your Team Name</span></h2>
					</div>
					<br>
					<div class="row">
						<h3><small>Your current team name is &nbsp&nbsp</small><span class="label label-default" id="currentTeamName">TEAM 1</span></h3>
						<br>
						<div class="alert alert-info" role="alert">
						  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
						  Pro-actively updating your team name as soon as possible has an advantage.
						  When the teams are drawn, the first two teams to register their team names with us will get a bye in the first round.							  
						</div>
						<hr>
						<form role="form" id="updateTeamNameForm">
							<div class="form-group">
								<input type="text" name="teamName" id="teamName" class="form-control input-md updateteamname" placeholder="Team name" required>
							</div>
							<div class="row">
								<div class="col-xs-12 col-md-6"><input id="updateTeamNameButton" type="submit" value="Update" class="btn btn-success btn-block btn-lg"></div>
							</div>
							<br>
							<div class="form-group" id="messageDiv">
								
							</div>						
							<hr>
						</form>
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