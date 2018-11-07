<%--
  Created by IntelliJ IDEA.
  User: Pourfex
  Date: 24/10/2018
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--@ page session="true" --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <%@include file="../jspf/dependencies.jspf" %>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="This is the setting page">
        <meta name="author" content="Alexis Delforges">

        <title>Paramètres</title>

    </head>

    <body>
        <div id="wrapper">
            <header>
                <%@ include file="/jspf/header_settings.jspf" %>
            </header>
            <div id="content">
                <h2>Paramètres</h2>
                <div id="param_detail">
                    <h3 id="temperature_param">Seuil température : ${seuil_temperature}</h3>
                    <h3 id="pression_param">Seuil pression : ${seuil_pression}</h3>
                    <h3 id="humidite_param">Seuil humidité : ${seuil_humidite}</h3>
                    <h3 id="vent_param">Seuil vitesse vent : ${seuil_vitesse_vent}</h3>
                    <div id="btn_edit_div">
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal_settings" data-whatever="@mdo">Modifier</button>
                    </div>

                </div>
            </div>
            <!-- Modal -->
            <div class="modal fade" id="modal_settings" tabindex="-1" role="dialog"
                 aria-labelledby="Settings" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle">Paramètres</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form>
                                <div class="form-group">
                                    <label for="input_temperature" class="col-form-label">Seuil température</label>
                                    <input type="text" value="${seuil_temperature}" class="form-control" id="input_temperature">
                                    <br>
                                    <label for="input_pression" class="col-form-label">Seuil préssion</label>
                                    <input type="text" value="${seuil_pression}" class="form-control" id="input_pression">
                                    <br>
                                    <label for="input_humidite" class="col-form-label">Seuil humidité</label>
                                    <input type="text" value="${seuil_humidite}" class="form-control" id="input_humidite">
                                    <br>
                                    <label for="input_vitesse_vent" class="col-form-label">Seuil vitesse vent</label>
                                    <input type="text" value="${seuil_vitesse_vent}" class="form-control" id="input_vitesse_vent">
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                            <button id="btn_save_modal" type="button" class="btn btn-primary">Sauvegardee</button>
                        </div>

                        <script>
                            $('#btn_save_modal').click(() => {
                                alert('lol');
                                let new_temperature = $('#input_temperature').val();
                                let new_pression = $('#input_pression').val();
                                let new_humidite = $('#input_humidite').val();
                                let new_vitesse_vent = $('#input_vitesse_vent').val();

                                ${seuil_temperature} = new_temperature;
                                ${seuil_pression} = new_pression;
                                ${seuil_humidite} = new_humidite;
                                ${seuil_vitesse_vent} = new_vitesse_vent;
                                /*

                                $('#temperature_param').text(${seuil_temperature});
                                $('#pression_param').text(${seuil_pression});
                                $('#humidite_param').text(${seuil_humidite} );
                                $('#vent_param').text(${seuil_vitesse_vent});*/
                                $('#modal_settings').modal('hide')

                            });
                        </script>
                    </div>
                </div>
            </div>

            <footer>
                <%@ include file="/jspf/footer.jspf" %>
            </footer>
        </div>
    </body>
</html>