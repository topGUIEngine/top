<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="google-signin-client_id" content="896100416043-v0cvdf52tteag7ha8939fog24sr7bm2g.apps.googleusercontent.com">
    <title>login</title>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
</head>
<body>

<div class="g-signin2" data-onsuccess="onSignIn"></div>


<script>
    function onSignIn(gUser) {
        console.log(gUser.getAuthResponse().id_token);
    }
</script>

</body>
</html>