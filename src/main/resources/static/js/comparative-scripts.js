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

$( document ).ready(function() {
    $( "input:checked" ).prop('checked',false);
});

function choixPlt() {
    /*var xhr = new XMLHttpRequest();
    xhr.open("GET", "http://192.168.33.10:8080/media", true);
    xhr.onload = function () {
        console.log(xhr.responseText);
    };
    xhr.send();*/
    /*$.get("http://192.168.33.10:8080/media").done(function (data) {
        console.log(data);
    });*/
    /*$.getJSON('http://192.168.33.10:8080/media', function(data) {
            console.log(data[0]);
        /*if(objets.length == 2) {
            $('#collee').append("<div class='col-md-3 col-sm-3 comparaisonPlt'><div><button class='btn btn-danger' data-toggle='modal' data-target='#myModal' onclick='comparerDeuxPlateformes()' >Comparer</button><button class='btn btn-danger' onclick='deleteComparaison()' style='margin-left: 20px;'>Supprimer</button></div></div>");
        }
        else {
            $('#comparaisonPlt').remove();
        }*/
    //});
    console.log($( "input:checked" ).length);
    /*var i = 0;
    var nbbox = $(".checkbox3").length;
    for(var it=0;it<nbbox;i++) {
        if($('#'+i).is(":checked")) {
            i++;
        }
    }*/
    if($( "input:checked" ).length== 2)
    {
        $('#validCompareHist').prop('disabled', false);
        $('#validCompareHist').css("display","initial");
    }
    else
    {
        $('#validCompareHist').prop('disabled', true);
        $('#validCompareHist').css("display","none");
    }
    /*while(true) {
        i = i+1;
        if(typeof $('#'+3).val() === undefined) break;
    }
    console.log(i);*/
    /*while($('#'+i).val()!="") {
        i = i +1;
        if($('#'+i).val()=="") {
            return;
        }
    }/*
    console.log(i);
    /*console.log($('.checkbox-2').is(':checked'));
    console.log($('input[type=checkbox]').val());*/
}
/*$('.checkbox-2').on('change',function(){

    if($('input:checkbox').is(':checked').length>= 2)
    {
        $('#validCompareHist').prop('disabled', true);
    }
    else
    {
        $('#validCompareHist').prop('disabled', false);
    }
    //$("#validCompareHist").hide() // try to hide google navigation bar
});*/
/*$(function(){
    $("#validCompareHist").hide() // try to hide google navigation bar
});*/

/*$('#formCompareHisto').bind('keydown', function(event) {
    // IE
    if(event.keyCode == 13) {
        event.returnValue = false;
        event.cancelBubble = true;
    }
    // DOM
    if(event.which == 13) {
        event.preventDefault();
        event.stopPropagation();
    }
});*/

