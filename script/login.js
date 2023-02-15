$(document).ready(function() {

    $('#login-submit').click(function(event) {
        event.preventDefault();
        var userName = $('#user-name').val();
        var password = $('#password').val();
        logIn(userName, password);
    });

    function logIn(u, p) {
        var data = {username: u, password: p};
        $.ajax({
             type: "POST",
             url: "http://localhost:8080/api/authenticate",
             dataType: 'json',
             data: JSON.stringify(data),
             headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
             success: function (result, status, xhr) {
                 sessionStorage.setItem("token", "Bearer " + result.id_token);
                 window.location.href = "dashboard.html";
             },
             error: function (xhr, status, error) {
                 console.log(error);
             },
         });
    }
});