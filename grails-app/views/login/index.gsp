<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    %{--<meta name="google-signin-scope" content="profile email">--}%
    %{--<meta name="google-signin-client_id" content="460384913941-o01p3pu021rrnq6ibbanenfrmg6r87at.apps.googleusercontent.com">--}%
    <title>Open Clicker:Login</title>
    <asset:stylesheet src="bootstrap.css"/>
    <asset:javascript src="jquery-2.2.0.min.js"/>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <asset:javascript src="Authenticator.js"/>
</head>

<body onload="onLoaded()">

<div id="signInBtn"></div>

<button id="testme" class="btn btn-lg btn-primary">Click me</button>

%{--<div class="g-signin2"  data-theme="dark"></div>--}%



%{--<a id="testme" href="${createLink([controller: 'dashboard', action: 'index'])}">click me</a>--}%

%{--<button class="btn btn-lg btn-primary" onclick="theButtonClick()">I am a button</button>--}%

%{--<script>

    function theButtonClick() {
//        document.getElementById('testme').click();
        document.location.href = "/";
    }

    function onSignIn(googleUser) {
        // Useful data for your client-side scripts:
        var profile = googleUser.getBasicProfile();
        console.log("ID: " + profile.getId()); // Don't send this directly to your server!
        console.log('Full Name: ' + profile.getName());
        console.log('Given Name: ' + profile.getGivenName());
        console.log('Family Name: ' + profile.getFamilyName());
        console.log("Image URL: " + profile.getImageUrl());
        console.log("Email: " + profile.getEmail());

        // The ID token you need to pass to your backend:
        var id_token = googleUser.getAuthResponse().id_token;
        console.log("ID Token: " + id_token);
    }



</script>--}%


<script>

    function onLoaded() {
        var signInManager = new Authenticator(
            function (profile) {
                console.log("Callled");
                if(signInManager.isValidInfo()) {
                    signInManager.sendSigninToServer(function (data) {
                        signInManager.showDashboard();
                    });
                }
            },

            function () {

            });

        signInManager.configureButton({
            width: 240,
            height: 50,
            longtitle: true,
            theme: 'dark'
        });

        var btn = document.getElementById('testme');
        btn.addEventListener('click', function () {
            signInManager.rejectGoogleUser(function () {
                console.log('it is done');
            })
        });
    }


</script>

</body>
</html>