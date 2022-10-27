// const dataValue =;

const labels = [
    'January',
    'February',
    'March',
    'April',
    'May',
    'June',
    'July',
    'August',
    'September',
    'October',
    'November',
    'December',
];
//data
const data = {
    labels: ['2021-01-01', '2022-01-01', '2022-11-11'],
    datasets: [{
    label: 'Steps through the Year',
    backgroundColor: 'rgb(97, 133, 100)',
    borderColor: 'rgb(97, 133, 100)',
    data: [6, 3, 9],
    }]
};
//config
const config = {
    type: 'line',
    data: data,
    options: {
        scales:{
            x:{
            type: 'time',
                time:{
                unit:
                    'day'
                },
                min: '2022-01-01',
                max:  new Date()
            }, 
            y:{
                beginAtZero: true
            }
            }
        }
    };

//rendering
  const myChart = new Chart(
    document.getElementById('lineChart'),
    config);
