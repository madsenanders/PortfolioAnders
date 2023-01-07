let slideIndex = 0;
let i;
let slides = document.getElementsByClassName("mySlides");
let dots = document.getElementsByClassName("dot");

for (i = 0; i < dots.length; i++) {
        if (i === 0) {

            dots[i].onclick = use0;
        }

        else if (i === 1) {
            dots[i].onclick = use1;
        }

        else if (i === 2){
            dots[i].onclick = use2;
        }

        else if (i === 3){
            dots[i].onclick = use3;
        }

        else if (i === 4){
            dots[i].onclick = use4;
        }

        else {
            dots[i].onclick = use5;
        }
}
showSlides();

function showSlides() {
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    slideIndex++;
    if (slideIndex > slides.length) {
        slideIndex = 1
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");

    }
    slides[slideIndex-1].style.display = "inline";
    dots[slideIndex-1].className += " active";
    setTimeout(showSlides, 5000);
}

function adjustImage(clickedDot) {
    slideIndex = clickedDot + 1
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    slides[slideIndex-1].style.display = "inline";
    dots[slideIndex-1].className += " active";
}

function use0(){
    adjustImage(0)
}

function use1(){
    adjustImage(1)
}

function use2(){
    adjustImage(2)
}

function use3(){
    adjustImage(3)
}

function use4(){
    adjustImage(4)
}

function use5() {
    adjustImage(5)
}