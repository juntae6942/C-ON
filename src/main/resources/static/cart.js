document.addEventListener("DOMContentLoaded", function() {
    updateTotalAmount();
});

function changeQuantity(button, change) {
    const item = button.closest('.foodItem');
    const quantitySpan = item.querySelector('.quantity');
    let quantity = parseInt(quantitySpan.textContent, 10);
    quantity += change;

    if (quantity < 1) {
        quantity = 1;
    }

    quantitySpan.textContent = quantity+" ps";

    updateItemTotalPrice(item);
    updateTotalAmount();
}

function updateItemTotalPrice(item) {
    const price = parseInt(item.getAttribute('data-price'), 10);
    const quantity = parseInt(item.querySelector('.quantity').textContent, 10);
    const itemTotalPrice = item.querySelector('.itemTotalPrice');
    itemTotalPrice.textContent = (price * quantity).toLocaleString() + '원';
}

function updateTotalAmount() {
    const items = document.querySelectorAll('.foodItem');
    let totalAmount = 0;

    items.forEach(item => {
        const price = parseInt(item.getAttribute('data-price'), 10);
        const quantity = parseInt(item.querySelector('.quantity').textContent, 10);
        totalAmount += price * quantity;
    });

    const totalAmountElement = document.getElementById('totalAmount');
    totalAmountElement.textContent = totalAmount.toLocaleString() + '원';
}