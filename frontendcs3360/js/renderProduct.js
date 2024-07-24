const baseURL = "http://localhost:8080/api/v1/";
let minPrice, maxPrice;
// function render all products and slider by GET method when init page
document.addEventListener("DOMContentLoaded", function () {
  getData(baseURL + "products/get-all").then((data) => {
    // display all fetched data
    displaySearchResults(data.data);
    // console.log(dataToRender);
  });
  fetchCart();
  updateNumberOfItemsInCart();
});
// rerender when change in localStorage in different tabs and windows
window.addEventListener("storage", function (event) {
  if (event.key === "cartItems") {
    updateNumberOfItemsInCart();
  }
});
// fetch data from API getAllProducts
async function getData(url = baseURL) {
  try {
    const response = await fetch(url, {
      method: "GET",
      mode: "cors", // Use "cors" instead of "no-cors"
      cache: "no-cache",
      credentials: "same-origin",
      headers: {
        "Content-Type": "application/json",
      },
    });
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    let data = await response.json();
    return data;
    //   console.log(data); // Log or process the data as needed
  } catch (error) {
    console.error("Error fetching data:", error);
  }
}

async function postCustomerInfo() {
  const phoneNumberInput = document.getElementById("customer-phoneNumber-input").value;

  // Validate the phone number
  if (!/^\d{10}$/.test(phoneNumberInput)) {
    document.getElementById("message").innerText = "Invalid phone number. Please enter a 10-digit number.";
    return;
  }

  const customerInfo = {
    customerName: document.getElementById("customer-name-input").value,
    phoneNumber: phoneNumberInput,
    address: document.getElementById("customer-address-input").value,
  };

  try {
    let response;
    const existingCustomerInfo = JSON.parse(localStorage.getItem("customerInfo"));

    if (existingCustomerInfo && existingCustomerInfo.phoneNumber === phoneNumberInput) {
      // If the phone number already exists, update the customer
      response = await fetch(baseURL + "customers/update", {
        method: "PUT",
        mode: "cors",
        cache: "no-cache",
        credentials: "same-origin",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(customerInfo),
      });
    } else {
      // If the phone number doesn't exist, insert a new customer
      response = await fetch(baseURL + "customers/insert", {
        method: "POST",
        mode: "cors",
        cache: "no-cache",
        credentials: "same-origin",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(customerInfo),
      });
    }

    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }

    let data = await response.json();
    localStorage.setItem("customerInfo", JSON.stringify(data.data));

    // Display a success message
    document.getElementById("message").innerText = "Your information has been successfully submitted!";
    return data;

  } catch (error) {
    console.error("Error fetching data:", error);
    document.getElementById("message").innerText = "Please fill in all information!";
  }
}

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
}
// function get products by NAME with GET method
async function searchProducts() {
  console.log("searching");
  // Get the input value
  const productName = document.getElementById("product-search-input").value;
  if (productName.length > 0) {
    try {
      const response = await fetch(
        "http://localhost:8080/api/v1/products/get-by-name/" +
        encodeURIComponent(productName),
        {
          method: "GET",
          mode: "cors", // Use "cors" instead of "no-cors"
          cache: "no-cache",
          credentials: "same-origin",
          headers: {
            "Content-Type": "application/json",
          },
        }
      )
        .then((res) => res.json())
        .then((data) => {
          // display response data
          displaySearchResults(data.data);
        });

      // return await response.json();
      //   console.log(data); // Log or process the data as needed
    } catch (error) {
      console.error("Error fetching search results:", error);
    }
  } else {
    try {
      const response = await fetch(baseURL + "products/get-all", {
        method: "GET",
        mode: "cors", // Use "cors" instead of "no-cors"
        cache: "no-cache",
        credentials: "same-origin",
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((res) => res.json())
        .then((data) => {
          displaySearchResults(data.data);
        });

      // return await response.json();
      //   console.log(data); // Log or process the data as needed
    } catch (error) {
      console.error("Error fetching search results:", error);
    }
  }
}

// GET products by price ascending order
async function displayAscendingOrder() {
  try {
    const response = await fetch(baseURL + "products/get-all-asc", {
      method: "GET",
      mode: "cors", // Use "cors" instead of "no-cors"
      cache: "no-cache",
      credentials: "same-origin",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((res) => res.json())
      .then((data) => {
        // display response data
        displaySearchResults(data.data);
        // console.log(data);
      });

    // return await response.json();
    //   console.log(data); // Log or process the data as needed
  } catch (error) {
    console.error("Error fetching search results:", error);
  }
}
// GET products by price ascending order
async function displayDescendingOrder() {
  try {
    const response = await fetch(baseURL + "products/get-all-desc", {
      method: "GET",
      mode: "cors", // Use "cors" instead of "no-cors"
      cache: "no-cache",
      credentials: "same-origin",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((res) => res.json())
      .then((data) => {
        // display response data
        displaySearchResults(data.data);
        // console.log(data);
      });

    // return await response.json();
    //   console.log(data); // Log or process the data as needed
  } catch (error) {
    console.error("Error fetching search results:", error);
  }
}

// function display all received data, every function above call this function to render the results
function displaySearchResults(products) {
  const clothesSection = document.getElementById("clothes-section");
  const accessoriesSection = document.getElementById("accessories-section");
  clothesSection.innerHTML = "";
  accessoriesSection.innerHTML = "";
  // Assuming each result is an object with a 'productName' property
  products.forEach((product) => {
    const addToCartButton = document.createElement("button");
    addToCartButton.textContent = "Add to Cart";
    addToCartButton.classList.add("add-to-cart-button");
    addToCartButton.addEventListener("click", () => addToCart(product));
    const productCard = document.createElement("div");
    productCard.classList.add("product-card");
    productCard.innerHTML = `
    <img src="${product.imagePath}" alt="${product.productName}">
    <div class="product-info">
        <h2>${product.productName}</h2>
        <div class="price">
            <span class="current-price">${product.price} $</span>
        </div>
        <p>${product.description}</p>
        <div class="product-meta">
            ${product.brand ? `<span>Brand: ${product.brand}</span><br>` : ''}
            ${product.size ? `<span>Size: ${product.size}</span><br>` : ''}
            ${product.type ? `<span>Type: ${product.type}</span><br>` : ''}
            ${product.material ? `<span>Material: ${product.material}</span><br>` : ''}
            ${product.weight ? `<span>Weight: ${product.weight}</span><br>` : ''}
        </div>
    </div>
`;
    productCard.appendChild(addToCartButton);
    if (!product.weight) {
      clothesSection.appendChild(productCard);
    } else {
      accessoriesSection.appendChild(productCard);
    }
  });
}

// Add a product to the cart using both post method postNewOrdersItem and save that object data to localStorage.
async function addToCart(product) {
  // console.log("adding to cart");
  // console.log(product);
  const customerInfo = JSON.parse(localStorage.getItem("customerInfo"));
  // Check if customer info exists
  if (!customerInfo) {
    // If customer info does not exist, display an error message and stop the function
    document.getElementById("warning-message").innerText = "Please enter your information first!";

    // Scroll to the warning message
    document.getElementById("warning-message").scrollIntoView({ behavior: 'smooth' });
    return;
  }
  const productToAdd = {
    customerId: customerInfo.customerId,
    itemId: product.itemId,
    quantity: 1,
  };
  // console.log(productToAdd);
  try {
    const response = await fetch("http://localhost:8080/api/orderItems/add", {
      method: "POST",
      mode: "cors", // Use "cors" instead of "no-cors"
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(productToAdd),
    })
      .then((res) => res.json())
      .then((data) => {
        // displaySearchResults(data.data);
        console.log(data);
      });

    // return await response.json();
    //   console.log(data); // Log or process the data as needed
  } catch (error) {
    console.error("Error fetching search results:", error);
  }
  await fetchCart();
  updateNumberOfItemsInCart();
}
// rerender the number of items in the cart
function updateNumberOfItemsInCart() {
  const cartData = JSON.parse(localStorage.getItem("cartItems"));
  console.log(cartData);

  const numberOfItemsInCart = document.getElementById(
    "number-of-items-in-cart"
  );
  if (cartData) {
    numberOfItemsInCart.innerHTML = "";
    numberOfItemsInCart.innerHTML = cartData.length;
  } else {
    numberOfItemsInCart.innerHTML = "";
    numberOfItemsInCart.innerHTML = 0;
  }
}

// save OrdersItem to localStorage
function saveOrdersItemToLocalStorage(product) {
  // Retrieve the cart from local storage and parse it into an array
  let cart = JSON.parse(localStorage.getItem("cart")) || [];

  // Check if the product already exists in the cart
  const existingProductIndex = cart.findIndex((p) => p.id === product.id);
  if (existingProductIndex > -1) {
    // Update quantity if product exists
    cart[existingProductIndex].quantity += product.quantity;
  } else {
    // Add new product to the cart
    cart.push(product);
  }

  // Save the updated cart back to local storage
  localStorage.setItem("cart", JSON.stringify(cart));
}

function postNewOrdersItem(product) {
  console.log(product);
  const newDataToSend = {
    product: {
      id: product.id,
    },
    quantity: product.quantity,
    coupon: {},
  };
}

function signOut() {
  // Clear all data in localStorage
  localStorage.clear();

  window.location.reload();
}

window.onload = function () {
  // Get customer info from localStorage
  const customerInfo = JSON.parse(localStorage.getItem("customerInfo"));

  // If customer info exists, display it
  if (customerInfo) {
    document.getElementById("customer-phoneNumber-input").value = customerInfo.phoneNumber;
    document.getElementById("customer-name-input").value = customerInfo.customerName;
    document.getElementById("customer-address-input").value = customerInfo.address;
  }
}
