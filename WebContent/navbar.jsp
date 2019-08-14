<!doctype html>
<html lang="en">
  <head>
  
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    
  </head>
  <body>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
		  <a class="navbar-brand ml-5" href="#"><i class="fab fa-grunt"></i></a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		
		  <div class="collapse navbar-collapse" id="navbarSupportedContent">
		    <ul class="navbar-nav mr-auto">
		      <li class="nav-item active">
		        <a class="nav-link" href="index">Home <span class="sr-only">(current)</span></a>
		      </li>
		    </ul>
		    <ul class="navbar-nav mr-5">
	    	<% 
		      if(session.getAttribute("username") == null){  %>
			    <li class="nav-item">
			        <a class="nav-link" href="form">Login/Register</a>
		      	</li>
      
		    <% } else { %>
				  
	      	<li class="nav-item">
		        <a class="nav-link" href="LogoutServlet">Logout</a>
      		</li>
		    <% } %>
	      	
		    </ul>
		  </div>
		</nav>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  </body>
  
</html>