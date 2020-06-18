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
/**
function loadRB(type) {
    if (type == 0) {
        var output = $("#createRB").find("#rbId");
        output.html("");
    }

        })

    $.ajax({
        url: "api/rbService/getRBList",
        contentType: "application/JSON",
        success: function (raavare) {
            console.log(raavare);
            var rbArray = [];
            for (let i = 0; i < raavare.length; i++) {
                if (!rbArray.includes(raavare[i].rbId)) {
                    output.append(` <option value="${raavare[i].rbId}">${raavare[i].rbId}</option>`);
                    rbArray.push(raavare[i].rbId);
                }
            }
        },
        error: function (XHR) {
            console.log(XHR);
            alert("Fejl:" + XHR.responseText);
        },

    });
}
*/

    function getRaavareId(raavareId, _callback) {
        $.ajax({
            type: "GET",
            url: "api/rbService/getRbId",
            data: {raavareId: raavareId},
            contentType: "application/JSON",
            success: function (data) {
                _callback(data);
            },
            error: function (XHR) {
                console.log(XHR);
                alert("Fejl:" + XHR.responseText);
            }

        });

    }