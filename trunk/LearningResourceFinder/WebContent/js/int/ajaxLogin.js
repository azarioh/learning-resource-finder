function ajaxlogin() {
	var userNameOrMail = $('#userNameOrMail').val();
	var password  = $('#password').val();
			
	$.ajax({
		type : "POST",
	    url : '/ajax/loginsubmit',
	    data: "userNameOrMail="+userNameOrMail+"&password="+password,
	    success : function(data) {
	      location.reload();
	    },
	    error : function(data) {
	    	alert(data);
	    }
	}); 
}

$(document).ready(function() {
	$('#registerForm').submit(ajaxRegister);
});
  
function ajaxRegister(e) {
	e.preventDefault();
	var emailRegister = $('#emailRegister').val();
	var usernameRegister  = $('#usernameRegister').val();
	var passwordRegister  = $('#passwordRegister').val();
	var userTypeRegister  = $('#userTypeRegister').val();
			
	$.ajax({
		type : "POST",
	    url : '/ajax/registersubmit',
	    data: "emailRegister="+emailRegister+"&usernameRegister="+usernameRegister+"&passwordRegister="+passwordRegister+"&userTypeRegister="+userTypeRegister,
	    success : function(data) {	
	    		 location.reload();
	    },
	    error : function(data) {
	    	alert("erreur register "+data);
	    	return false;
	    }
	   
	}); 
}

function submitenter(myfield,e)
{
    if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13))
    {
        myfield.form.submit();
    }
}