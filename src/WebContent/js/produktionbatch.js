/**
 * @Author Christian
 */



function switchPage(page) {
    $("body").load(page);
}



function getPrintInfo(pbId, receptId, date, status, pStartDate, update) {

    document.getElementById("printDate").innerHTML ="Udskrevet: " + getPrintDate();
    getPbId(pbId);
    getReceptId(receptId);
    getPbStatus(status, date, pbId, pStartDate, update);


}

function getPrintDate() {
    n =  new Date();
    y = n.getFullYear();
    m = n.getMonth() + 1;
    d = n.getDate();

    if(m < 10) {
        return y + "-0" + m + "-" + d;
    } else return y + "-" + m + "-" + d;
}

function getPbId(pbId) {
    n = pbId;
    document.getElementById("printPbId").innerHTML = "Produktbatch ID: " +  n;
}

function getReceptId(receptId) {
    n = receptId;
    document.getElementById("printReceptId").innerHTML = "Recept ID: " +  n;
}

function getPbDate(date) {
    n = date;
    return n;

}

function getPbStatus(status, date, pbId, pStartDate, update) {



    if (update == 0) {
        document.getElementById("printStatus").innerHTML = "Status: 'oprettet'";
        document.getElementById("pbStart").innerHTML = "Produktions Start: " + "-ikke begyndt-";

    } else {

        if (status == 0) {

            document.getElementById("printStatus").innerHTML = "Status: 'oprettet'";
            document.getElementById("pbStart").innerHTML = "Produktions Start: " + "-ikke begyndt-";
        } else {
            $.ajax({
                url: "api/pbService/getPB/",
                data: {pbId: pbId},
                contentType: "application/JSON",
                method: "GET",
                success: function (pb) {
                    console.log(pb);
                    if (confirm('Vil du ændre status fra ' + pb.status + ' til ' + status + '?')) {
                        if (status == 1) {
                            if (pb.pStartDato == null) {

                                pb.pStartDato = getPrintDate();
                                pb.status = 1;
                                console.log(pb);
                            }
                        }
                        if (status == 2) {
                            pb.status = 2;
                        } else pb.status = status;

                        $.ajax({
                            url: "api/pbService/updatePB",
                            data: JSON.stringify(pb),
                            contentType: "application/JSON",
                            method: "PUT",
                            success: function (pbUp) {

                                alert(pbUp);
                                if (status == 1) {
                                    document.getElementById("printStatus").innerHTML = "Status: 'under produktion'";
                                    document.getElementById("pbStart").innerHTML = "Produktions Start: " + pb.pStartDato;
                                } else {
                                    document.getElementById("printStatus").innerHTML = "Status: 'afsluttet'";
                                    document.getElementById("pbStart").innerHTML = "Produktions Start: " + getPbDate(pStartDate);
                                }
                            },
                            error: function (XHR) {
                                console.log(XHR);
                                alert("Fejl: " + XHR.responseText);
                                main.switchPage('HTML/login.html')
                            },

                        });
                    } else main.switchPage('HTML/login.html');

                },
            });
        }
    }
}


function printAppend(print, i) {
    var loop = i;


        print.append(` <table id="pbPrint-tabel">
        <thead>
        <tr>
            <th>Råvare ID</th>
            <th>Råvare</th>
            <th>Mængde</th>
            <th>Tolerance</th>
            <th>Tara</th>
            <th>Netto(kg)</th>
            <th>Tolerance</th>
            <th>Råvarebatch</th>
            <th>Opr</th>
        </tr>
        </thead>
        <tbody id="tableItems${loop}">
        </tbody>
    </table><br><br>`);

}


function printAppendTable(print, pbkList) {
    a = 0;

    $.each(pbkList, function (v, k) {
        a++;
    });
    console.log(a);

    for(let i = 0; i<a; i++) {
        printAppend(print, i);
        var print2 = $(`#printTables`).find(`#tableItems${i}`);
            print2.append(`<tr>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td>${pbkList[i].tara}</td>
    <td>${pbkList[i].netto}</td>
    <td></td>
    <td>${pbkList[i].rbId}</td>
    <td>${pbkList[i].oprId}</td>
    </tr>`);
       /* setTimeout(() => {
        }, 10)*/
    }

}


