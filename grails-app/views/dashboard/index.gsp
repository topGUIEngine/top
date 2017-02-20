<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id"
          content="460384913941-o01p3pu021rrnq6ibbanenfrmg6r87at.apps.googleusercontent.com">
    <title>Open Clicker:Dashboard</title>
    <asset:stylesheet src="bootstrap.css"/>
    <asset:javascript src="jquery-2.2.0.min.js"/>
    <script src="https://apis.google.com/js/platform.js?" async defer></script>
    <asset:javascript src="Authenticator.js"/>
</head>

<body onload="onLoaded()">
<h1>This is your dashboard</h1>

<a id="signoutbtn" class="btn btn-primary" href="#">Logout</a>

<script>
    function onLoaded() {
        function onSignout() {
            auth.rejectGoogleUser(function () {
                auth.sendSignoutToServer(function () {
                    auth.showLoginScreen();
                })
            })
        }

        function onSignIn() {}

        var auth = new Authenticator(onSignIn, onSignout);

        document.getElementById('signoutbtn').onclick = function () {
            auth.rejectGoogleUser();
        }

    }
</script>
</body>

</html>