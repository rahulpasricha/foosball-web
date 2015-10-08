<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        </style>
		<script type="text/javascript">
			$(document).ready(function() {
			
				$('.createuser').hide();
				
				$('#createUserButton').on('click', function() {
					$('.createuser').show();
				});
				
				$('#signInButton').on('click', function() {
					$('.createuser').hide();
				});
				
				$('.createuser').change(function() {
					$('#messageDiv').html('');
				});
				
				/*$("#loginForm").submit(function( event ) {
					$('#messageDiv').html('');
					var username = $( "#username" ).val();
					var password = $( "#password" ).val();
					
					var btn = $(this).find("input[type=submit]:focus");
					var url;
					if ('signIn' == btn.attr('name')) {
						url='../login';
						alert('Logging in...');
					} else if ('createUser' == btn.attr('name')) {
					
						var firstName = $('#firstName').val();
						var lastName = $('#lastName').val();
						
						if (!firstName || !lastName) {
							$('#messageDiv').append('<div class="alert alert-danger" role="alert"><strong>Both first and last names are required</strong></div>');
							return false;
						}
						
						url='../createuser';
						alert('Creating a new user...');
					}
					
					var formArray = $('#loginForm').serializeArray();
					var formJson = $.toJSON(formArray);
					
					$.ajax ({
						type: 'POST',
						contentType: 'application/json; charset=utf-8',
						data: formJson,
						url: url,
						dataType: 'json',												
						success: function(result) {
							
						},error:function(jqXHR, textStatus, errorThrown){
							$('#messageDiv').append('<div class="alert alert-danger" role="alert">Failed : <strong>' + errorThrown + '</strong></div>');
						}			
					});
					
					event.preventDefault();
				});*/
				
			});
		</script>
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
						<li><a href="home">DHL Foosball Tournament Home</a></li>
						<li class="active"><a href="${contextPath}/signup">Sign In</a></li>
					</ul>
				</div>            
			</nav>
		</div>
		<br>
        <div class="container">		
            <div class="row">
				<div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
					<form class="form" role="form" accept-charset="UTF-8" id="loginForm" action="<c:url value='/j_spring_security_check' />" method='POST'>
						<h3>Sign In / Create a User </h3>
						<hr>
						<div class="form-group">
							<input type="username" name="username" id="username" class="form-control input-lg" placeholder="Username" required>
						</div>
						<div class="form-group">
							<input type="password" name="password" id="password" class="form-control input-lg" placeholder="Password" required>
						</div>
						<div class="form-group">
							<input type="text" name="firstName" id="firstName" class="form-control input-lg createuser" placeholder="First Name">
						</div>
						<div class="form-group">
							<input type="text" name="lastName" id="lastName" class="form-control input-lg createuser" placeholder="Last Name">
						</div>
						<div class="row">
							<div class="col-xs-12 col-md-6"><input id="signInButton" type="submit" name="signIn" value="Sign In" class="btn btn-success btn-block btn-lg"></div>
							<div class="col-xs-12 col-md-6"><input id="createUserButton" type="submit" name="createUser" value="Create User" class="btn btn-primary btn-block btn-lg"></div>
						</div>
						<br>
						<div class="form-group" id="messageDiv">
							<c:if test="${not empty error}">
								<div class="alert alert-danger" role="alert">Failed : <strong>${error}</strong></div>
							</c:if>
							<c:if test="${not empty logout}">
								<div class="alert alert-info" role="alert">Logged out successfully!!!</div>
							</c:if>
						</div>
						<hr>
						<div class="form-group">
						    <div class="form-group pull-right">
								<a href="resetpassword">Forgot Password?</a>
							</div>
						</div>
						</form>
						</div>
						</div>
        </div>
		<footer class="pull-right">
			<p style="margin-top: 4px;margin-right:20px;color: lightgrey;font-size:11px">&copy; Development Team</p>
		</footer>
    </body>
</html>