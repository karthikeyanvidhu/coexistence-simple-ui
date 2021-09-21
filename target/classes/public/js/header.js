    $(document).ready(function(){     
          var loggedIn=localStorage['loggedIn'];

          if(loggedIn=='' || loggedIn==null){
	      $('#nm').hide();
	      $('#em').hide();
          $('#logout').hide();

          }
          
    	$("#logout").on("click", function(){
    		signOut() ; 
    		$('#user').html("");
    		localStorage['loggedIn']='';
    		window.location.href = 'index.html';
    		});
    	
    });
      function onSignIn(googleUser) {
        // Useful data for your client-side scripts:
        var profile = googleUser.getBasicProfile();
        console.log("ID: " + profile.getId()); // Don't send this directly to your server!
        $('#user').html('Admin User:'+profile.getName());
        console.log('Full Name: ' + profile.getName());
        console.log('Given Name: ' + profile.getGivenName());
        console.log('Family Name: ' + profile.getFamilyName());
        console.log("Image URL: " + profile.getImageUrl());
        console.log("Email: " + profile.getEmail());
        // The ID token you need to pass to your backend:
        var id_token = googleUser.getAuthResponse().id_token;
        console.log("ID Token: " + id_token);
        

	      $('#nm').show();
	      $('#em').show();
	      $('#logout').show();
		  localStorage['loggedIn']='true';
      }
      
      function signOut() {
    	  
    	    var auth2 = gapi.auth2.getAuthInstance();
    	    auth2.signOut().then(function () {
    	      alert("User signed out");
    	      $('#nm').hide();
    	      $('#em').hide();
    	      $('#logout').hide();
    	      console.log('User signed out.');
    	    });
    	  }