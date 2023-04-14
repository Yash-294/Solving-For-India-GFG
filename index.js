var password = document.getElementById("inputPass");
var confirm_password = document.getElementById("inputRPass");

function validatePassword() {
    if (password.value != confirm_password.value) {
        confirm_password.setCustomValidity("Passwords Don't Match");
    } 
    else {
        confirm_password.setCustomValidity("");
    }
}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;

function myFunction() {
    var x = document.getElementById("inputPass");
    if (x.type === "password") {
      x.type = "text";
    } else {
      x.type = "password";
    }
}

