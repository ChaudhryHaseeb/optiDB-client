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
    /*var entete = '<div class="col-sm-4"><div class="row rowTitre">Plateforme</div><div class="row rowLogo">Photo</div>' +
        '<div class="row rowType">Type</div><div class="row rowRequete">Langage de requêtage</div></div>';
    $('.listePltChoisies').append(entete);*/
    var content = '<table class="table"><tbody>';
    content = content +'<tr><td scope="row" class="text-center align-middle">Plateforme</td>';
    objets.forEach(function(element) {
        content = content + '<td class="text-center"><h2>'+element.name+'</h2><br/><img style="width:100px; height:auto;" src="'+element.logo+'"/></td>';
    });
    /*content = content + '</tr><tr><td scope="row" class="text-center"></td>';
    objets.forEach(function(element) {
        content = content + '<td class="text-center"><img style="height:40px; width:auto;" src="'+element.logo+'"/></td>';
    });*/
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
    /*var properties = Object.getOwnPropertyNames(objets[0]);
    for(var i = 0;i<properties.length;i++) {
        var propriete = properties[i].toString();
        content=content+'<tr><td>';
        var nomColonne = '';
        switch (propriete) {
            case 'name': nomColonne = 'Nom'; break;
            case 'version': nomColonne = 'Version'; break;
            case 'description': nomColonne = 'Description'; break;
            case 'typeModel': nomColonne = 'Nom du modèle'; break;
            case 'website': nomColonne = 'Site'; break;
            case 'developer': nomColonne = 'Développeur'; break;
            case 'initialRelease': nomColonne = 'Date de sortie'; break;
            case 'license': nomColonne = 'Licence'; break;
            case 'requetage': nomColonne = 'Langage de requêtage'; break;
        }
        content=content+'</td>';
        objets.forEach(function(element) {
            console.log(element);
            console.log(element.name);
            content=content+'<td>';
            if(propriete == name) { content = content + '<h2>'+element.propriete+'</h2>'; }
            else {
                console.log(propriete);
                content = content + element.propriete;
            }
            content=content+'</td>';
        });
        //console.log(properties[i]);
        content=content+'</tr>';
    }*/

/*var con ='<tr> <td>Closed</td> <td>Open</td> <td>Open</td> <td>Closed</td> <td>Closed</td> </tr>';
    content=content+'</table>';
    objets.forEach(function(element) {
        var content = '<div class="col-sm-4">';
        content=content+'<div class="row rowTitre"><h2>'+element.name+'</h2></div>';
        content=content+'<div  class="row rowLogo logoComparatif"><img style="height:40px; width:auto;" src="'+element.logo+'"/></div>';
        content=content+'<div  class="row rowType">'+element.type+'</div>';
        content=content+'<div  class="row rowRequete">'+element.name+'</div>';
        content=content+'</div>';
        $('.listePltChoisies').append(content);
    });*/
}