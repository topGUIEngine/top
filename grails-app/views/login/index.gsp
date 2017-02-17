<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Open Clicker:Login</title>
    <asset:stylesheet src="bootstrap.css"/>
    <asset:javascript src="jquery-2.2.0.min.js"/>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <asset:javascript src="Authenticator.js"/>
</head>

<body onload="onLoaded()">

<div id="signInBtn"></div>


<script>

    function onLoaded() {

        function onUserSignIn(uInfo) {
            if (uInfo.email.indexOf("oswego.edu") != -1 && auth.isValidInfo()) {
                auth.sendSigninToServer(function (result) {
                    if (result.success) {
                        auth.showDashboard();
                    } else {
                        alert(result.message);
                        auth.rejectGoogleUser();
                    }
                });
            } else {
                alert("Use oswego.edu email");
            }
        }

        function onUserSignOut() {
            auth.rejectGoogleUser(function () {
            });
            auth.sendSignoutToServer(function () {
            });
        }

        var auth = new Authenticator(onUserSignIn, onUserSignOut);
        auth.configureButton('signInBtn', {
            width: 240,
            height: 50,
            longtitle: true,
            theme: 'dark'
        });

    }


</script>
</body>
</html>