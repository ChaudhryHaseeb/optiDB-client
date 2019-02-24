var objets = [];

function deleteComparaison() {
    objets.splice(0,objets.length);
    $("#collee").empty();
    $("#collee").css("display","none");
}

function deleteFromComparaison(objet) {
    objets.forEach(function(element, index) {
        if(element.name == objet) {
            objets.splice(index,1);
            $('.'+element.name).remove();
            $('.comparaisonPlt').remove();
        }
    });
    console.log(objets);
    if(objets.length == 2) {
        $('#collee').append("<div class='col-md-3 col-sm-3 comparaisonPlt'><div><button class='btn btn-danger' data-toggle='modal' data-target='#myModal' onclick='comparerDeuxPlateformes()' >Comparer</button><button class='btn btn-danger' onclick='deleteComparaison()' style='margin-left: 20px;'>Supprimer</button></div></div>");
    }
    else {
        $('#comparaisonPlt').remove();
    }
    if(objets.length == 0) $("#collee").css("display","none");
}

function comparate(objet) {
        var presence = false;
        var cpt;
        for(cpt=0;cpt<objets.length;cpt++) {
            if(objets[cpt].name == objet) presence=true;
        }
        if(!presence) {
            $.getJSON('http://localhost:8080/js/listeComparaison.json', function(data) {
                console.log(data);
                $.each(data, function(i, v) {
                    console.log(objets);
                    if (v.name == objet) {
                        var bloc = '<div class="col-md-3 col-sm-3 '+v.name+'"><div>'+v.name+'<button href="#" onclick="deleteFromComparaison(\''+v.name+'\')" class="tn btn-danger">X</button></div></div>';

                        if(objets[0]==null) {
                            objets[0] = v;
                            $('#collee').append(bloc);
                            return;
                        }
                        if(objets[1] == null) {
                            objets[1] = v; $('#collee').append(bloc);
                            return;
                        }
                    }
                });
                if(objets.length == 2) {
                    $('#collee').append("<div class='col-md-3 col-sm-3 comparaisonPlt'><div><button class='btn btn-danger' data-toggle='modal' data-target='#myModal' onclick='comparerDeuxPlateformes()' >Comparer</button><button class='btn btn-danger' onclick='deleteComparaison()' style='margin-left: 20px;'>Supprimer</button></div></div>");
                }
                else {
                    $('#comparaisonPlt').remove();
                }
            });
        }
        $("#collee").css("display", "block");
    //console.log(objets.plateforme);
}

function comparerDeuxPlateformes() {
    console.log(objets);
    $('.listePltChoisies').empty();
    var entete = '<div class="col-sm-4"><div class="row rowTitre">Plateforme</div><div class="row rowLogo">Photo</div>' +
        '<div class="row rowType">Type</div><div class="row rowRequete">Langage de requêtage</div></div>';
    $('.listePltChoisies').append(entete);
    objets.forEach(function(element) {
        var content = '<div class="col-sm-4">';
        content=content+'<div class="row rowTitre"><h2>'+element.name+'</h2></div>';
        content=content+'<div  class="row rowLogo logoComparatif"><img style="height:40px; width:auto;" src="'+element.logo+'"/></div>';
        content=content+'<div  class="row rowType">'+element.type+'</div>';
        content=content+'<div  class="row rowRequete">'+element.name+'</div>';
        content=content+'</div>';
        $('.listePltChoisies').append(content);
    });
}