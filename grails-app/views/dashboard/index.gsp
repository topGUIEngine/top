<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id"
          content="460384913941-o01p3pu021rrnq6ibbanenfrmg6r87at.apps.googleusercontent.com">
    <title>Open Clicker:Dashboard</title>
    <asset:stylesheet src="bootstrap.css"/>
</head>

<body onload="onLoaded()">
<h1>This is your dashboard</h1>

<a class="btn btn-primary" onclick="signOut()" href="#">Logout</a>

<script>
    function signOut() {
        console.log(gapi);
        var auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(function () {
            console.log('User signed out.');
        });
    }
</script>
<script src="https://apis.google.com/js/platform.js?" async defer></script>
<script>
    function onLoaded() {
        gapi.load('auth2', function () {
            gapi.auth2.init();
        });
    }
</script>

</body>

</html>