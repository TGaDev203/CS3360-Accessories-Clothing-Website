async function fetchCart() {
  let cartInfo = {};
  const customerInfo = JSON.parse(localStorage.getItem("customerInfo"));
  const customerId = customerInfo.customerId;
  //   console.log(customerId);
  const url = `http://localhost:8080/api/orderItems/get-all/${customerId}`;
  // const testURL = `http://localhost:8080/api/orderItems/get-all/1`;
  //   console.log(url);
  //   console.log(customerInfo);
  try {
    const response = await fetch(url, {
      method: "GET",
      mode: "cors", // Use "cors" instead of "no-cors"
      headers: {
        "Content-Type": "application/json",
      },
    });
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }

    await response.json().then((data) => {
      cartInfo = data.data;
      localStorage.setItem("cartInfo", JSON.stringify(cartInfo));
    });

    //   console.log(data); // Log or process the data as needed
  } catch (error) {
    console.error("Error fetching data:", error);
  }
  // console.log(cartInfo);
  //  cartItems.push(cartInfo.orderItems);
  if (localStorage.getItem("cartItems") != null) {
    localStorage.removeItem("cartItems");
  }
  let cartItems = cartInfo.orderItems;
  localStorage.setItem("cartItems", JSON.stringify(cartItems));
  // console.log(cartItems);
  renderCartItems();
}

function renderCartItems() {
  const cartBody = document.getElementById("cart-body");
  const emptyCartMessage = document.getElementById("empty-cart-message");
  const checkoutBtnContainer = document.getElementById(
    "checkout-btn-container"
  );
  const totalAmount = document.getElementById("total-amount");
  totalAmount.textContent = "Total: $0.00";
  cartBody.innerHTML = "";
  let cartItems = JSON.parse(localStorage.getItem("cartItems")) || [];
  // console.log(cartItems.length);
  if (cartItems.length == 0) {
    emptyCartMessage.style.display = "block";
    checkoutBtnContainer.classList.add("hidden");
    totalAmount.textContent = "Total: $0.00";
    // console.log("working");
  } else {
    emptyCartMessage.style.display = "none";
    checkoutBtnContainer.classList.remove("hidden");

    let total = 0;
    // console.log(cartItems);
    cartItems.forEach((item, index) => {
      // console.log(item);
      // console.log(item.item.price);
      const row = document.createElement("tr");
      const itemTotal = item.item.price * item.quantity;
      total += itemTotal;

      row.innerHTML = `
                <td>${item.item.productName}</td>
                <td>${item.item.price}</td>
                <td>
                    <button onClick="handleDecreaseQuantity(${
                      item.item.itemId
                    }, ${item.quantity})">-</button>
                    <span>${item.quantity}</span>
                    <button onClick="handleIncreaseQuantity(${
                      item.item.itemId
                    }, ${item.quantity})">+</button>
                </td>
                <td>$${itemTotal.toFixed(2)}</td>
                <td>
                    <button class="btn" onclick="removeFromCart(${
                      item.item.itemId
                    })">Remove</button>
                </td>
            `;
      cartBody.appendChild(row);
    });

    totalAmount.textContent = `Total: $${total.toFixed(2)}`;
  }
}

document.addEventListener("DOMContentLoaded", () => {
  fetchCart();
  renderCartItems();
});
async function handleDecreaseQuantity(itemId, quantity) {
  // console.log(itemId);
  let customerId = JSON.parse(localStorage.getItem("customerInfo")).customerId;
  // console.log(customerId);
  // console.log(quantity);
  const dataToSend = {
    quantity: quantity - 1,
  };
  if (dataToSend.quantity < 1) {
    return;
  }
  const url = `http://localhost:8080/api/orderItems/change-quantity/${customerId}/${itemId}`;
  try {
    const response = await fetch(url, {
      method: "PUT",
      mode: "cors", // Use "cors" instead of "no-cors"
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(dataToSend),
    });
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    //   console.log(data); // Log or process the data as needed
  } catch (error) {
    console.error("Error fetching data:", error);
  }
  fetchCart();
}
async function handleIncreaseQuantity(itemId, quantity) {
  // console.log(itemId);
  let customerId = JSON.parse(localStorage.getItem("customerInfo")).customerId;
  // console.log(customerId);
  // console.log(quantity);
  const dataToSend = {
    quantity: quantity + 1,
  };

  const url = `http://localhost:8080/api/orderItems/change-quantity/${customerId}/${itemId}`;
  try {
    const response = await fetch(url, {
      method: "PUT",
      mode: "cors", // Use "cors" instead of "no-cors"
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(dataToSend),
    });
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    //   console.log(data); // Log or process the data as needed
  } catch (error) {
    console.error("Error fetching data:", error);
  }
  fetchCart();
}
async function removeFromCart(itemId) {
  // console.log(itemId);
  const customerInfo = JSON.parse(localStorage.getItem("customerInfo"));
  const customerId = customerInfo.customerId;
  console.log(itemId);
  const url = `http://localhost:8080/api/orderItems/delete/${customerId}/${itemId}`;
  // const testurl = `http://localhost:8080/api/orderItems/delete/1/2`;
  try {
    const response = await fetch(url, {
      method: "DELETE",
      mode: "cors", // Use "cors" instead of "no-cors"
      headers: {
        "Content-Type": "application/json",
      },
    });
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    //   console.log(data); // Log or process the data as needed
  } catch (error) {
    console.error("Error fetching data:", error);
  }
  fetchCart();
}

async function confirmCheckout() {
  const customerInfo = JSON.parse(localStorage.getItem("customerInfo"));
  const customerId = customerInfo.customerId;
  console.log(customerId);
  const url = `http://localhost:8080/api/orderItems/checkout/${customerId}`;
  console.log(url);
  const cartItems = JSON.parse(localStorage.getItem("cartItems"));
  console.log(cartItems);
  if (cartItems.length == 0) {
    alert("Your cart is empty!");
  } else {
    try {
      const response = await fetch(url, {
        method: "POST",
        mode: "cors", // Use "cors" instead of "no-cors"
        headers: {
          "Content-Type": "application/json",
        },
      });
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      alert("Checkout successful!");
      localStorage.removeItem("cartItems");
      window.location.href = "orderHistory.html";
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  }
}

function showBubbles() {
  const bubbleContainer = document.createElement("div");
  bubbleContainer.classList.add("bubble-container");
  document.body.appendChild(bubbleContainer);
  for (let i = 0; i < 100; i++) {
    const bubble = document.createElement("div");
    bubble.classList.add("bubble");
    bubble.style.left = Math.random() * 100 + "vw";
    bubble.style.animationDuration = Math.random() * 2 + 3 + "s";
    bubbleContainer.appendChild(bubble);
  }
  setTimeout(() => bubbleContainer.remove(), 3000);
}

document.addEventListener("DOMContentLoaded", () => {
  const cartLink = document.querySelector('.brand-nav a[href="cart.html"]');
  const orderHistoryBtn = document.getElementById("orderHistoryLink");
});
