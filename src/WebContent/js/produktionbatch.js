/**
 * @Author Christian
 */


/**
 * ----------------------------------------------------------------------------------------
 * ------ Funktionalitet for de diverse 'submit' knapper, tilhørende formene på: ----------
 * ------ opretProduktbatch.html - opretProduktbatchKomp.html - specificPB.html -----------
 * ----------------------------------------------------------------------------------------
 */

//Tilføj produktbatch   (opretProduktbatch.html)
$("#createPB").submit(function(event) {
    event.preventDefault();
    var pb = $("#createPB").serializeJSON();
    $.ajax({

        url: "api/pbService/createPB",
        data: JSON.stringify(pb),
        contentType: "application/JSON",
        method: "POST",
        success: function (data) {
            console.log("createPB" + pb);
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



//Tilføj produktbatchkomponent (opretProduktbatchkKomp.html)
$("#createPBKfirstPage").submit(function(event) {
    event.preventDefault();
    var pb = $("#pbId").val();

    $.ajax({
        url: "api/pbService/getPB",
        data: {pbId: pb},
        contentType: "application/JSON",
        success: function (product) {



            main.switchPage("HTML/produktBatch/opretProduktbatchKomp.html");
            getSpecificReceptKomps(product.receptId, (sRk) => {

                var printPbk = $(`#pbk-tabel`).find("tbody");
                printPbk.html("");
                var print = $(`#recept-tabel`).find("tbody");
                print.html("");
                var output = $("#createPBK").find("#rbId");
                output.html("");

                getRecept(product.receptId, (recept) =>{

                    $("#createPBK").find("#pbId").val(pb);

                    getElementsBy(pb, recept.receptId, recept.receptNavn)
                    getSpecificPBKList(pb, (pbkList) => {

                        pbkList.forEach(function (pbkList) {
                            printPbk.append(`<tr>
                                <td>${pbkList.pbId}</td>                               
                                <td>${pbkList.tara}</td>
                                <td>${pbkList.netto}</td>
                                <td>${pbkList.rbId}</td>
                                 </tr>`);
                        })
                    });

                    sRk.forEach(function (sRk) {
                        getRaavareId(sRk.raavareId, (raavare) =>{
                            output.append(` <option value="${raavare.rbId}">${raavare.rbId}</option>`);
                            print.append(`<tr>                   
                            <td>${sRk.raavareId}</td>
                            <td>${sRk.nomNetto} kg</td>
                            <td>${sRk.tolerance} %</td>
                            <td>${raavare.rbId}</td>
                            </tr>`);

                        });
                    });

                });
            });
        },
        error: function (XHR) {
            console.log(XHR);
            alert("Fejl:" + XHR.responseText);
        },
    });
});

function getElementsBy(pbId, receptId, receptNavn){
    document.getElementById("visID").innerHTML = "Produkt ID: " + pbId;
    document.getElementById("recept").innerHTML = "Recept: " + receptId;
    document.getElementById("receptNavn").innerHTML = "Produkt: " + receptNavn;

}

$("#updatePBKfirstPage").submit(function(event) {
    event.preventDefault();
    var pb = $("#pbId").val();
    console.log(pb);

    $.ajax({
        url: "api/pbService/getPB",
        data: {pbId: pb},
        contentType: "application/JSON",
        success: function (product) {
            main.switchPage("HTML/produktBatch/editPBK.html");

                getRecept(product.receptId, (recept) => {
                    $("#updatePBK").find("#pbId").val(pb);
                    getElementsBy(pb, recept.receptId, recept.receptNavn);
                    getSpecificPBKList(pb, (pbkList) =>{
                        var rbIdAppend = $(`#updatePBK`).find("#rbId");
                        rbIdAppend.html("");
                        var printPbk = $(`#pbk-tabel`).find("tbody");
                        printPbk.html("");

                        pbkList.forEach(function (pbkList) {
                            rbIdAppend.append(` <option value="${pbkList.rbId}">${pbkList.rbId}</option>`);

                            printPbk.append(`<tr>
                            <td>${pbkList.pbId}</td>
                            <td>${pbkList.rbId}</td>
                            <td>${pbkList.tara}</td>
                            <td>${pbkList.netto}</td>
                            <td>${pbkList.oprId}</td>
                             </tr>`);
                        })
                    });
            });
        },
        error: function (XHR) {
            console.log(XHR);
            alert("Fejl:" + XHR.responseText);
        },
    });

});



//Tilføj produktbatchkomponent (opretProduktbatchkKomp.html)
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
                        loadPrintPBK(pbkListe, pb)
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


//Opdater produktbatch-komponent
$("#updatePBK").submit(function (event) {
    event.preventDefault();
    var test = JSON.stringify($("#updatePBK").serializeJSON());
    console.log(test);
    $.ajax({
        url: "api/pbService/updatePBK",
        data: JSON.stringify($("#updatePBK").serializeJSON()),
        contentType: "application/JSON",
        method: "PUT",

        success: function (data) {
            console.log(data);
            alert(data);
            main.switchPage("HTML/produktBatch/pbkListe.html")

        },
        error: function (XHR) {
            console.log(XHR);
            alert("Fejl: " + XHR.responseText)
        }
    })

});

//Find specifik PB (specifikPB.html)

$("#findPB").submit(function(event) {
    event.preventDefault();

    var pb = $("#findPB").serializeJSON();
    $.ajax({
        url: "api/pbService/getPBKList/" + pb.pbId,

        contentType: "application/JSON",
        method: "GET",
        success: function (pbkListe) {
            console.log(pbkListe);
            loadPrintPB(pb, 1);
            setTimeout(() => {
                $("#pbPrint-tabel").remove();
                console.log("3");
                loadPrintPBK(pbkListe, pb)
            }, 1000)
            ;
        },
        error: function(XHR) {
            console.log(XHR);
            alert("Fejl: " + XHR.responseText);
        },
    });
});


/**
 * ----------------------------------------------------------------------------------------
 * -------------- Funktionalitet for hvad der skal printes. Dato, status osv. -------------
 * ----------------------------------------------------------------------------------------
 */


//Henter hvad der skal printes, bruger af getPrintInfo()
function loadPrintPB(pb, update){


    $.ajax({
        url: "api/pbService/getPB",
        data: {pbId: pb.pbId},
        contentType: "application/JSON",
        method: "GET",
        success: function (printPb) {
            console.log("loadPrintPB" + printPb);
            main.switchPage('HTML/produktBatch/pbPrint.html');
            setTimeout(() => {

                getPrintInfo(pb.pbId, printPb.receptId, printPb.date, pb.status, printPb.pStartDato, update, printPb);

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

//Henter hvilke PBK'er der skal appendes på print siden.
function loadPrintPBK(pbkList, pb){
    console.log("4");
    setTimeout(() => {

        var print = $("#printTables");

        printAppendTable(print, pbkList, pb);

        console.log("loadPrintPBK" + print);
    }, 10)
}


//Henter hvad der skal printes, bruges af loadPrintPb()
function getPrintInfo(pbId, receptId, date, status, pStartDate, update, oldPb) {

    document.getElementById("printDate").innerHTML = "Udskrevet: " + getPrintDate();
    getPbId(pbId);
    getReceptId(receptId);
    getPbStatus(status, date, pbId, pStartDate, update, oldPb);


    //Hent nuværende date
    function getPrintDate() {
        n = new Date();
        y = n.getFullYear();
        m = n.getMonth() + 1;
        d = n.getDate();

        if (m < 10) {
            return y + "-0" + m + "-" + d;
        } else return y + "-" + m + "-" + d;
    }

    //Return pbId, indsæt pbId på pbPrint.html
    function getPbId(pbId) {
        n = pbId;
        document.getElementById("printPbId").innerHTML = "Produktbatch ID: " + n;
    }

    // Return receptId, indsæt receptId på pbPrint.html
    function getReceptId(receptId) {
        n = receptId;
        document.getElementById("printReceptId").innerHTML = "Recept ID: " + n;
    }

    //Return pb oprettelses dato
    function getPbDate(date) {
        n = date;
        return n;

    }

    function getPbStatus(status, date, pbId, pStartDate, update, oldPb) {


        if (update == 0) {
            document.getElementById("printStatus").innerHTML = "Status: 'oprettet'";
            document.getElementById("pbStart").innerHTML = "Produktions Start: " + "-ikke begyndt-";

        } else {

            if (status == 0 && oldPb.status == 0) {

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

                        if (status == 1 && oldPb.status == 1) {
                            updatePb(pb, 0);

                        } else if (status == 2 && oldPb.status == 2) {
                            updatePb(pb, 0);

                        } else if (confirm('Vil du ændre status fra ' + pb.status + ' til ' + status + '?')) {

                            if (status == 0) {
                                pb.pStartDato = null;
                                pb.status = 0;
                                console.log("status0:" + pb);
                            }
                            if (status == 1) {
                                if (pb.pStartDato == null) {

                                    pb.pStartDato = getPrintDate();
                                    pb.status = 1;
                                    console.log("status1: " + pb);
                                }
                            }
                            if (status == 2) {
                                pb.status = 2;
                            } else pb.status = status;

                            updatePb(pb, 1);

                        } else main.switchPage('HTML/login.html');

                    },
                });
            }
        }

    }

    function updatePb(pb, select) {
        $.ajax({
            url: "api/pbService/updatePB",
            data: JSON.stringify(pb),
            contentType: "application/JSON",
            method: "PUT",
            success: function (pbUp) {
                console.log("pbUpdate: " + pbUp);
                if(select==1) {
                    alert(pbUp);
                }
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
    }

}
//Append pb information på pbPrint.html
    function printAppend(print, i) {
        var loop = i;


        print.append(` <table id="pbPrint-tabel">
        <thead>
        <tr>
            <th>Råvare ID</th>
            <th>Råvare</th>
            <th>Mængde(kg)</th>
            <th>Tolerance</th>
            <th>Tara(kg)</th>
            <th>Netto(kg)</th>
            <th>Råvarebatch</th>
            <th>Opr</th>
        </tr>
        </thead>
        <tbody id="tableItems${loop}">
        </tbody>
    </table><br><br>`);

    }

//Append pbk information på pbPrint.html
    function printAppendTable(print, pbkList, pb) {

        for (let i = 0; i < pbkList.length; i++) {
            console.log(pbkList.rbId);
            getRbId(pbkList[i].rbId, (rbIdPrint) => {
                getRaavare(rbIdPrint.raavareId, (raaPrint) => {
                    getTolerance(pb, rbIdPrint.raavareId, (tolerance) => {
                        console.log(raaPrint);
                        console.log(tolerance);
                        printAppend(print, i);


                        var print2 = $(`#printTables`).find(`#tableItems${i}`);
                        print2.append(`<tr>
                            <td>${rbIdPrint.raavareId}</td>
                            <td>${raaPrint}</td>
                            <td>${rbIdPrint.maengde} kg</td>
                            <td>${tolerance} %</td>
                            <td>${pbkList[i].tara} kg</td>
                            <td>${pbkList[i].netto} kg</td>
                            <td>${pbkList[i].rbId}</td>
                            <td>${pbkList[i].oprId}</td>
                            </tr>`);
                    })
                })
            })
        }

    }

    function getRbId(rbId, _callback) {
        $.ajax({
            url: "api/rbService/getRB",
            data: {rbId: rbId},
            contentType: "application/JSON",

            success: function (rb) {
                _callback(rb);
            },
            error: function (XHR) {
                console.log(XHR);
                alert("Fejl: " + XHR.responseText);
            },
        });
    }


    function getRaavare(raavareId, _callback) {
        $.ajax({
            url: "api/raaService/getRaa",
            data: {raavareId: raavareId},
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

    function getTolerance(pb, raavareId, _callback) {

        $.ajax({
            url: "api/pbService/getPB",
            data: {pbId: pb.pbId},
            contentType: "application/JSON",

            success: function (data) {

                $.ajax({
                    url: "api/recept1/getRKomp",
                    data: {receptId: data.receptId, raavareId: raavareId},
                    contentType: "application/JSON",

                    success: function (receptKomp) {
                        console.log(receptKomp);
                        _callback(receptKomp.tolerance);
                    },
                    error: function (XHR) {
                        console.log(XHR);
                        alert("Fejl: " + XHR.responseText);
                    },
                });

            },
            error: function (XHR) {
                console.log(XHR);
                alert("Fejl: " + XHR.responseText);
            },
        });


    }


    /**
     * ----------------------------------------------------------------------------------------
     * ------------------ Load produktbatch og produktbatch-komponent lister ------------------
     * ----------------------------------------------------------------------------------------
     */

//Load PB's (pbListe.html)
    function loadPB() {
        var table = $("#pb-tabel").find("tbody");
        table.html("");
        $.ajax({
            url: "api/pbService/getPBList",
            contentType: "application/JSON",
            success: function (products) {
                console.log(products);
                products.forEach(function (products) {
                    getRecept(products.receptId, (recept) =>{
                        table.append(`<tr>
                    <td>${products.pbId}</td>
                    <td>${products.receptId}</td>
                    <td>${recept.receptNavn}</td>
                    <td>${products.status}</td>
                    <td>${products.date}</td>
                    <td>${products.pStartDato}</td>
                   <td><button onclick="confirmDeletePB(${products.pbId})">slet</button></td>
                </tr>`);});

                })
            },
            error: function (XHR) {
                console.log(XHR);
                alert("Fejl:" + XHR.responseText);
            },
        });
    }


    function getSpecificPBKList(pbId, _callback) {

        $.ajax({
            url: "api/pbService/getPBKList/" + pbId,
            contentType: "application/JSON",
            success: function (products) {
                _callback(products);
            },
            error: function (XHR) {
                console.log(XHR);
            },
        });
    }

//Load PBK's (pbkListe.html)
    function loadPBK() {

        var table = $("#pbk-tabel").find("tbody");
        table.html("");
        $.ajax({
            url: "api/pbService/getPBKList",
            contentType: "application/JSON",
            success: function (products) {
                console.log(products);
                products.forEach(function (products) {
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
            error: function (XHR) {
                console.log(XHR);
                alert("Fejl:" + XHR.responseText);
            },
        });
    }


    /**
     * ----------------------------------------------------------------------------------------
     * ---------------------- Slet ProduktBatch og ProdtukBatchKomponent ----------------------
     * ----------------------------------------------------------------------------------------
     */

// Slet PB (pbListe.html)
    function confirmDeletePB(pbId) {
        $.ajax({
            url: "api/pbService/getPB",
            data: {pbId: pbId},
            contentType: "application/JSON",
            method: "GET",
            success: function (pb) {
                console.log(pb);
                if (confirm('Vil du slette produkbach med ID : ' + pb.pbId + '?')) {
                    $.ajax({
                        url: "api/pbService/deletePB",
                        data: JSON.stringify({pbId: pbId}),
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

// Slet PBK (pbkListe.html)
    function confirmDeletePBK(pbId, rbId) {
        $.ajax({
            url: "api/pbService/getPBK",
            data: {pbId: pbId, rbId: rbId},
            contentType: "application/JSON",
            method: "GET",
            success: function (pbkList) {
                console.log(pbkList);
                if (confirm('Vil du slette produkbach-komponent med ID : ' + pbkList.pbId + '?')) {
                    $.ajax({
                        url: "api/pbService/deletePBK",
                        data: JSON.stringify({pbId: pbId, rbId: rbId}),
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


    /**
     * ----------------------------------------------------------------------------------------
     * ------------------------ Hent data fra database ind som forslag ------------------------
     * ----------------------------------------------------------------------------------------
     */
//Load PB's pbId automatisk ind som forslag til pbId (specificPB.html)

    function loadPBKs(type)     {
        if (type == 0) {
            var output = $("#createPBK").find("#pbId");
            output.html("");
            var url1 = "api/pbService/getPBList";
        }

        if (type == 1) {
            var output = $("#updatePBK").find("#pbId");
            output.html("");
            var url1 = "api/pbService/getPBKList";
        }
        if (type == 2) {
            var output = $("#findPB").find("#pbId");
            output.html("");
            var url1 = "api/pbService/getPBList";
        }
        if (type == 3) {
            var output = $("#createPBKfirstPage").find("#pbId");
            output.html("");
            var url1 = "api/pbService/getPBList";
        }
        if (type == 4) {
            var output = $("#updatePBKfirstPage").find("#pbId");
            output.html("");
            var url1 = "api/pbService/getPBList";
        }


        $.ajax({
            url: url1,
            contentType: "application/JSON",
            success: function (products) {
                console.log(products);
                var pbArray = [];
                for (let i = 0; i < products.length; i++) {
                    if (!pbArray.includes(products[i].pbId)) {
                        output.append(` <option value="${products[i].pbId}">${products[i].pbId}</option>`);
                        pbArray.push(products[i].pbId);
                    }
                }

            },
            error: function (XHR) {
                console.log(XHR);
                alert("Fejl:" + XHR.responseText);
            },
        });
    }

    function loadRBs(type) {

        if (type == 0) {
            var output = $("#createPBK").find("#rbId");
            output.html("");
        }
        if (type == 1) {
            var output = $("#updatePBK").find("#rbId");
            output.html("");
        }

        $.ajax({
            url: "api/rbService/getRBList",
            contentType: "application/JSON",
            success: function (raavare) {
                console.log(raavare);

                for (let i = 0; i < raavare.length; i++) {
                    output.append(` <option value="${raavare[i].rbId}">${raavare[i].rbId}</option>`);
                }

            },
            error: function (XHR) {
                console.log(XHR);
                alert("Fejl:" + XHR.responseText);
            },
        });
    }

    function loadUsers(type) {

        if (type == 0) {
            var output = $("#createPBK").find("#oprId");
            output.html("");
        }
        if (type == 1) {
            var output = $("#updatePBK").find("#oprId");
            output.html("");
        }

        $.ajax({
            url: "api/bruger/all",
            contentType: "application/JSON",
            method: "GET",
            success: function (users) {
                console.log(users);

                for (let i = 0; i < users.length; i++) {
                    output.append(` <option value="${users[i].oprId}">${users[i].oprNavn}</option>`);
                }

            },
            error: function (XHR) {
                console.log(XHR);
                alert("Fejl:" + XHR.responseText);
            },
        });
    }

    function loadRecepter() {

        var output = $("#createPB").find("#receptId");
        output.html("");

        $.ajax({
            url: "api/recept1/rList",
            contentType: "application/JSON",
            success: function (recepter) {
                console.log(recepter);

                for (let i = 0; i < recepter.length; i++) {
                    output.append(` <option value="${recepter[i].receptId}">${recepter[i].receptId}</option>`);
                }

            },
            error: function (XHR) {
                console.log(XHR);
                alert("Fejl:" + XHR.responseText);
            },
        });
    }




