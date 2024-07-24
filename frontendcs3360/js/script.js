document.addEventListener("DOMContentLoaded", () => {
  renderProducts();
  updateCartCount();
});

let cartCount = 0; // Initialize cart count

// Function to toggle the customer information section
function toggleCustomerInfo() {
  const infoContainer = document.getElementById("customer-info-container");
  const infoButton = document.getElementById("customer-info-button");
  if (infoContainer.style.display === "none") {
    infoContainer.style.display = "block";
    infoButton.textContent = "Hide Information";
  } else {
    infoContainer.style.display = "none";
    infoButton.textContent = "Customer Information";
  }
  document.getElementById("warning-message").innerText = "";
}

// Function to get the products from the server
function renderProducts() {
  const products = getProducts();
  const clothesSection = document.getElementById("clothes-section");
  const accessoriesSection = document.getElementById("accessories-section");
  console.log(products);
}

// Add product to cart
function addToCart() {
  cartCount++;
  updateCartCount();
}

// Post the customer info to the server
function postCustomerInfo() {
  const phoneNumber = document.getElementById(
    "customer-phoneNumber-input"
  ).value;
  const name = document.getElementById("customer-name-input").value;
  const address = document.getElementById("customer-address-input").value;

  // Post the customer info to the server or process it as needed
  console.log("Customer Info:", { phoneNumber, name, address });
}

// Display the products in ascending order
function displayAscendingOrder() {
  const products = getProducts();
  const sortedProducts = products.sort(
    (a, b) =>
      parseFloat(a.currentPrice.slice(1)) - parseFloat(b.currentPrice.slice(1))
  );
  displayProducts(sortedProducts);
}

// Display the products in descending order
function displayDescendingOrder() {
  const products = getProducts();
  const sortedProducts = products.sort(
    (a, b) =>
      parseFloat(b.currentPrice.slice(1)) - parseFloat(a.currentPrice.slice(1))
  );
  displayProducts(sortedProducts);
}

// Update the cart count
function updateCartCount() {
  document.getElementById("number-of-items-in-cart").textContent = cartCount;
}
