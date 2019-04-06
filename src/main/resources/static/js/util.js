var address = "http://localhost:8080/user/";
// var address = "http://120.77.150.210:8081/user/"
function setCookie(name, value, days) {
    var date = new Date;
    date.setTime(date.getTime() + 24*60*60*1000*days);
    window.document.cookie = name + "=" + value + ";path=/;expires=" + date.toGMTString();
    console.log(window.document.cookie)
}

function getCookie(name) {
    var v = window.document.cookie.match('(^| )' + name + '=([^;]*)(;|$)');
    return v ? JSON.parse(v[2]) : null;
}