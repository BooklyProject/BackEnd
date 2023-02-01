const passwordInput2 = document.getElementById("password");
const confirmPasswordInput2 = document.getElementById("confirmPassword");
const submitButton2 = document.querySelector("button[type='submit']");

submitButton2.addEventListener("click", function(event) {
    event.preventDefault();
    let errorMessage = "";
    const errorAlert = document.querySelector(".alert.alert-danger");

    // Rimuovi eventuali messaggi di errore esistenti
    if (errorAlert) {
        errorAlert.remove();
    }

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
        // Mostra il messaggio di errore come un messaggio pop-up Bootstrap
        const errorAlert = document.createElement("div");
        errorAlert.className = "alert alert-danger";
        errorAlert.innerHTML = errorMessage;
        passwordInput2.parentNode.insertBefore(errorAlert, passwordInput2);
    } else if (passwordInput2.value !== confirmPasswordInput2.value) {
        // Mostra un messaggio di errore personalizzato per le password non coincidenti
        const errorAlert = document.createElement("div");
        errorAlert.className = "alert alert-danger";
        errorAlert.innerHTML = "Le password non coincidono.";
        passwordInput2.parentNode.insertBefore(errorAlert, passwordInput2);
    } else {
        // Invia il form se non ci sono errori
        this.closest("form").submit();
    }
});

