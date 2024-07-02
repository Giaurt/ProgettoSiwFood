//// scripts.js
//let currentSlide = 0;
//const slider = document.querySelector('.slider');
//let scrollTimeout;
//
//function showSlide(index) {
//    const slides = document.querySelectorAll('.flex-item');
//    if (index >= slides.length) {
//        currentSlide = 0;
//    } else if (index < 0) {
//        currentSlide = slides.length - 1;
//    } else {
//        currentSlide = index;
//    }
//    slider.style.transform = `translateY(-${currentSlide * 10}%)`;
//}
//
//function nextSlide() {
//    showSlide(currentSlide + 1);
//}
//
//function prevSlide() {
//    showSlide(currentSlide - 1);
//}
//
// Auto-slide every 3 seconds
//setInterval(() => {
//    nextSlide();
//}, 3000);
//
//document.addEventListener('DOMContentLoaded', () => {
//    showSlide(currentSlide);
//});
//
//// Add event listener for mouse wheel
//slider.addEventListener('wheel', (event) => {
//    event.preventDefault(); // Prevent default scroll behavior
//    clearTimeout(scrollTimeout); // Clear previous timeout
//    scrollTimeout = setTimeout(() => {
//        if (event.deltaY < 0) {
//            prevSlide();
//        } else {
//            nextSlide();
//        }
//    }, 150); // Adjust scroll sensitivity here (milliseconds)
//});


// scripts.js
let currentSlide = 0;

function showSlide(index) {
    const slides = document.querySelectorAll('.flex-item');
    console.print(slides)
    if (index >= slides.length) {
        currentSlide = 0;
    } else if (index < 0) {
        currentSlide = slides.length - 1;
    } else {
        currentSlide = index;
    }
    const slider = document.querySelector('.flex-item');
    slider.style.transform = `translateY(-${currentSlide * 7}%)`;
}

function nextSlide() {
    showSlide(currentSlide + 1);
}

function prevSlide() {
    showSlide(currentSlide - 1);
}

// Auto-slide every 3 seconds
setInterval(() => {
    nextSlide();
}, 10000);

document.addEventListener('DOMContentLoaded', () => {
    showSlide(currentSlide);
});







