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


const plantUpForm = document.querySelector("picUploadForm");

function showResults(res) {
    const plantArr = res.suggestions;
    console.log("Plant array:", plantArr);
        for (let i=0; i<plantArr.length; i++){
            let wiki_description = plantArr[i].plant_details.wiki_description.value;
            let common_names = plantArr[i].plant_details.common_names[0];
            let { url } = plantArr[i].plant_details;
            let plant_name = plantArr[i].plant_details.scientific_name;

            console.log(wiki_description);
            console.log(plant_name);
            console.log(common_names);
            
            let newCommName = document.createElement('a');
            let newDescr = document.createElement('p');
            let newImg = document.createElement('img');
            let newSciName = document.createElement('p');
            let divContainer = document.createElement('div');
            
            newImg.src = plantArr[i].similar_images[0].url_small;
            newCommName.textContent = common_names;
            newDescr.textContent = wiki_description;
            newSciName.textContent = plant_name;

            newCommName.href = `${url}`;

            const list = document.createElement("span");
            const plantresults = document.getElementById("results");
                list.setAttribute("id", "PlantList");
                list.setAttribute("class", "PlantCard");
            
                
                list.appendChild(newImg);       
                divContainer.appendChild(newCommName);
                list.appendChild(divContainer);
                list.appendChild(newSciName);
                list.appendChild(newDescr);
                plantresults.appendChild(list);
        }
}

function sendIdentification() {
    const files = [...document.querySelector('input[type=file]').files];
    const promises = files.map((file) => {
        return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.onload = (event) => {
            const res = event.target.result;
            // console.log(res);
            resolve(res);
            }
            reader.readAsDataURL(file)
        })
    })
    
    Promise.all(promises).then((base64files) => {
        // console.log(base64files)
            
        const data = {
        api_key: "GY7rEXZe3tKZMrgQZpIjWb6F24mfMch1R1YwlNAIgBzAEReWg9",
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
        showResults(data);
        })
        .catch((error) => {
        console.error('Error:', error);
        });
    })
    let T = document.getElementById("toggleVis");
    T.style.display = "block";
};

plantUpForm.addEventListener("submit", sendIdentification);
