// Header login buttons
const loginButton = document.querySelector('.loginButton')
const logoutButton = document.querySelector('.logoutButton')

if (loginButton) {
    loginButton.onclick = loginSide
}

if (logoutButton) {
    logoutButton.onclick = logout
}

function loginSide () {
    window.location.href = '/loginSide'
}

function logout () {
    window.location.href = '/logout'
}

// Login side
const credLoginBtn = document.querySelector('.LogonWCreds')
const userNameInp = document.querySelector('#loginName')
const passwordInp = document.querySelector('#loginPassword')
const error = document.querySelector('#error')

if (credLoginBtn) {
    credLoginBtn.onclick = login
}

async function login() {
    const body = JSON.stringify({username: userNameInp.value, password: passwordInp.value})
    const resp = await fetch("/login", {
        method: "POST",
        headers: {
            "content-type": "application/json"
        },
        body: body
    })
    if (resp.ok) {
        window.location.href = '/beskeder'
    } else {
        error.textContent = "Login failed"
    }
}