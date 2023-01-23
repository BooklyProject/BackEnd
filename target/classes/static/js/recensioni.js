function showRec() {
    const btn = document.getElementById("mostraRecensioni");

    if(btn.innerText === "Visualizza recensioni"){
        document.getElementById('recensioni').style.display = "block";
        btn.innerText = "Mostra meno";
    } else{
        btn.innerText = "Visualizza recensioni";
        document.getElementById('recensioni').style.display = "none";
    } 
}

function showComments() {
    document.getElementById("commenti").style.display = "block";
}

function hideComments() {
    document.getElementById("commenti").style.display = "none";
} 

//const ratings = document.getElementById('rating1'); const rating1 = new CDB.Rating(ratings);
//rating1.getRating;