/**
 * @author Zahra
 */

//Tilføj råvarebatch
$("#createRB").submit(function (event) {
    event.preventDefault();

    var RB_json = $("#createRB").serializeJSON();
    console.log(RB_json);
    $.ajax({
        url: "api/rbService/createRB",
        data: JSON.stringify(RB_json),
        contentType: "application/JSON",
        method: "POST",
        success: function (data) {
            console.log("createRB" + RB_json);
            alert(data);
        },

        error: function (XHR) {
            console.log(XHR);
            alert("Fejl: " + XHR.responseText);
        },
    });
});

//Load og se råvarebatches

function loadRB() {
    var rbTable = $("#rb-table").find("tbody");
    rbTable.html("");

    $.ajax({
        url: "api/rbService/getRBList",
        contentType: "application/JSON",

        success: function (raavareListe) {
            console.log(raavareListe);
            raavareListe.forEach(function (raavareListe) {
                rbTable.append(`<tr> 
                                     <td>${raavareListe.rbId}</td>
                                     <td>${raavareListe.raavareId}</td>
                                     <td>${raavareListe.maengde}</td>
                                     <td><button onclick="deleteRB(${raavareListe.rbId})">slet</button></td>
                                     </tr>`);
            })
        },
        error: function (fejlbesked) {
            console.log(fejlbesked);
            alert("Fejl: " + fejlbesked.responseText)
        }
    });

}


//Opdater råvarebatch
$("#updateRB").submit(function (event) {
    event.preventDefault();
    $.ajax({
        url: "api/rbService/updateRB",
        data: JSON.stringify($("#updateRB").serializeJSON()),
        contentType: "application/JSON",
        method: "PUT",
        success: function (data) {
            console.log(data);
            alert(data)
            main.switchPage("HTML/raavareBatch/visRaavarebatch.html")
        },
        error: function (XHR) {
            console.log(XHR);
            alert("Fejl: " + XHR.responseText);
        }
    })
});

//slette råvarebatch
function deleteRB(rbId) {
    console.log(rbId);
    $.ajax({
        url: "api/rbService/getRB",
        data: {rbId: rbId},
        contentType: "application/JSON",
        media: "GET",
        success: function (raavarebatch) {
            if(confirm('Vile du slette råvarebatch med ID:' + raavarebatch.rbId) + "?"){

                $.ajax({
                    url: "api/rbService/deleteRB",
                    data: JSON.stringify(raavarebatch),
                    contentType: "application/JSON",
                    method: "DELETE",
                    success: function (data) {
                        console.log(data);
                        alert(data);
                        main.switchPage('HTML/raavareBatch/visRaavarebatch.html')
                    },
                    error: function (XHR) {
                        console.log(XHR);
                        alert("Fejl:" + XHR.responseText);
                    },
                });

            }

        },
        error: function (xhr) {
            console.log(xhr);
            alert("Fejl: " + xhr.responseText);
        }
    });
}

//Hent data fra database
function loadRaavareList(value) {

    if(value == 0) {
        var output = $("#createRB").find("#raavareId");
        output.html("");
    }
    if(value == 1) {
        var output = $("#updateRB").find("#raavareId");
        output.html("");
    }


    $.ajax({
        url: "api/raaService/getRaaList",
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

function loadRaavarebatch() {

    var output = $("#updateRB").find("#rbId");
    output.html("");

    $.ajax({
        url: "api/rbService/getRBList",
        contentType: "application/JSON",
        success: function (raavarebatch) {
            console.log(raavarebatch);

            for (let i = 0; i < raavarebatch.length; i++) {
                output.append(` <option value="${raavarebatch[i].rbId}">${raavarebatch[i].rbId}</option>`);
            }

        },
        error: function (XHR) {
            console.log(XHR);
            alert("Fejl:" + XHR.responseText);
        },
    });

}