function loadPrintPB(pb, update){

    console.log(pb.pbId);

    $.ajax({
        url: "api/pbService/getPB",
        data: {pbId: pb.pbId},
        contentType: "application/JSON",
        method: "GET",
        success: function (printPb) {
            console.log(printPb);
            main.switchPage('HTML/produktBatch/pbPrint.html');
            setTimeout(() => {

                getPrintInfo(pb.pbId, printPb.receptId, printPb.date, pb.status, printPb.pStartDato, update);

                var print = $("#printTables");
                $("#pbPrint-tabel").remove();
                console.log("1");

                printAppend(print, 1);

                console.log("2");
            }, 10)

        },
        error: function(XHR) {
            console.log(XHR);
            alert("Fejl: " + XHR.responseText);
        },
    });
}

function loadPrintPBK(pbkList){
    console.log("4");
    setTimeout(() => {

        var print = $("#printTables");

        printAppendTable(print, pbkList);

        console.log(print);
    }, 10)
}

//Tilføj produktbatch
$("#createPB").submit(function(event) {
    event.preventDefault();
    var pb = $("#createPB").serializeJSON();
    $.ajax({

        url: "api/pbService/createPB",
        data: JSON.stringify(pb),
        contentType: "application/JSON",
        method: "POST",
        success: function (data) {
            console.log(pb);
            alert(data);
            loadPrintPB(pb, 1);

        },
        error: function(XHR) {
            console.log(pb);
            console.log(XHR);
            alert("Fejl: " + XHR.responseText);
        },
    });
});

//Tilføj produktbatchkomponent
$("#createPBK").submit(function(event) {
    event.preventDefault();
    $.ajax({
        url: "api/pbService/createPBK",
        data: JSON.stringify($("#createPBK").serializeJSON()),
        contentType: "application/JSON",
        method: "POST",
        success: function (data) {
            console.log(data);
            alert(data);
            var pb = $("#createPBK").serializeJSON();
            $.ajax({
                url: "api/pbService/getPBKList/" + pb.pbId,
                data: {pbId: pb.pbId},
                contentType: "application/JSON",
                method: "GET",
                success: function (pbkListe) {
                    console.log(pbkListe);
                    console.log(pb);
                    loadPrintPB(pb, 0);
                    setTimeout(() => {
                        $("#pbPrint-tabel").remove();
                        console.log("3");
                        loadPrintPBK(pbkListe)
                    }, 1000)
                    ;
                },
                error: function(XHR) {
                    console.log(XHR);
                    alert("Fejl: " + XHR.responseText);
                },
            });

        },
        error: function(XHR) {
            console.log(XHR);
            alert("Fejl: " + XHR.responseText);
        },
    });
});

$("#findPB").submit(function(event) {
    event.preventDefault();

        var pb = $("#findPB").serializeJSON();
        $.ajax({
            url: "api/pbService/getPBKList/",
            data: {pbId: pb.pbId},
            contentType: "application/JSON",
            method: "GET",
            success: function (pbkListe) {
                console.log(pbkListe);
                loadPrintPB(pb, 1);
                setTimeout(() => {
                    $("#pbPrint-tabel").remove();
                    console.log("3");
                    loadPrintPBK(pbkListe)
                }, 1000)
                ;
            },
            error: function(XHR) {
                console.log(XHR);
                alert("Fejl: " + XHR.responseText);
            },
        });
});


function loadPB() {
    var table = $("#pb-tabel").find("tbody");
    table.html("");
    $.ajax({
        url: "api/pbService/getPBList",
        contentType: "application/JSON",
        success: function (products) {
            console.log(products);
            products.forEach(function(products) {
                console.log(products);
                table.append(`<tr>
                    <td>${products.pbId}</td>
                    <td>${products.receptId}</td>
                    <td>${products.status}</td>
                    <td>${products.date}</td>
                    <td>${products.pStartDato}</td>
                   <td><button onclick="confirmDeletePB(${products.pbId})">slet</button></td>
                </tr>`);
            })
        },
        error: function(XHR) {
            console.log(XHR);
            alert("Fejl:" + XHR.responseText);
        },
    });
}

