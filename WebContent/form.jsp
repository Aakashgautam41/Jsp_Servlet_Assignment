<!doctype html>
<html lang="en">
  <head>
  
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="style.css">
    <title>Login/Register</title>
  </head>
  <body>
  	<%@ include file="navbar.jsp" %>   <!-- Navbar Component -->
  	
    <div class="container">
    	<div class="jumbotron bg-white">
		  <h1 class="display-4">Log In</h1>
	    	<!-- Register Form -->
			<form class="needs-validation hidden register-form" novalidate  action="registerServlet"  method="post">
			  <div class="form-row">
			    <div class="col-md-3 mb-3">
			      <label for="validationCustom01">First name</label>
			      <input type="text" class="form-control" id="validationCustom01" placeholder="First name"  name="fname" required >
			      <div class="valid-feedback">
			        Looks good!
			      </div>
			    </div>
			    <div class="col-md-3 mb-3">
			      <label for="validationCustom02">Last name</label>
			      <input type="text" class="form-control" id="validationCustom02" placeholder="Last name" value="Otto"   name="lname"  required>
			      <div class="valid-feedback">
			        Looks good!
			      </div>
			    </div>
			    <div class="col-md-3 mb-3">
			      <label for="validationCustomEmail">Email</label>
			      <div class="input-group">
			        <div class="input-group-prepend">
			          <span class="input-group-text" id="inputGroupPrepend">@</span>
			        </div>
			        <input type="email" class="form-control" id="validationCustomUsername" placeholder="Email" aria-describedby="inputGroupPrepend"  name="email"  required>
			        <div class="invalid-feedback">
			          Please enter a valid email.
			        </div>
			      </div>
			    </div>
			       <div class="col-md-3 mb-3">
			      <label for="validationCustomEmail">Confirm Email</label>
			      <div class="input-group">
			        <div class="input-group-prepend">
			          <span class="input-group-text" id="inputGroupPrepend">@</span>
			        </div>
			        <input type="email" class="form-control" id="validationCustomUsername" placeholder="Confirm Email" aria-describedby="inputGroupPrepend" required>
			        <div class="invalid-feedback">
			          Provided email does not match
			        </div>
			      </div>
			    </div>
			  </div>
			  <div class="form-row">
			    <div class="col-md-6 mb-3">
			      <label for="validationCustom03">Password</label>
			      <input type="password" class="form-control" id="validationCustom03" placeholder="Password"   name="password"  required>
			      <div class="invalid-feedback">
			       		Password must be 6 characters long
			      </div>
			    </div>
			    <div class="col-md-3 mb-3">
		       	  <label for="validationCustom03">Confirm Password</label>
			      <input type="password" class="form-control" id="validationCustom03" placeholder="Confirm Password" required>
			      <div class="invalid-feedback">
			       		Passwords does not match
			      </div>
			    </div>
			    <div class="col-md-3 mb-3">
			      <label for="validationCustom05">Zip</label>
			      <input type="text" class="form-control" id="validationCustom05" placeholder="Zip"   name="zip"  required>
			      <div class="invalid-feedback">
			        Please provide a valid zip.
			      </div>
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="form-check">
			      <input class="form-check-input" type="checkbox" value="" id="">
			      <label class="">
			        Subscribe to emails
			      </label>
	
			    </div>
			  </div>
			  <button class="btn btn-primary" type="submit"  id="btnSubmit">Submit form</button>
			  <div>
			  	<span class="form-triggers"><a href="#" id="login">Already have an account? Sign in instead.</a></span>
			  </div>
			  
			</form>
		
			<!-- Login Form -->
			<form class=" login-form">
			  <div class="form-group">
			    <label for="exampleInputEmail1">Email address</label>
			    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
			    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
			  </div>
			  <div class="form-group">
			    <label for="exampleInputPassword1">Password</label>
			    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
			  </div>
			  <div class="form-group form-check">
			    <input type="checkbox" class="form-check-input" id="exampleCheck1">
			    <label class="form-check-label" for="exampleCheck1">Remember me</label>
			  </div>
			  <button type="submit" class="btn btn-primary">Submit</button>
			  <div>
			  	<span class="form-triggers"><a href="#" id="register">Create an account/Register</a></span>
			  </div>
			</form>
			
		</div>
    
    </div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script>
	// Example starter JavaScript for disabling form submissions if there are invalid fields
	(function() {
	  'use strict';
	  window.addEventListener('load', function() {
	    // Fetch all the forms we want to apply custom Bootstrap validation styles to
	    var forms = document.getElementsByClassName('needs-validation');
	    // Loop over them and prevent submission
	    var validation = Array.prototype.filter.call(forms, function(form) {
	      form.addEventListener('submit', function(event) {
	        if (form.checkValidity() === false) {
	          event.preventDefault();
	          event.stopPropagation();
	        }
	        form.classList.add('was-validated');
	      }, false);
	    });
	  }, false);
	})();
	</script>
	
	<script>
		$("#register").click(function(){
			$(".login-form").addClass("hidden");
			$(".register-form").removeClass("hidden");
			$(".display-4").text("Register");
		});
		$("#login").click(function(){
			$(".register-form").addClass("hidden");
			$(".login-form").removeClass("hidden");
			$(".display-4").text("Log In");
		});
	</script>

	
	
  </body>
</html>