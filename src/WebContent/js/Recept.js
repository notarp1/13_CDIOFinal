/**
 * @Author Ismail
 */



//Recept

$("#createR").submit(function (event) {
    event.preventDefault();
    $.ajax({
        url: "api/recept",
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


$("#updateR").submit(function (event) {
    event.preventDefault()
    $.ajax({
        url: "api/recept",
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
        url: "api/recept/" + receptId,
        contentType: "application/JSON",
        method: "GET",
        success: function (recept) {
            console.log(recept);
            if (confirm('Vil du slette recept med ID : '+ recept.receptId+'?')) {
                $.ajax({
                    url: "api/recept/" + receptId,
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
        url: "api/recept/list",
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

function getRecept(receptId, _callback){

    $.ajax({
        type: "GET",
        url: "api/recept/" + receptId,
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

function getSpecificReceptKomps(receptId, _callback){

    $.ajax({
        type: "GET",
        url: "api/recept/komp/list/" + receptId,
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


//ReceptKomp

$("#createRKomp").submit(function (event) {
    event.preventDefault();
    $.ajax({
        url: "api/recept/komp",
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

$("#updateRKomp").submit(function (event) {
    event.preventDefault()
    $.ajax({
        url: "api/recept/komp",
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
        url: "api/recept/komp/" + receptId + "/" + raavareId,
        contentType: "application/JSON",
        method: "GET",
        success: function (recept) {
            console.log(recept);
            if (confirm('Vil du slette ReceptKomp med ID : '+recept.receptId + '  og Raavare ID: ' + recept.raavareId+'?')) {
                $.ajax({
                    url: "api/recept/komp/" + receptId + "/" + raavareId,
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
        url: "api/recept/komp/list",
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


//Update af Recept
function loadR(type) {
    if(type == 1) {
        var output = $("#updateR").find("#receptId");
        output.html("");
    }


    $.ajax({
        url: "api/recept/list",
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




//oprettelse for ReceptKomp (receptID)
function loadRKomp(type) {
    if(type == 0) {
        var output = $("#createRKomp").find("#receptId");
        output.html("");
    }

    $.ajax({
        url: "api/recept/list",
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

//oprettelse af receptKomp (raavareId)
function loadRKompR(type) {
    if(type == 0) {
        var output = $("#createRKomp").find("#raavareId");
        output.html("");
    }


    $.ajax({
        url: "api/raaService/list",
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

//update af ReceptKomp(recept ID)
function loadRKompUpdate(type) {

    if(type == 1) {
        var output = $("#updateRKomp").find("#receptId");
        output.html("");
    }


    $.ajax({
        url: "api/recept/komp/list",
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

//update af ReceptKomp(raavare ID)
function loadRKompRUpdate(type) {

    if(type == 1) {
        var output = $("#updateRKomp").find("#raavareId");
        output.html("");
    }


    $.ajax({
        url: "api/recept/komp/list",
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