async function fetchOrderHistory(order = "desc") {
  const customerInfo = JSON.parse(localStorage.getItem("customerInfo"));
  const customerId = customerInfo.customerId;

  const url =
    order === "asc"
      ? `http://localhost:8080/api/orderItems/historyAsc/${customerId}`
      : `http://localhost:8080/api/orderItems/historyDesc/${customerId}`;

  try {
    const response = await fetch(url, {
      method: "GET",
      mode: "cors",
      headers: {
        "Content-Type": "application/json",
      },
    });

    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }

    let data = await response.json();
    localStorage.setItem("orderHistoryData", JSON.stringify(data.data));

    // Call the renderOrderHistory function to update the UI
    renderOrderHistory();

    return data;

  } catch (error) {
    console.error("Error fetching data:", error);
  }
}

// Add the order history to the table
const renderOrderHistory = () => {
  const tbody = document.getElementById("order-history-body");
  tbody.innerHTML = ""; 
  const data = JSON.parse(localStorage.getItem("orderHistoryData"));

  data.orderItems.forEach((orderItem) => {
    const row = document.createElement("tr");
    row.innerHTML = `
    <td class="px-4 py-2">${orderItem.item.productName}</td>
    <td class="px-4 py-2">${orderItem.quantity}</td>
    <td class="px-4 py-2">${orderItem.item.price}</td>
    <td class="px-4 py-2">${orderItem.item.price * orderItem.quantity}</td>
    <td class="px-4 py-2">${new Date(orderItem.dateOfPurchase).toLocaleString()}</td>

            `;
    tbody.appendChild(row);
  });
};

// Call the fetchOrderHistory function when the DOM is loaded
document.addEventListener("DOMContentLoaded", async function () {
  await fetchOrderHistory();
  renderOrderHistory();
});

window.onload = function() {
  fetchOrderHistory('asc');
};
