const addStepsSubmit = document.getElementById("addStepsForm");
const stepsToAdd = document.getElementById("stepcount");
const dateOfSteps = document.getElementById("date");


const headers = {
    'Content-Type':'application/json'
}
const baseUrl1 = "http://localhost:8080/api/v1/steps"

let userId = (document.cookie.split('=')[1]);
userId = parseInt(userId);


//logout that clears the cookies
function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}


// async function getSteps(userId){
//     await fetch(`${baseUrl1}/user/${userId}`, {
//         method: "GET",
//         headers : headers
//     })
//         .then(response => response.json())
//         .then(data => console.log("here is the data:", data))
//         .catch(err => console.error(err))
// }

// const createNoteCards = (array) => {
//     noteContainer.innerHTML = ''
//     array.forEach(obj => {
//         //creating div and html inside
//         let noteCard = document.createElement("div")
//         noteCard.classList.add("m-2")
//         noteCard.innerHTML = ``}};

//data
const data = {
    // labels: ['2021-01-01', '2022-01-01', '2022-11-11'],
    datasets: [{
    label: 'Steps through the Year',
    backgroundColor: 'rgb(97, 133, 100)',
    borderColor: 'rgb(97, 133, 100)',
    //data: [6000, 3600, 9000, 8220, 9003, 10000, 20324, 45670]
    }]
};
//config
const config = {
    type: 'line',  //type of chart
    data: data, //pulls data array from above
    options: {
        scales:{
            x:{
            type: 'time', //sets x axis to days
                time:{
                unit:
                    'day'
                },
                min: '2022-01-01', //shows how far back you want to go
                max:  new Date()  //max is the current date
            }, 
            y:{
                beginAtZero: true,
                max: 50000
            }
            }
        }
    };

//rendering
  const myChart = new Chart(
    document.getElementById('lineChart'),
    config);

    //the select months function, 'months' parameter shows the value from the select options
function filterChart(months){
    // console.log(luxon.DateTime.now().plus({ months:-months.value}).toISODate())
    myChart.config.options.scales.x.min = luxon.DateTime.now().plus({ months: -months.value }).toISODate();
    myChart.config.options.scales.x.max = luxon.DateTime.now().toISODate();
    myChart.update();
}

//update chart with user steps info based on userID
function updateChart(){

    async function fetchData(){
        const url = `http://localhost:8080/api/v1/steps/user/${userId}`
        const response = await fetch(url);
        const datapoints = await response.json() //parsing into json file so js can read it
        console.log(datapoints);
        return datapoints;
    };
//function to grab date and steps from the DB
    fetchData().then(datapoints => {
        const month = datapoints.map((month, index) => {
            return month.dateOfWalk;
        }); //runs through the DB array obtained through fetchData function, grabs out dates into new array

        const userSteps = datapoints.map((steps, index)=>{
            return steps.stepCount;
        })
        //this takes everything grabbed from above and adds it as params for the chart
        myChart.config.data.datasets[0].data = userSteps;
        myChart.config.data.labels = month;

        //updates chart
        myChart.update();
    })
};
document.onload = updateChart();