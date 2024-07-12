const onlyLettersPattern = /^[A-Za-z]+$/;
const emailPattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
const passwordPattern = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}/;
const postalCodePattern = /^\d{5}$/;
const cityPattern = /^[A-Za-z ]+$/;

const cardNumberPattern = /^\d{4}-\d{4}-\d{4}-\d{4}$/;
const securityNumberPattern = /^\d{3}$/;

const firstNameError = "First name must contain only letters";
const lastNameError = "Last name must contain only letters";
const passwordError = "Password must contain at least one number, one uppercase and lowercase letter, and at least 8 or more characters";
const confirmPasswordError = "Passwords do not match";
const emailError = "Email entered not in correct format ";
const postalCodeError = "Postal code must be composed of exactly 5 numbers";
const cityError = "The name of the city must be composed of only letters.";

const cardNumberError = "The card format must contain only numbers and in NNNN-NNNN-NNNN-NNNN format";
const securityNumberError = "The security number must contain only numbers and in NNN format";
const expiredCardMessage = "You cannot use an expired card";

function validateFormElem(formElem, pattern, span, message) {
    if (formElem.value.match(pattern)) {
        formElem.classList.remove("error");
        span.style.color = "black";
        span.innerHTML = "";
        return true;
    }
    formElem.classList.add("error");
    span.innerHTML = message;
    span.style.color = "red";
    return false;
}

function validateFirstName() {
    let valid = true;
    let form = document.getElementById("signUpForm");
    let spanName = document.getElementById("errorFirstName");

    if (!validateFormElem(form.firstName, onlyLettersPattern, spanName, firstNameError))
        valid = false;

    return valid;
}

function validateLastName() {
    let valid = true;
    let form = document.getElementById("signUpForm");
    let spanName = document.getElementById("errorLastName");

    if (!validateFormElem(form.lastName, onlyLettersPattern, spanName, lastNameError))
        valid = false;

    return valid;
}

function validateEmail() {
    let valid = true;
    let form = document.getElementById("signUpForm");
    let spanName = document.getElementById("errorEmail");

    if (!validateFormElem(form.email, emailPattern, spanName, emailError))
        valid = false;

    return valid;
}

function validatePassword() {
    let valid = true;
    let form = document.getElementById("signUpForm");
    let spanName = document.getElementById("errorPassword");

    if (!validateFormElem(form.password, passwordPattern, spanName, passwordError))
        valid = false;

    return valid;
}


function validatePostalCode() {
    let valid = true;
    let form = document.getElementById("signUpForm");
    let spanName = document.getElementById("errorPostalCode");

    if (!validateFormElem(form.postalCode, postalCodePattern, spanName, postalCodeError))
        valid = false;

    return valid;
}

function validateCity() {
    let valid = true;
    let form = document.getElementById("signUpForm");
    let spanName = document.getElementById("errorCity");

    if (!validateFormElem(form.city, cityPattern, spanName, cityError))
        valid = false;

    return valid;
}

function passwordMatching() {

    let form = document.getElementById("signUpForm");
    let spanPassword = document.getElementById("matchError");

    let psw1 = form.password.value;
    let psw2 = form.confirmPassword.value;


    if (psw1 != psw2) {
        spanPassword.classList.add("error");
        spanPassword.innerHTML = confirmPasswordError;
        spanPassword.style.color = "red";
        return false;
    }

    spanPassword.classList.remove("error");
    spanPassword.style.color = "black";
    spanPassword.innerHTML = "";
    return true;

}

function validateDateOfBirth() {
    let form = document.getElementById("signUpForm");
    let span = document.getElementById("errorDateOfBirth");
    let data = form.dateOfBirth.value;

    const today = new Date();
    const dataObj = new Date(data);

    if (dataObj > today) {
        span.classList.add("error");
        span.innerHTML = "Wrong date format";
        span.style.color = "red";
        return false;
    } else {
        span.classList.remove("error");
        span.style.color = "black";
        span.innerHTML = "";
        return true;
    }
}

