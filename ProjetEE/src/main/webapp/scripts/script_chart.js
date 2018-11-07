$(document).ready(function () {

    $.get("/capteursdata",
        function (result) {
            $('.loader').hide();

            if (result.success) {
                let data = JSON.parse(result.data);

                console.log(data);
                displayCharts(data)

            } else {
                showSnackBar("Erreur : Impossible de récupérer les données des capteurs", 'snack_ko', 3000);
            }
        });

    $('#btn_search').click(() => {


        let id = $('#search_id').val();
        let tag = $('#search_tag').val();
        let type = $('#search_type').val();


        if (id.length === 0 && tag.length === 0 && type.length === 0) {
            showSnackBar("Veuillez entrer au moins un paramètre de recherche", 'snack_ko', 3000);
        } else {
            let ids = id.length === 0 ? "all" : id;
            let tags = tag.length === 0 ? "all" : tag;
            let types = type.length === 0 ? "all" : type;

            $('.loader').show();
            //let queryString = "/search?ids=" + ids + "&tags=" + tags + "&types=" + types;

            let queryString = "/search";
            console.log(queryString);

            $.ajax({
                url: queryString,
                type: "POST",
                data: {
                    ids: ids,
                    tags: tags,
                    types: types
                },
                success: function (result) {
                    $('.loader').hide();

                    if (result.success) {
                        let data = JSON.parse(result.data);

                        console.log(data);
                        displayCharts(data)

                    } else {
                        showSnackBar("Erreur : Impossible de récupérer les données des capteurs", 'snack_ko', 3000);
                    }
                }
            });
        }
    });


//const chartArray = [];
    function displayCharts(arrayData) {
        if (arrayData.length > 0) {
            for (const dataObj of arrayData) {
                let capteurData = [];
                let somme_moyennes = 0;
                let valeur_moyenne = null;
                let lastElem;

                if (dataObj.data.length > 0) {
                    for (const element of dataObj.data) {

                        somme_moyennes += element.value;

                        capteurData.push({
                            x: element.timestamp,
                            y: element.value
                        });
                    }
                    valeur_moyenne = somme_moyennes / dataObj.data.length;
                    lastElem = dataObj.data[dataObj.data.length - 1];

                    let graphColor = null;
                    let graphBorderColor = null;
                    switch (lastElem.type) {
                        case "TEMPERATURE":
                            graphColor = "rgba(244,67,54, 0.2)";
                            graphBorderColor = "#F44336";
                            break;
                        case "PRESSION":
                            graphColor = "rgba(76,175,80, 0.2)";
                            graphBorderColor = "#4CAF50";
                            break;
                        case "HUMIDITE" :
                            graphColor = "rgba(33,150,243, 0.2)";
                            graphBorderColor = "#2196F3";
                            break;
                        case "VITESSE_VENT":
                            graphColor = "rgba(255,152,0 0.2)";
                            graphBorderColor = "#FF9800";
                            break;
                    }

                    const chartData = {
                        datasets: [{
                            data: capteurData,
                            label: '',
                            fill: 'origin',
                            backgroundColor: graphColor,
                            borderColor: graphBorderColor,
                            pointBorderWidth: 1,
                            pointHoverBorderWidth: 2,
                            pointHoverRadius: 2,
                            pointRadius: 1,
                            borderWidth: 2,
                            cubicInterpolationMode: 'monotone'
                        }
                        ]
                    };

                    const chartCanvas = "<li class=\"row\">" +
                        "<div class=\"chart_canvas col-md-8\">" +
                        "<canvas id=\"chart" + lastElem.id + "\"></canvas>" +
                        "</div>" +
                        "<div class=\"col chart_info col-md-4\">" +
                        "<h3>Nom : " + toTitleCase(dataObj.nom) + "</h3>" +
                        "<h3>Type : " + toTitleCase(lastElem.type) + "</h3>" +
                        "<h3>Tag : " + toTitleCase(dataObj.tag) + "</h3>" +
                        "<h3>Ville : " + toTitleCase(dataObj.ville.split('+')[1].trim()) + "</h3>" +
                        "<h3>Pays : " + toTitleCase(dataObj.pays.split('+')[1].trim()) + "</h3>" +
                        "<h3>Moyenne en 2h : " + Math.round(valeur_moyenne) + "</h3>" +
                        "<a target=\"_blank\" href=\"capteur?id=" + lastElem.id + "\">Voir plus</a>" +
                        "</div>" +
                        "</li>";

                    $("#chart_list").append(chartCanvas);
                    const context = document.getElementById("chart" + dataObj.data[0].id).getContext('2d');
                    let myChart = new Chart(context, {
                        type: 'line',
                        data: chartData,
                        options: {
                            responsive: true,
                            maintainAspectRatio: false,
                            legend: {
                                display: false
                            },
                            tooltips: {

                                callback: function (tooltipItem) {
                                    return tooltipItem.yLabel;
                                }
                            },
                            scales: {
                                xAxes: [{
                                    type: 'time',
                                    time: {
                                        max: lastElem.date + 300000,
                                        min: lastElem.date - 7200000,
                                        unit: 'hour',
                                        tooltipFormat: 'DD-MM-YYYY HH:mm:ss',
                                        stepSize: 0.05,
                                        displayFormats: {
                                            'hour': 'HH:mm'
                                        },
                                    },
                                    ticks: {
                                        autoSkip: true,
                                        maxTicksLimit: 8
                                    }
                                }]
                            }
                        }
                    });
                }

            }
        } else {
            showSnackBar("Pas de données disponibles pour les deux dernières heures", 'snack_ko', 3000);
        }
    }

})
;


function getFormattedTime(timestamp) {
    let dateWithoutSecond = new Date(timestamp);
    return dateWithoutSecond.toLocaleTimeString([], {hour: '2-digit', minute: '2-digit', hour12: false});
}


function toTitleCase(str) {
    return str.replace(/\w\S*/g, function (txt) {
        return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
    });
}

function showSnackBar(message, state, duration) {
    $('#snackbar').text(message);
    $('#snackbar').addClass(state);
    $('#snackbar').addClass("show");

    // After duration seconds, remove the show class from DIV
    setTimeout(function () {
        $('#snackbar').removeClass("show");
        $('#snackbar').removeClass(state);
    }, duration);
}