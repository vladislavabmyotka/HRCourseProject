function checkPasswordsMatch(form) {
    var password = form.password.value;
    var repeatPassword = form.repeatPassword.value;

    if (password === repeatPassword) {
        return true;
    } else {
        alert("Entered passwords do not match!");
        return false;
    }
}

function undisable(element, button) {
    document.getElementById(element.toString()).disabled=false;
    button.disabled=true;
}