function checkSignUp(obj) {
    let check = true;

    if (!validateFirstName()) check = false;
    if (!validateLastName()) check = false;
    if (!validateEmail()) check = false;
    if (!validatePassword()) check = false;
    if (!passwordMatching()) check = false;
    if (!validatePostalCode()) check = false;
    if (!validateCity()) check = false;
    if (!validateDateOfBirth()) check = false;


    return check;
}

function checkLogin(obj) {
    let check = true;

    if (!validateEmail()) check = false;

    return check;
}

function validateCard() {
    let form = document.getElementById("checkoutForm");
    let span = document.getElementById("cardNumberError");
    let numCarta = form.cardNumber.value;

    if (numCarta.match(cardNumberPattern)) {
        span.classList.remove("error");
        span.style.color = "black";
        span.innerHTML = "";
        return true;
    } else {
        span.classList.add("error");
        span.innerHTML = cardNumberError;
        span.style.color = "red";
        return false;
    }
}

function validateExpiredCard() {
    let form = document.getElementById("checkoutForm");
    let span = document.getElementById("expirationDateError");
    let data = form.expirationDate.value;

    const today = new Date();
    const dataObj = new Date(data);

    if (dataObj < today) {
        span.classList.add("error");
        span.innerHTML = expiredCardMessage;
        span.style.color = "red";
        return false;
    } else {
        span.classList.remove("error");
        span.style.color = "black";
        span.innerHTML = "";
        return true;
    }
}

function validateSecurityNumber() {
    let form = document.getElementById("checkoutForm");
    let span = document.getElementById("securityNumberError");
    let cvv = form.cvv.value;

    if (cvv.match(securityNumberPattern)) {
        span.classList.remove("error");
        span.style.color = "black";
        span.innerHTML = "";
        return true;
    } else {
        span.classList.add("error");
        span.innerHTML = securityNumberError;
        span.style.color = "red";
        return false;
    }
}



function validateAddress() {
    let form = document.getElementById("checkoutForm");
    let span = document.getElementById("addressError");
    let streetCustomer = form.streetCustomer.value;

    if (streetCustomer.trim() === "") {
        span.classList.add("error");
        span.innerHTML = "The address cannot be null";
        span.style.color = "red";
        return false;
    } else {
        span.classList.remove("error");
        span.style.color = "black";
        span.innerHTML = "";
        return true;
    }
}

function checkCheckout(obj) {
    let check = true;

    if (!validateAddress()) check = false;
    if (!validateCard()) check = false;
    if (!validateExpiredCard()) check = false;
    if (!validateSecurityNumber()) check = false;

    return check;
}


function validateProductCode() {
    let form = document.getElementById("productForm");
    let span = document.getElementById("productCodeError");
    let productCode = form.productCode.value;

    if (productCode.trim() === "") {
        span.classList.add("error");
        span.innerHTML = "Product code cannot be null";
        span.style.color = "red";
        return false;
    } else {
        span.classList.remove("error");
        span.style.color = "black";
        span.innerHTML = "";
        return true;
    }
}

function validateNameProduct() {
    let form = document.getElementById("productForm");
    let span = document.getElementById("nameProductError");
    let nameProduct = form.nameProduct.value;

    if (nameProduct.trim() === "") {
        span.classList.add("error");
        span.innerHTML = "Product name cannot be null";
        span.style.color = "red";
        return false;
    } else {
        span.classList.remove("error");
        span.style.color = "black";
        span.innerHTML = "";
        return true;
    }
}

function validateSoftwareHouseProduct() {
    let form = document.getElementById("productForm");
    let span = document.getElementById("productSoftwareHouseError");
    let softwareHouseProduct = form.softwareHouseProduct.value;

    if (softwareHouseProduct.trim() === "") {
        span.classList.add("error");
        span.innerHTML = "Software house cannot be null";
        span.style.color = "red";
        return false;
    } else {
        span.classList.remove("error");
        span.style.color = "black";
        span.innerHTML = "";
        return true;
    }
}

function validatePlatformProduct() {
    let form = document.getElementById("productForm");
    let span = document.getElementById("productPlatformError");
    let platformProduct = form.platformProduct.value;

    if (platformProduct.trim() === "") {
        span.classList.add("error");
        span.innerHTML = "Platform cannot be null";
        span.style.color = "red";
        return false;
    } else {
        span.classList.remove("error");
        span.style.color = "black";
        span.innerHTML = "";
        return true;
    }
}

