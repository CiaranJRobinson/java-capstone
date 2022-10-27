// const dataValue =;


//data
const data = {
    labels: ['2021-01-01', '2022-01-01', '2022-11-11'],
    datasets: [{
    label: 'Steps through the Year',
    backgroundColor: 'rgb(97, 133, 100)',
    borderColor: 'rgb(97, 133, 100)',
    data: [6000, 3600, 9000, 8220, 9003, 10000, 20324, 45670]
    }]
};
//config
const config = {
    type: 'line',  //type of chart
    data: data.datasets.data, //pulls data array from above
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
