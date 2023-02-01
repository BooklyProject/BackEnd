function showPopup() {
    alert("Email correttamente inviata. Controlla nella tua casella di posta.");
}

document.querySelector('[type="submit"]').addEventListener("click", showPopup);