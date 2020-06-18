/**
 * @Author Ismail
 */



//Recept

$("#createR").submit(function (event) {
    event.preventDefault();
    $.ajax({
        url: "api/recept1/createR",
        data: JSON.stringify($("#createR").serializeJSON()),
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

//
function loadR(type) {
    if(type == 0) {
        var output = $("#createR").find("#updateR");
        output.html("");
    }

    if(type == 1) {
        var output = $("#updateR").find("#receptId");
        output.html("");
    }


    $.ajax({
        url: "api/recept1/rList",
        contentType: "application/JSON",
        success: function (recept) {
            console.log(recept);

            for(let i = 0; i<recept.length; i++){
                output.append(` <option value="${recept[i].receptId}">${recept[i].receptId}</option>`);
            }

        },
        error: function(XHR) {
            console.log(XHR);
            alert("Fejl:" + XHR.responseText);
        },
    });
}

$("#updateR").submit(function (event) {
    event.preventDefault()
    $.ajax({
        url: "api/recept1/updateR",
        data: JSON.stringify($("#updateR").serializeJSON()),
        contentType: "application/json",
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

function deleteRecept(receptId) {
    $.ajax({
        url: "api/recept1/rId",
        data: {receptId: receptId},
        contentType: "application/JSON",
        method: "GET",
        success: function (recept) {
            console.log(recept);
            if (confirm('Vil du slette recept med ID : '+ recept.receptId+'?')) {
                $.ajax({
                    url: "api/recept1/deleteR",
                    data: JSON.stringify({receptId: receptId}),
                    contentType: "application/JSON",
                    method: "DELETE",
                    success: function (data) {
                        console.log(data);
                        alert(data);
                        main.switchPage('HTML/recept/Recept.html')
                    },
                    error: function (XHR) {
                        console.log(XHR);
                        alert("Fejl:" + XHR.responseText);
                    },
                });
            }
        },
        error: function (XHR) {
            console.log(XHR);
            alert("Fejl:" + XHR.responseText);
        },
    });
}


function recpetList() {
    var rList = $("#rList");
    $.ajax({
        type: "GET",
        url: "api/recept1/rList",
        contentType: "application/JSON",
        success: function (data) {
            console.log(data);
            data.forEach(function (data) {
                console.log(data);
                rList.append(`<tr>
                    <td>${data.receptId}</td>
                    <td>${data.receptNavn}</td>
                    <td><button onclick="deleteRecept(${data.receptId})">slet</button></td>
                </tr>`);
            })
        },
        error: function (XHR) {
            console.log(XHR);
            alert("Fejl:" + XHR.responseText);
        }
})

}


//ReceptKomp

$("#createRKomp").submit(function (event) {
    event.preventDefault();
    $.ajax({
        url: "api/recept1/createRKomp",
        data: JSON.stringify($("#createRKomp").serializeJSON()),
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

//RKomp ID
function loadRKomp(type) {
    if(type == 0) {
        var output = $("#createRKomp").find("#receptId");
        output.html("");
    }

    $.ajax({
        url: "api/recept1/rList",
        contentType: "application/JSON",
        success: function (recept) {
            console.log(recept);

            for(let i = 0; i<recept.length; i++){
                output.append(` <option value="${recept[i].receptId}">${recept[i].receptId}</option>`);
            }

        },
        error: function(XHR) {
            console.log(XHR);
            alert("Fejl:" + XHR.responseText);
        },
    });

}

//RKomp raavare ID
function loadRKompR(type) {
    if(type == 0) {
        var output = $("#createRKomp").find("#raavareId");
        output.html("");
    }


    $.ajax({
        url: "api/raaService/getRaaList",
        contentType: "application/JSON",
        success: function (raavare) {
            console.log(raavare);

            for(let i = 0; i<raavare.length; i++){
                output.append(` <option value="${raavare[i].raavareId}">${raavare[i].raavareId}</option>`);
            }

        },
        error: function(XHR) {
            console.log(XHR);
            alert("Fejl:" + XHR.responseText);
        },
    });

}

function loadRKompUpdate(type) {

    if(type == 1) {
        var output = $("#updateRKomp").find("#receptId");
        output.html("");
    }


    $.ajax({
        url: "api/recept1/RKompList2",
        contentType: "application/JSON",
        success: function (recept) {
            console.log(recept);

            for(let i = 0; i<recept.length; i++){
                output.append(` <option value="${recept[i].receptId}">${recept[i].receptId}</option>`);
            }

        },
        error: function(XHR) {
            console.log(XHR);
            alert("Fejl:" + XHR.responseText);
        },
    });

}

function loadRKompRUpdate(type) {

    if(type == 1) {
        var output = $("#updateRKomp").find("#raavareId");
        output.html("");
    }


    $.ajax({
        url: "api/recept1/RKompList2",
        contentType: "application/JSON",
        success: function (raavare) {
            console.log(raavare);

            for(let i = 0; i<raavare.length; i++){
                output.append(` <option value="${raavare[i].raavareId}">${raavare[i].raavareId}</option>`);
            }

        },
        error: function(XHR) {
            console.log(XHR);
            alert("Fejl:" + XHR.responseText);
        },
    });

}

$("#updateRKomp").submit(function (event) {
    event.preventDefault()
    $.ajax({

        url: "api/recept1/updateRKomp",
        data: JSON.stringify($("#updateRKomp").serializeJSON()),
        contentType: "application/json",
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


function deleteRKomp(receptId, raavareId) {
    $.ajax({
        url: "api/recept1/getRKomp",
        data: {receptId : receptId, raavareId : raavareId},
        contentType: "application/JSON",
        method: "GET",
        success: function (recept) {
            console.log(recept);
            if (confirm('Vil du slette ReceptKomp med ID : '+recept.receptId + '  og Raavare ID: ' + recept.raavareId+'?')) {
                $.ajax({
                    url: "api/recept1/deleteRkomp",
                    data: JSON.stringify({receptId : receptId, raavareId : raavareId}),
                    contentType: "application/JSON",
                    method: "DELETE",
                    success: function (data) {
                        console.log(data);
                        alert(data);
                        main.switchPage('HTML/recept/ReceptKomp.html')
                    },
                    error: function (XHR) {
                        console.log(XHR);
                        alert("Fejl:" + XHR.responseText);
                    },
                });
            }
        },
        error: function (XHR) {
            console.log(XHR);
            alert("Fejl:" + XHR.responseText);
        },
    });
}


function rKompList() {
    var rKompList = $("#rKompList");
    $.ajax({
        type: "GET",
        url: "api/recept1/RKompList2",
        contentType: "application/JSON",
        success: function (data) {
            console.log(data);
            data.forEach(function (data) {
                console.log(data);
                rKompList.append(`<tr>
                    <td>${data.receptId}</td>
                    <td>${data.raavareId}</td>
                    <td>${data.nomNetto}</td>
                    <td>${data.tolerance}</td>
                    
                    <td><button onclick="deleteRKomp(${data.receptId}, ${data.raavareId})">slet</button></td>
                </tr>`);
            })
        },
        error: function (XHR) {
            console.log(XHR);
            alert("Fejl:" + XHR.responseText);
        }
    })

}