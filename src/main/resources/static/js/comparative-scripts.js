var objets = [];

function deleteFromComparaison(objet) {
    objets.forEach(function(element, index) {
        if(element.plateforme == objet) {
            objets.splice(index,1);
            $('.'+element.plateforme).remove();
            $('.comparaisonPlt').remove();
        }
    });
    console.log(objets);
    if(objets.length == 2) {
        $('#collee').append("<div class='col-md-3 col-sm-3 comparaisonPlt'><div><button class='btn btn-danger' data-toggle='modal' data-target='#myModal' onclick='comparerDeuxPlateformes()' >Comparer</button></div></div>");
    }
    else {
        $('#comparaisonPlt').remove();
    }
}

function comparate(objet) {
    var presence = false;
    var cpt;
    for(cpt=0;cpt<objets.length;cpt++) {
        if(objets[cpt].plateforme == objet) presence=true;
    }
    if(!presence) {
        //if(objets)
        $.getJSON('http://localhost:8080/js/listeComparaison.json', function(data) {
            $.each(data, function(i, v) {
                console.log(objets);
                if (v.plateforme == objet && !objets.includes(v)) {
                    var bloc = '<div class="col-md-3 col-sm-3 '+v.plateforme+'"><div>'+v.plateforme+'<button href="#" onclick="deleteFromComparaison(\''+v.plateforme+'\')" class="tn btn-danger">Supprimer</button></div></div>';

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
                $('#collee').append("<div class='col-md-3 col-sm-3 comparaisonPlt'><div><button class='btn btn-danger' data-toggle='modal' data-target='#myModal' onclick='comparerDeuxPlateformes()' >Comparer</button></div></div>");
            }
            else {
                $('#comparaisonPlt').remove();
            }
        });
    }
    console.log(objets.plateforme);

}

function comparerDeuxPlateformes() {
    console.log(objets);
    $('.listePltChoisies').empty();
    var entete = '<div class="col-sm-4"><div class="row rowTitre">Plateforme</div><div class="row rowLogo">Photo</div>' +
        '<div class="row rowType">Type</div><div class="row rowRequete">Langage de requêtage</div></div>';
    $('.listePltChoisies').append(entete);
    objets.forEach(function(element) {
        var content = '<div class="col-sm-4">';
        content=content+'<div class="row rowTitre"><h2>'+element.plateforme+'</h2></div>';
        content=content+'<div  class="row rowLogo logoComparatif"><img style="height:40px; width:auto;" src="'+element.logo+'"/></div>';
        content=content+'<div  class="row rowType">'+element.type+'</div>';
        content=content+'<div  class="row rowRequete">'+element.requetage+'</div>';
        content=content+'</div>';
        $('.listePltChoisies').append(content);
    });
}