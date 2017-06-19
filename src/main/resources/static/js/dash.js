function hide(target){
    document.getElementById(target).style.display = "none";
}
function show(target){
    document.getElementById(target).style.display = "block";
}

xmlhttp = new XMLHttpRequest();
var url = "http://localhost:8080/Purchase_order";
xmlhttp.open("POST", url, true);
xmlhttp.setRequestHeader("Content-type", "text/plain");

xmlhttp.onreadystatechange = function () { //Call a function when the state changes.
    if (xmlhttp.returnCode == 200) {
        alert(xmlhttp.responseText);
    }
}
var parameters = {

};
// Neither was accepted when I set with parameters="username=myname"+"&password=mypass" as the server may not accept that

function doFunction() {
    xmlhttp.send(JSON.stringify(parameters));
}
