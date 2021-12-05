let calcTotal = (quantity, price, id) => {
    document.getElementById(id).innerHTML = quantity * price + " EUR"
}