function loadPBK() {
    var table = $("#pbk-tabel").find("tbody");
    table.html("");
    $.ajax({
        url: "api/pbService/getPBKList",
        contentType: "application/JSON",
        success: function (products) {
            console.log(products);
            products.forEach(function(products) {
                console.log(products);
                table.append(`<tr>
                    <td>${products.pbId}</td>
                    <td>${products.rbId}</td>
                    <td>${products.tara}</td>
                    <td>${products.netto}</td>
                    <td>${products.oprId}</td>
                   <td><button onclick="confirmDeletePBK(${products.pbId}, ${products.rbId})">slet</button></td>
                </tr>`);
            })
        },
        error: function(XHR) {
            console.log(XHR);
            alert("Fejl:" + XHR.responseText);
        },
    });
}


function confirmDeletePB(pbId) {
    $.ajax({
        url: "api/pbService/getPB",
        data: {pbId: pbId},
        contentType: "application/JSON",
        method: "GET",
        success: function (pbList) {
            console.log(pbList);
            if (confirm('Vil du slette produkbach med ID : '+ pbList.pbId+'?')) {
                $.ajax({
                    url: "api/pbService/deletePB",
                    data: JSON.stringify({pbId : pbId}),
                    contentType: "application/JSON",
                    method: "DELETE",
                    success: function (data) {
                        console.log(data);
                        alert(data);
                        main.switchPage('HTML/produktBatch/pbListe.html')
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

function confirmDeletePBK(pbId, rbId) {
    $.ajax({
        url: "api/pbService/getPBK",
        data: {pbId: pbId, rbId: rbId},
        contentType: "application/JSON",
        method: "GET",
        success: function (pbkList) {
            console.log(pbkList);
            if (confirm('Vil du slette produkbach-komponent med ID : '+pbkList.pbId+'?')) {
                $.ajax({
                    url: "api/pbService/deletePBK",
                    data: JSON.stringify({pbId : pbId, rbId : rbId}),
                    contentType: "application/JSON",
                    method: "DELETE",
                    success: function (data) {
                        console.log(data);
                        alert(data);
                        main.switchPage('HTML/produktBatch/pbkListe.html')
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



//!!!!!IKKE I BRUG

$("#editUser").submit(function(event) {
    event.preventDefault();
    $.ajax({
        url: "../api/userAdmin/update",
        data: JSON.stringify($("#editUser").serializeJSON()),
        contentType: "application/JSON",
        method: "POST",
        success: function (data) {
            alert("Bruger opdateret succesfuldt!");
            switchPage("brugerAdmin.html");
        },
        error: function(XHR) {
            console.log(XHR);
            alert("Fejl:" + XHR.responseText);
        },
    });
});



window.onload = function () {


    $(document).ready(function(){
        $("input").focus(function(){
            $(this).css("background-color", "grey");
        });
        $("input").blur(function(){
            $(this).css("background-color", "white");
        });
    });


}




function loadPBs(type) {
    if(type == 0) {
        var output = $("#createPBK").find("#pbId");
        output.html("");
    }

    if(type == 1) {
        var output = $("#findPB").find("#pbId");
        output.html("");
    }

    a = 0;

    $.ajax({
        url: "api/pbService/getPBList",
        contentType: "application/JSON",
        success: function (products) {
            console.log(products);
            $.each(products, function (v, k) {
                a++;
            });
            for(let i = 0; i<a; i++){
                output.append(` <option value="${products[i].pbId}">${products[i].pbId}</option>`);
            }

        },
        error: function(XHR) {
            console.log(XHR);
            alert("Fejl:" + XHR.responseText);
        },
    });
}





