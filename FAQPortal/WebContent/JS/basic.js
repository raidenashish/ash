function validateForm() {
	
    var user_id= document.forms["login_form"]["userid"].value;
    if (user_id == "") {
        alert("User IDmust be filled out");
        return false;
    }
    var user_password=document.forms["login_form"]["userpassword"].value;
    if (user_password == "") {
        alert("Password must be filled out");
        return false;
    }
    else
    {
    	alert("data to be passed to backend");
    	
    }

}

 FB.ui(
 {
  method: 'share',
  href: 'https://developers.facebook.com/docs/'
}, function(response){});