let cvvre = /\b\d{3}\b/;
let intsre = /^[a-zA-Z\s]{1,30}$/;
let numre = /^\d{4}-\d{4}-\d{4}-\d{4}$/;


window.onload = function() {
    let error = document.getElementById("error");
}

function checkData(){
    let date = document.getElementById("expirationDate");
    let currentDate = new Date();
    if((month.value<=12 && month.value>=0) || (year.value>9999)){
        if (date < currentDate) {
            error.innerText = "Expiration date cannot be earlier than today and the format must be MM/YY";
            return false;
        }

        else {
            error.innerText = "";
            return true;
        }
    }
    else{
        year.style.borderColor = "red";
        month.style.borderColor = "red";
        error.innerText = "Expiration date cannot be earlier than today and the format must be MM/YY";
        return false;
    }
}
function checkCvv(){
    let cvv = document.getElementById("cvv");
    if(!cvv.value.match(cvvre)){
        cvv.style.borderColor = "red";
        error.innerText = "The Cvv must have the format ***, and consists of only digits.";
        return false;
    }
    else{
        cvv.style.borderColor = "green";
        error.innerText = "";
        return true;
    }
}
function  checkNum(){
    let cardNumber=document.getElementById("cardNumber");
    if(!cardNumber.value.match(numre)){
        cardNumber.style.borderColor = "red";
        cardNumber.innerText = "The card number must have the format ****-****-****-****, and consist of only digits.";
        return false;
    }
    else {
        cardNumber.style.borderColor = "green";
        cardNumber.innerText = "";
        return true;
    }
}


function send() {

    if(checkCvv() && checkData() && checkNum()) {
        let form = document.getElementById("checkoutForm");
        form.action = "/PayOrder";
        form.submit();
    } else {
        event.preventDefault();
    }

}

