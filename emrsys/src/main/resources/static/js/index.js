var password = document.getElementById("inputPass");
var confirm_password = document.getElementById("inputPass2");

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

function storeValue(){
	document.getElementById('hiddenInput').value=document.getElementById('inputState').value;
}

function storeGenderValue(){
	document.getElementById('hiddenGender').value=document.getElementById('inputGender').value;
}