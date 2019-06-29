
const setExpires = function(exdays) {

    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    return d.toUTCString();

}

export default {
    // Alle basis functies om met cookies te kunnen werken.

    setCookie(cname, cvalue='', exdays=null) {

        var expires = "expires=Thu, 01 Jan 1970 00:00:00 GMT";
        if (exdays!=null)
            expires = "expires=" + setExpires(exdays);

        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";

    },

    getCookie(cname) {

        var name = cname + "=";
        var decodedCookie = decodeURIComponent(document.cookie);
        var ca = decodedCookie.split(';');

        for (var i = 0; i < ca.length; i++){
            var c = ca[i];
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
            }
        }
        
        // If nothing found
        return "";

    },

    deleteCookie(cname) {

    },

    checkCookie(cname) {
        if (this.getCookie(cname) != "") return true;
        else return false;
    },

    // Als het cookie "loggedIn" een value heeft dan is er iemand ingelogd op het apparaat.
    checkLoggedIn() {
        if (this.getCookie("loggedIn") != "") return true;
        else return false;
    }
}


