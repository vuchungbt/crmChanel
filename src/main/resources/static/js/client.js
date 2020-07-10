
$(function () {
   // VARIABLES =============================================================
   var TOKEN_KEY = "jwtToken";
   var $notLoggedIn = $("#notLoggedIn");

   // FUNCTIONS =============================================================
   function getJwtToken() {
      return localStorage.getItem(TOKEN_KEY);
   }

   function setJwtToken(token) {
      localStorage.setItem(TOKEN_KEY, token);
   }

   function removeJwtToken() {
      localStorage.removeItem(TOKEN_KEY);
   }
   function createAuthorizationTokenHeader() {
      var token = getJwtToken();
      if (token) {
         return {"Authorization": "Bearer " + token};
      } else {
         return {};
      }
   }

   function doLogin(loginData) {
            $.ajax({
                url: "/api/auth",
                type: "POST",
                data: JSON.stringify(loginData),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data, textStatus, jqXHR) {
                    console.log("id_token",data.id_token);
                   setJwtToken(data.id_token);
                   console.log(getJwtToken());
                   document.location = './index.html';
                },
                error: function (jqXHR, textStatus, errorThrown) {
                   if (jqXHR.status === 401) {
                       alert(jqXHR.status);
                   } else {
                       alert("else");
                   }
                }
             });
   }
   //--------------request to login -----------
  
       if(getJwtToken()) {
            $.ajax({
                url: "/api/me",
                type: "GET",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                headers: createAuthorizationTokenHeader(),
                success: function (data, textStatus, jqXHR) {
                   document.location = './index.html';
                },
                error: function (jqXHR, textStatus, errorThrown) {
                   if (jqXHR.status === 401) {
                       alert("401 error");
                   } else {
                       alert("else error");

                   }
                }
             });
           
       }


     //---------login submit---------
      $("#loginForm").submit(function (event) {
      event.preventDefault();
      console.log("submit...");

      var $form = $(this);
      var formData = {
         username: $form.find('input[name="username"]').val(),
         password: $form.find('input[name="password"]').val()
      };
      console.log("inlogin:");

      doLogin(formData);
   });
});
