function handleAdd(button, id) {
    button.style.display = 'none';

    const counterControls = button.nextElementSibling;
    counterControls.style.display = 'flex';

    const counter = counterControls.querySelector('.counter');
    counter.textContent = '1';

    fetch('https://foodapp-yl5h.onrender.com/foodApplication/AddToCartServlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({
            id,
            action: "add"
        }).toString()
    })
        .then(response => response.text())
        .then(cartSize => {
            updateCartCard(cartSize);
        })
        .catch(error => console.error("Error:", error));
}

function updateCounter(button, increment, id, flag) {
    const counterControls = button.parentElement;
    const counter = counterControls.querySelector('.counter');
    let currentValue = parseInt(counter.textContent, 10);

    currentValue += increment;

    if (currentValue === 0) {
        counterControls.style.display = 'none';
        if (!flag) {
            const addButton = counterControls.previousElementSibling;
            addButton.style.display = 'flex';
        } else {
            removeItemFromUI(id, name);
        }
    } else {
        counter.textContent = currentValue;
    }

    fetch('https://foodapp-yl5h.onrender.com/foodApplication/AddToCartServlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({
            num: currentValue,
            id,
            action: "update"
        }).toString()
    })
        .then(response => response.text())
        .then(cartSize => {
            if (!flag)
                updateCartCard(cartSize);
        })
        .catch(error => console.error("Error:", error));

    if (flag) {
        const itemName = button.getAttribute("data-item-name");
        const itemPrice = parseFloat(button.getAttribute("data-item-price"));

        updateItemTotal(itemName, itemPrice, currentValue);
    }
}


function updateCartCard(cartSize) {
    const cartCard = document.getElementById("cart_card");
    if (!cartCard) {
        console.error("cart_card element not found!");
        return;
    }

    if (cartSize > 0) {
        cartCard.classList.remove("hide");
        cartCard.querySelector('h3').innerText = ("Added " + cartSize + "Items in Cart");
    } else {
        cartCard.classList.add("hide");
    }
}

function updateItemTotal(itemName, price, quantity) {
    const itemId = `item_${itemName.replace(/\s+/g, "_")}`;
    const itemRow = document.getElementById(itemId);

    if (itemRow) {
        if (quantity == 0) {
            itemRow.remove();
        }
        else {
            const quantityCell = itemRow.querySelector('.item_quantity');
            const priceCell = itemRow.querySelector('.item_price');

            if (quantityCell) {
                quantityCell.innerHTML = `${price} * ${quantity}`;
            }

            if (priceCell) {
                priceCell.innerHTML = price * quantity;
            }
        }
    }

    calculateGrandTotal();
}

function calculateGrandTotal() {
    let grandTotal = 0;

    const rows = document.querySelectorAll("tr[id^='item_']");
    rows.forEach((row) => {
        const priceCell = row.querySelector('.item_price');
        if (priceCell && !isNaN(parseFloat(priceCell.innerHTML))) {
            grandTotal += parseFloat(priceCell.innerHTML);
        }
    });

    const grandTotalElement = document.getElementById('grand_total');
    if (grandTotalElement) {
        grandTotalElement.innerHTML = `Grand Total: &#8377; ${grandTotal.toFixed(2)}`;
    }
}

calculateGrandTotal();

function removeItemFromUI(id, itemName) {
    const cartItemDiv = document.querySelector(`.cart_item[data-id="${id}"]`);
    if (cartItemDiv) {
        cartItemDiv.remove();
    }

    const sanitizedItemName = itemName.replace(/\s+/g, "_");
    const row = document.getElementById(`item_${sanitizedItemName}`);
    if (row) {
        row.remove();
    }
	
    calculateGrandTotal();

    const cartItems = document.querySelectorAll(".cart_item");
    if (cartItems.length === 0) {
        const cartPaymentDiv = document.querySelector(".cart_payment");
        if (cartPaymentDiv) {
            cartPaymentDiv.innerHTML = `
                <div class="empty_cart">
                    <i class="fa-solid fa-cart-shopping"></i>
                    <p>Empty Cart</p>
                    <a href="home.jsp">Start Ordering</a>
                </div>
            `;
        }
    }
}

function deleteItem(id, name) {
    fetch('https://foodapp-yl5h.onrender.com/foodApplication/AddToCartServlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({
            id,
            action: "remove"
        }).toString()
    })
        .then(response => response.text())
        .then(result => {
            if (result) {
                console.log(result);
                removeItemFromUI(id, name);
            } else {
                console.error("Failed to delete item:", result.message);
            }
        })
        .catch(error => console.error("Error:", error));
}

function clearCart(){
	fetch('https://foodapp-yl5h.onrender.com/foodApplication/AddToCartServlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({
            id : 0,
            action: "clear"
        }).toString()
    })
    .then(response => response.text())
    .then(result => {
        if (result == 0) {
			const cartPaymentDiv = document.querySelector(".cart_payment");
	        if (cartPaymentDiv) {
	            cartPaymentDiv.innerHTML = `
	                <div class="empty_cart">
	                    <i class="fa-solid fa-cart-shopping"></i>
	                    <p>Empty Cart</p>
	                    <a href="home.jsp">Start Ordering</a>
	                </div>
	            `;
	        }
        } else {
            console.error("Failed to clear cart:", result.message);
        }
    })
    .catch(error => console.error("Error:", error));
}

function handleOrder() {
	let selectedPayment = document.getElementById("payment_method").value;
	
    fetch('https://foodapp-yl5h.onrender.com/foodApplication/ConfirmOrder', {
        method: 'POST',
		headers: {
	        "Content-Type": "application/x-www-form-urlencoded"
	    },
	    body: "paymentMethod=" + encodeURIComponent(selectedPayment)
    }).then(async (response) => {
		if (response.redirected) {
            window.location.href = "https://foodapp-yl5h.onrender.com/foodApplication/login.jsp";
        }
		if(await response.text() === "order confirmed"){
			window.location.href = "https://foodapp-yl5h.onrender.com/foodApplication/cartPage.jsp";
		}
	}).catch(error => console.error("Error:", error));
}

