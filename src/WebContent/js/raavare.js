
/**
 *@Author Christine
 */


//Tilføj raavare
$("#createRaa").submit(function (event){
    event.preventDefault();
    $.ajax({
        url: "api/raaService",
        data: JSON.stringify($("#createRaa").serializeJSON()),
        contentType: "application/JSON",
        method: "POST",
        success: function (data) {
            console.log(data);
            alert(data)
        },
        error: function(XHR) {
            console.log(XHR);
            alert("Fejl: " + XHR.responseText);

        },
    });
});

// edit

$("#updateRaa").submit(function (event){
    event.preventDefault();
    $.ajax({
        url: "api/raaService",
        data: JSON.stringify($("#updateRaa").serializeJSON()),
        contentType: "application/JSON",
        method: "PUT",
        success: function (data) {
            console.log(data);
            alert(data)
        },
        error: function(XHR) {
            console.log(XHR);
            alert("Fejl: " + XHR.responseText);
        },
    });
});

// slette raavare

function deleteRaavare(raavareId){
    $.ajax({
        url: "api/raaService/" + raavareId,
        contentType: "application/JSON",
        method: "DELETE",
        success: function (raavare) {
            alert(raavare);
            loadRaavare();
        },
        error: function (xhr) {
            console.log(xhr);
            alert("Fejl: " + xhr.responseText);
        }
    })
}


// se raavare listen
function loadRaavare() {
    var raavareTable = $("#raavare-table").find("tbody");
    raavareTable.html("");
    $.ajax({
        type: "GET",
        url: "api/raaService/list",
        contentType: "application/JSON",

        success: function (data) {
            console.log(data);
            data.forEach(function (data) {
                console.log(data);
                raavareTable.append(`<tr>
                    <td>${data.raavareId}</td>
                    <td>${data.raavareNavn}</td>
                    <td>${data.leverandoer}</td>
                    <td><button onclick="deleteRaavare(${data.raavareId})">slet</button></td>
                </tr>`);
            })

        },
        error: function (XHR) {
            console.log(XHR);
            alert("Fejl: " + XHR.responseText);
        }
    })

}


function getRaavare(raavareId, _callback) {
    $.ajax({
        url: "api/raaService/" + raavareId,
        contentType: "application/JSON",


        success: function (raa) {
            console.log(raa);

            _callback(raa.raavareNavn);

        },
        error: function (XHR) {
            console.log(XHR);
            alert("Fejl: " + XHR.responseText);
        },
    });
}


//gør så man kan vælge mellem eksisterende raavare id'er naar man opdatere en raavare
function loadRaavareSelection() {


    var output = $("#updateRaa").find("#raavareId");
    output.html("");



    $.ajax({
        url: "api/raaService/list",
        contentType: "application/JSON",
        success: function (raavare) {
            console.log(raavare);

            for (let i = 0; i < raavare.length; i++) {
                output.append(` <option value="${raavare[i].raavareId}">${raavare[i].raavareId}</option>`);
            }

        },
        error: function (XHR) {
            console.log(XHR);
            alert("Fejl:" + XHR.responseText);
        },
    });
}

function getRaavare(raavareId, _callback) {
    $.ajax({
        url: "api/raaService/" + raavareId,
        contentType: "application/JSON",

        success: function (raa) {
            console.log(raa);

            _callback(raa.raavareNavn);

        },
        error: function (XHR) {
            console.log(XHR);
            alert("Fejl: " + XHR.responseText);
        },
    });
}


