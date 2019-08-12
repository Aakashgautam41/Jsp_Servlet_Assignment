<!doctype html>
<html lang="en">
  <head>
  
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link href="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/theme-default.min.css"
    rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="style.css">
    <title>Login/Register</title>
   	<script>
	  var isLoggedIn = "<%= (String)session.getAttribute("username")%>";
	  if(isLoggedIn !== "null"){
	      window.location.href="welcome.jsp";
	  }
	</script>
  </head>
  <body>
  	<%@ include file="navbar.jsp" %>   <!-- Navbar Component -->
  	
    <div class="container">
    	<div class="jumbotron bg-white">
		  <h1 class="display-4">Log In</h1>
	    	<!-- Register Form -->
			<form class="needs-validation hidden register-form" action="registerServlet"  method="post">
			  <div class="form-row">
			    <div class="col-md-3 mb-3">
			      <label for="validationCustom01">First name</label>
			      <input type="text" class="form-control" id="validationCustom01" placeholder="First name"  name="fname" data-validation="length" data-validation-length="min2" data-validation-error-msg="First name must be greater than 2 letters"  >
			    </div>
			    <div class="col-md-3 mb-3">
			      <label for="validationCustom02">Last name</label>
			      <input type="text" class="form-control" id="validationCustom02" placeholder="Last name" name="lname" data-validation="length" data-validation-length="min2" data-validation-error-msg="Last name must be greater than 2 letters">
			    </div>
			    <div class="col-md-3 mb-3">
			      <label for="validationCustomEmail">Email</label>
			      <div class="input-group">
			        <div class="input-group-prepend">
			          <span class="input-group-text" id="inputGroupPrepend">@</span>
			        </div>
			        <input type="email" class="form-control" id="email" placeholder="Email" aria-describedby="inputGroupPrepend" name="user-email_confirmation" data-validation="email" data-validation-error-msg="Please enter a valid email address">
			      	
			      	
			      </div>
			      <span class="form-error" id="email-error-msg"></span>
			    </div>
			       <div class="col-md-3 mb-3">
			      <label for="validationCustomEmail">Confirm Email</label>
			      <div class="input-group">
			        <div class="input-group-prepend">
			          <span class="input-group-text" id="inputGroupPrepend">@</span>
			        </div>
			        <input type="email" class="form-control" id="confirm-email" placeholder="Confirm Email" aria-describedby="inputGroupPrepend">
			  		
			      </div>
			      <span class="form-error" id="confirm-email-error-msg"></span>
			    </div>
			  </div>
			  <div class="form-row">
			    <div class="col-md-6 mb-3">
			      <label for="validationCustom03">Password</label>
			      <input type="password" class="form-control" id="validationCustom03" placeholder="Password" name="password_confirmation" data-validation="strength" data-validation-strength="5"  data-toggle="tooltip" data-placement="top" title="Strong password consists numbers, digits & special characters and min 5 characters">
			    
			    </div>
			    <div class="col-md-3 mb-3">
		       	  <label for="validationCustom03">Confirm Password</label>
			      <input type="password" class="form-control" id="validationCustom03" placeholder="Confirm Password" name="password" data-validation="confirmation" data-validation-error-msg="Passwords does not match">
			   
			    </div>
			    <div class="col-md-3 mb-3">
			      <label for="validationCustom05">Zip</label>
			      <input type="number" class="form-control" id="validationCustom05" placeholder="Zip" name="zip" data-validation="length" data-validation-length="6-6" data-validation-error-msg="Zip code must be of 6 digits">
			    
			    </div>
			  </div>
			  
  			  <div class="form-row">
			    <div class="col-md-12 mb-3">
			      <label for="validationCustom03">Address</label>
			      <input type="text" class="form-control" id="validationCustom06" placeholder="Address"   name="address" data-validation="length" data-validation-length="10-500" data-validation-error-msg="Address should be between 10-500 characters">
			  
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
			<form class=" login-form" action="loginServlet" method= "post">
			  <div class="form-group">
			    <label for="exampleInputEmail1">Email address</label>
			    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email"  name="email">
			    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
			  </div>
			  <div class="form-group">
			    <label for="exampleInputPassword1">Password</label>
			    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password"  name="password">
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
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
	
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
	
	<script>
	  $.validate({
		  modules: 'security',
          onModulesLoaded: function () {

              // Show strength of password
              $('input[name="password_confirmation"]').displayPasswordStrength();

              // Bind card type to card number validator
              $('#card').on('change', function () {
                  var card = $(this).val();
                  $('input[name="ccard_num"]').attr('data-validation-allowing', card);
              });
          }
      });
	</script>
	<script>
		$("#btnSubmit").click(function(){
			var email = $("#email").val();
			var confirmEmail = $("#confirm-email").val();
			console.log(email);
			
			if(email != confirmEmail){
				$("#confirm-email-error-msg").text("Entered email does not match");
			}
			else if(!confirmEmail.includes(".com")){
				$("#confirm-email-error-msg").text("Plese enter a valid email address");
			}
		});
		
		$(function () {
			  $('[data-toggle="tooltip"]').tooltip()
		})
		
	</script>

  </body>
</html>