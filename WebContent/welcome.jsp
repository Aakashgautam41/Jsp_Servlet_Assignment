<!doctype html>
<html lang="en">
  <head>
  
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="style.css">
    <title>Servlet Assignment</title>
  </head>
  <body>
  	<%@ include file="navbar.jsp" %>   <!-- Navbar Component -->
  	
    <div class="container">
    	Welcome, <%
    	
    	//  Get username from session
    	String username = (String) session.getAttribute("username");
    	out.println( username);
    	
    	%> 		
    </div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  </body>
</html>