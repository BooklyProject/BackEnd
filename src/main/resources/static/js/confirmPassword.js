const passwordInput = document.getElementById("password");
const confirmPasswordInput = document.getElementById("confirmPassword");

function validatePassword() {
    if (passwordInput.value !== confirmPasswordInput.value) {
        passwordInput.style.borderColor = "red";
        confirmPasswordInput.style.borderColor = "red";
    } else {
        passwordInput.style.borderColor = "initial";
        confirmPasswordInput.style.borderColor = "initial";
    }
}
    passwordInput.addEventListener("input", validatePassword);
    confirmPasswordInput.addEventListener("input", validatePassword);
