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
                $.each(data, function(i, v) {
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
    $('.listePltChoisies').empty();
    var content = '<table class="table"><tbody>';
    content = content +'<tr><td scope="row" class="text-center align-middle">Plateforme</td>';
    objets.forEach(function(element) {
        content = content + '<td class="text-center"><h2>'+element.name+'</h2><br/><img style="width:100px; height:auto;" src="'+element.logo+'"/></td>';
    });
    content = content + '</tr><tr><td scope="row" class="text-center">Version</td>';
    objets.forEach(function(element) {
        content = content + '<td class="text-center">'+element.version+'</td>';
    });
    content = content + '</tr><tr><td scope="row" class="text-center">Type de modèle</td>';
    objets.forEach(function(element) {
        content = content + '<td class="text-center">'+element.typeModel+'</td>';
    });
    content = content + '</tr><tr><td scope="row" class="text-center">Développé par</td>';
    objets.forEach(function(element) {
        content = content + '<td class="text-center">'+element.developer+'</td>';
    });
    content = content + '</tr><tr><td scope="row" class="text-center">Première release en</td>';
    objets.forEach(function(element) {
        content = content + '<td class="text-center">'+element.initialRelease+'</td>';
    });
    content = content + '</tr><tr><td scope="row" class="text-center">Licence</td>';
    objets.forEach(function(element) {
        content = content + '<td class="text-center">'+element.license+'</td>';
    });
    content = content + '</tr><tr><td scope="row" class="text-center">Requêtage utilisé</td>';
    objets.forEach(function(element) {
        content = content + '<td class="text-center">'+element.requetage+'</td>';
    });
    content=content+'</tbody></table>';
    $('.listePltChoisies').append(content);

}