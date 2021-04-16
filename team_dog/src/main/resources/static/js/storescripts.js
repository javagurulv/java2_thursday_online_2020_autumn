/* globals Chart:false, feather:false */

(function () {
    'use strict'

    feather.replace()

    // Graphs
    var ctx = document.getElementById('myChart')
    // eslint-disable-next-line no-unused-vars
    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: [
                'Sunday',
                'Monday',
                'Tuesday',
                'Wednesday',
                'Thursday',
                'Friday',
                'Saturday'
            ],
            datasets: [{
                data: [
                    15339,
                    21345,
                    18483,
                    24003,
                    23489,
                    24092,
                    12034
                ],
                lineTension: 0,
                backgroundColor: 'transparent',
                borderColor: '#007bff',
                borderWidth: 4,
                pointBackgroundColor: '#007bff'
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: false
                    }
                }]
            },
            legend: {
                display: false
            }
        }
    })
})()


function myFunction() {
    alert("Hello! I am an alert box!");
}

function add_to_basket(itemId){
    var http = new XMLHttpRequest();
    http.open("POST", "http://localhost:8080/script/addToBasket", true);
    http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    http.send("itemId=" + itemId);
    http.onload = function() {
        alert(http.responseText);
    }
}

// function getPdf(){
//     var http = new XMLHttpRequest();
//     http.open("GET", "http://localhost:8080/script/getPdf", true);
//     http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
//     http.onload = function() {
//         alert("yo");
//     }
// }

// function complete_order(){
//     var http = new XMLHttpRequest();
//     http.open("POST", "http://localhost:8080/getPdf", true);
//     http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
//     http.send();
//     http.onload = function() {
//         alert("good ?");
//     }
// }
