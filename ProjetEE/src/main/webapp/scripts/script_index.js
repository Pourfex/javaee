$(document).ready(function () {

    // Menu highlight
    $(".nav-item").on("click", function() {
        $(".nav-item").removeClass("active_menu");
        $(this).addClass("active_menu");
    });

    var alerts = [{
        id:1,
        text:"Température raffinerie elevé"
    }, {
        id:2,
        text:"Pression raffinerie elevé"
    }];

    const nb_alerts = alerts.length;

    if(nb_alerts > 0){
        $("#alerts").show();
        $("#nb_alerts").text(nb_alerts>1?nb_alerts+" Alertes":nb_alerts+" Alerte");
        $("#alerts_detail").text("Dépuis votre dernière connexion");


        for(const alertObj of alerts){
            const alertElem = "<li>"+
                "<h3>"+alertObj.text+"</h3>"+
                "</li>";

            $("#alert_list").append(alertElem);
        }
    }


    $("#show_details").click(e => {
        e.preventDefault();
        let alert_list = $("#alert_list");

        if(alert_list.is(":visible")){
            alert_list.hide();

            $(".pull-right i").removeClass()
                .addClass("fa fa-arrow-circle-down");
            $(".pull-left").text("Afficher les détails");
        } else {
            alert_list.show();

            $(".pull-right i").removeClass()
                .addClass("fa fa-arrow-circle-up");
            $(".pull-left").text("Cacher les détails");
        }


    });


    $('#btn_save_modal').click(() => {

        let new_temperature = $('#input_temperature').val();
        let new_pression = $('#input_pression').val();
        let new_humidite = $('#input_humidite').val();
        let new_vitesse_vent = $('#input_vitesse_vent').val();

        /*
        "${seuil_temperature}" = new_temperature;
        ${seuil_pression} = new_pression;
        ${seuil_humidite} = new_humidite;
        ${seuil_vitesse_vent} = new_vitesse_vent;
        */

        $('#temperature_param').text("Seuil température : "+new_temperature);
        $('#pression_param').text("Seuil pression : "+new_pression);
        $('#humidite_param').text("Seuil humidité : "+new_humidite);
        $('#vent_param').text("Seuil vitesse vent : "+new_vitesse_vent);
        $('#modal_settings').modal('hide')

    });

});