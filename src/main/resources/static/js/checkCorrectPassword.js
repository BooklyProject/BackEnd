const passwordInput2 = document.getElementById("password");
const confirmPasswordInput2 = document.getElementById("confirmPassword");
const submitButton2 = document.querySelector("button[type='submit']");
submitButton2.addEventListener("click", function(event) {
    event.preventDefault();
    let errorMessage = "";
    if (passwordInput2.value.length < 8) {
    errorMessage = "La password deve essere lunga almeno 8 caratteri.\n";
    }
    if (!/[A-Z]/.test(passwordInput2.value)) {
    errorMessage += "La password deve contenere almeno una lettera maiuscola.\n";
    }
    if (/\s/.test(passwordInput2.value)) {
    errorMessage += "La password non deve contenere spazi.";
    }
    if (errorMessage) {
    alert(errorMessage);
    } else if (passwordInput2.value !== confirmPasswordInput2.value) {
        alert("Le password non coincidono.");
        } else {
            // Invia il form se non ci sono errori
            this.closest("form").submit();
    }
});

