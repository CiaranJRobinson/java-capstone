//cookies
const cookieArr = document.cookie.split("=");
const userId = cookieArr[1];
console.log(cookieArr);
console.log(userId);
//form info
const loginUsername = document.getElementById("username");
const loginPassword = document.getElementById("password");
const regUsername = document.getElementById("regusername");
const regPassword = document.getElementById("regpassword");
const regEmail = document.getElementById("email");
const regDOB = document.getElementById("dateOfBirth");

//submit btns
const loginSubmit = document.getElementById("loginForm");
const regSubmit = document.getElementById("regForm");

const headers = {
    'Content-Type':'application/json'
}
const baseUrl = "http://localhost:8080/api/v1/users"

//logout that clears the cookies
function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
    document.getElementById("displayName").innerHTML = ' ';
}
//Login function
const handleSubmitLogin = async (e) => {
    e.preventDefault()
    let bodyObj = {
        username: loginUsername.value,
        password: loginPassword.value
    }
    
    const response = await fetch(`${baseUrl}/login`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err.message))

    const responseArr = await response.json()

    if (response.status === 200){
        document.cookie = `userId=${responseArr[1]}`
        window.location.replace(responseArr[0])
    }
    document.getElementById("displayName").innerHTML = `Hello ${bodyObj.username}`;
   
}
//Register Function
const handleSubmitReg = async (e) => {
    e.preventDefault()
    let bodyObj = {
        username: regUsername.value,
        password: regPassword.value,
        email: regEmail.value,
        dob: regDOB.value
    }
    
    const response = await fetch(`${baseUrl}/register`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err.message))

    const responseArr = await response.json()

    if (response.status === 200){
        window.location.replace (responseArr[0])
        }
}


//submit btns
loginSubmit.addEventListener("submit", handleSubmitLogin);
regSubmit.addEventListener("submit", handleSubmitReg)