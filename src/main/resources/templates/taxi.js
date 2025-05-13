function openModal() {
    document.getElementById('orderModal').style.display = 'flex';
}

function closeModal() {
    document.getElementById('orderModal').style.display = 'none';
}

function submitForm() {
    const name = document.querySelector('#orderModal input[type="text"]').value;
    const phone = document.querySelector('#orderModal input[type="tel"]').value;
    const destination = document.querySelector('#orderModal textarea').value;

    const tripData = {
        startPoint: "Unknown", // Replace with actual logic to determine start point
        endPoint: destination,
        date: new Date().toISOString().split('T')[0], // Current date
        time: new Date().toTimeString().split(' ')[0] // Current time
    };

    fetch('/api/trips', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(tripData),
    })
    .then(response => response.json())
    .then(data => {
        alert('Ваше замовлення прийнято! Ми зв’яжемося з вами.');
        closeModal();
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Помилка при замовленні. Спробуйте ще раз.');
    });
}

// Registration form submission
function submitRegistrationForm(event) {
    event.preventDefault();
    const name = document.querySelector('#name').value;
    const email = document.querySelector('#email').value;
    const password = document.querySelector('#password').value;

    const userData = {
        username: name,
        email: email,
        password: password,
        rating: "0", // Default rating
        enabled: true,
        role: "USER"
    };

    fetch('/api/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userData),
    })
    .then(response => {
        if (!response.ok) {
            return response.json().then(err => { throw new Error(err.message); });
        }
        return response.json();
    })
    .then(data => {
        alert('Реєстрація успішна! Ви можете увійти.');
        window.location.href = '/login.html';
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Помилка при реєстрації: ' + error.message);
    });
}

// Login form submission
function submitLoginForm(event) {
    event.preventDefault();
    const email = document.querySelector('#email').value;
    const password = document.querySelector('#password').value;

    const loginData = {
        email: email,
        password: password
    };

    fetch('/api/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(loginData),
    })
    .then(response => {
        if (!response.ok) {
            return response.json().then(err => { throw new Error(err.message); });
        }
        return response.json();
    })
    .then(data => {
        alert('Вхід успішний!');
        window.location.href = '/index.html';
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Помилка при вході: ' + error.message);
    });
}