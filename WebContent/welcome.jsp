<!doctype html>
<html lang="en">
  <head>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="style.css">
    <title>Welcome</title>
  </head>
  <script>  //Prevents unauthorized access to pages
	  var isLoggedIn = "<%= (String)session.getAttribute("username")%>";
	  if(isLoggedIn == "null"){
	      window.location.href="form.jsp";
	  }
  </script>
  <body>
  	<%@ include file="navbar.jsp" %>   <!-- Navbar Component -->
  	
    <div class="container display-4">
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