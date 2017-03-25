<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Instructor</title>
    <asset:stylesheet href="bootstrap.min.css"/>
    <asset:stylesheet href="bootstrap-theme.min.css"/>
</head>
<body>
    <div class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
            <asset:image class="img-responsive navbar-brand" src="logo.png"/>
            <a class="navbar-brand">Instructor Dashboard</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <button onclick="logout()" class="btn btn-default navbar-right navbar-btn">Logout</button>
            </ul>
        </div>
    </div>

<asset:javascript src="jquery-3.2.0.min.js"/>
<script src="https://apis.google.com/js/platform.js"></script>
<asset:javascript src="auth/config.js"/>
<asset:javascript src="auth/logout.js"/>
</body>
</html>