function validateCategoryProduct() {
    let form = document.getElementById("productForm");
    let span = document.getElementById("productCategoryError");
    let categoryProduct = form.categoryProduct.value;

    if (categoryProduct.trim() === "") {
        span.classList.add("error");
        span.innerHTML = "Category cannot be null";
        span.style.color = "red";
        return false;
    } else {
        span.classList.remove("error");
        span.style.color = "black";
        span.innerHTML = "";
        return true;
    }
}

function validatePegiProduct() {
    let form = document.getElementById("productForm");
    let span = document.getElementById("productPegiError");
    let pegiProduct = form.pegiProduct.value;

    if (pegiProduct.trim() === "") {
        span.classList.add("error");
        span.innerHTML = "PEGI cannot be null";
        span.style.color = "red";
        return false;
    } else {
        span.classList.remove("error");
        span.style.color = "black";
        span.innerHTML = "";
        return true;
    }
}

function validateQuantityProduct() {
    let form = document.getElementById("productForm");
    let span = document.getElementById("productQuantityError");
    let quantityProduct = form.quantityProduct.value;

    if (quantityProduct.trim() === "" || isNaN(quantityProduct) || quantityProduct <= 0) {
        span.classList.add("error");
        span.innerHTML = "Quantity must be greater than 0";
        span.style.color = "red";
        return false;
    } else {
        span.classList.remove("error");
        span.style.color = "black";
        span.innerHTML = "";
        return true;
    }
}

function validatePriceProduct() {
    let form = document.getElementById("productForm");
    let span = document.getElementById("productPriceError");
    let priceProduct = form.priceProduct.value;

    if (priceProduct.trim() === "" || isNaN(priceProduct) || priceProduct <= 0) {
        span.classList.add("error");
        span.innerHTML = "Price must be greater than 0";
        span.style.color = "red";
        return false;
    } else {
        span.classList.remove("error");
        span.style.color = "black";
        span.innerHTML = "";
        return true;
    }
}

function validateImageProduct() {
    let form = document.getElementById("productForm");
    let span = document.getElementById("productImageError");
    let imageProduct = form.imageProduct.value;

    if (imageProduct.trim() === "") {
        span.classList.add("error");
        span.innerHTML = "Image cannot be null";
        span.style.color = "red";
        return false;
    } else {
        span.classList.remove("error");
        span.style.color = "black";
        span.innerHTML = "";
        return true;
    }
}

function validateYearReleaseProduct() {
    let form = document.getElementById("productForm");
    let span = document.getElementById("productYearReleaseError");
    let releaseYearProduct = form.releaseYearProduct.value;

    if (releaseYearProduct.trim() === "" || isNaN(releaseYearProduct) || releaseYearProduct > 2024) {
        span.classList.add("error");
        span.innerHTML = "Release year cannot be in the future ";
        span.style.color = "red";
        return false;
    } else {
        span.classList.remove("error");
        span.style.color = "black";
        span.innerHTML = "";
        return true;
    }
}


function checkAddProduct(obj) {
    let check = true;

    if (!validateProductCode()) check = false;
    if (!validateNameProduct()) check = false;
    if (!validateSoftwareHouseProduct()) check = false;
    if (!validatePlatformProduct()) check = false;
    if (!validateCategoryProduct()) check = false;
    if (!validatePegiProduct()) check = false;
    if (!validateQuantityProduct()) check = false;
    if (!validatePriceProduct()) check = false;
    //if (!validateImageProduct()) check = false;
    if (!validateYearReleaseProduct()) check = false;


    return check;
}

function checkUpdateProduct(obj) {
    let check = true;

    if (!validateProductCode()) check = false;
    if (!validateNameProduct()) check = false;
    if (!validateSoftwareHouseProduct()) check = false;
    if (!validatePlatformProduct()) check = false;
    if (!validateCategoryProduct()) check = false;
    if (!validatePegiProduct()) check = false;
    if (!validateQuantityProduct()) check = false;
    if (!validatePriceProduct()) check = false;
    if (!validateYearReleaseProduct()) check = false;


    return check;
}