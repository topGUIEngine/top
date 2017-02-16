/*
 *This file depends on jquery and the platform.js file from google.
 * @author Jeff Registre
 */


/**
 * Outer Object.
 * @type {{SigninManager, SignoutManager}}
 */
var Authenticator = (function () {

    var auth2 = null,
        googleUser = null;

    /**
     * Private function. Called to initialise google auth settings.
     */
    function initOAuth(onComplete) {
        gapi.load('auth2', function () {
            auth2 = gapi.auth2.init({
                client_id: '460384913941-o01p3pu021rrnq6ibbanenfrmg6r87at.apps.googleusercontent.com',
                scope: 'profile email'
            });
            onComplete();
        });
    }

    /**
     * Function to create a json with the basic profile information.
     * @return {{fullName: string, firstName: *, lastName: *, imageUrl: *, email: *}} | undefined.
     */
    function transcribeUserData() {
        var profile = null;
        if (googleUser !== null && googleUser !== undefined) {
            profile = googleUser.getBasicProfile();
            if (profile !== undefined) {
                return {
                    firstName: profile.getGivenName(),
                    lastName: profile.getFamilyName(),
                    imageUrl: profile.getImageUrl(),
                    email: profile.getEmail(),
                    idToken: googleUser.getAuthResponse().id_token
                }
            }
        }
        return undefined;
    }

    function translateServerResponse(data) {
        return data;
    }

    function queryServer(endPoint, data, onComplete) {
        $.ajax({
            url: endPoint,
            data : data,
            success : function (value) {
                onComplete(translateServerResponse(value));
            },
            failure : function (error) {
                onComplete(translateServerResponse(error));
            }
        });
    }


    /**
     * Empty constructor.
     * @constructor
     */
    function Authenticator(onSignin, onSignout) {
        this.onSignin = onSignin;
        this.onSignout = onSignout;
        this.initialise();
    }

    /**
     * Call this method to initialise the oAuth2 for login.
     *
     * has completed google signin.
     */
    Authenticator.prototype.initialise = function () {
        var cur = this;
        initOAuth(function () {

            // set up listener.
            auth2.currentUser.listen(function (gUser) {
                googleUser = gUser;

                //user is signed in
                if (auth2.isSignedIn.get() == true) {
                    if (cur.onSignin) {
                        cur.onSignin(transcribeUserData());
                    }
                }
                // user not signed in
                else {
                    if (cur.onSignout) {
                        cur.onSignout();
                    }
                }
            });

            if (auth2.isSignedIn.get() == true) {
                auth2.signIn();
                googleUser = auth2.currentUser.get();
            }

        });

    };

    Authenticator.prototype.configureButton = function (buttonOptions) {
        gapi.signin2.render('signInBtn', {
            'width': buttonOptions.width,
            'height': buttonOptions.height,
            'longtitle': buttonOptions.longtitle,
            'theme': buttonOptions.theme
        });
    };

    /**
     * Function to check if the user information should be accepted.
     * @return {Boolean} - Returns true if the current googleUser object contains
     * info that should be accepted.
     */
    Authenticator.prototype.isValidInfo = function () {
        var profile = transcribeUserData();
        var result = false;
        if (profile !== undefined && profile !== null) {
            var email = profile.email;
            if (email.indexOf('oswego.edu') !== -1) {
                result = true;
            }
        }
        return result;
    };

    /**
     * Function to query google to invalidate that user sign in and make it no longer use-able.
     * Use if you don't plan on accepting the user information.
     * @param {Function} onComplete - Function to call once the action is done.
     */
    Authenticator.prototype.rejectGoogleUser = function (onComplete) {
        gapi.auth2.getAuthInstance().signOut().then(onComplete);
    };

    /**
     * Function to send google user data to the server
     * @param onComplete
     */
    Authenticator.prototype.sendSigninToServer = function (onComplete) {
        queryServer('/login/authenticate', transcribeUserData(), onComplete);
    };

    Authenticator.prototype.sendSignoutToServer = function (onComplete) {
        queryServer('/logout', googleUser.getAuthResponse().id_token, onComplete)
    };

    /**
     * Function to redirect the browser to the dashboard. This should be called
     * after everything has been verified by the server.
     */
    Authenticator.prototype.showDashboard = function () {
        document.location.href = "/";
    };

    Authenticator.prototype.showLoginScreen = function () {
        document.location.href = "/login";
    };

    return Authenticator;

})();

