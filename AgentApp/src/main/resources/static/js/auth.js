// URL of the backend API
const API_URL = 'http://localhost:8080/api';

document.getElementById('registerForm')?.addEventListener('submit', registerUser);
document.getElementById('loginForm')?.addEventListener('submit', loginUser);

function registerUser(event) {
    event.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    fetch(`${API_URL}/auth/register`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
    })
    .then(response => response.json())
    .then(data => {
        alert('Registration successful!');
        window.location.href = 'login.html';
    })
    .catch(error => console.error('Error:', error));
}

function loginUser(event) {
    event.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    fetch(`${API_URL}/auth/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
    })
    .then(response => response.json())
    .then(user => {
        if (user && user.id) {
            sessionStorage.setItem('userId', user.id);
            window.location.href = 'dashboard.html';
        } else {
            alert('Invalid credentials');
        }
    })
    .catch(error => console.error('Error:', error));
}
