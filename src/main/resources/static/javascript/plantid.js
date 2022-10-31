//grabs user ID to save results with
let userId = (document.cookie.split('=')[1]);
userId = parseInt(userId);


//logout that clears the cookies
function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
    window.location.replace("http://localhost:8080/home.html");
}



document.querySelector('button').onclick = function sendIdentification() {
    const files = [...document.querySelector('input[type=file]').files];
    const promises = files.map((file) => {
        return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.onload = (event) => {
            const res = event.target.result;
            console.log(res);
            resolve(res);
            }
            reader.readAsDataURL(file)
        })
    })
    
    Promise.all(promises).then((base64files) => {
        console.log(base64files)
            
        const data = {
        api_key: "-- ask for one: https://web.plant.id/api-access-request/ --",
        images: base64files,
        // modifiers docs: https://github.com/flowerchecker/Plant-id-API/wiki/Modifiers
        modifiers: ["crops_fast", "similar_images"],
        plant_language: "en",
        // plant details docs: https://github.com/flowerchecker/Plant-id-API/wiki/Plant-details
        plant_details: ["common_names",
                        "url",
                        "name_authority",
                        "wiki_description",
                        "taxonomy",
                        "synonyms"],
        };
    
        fetch('https://api.plant.id/v2/identify', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
        })
        .then(response => response.json())
        .then(data => {
        console.log('Success:', data);
        })
        .catch((error) => {
        console.error('Error:', error);
        });
    })

};
