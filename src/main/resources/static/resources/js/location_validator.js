// changes money input to allow only two decimal places
function validate_rent(el) {
    el.value = parseFloat(el.value).toFixed(2);
}