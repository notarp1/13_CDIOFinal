/**
 * @author Zahra
 */

//Tilføj råvarebatch
$("#createRB").submit(function (event) {
    event.preventDefault();

    var RB_json = $("#createRB").serializeJSON();

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
                                     <td><button onclick="deleteRB(${data.rbId})">slet</button></td>
                                     </tr>`);
            })
        },
        error: function (fejlbesked) {
            console.log(fejlbesked);
            alert("Fejl: " + fejlbesked.responseText)
        }
    })



//Find specifik RB
    $("#findRB").submit(function (event) {
        event.preventDefault();

        var rb = $("#findRB").serializeJSON();
        $.ajax({
            url: "api/rbService/getRBList/" + rb.rbId,

            contentType: "application/JSON",
            method: "GET",
            success: function (rbListe) {
                console.log(rbListe);
                setTimeout(() => {
                    console.log("3");
                }, 1000)
                ;
            },
            error: function (XHR) {
                console.log(XHR);
                alert("Fejl: " + XHR.responseText);
            },
        });
    });

}

//Opdater råvarebatch
$("#updateRB").submit(function (event) {
    event.preventDefault()
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
})

//slette råvarebatch
function deleteRB(rbId) {
    $.ajax({
        url: "api/rbService/getRB",
        data: {rbId: rbId},
        contentType: "aplication/JSON",
        media: "DELETE",
        success: function (raavarebatch) {
            alert(raavarebatch);
            loadRB();
        },
        error: function (xhr) {
            console.log(xhr);
            alert("Fejl: " + xhr.responseText);
        }
    })
}

//Hent data fra database
/**
function loadRB(type) {
    if (type == 0) {
        var output = $("#createRB").find("#rbId");
        output.html("");
    }

    if (type == 1) {
        var output = $("#updateRB").find("#rbId");
        output.html("");
    }

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
