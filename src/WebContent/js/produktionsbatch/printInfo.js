
/**
 * ----------------------------------------------------------------------------------------
 * -------------- Funktionalitet for hvad der skal printes. Dato, tabeller, status osv. ---
 * ----------------------------------------------------------------------------------------
 */

//Henter hvad der skal printes, bruges af getPrintInfo()
function loadPrintPB(pb, update, _callback){

    $.ajax({
        url: "api/pbService/getPB",
        data: {pbId: pb.pbId},
        contentType: "application/JSON",
        method: "GET",
        success: function (printPb) {
            main.switchPage('HTML/produktBatch/printPB.html', "PB-print", () => {
                console.log(printPb + "saoidjasd");
                console.log("Satus" + printPb.status);
                getPrintInfo(pb.pbId, printPb.receptId, printPb.date, pb.status, printPb.pStartDato, update, printPb, (isSuccesfull) => {

                var print = $("#printTables");
                $("#pbPrint-tabel").remove();

                    printEmptyTablePBK(print, 1, () => {
                        _callback(isSuccesfull);
                    });
                });
            });

        },
        error: function(XHR) {
            console.log(XHR);
            alert("Fejl: " + XHR.responseText);
        },
    });
}

//Fortæller hvor hvilke tabel PBK info skal appendes til
function loadPrintPBK(pbkList, pb, _callback){

        var print = $("#printTables");
    alert("EWE")
        printTablePBK(print, pbkList, pb, () =>{
            _callback();
        });


}

//Henter hvad der skal printes, bruges af loadPrintPb()
function getPrintInfo(pbId, receptId, date, status, pStartDate, update, oldPb, _callback) {

    document.getElementById("printDate").innerHTML = "Udskrevet: " + getPrintDate();
    getPbId(pbId);
    getReceptId(receptId);
    getPbStatus(status, date, pbId, pStartDate, update, oldPb, (isSuccesful) => {
    setTimeout(() => {

        _callback(isSuccesful);

    }, 10)
    });

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

    //Return pbId, indsæt pbId på printPB.html
    function getPbId(pbId) {
        n = pbId;
        document.getElementById("printPbId").innerHTML = "Produktbatch ID: " + n;
    }

    // Return receptId, indsæt receptId på printPB.html
    function getReceptId(receptId) {
        n = receptId;
        document.getElementById("printReceptId").innerHTML = "Recept ID: " + n;
    }

    //Return pb oprettelses dato
    function getPbDate(date) {
        n = date;
        return n;

    }

    function getPbStatus(status, date, pbId, pStartDate, update, oldPb, _callback) {

        console.log("Satus" + oldPb.status);
        if (update == 0) {

            if(oldPb.status == 0) {
                document.getElementById("printStatus").innerHTML = "Status: 'oprettet'";
                document.getElementById("pbStart").innerHTML = "Produktions Start: " + "-ikke begyndt-";
            } else if(oldPb.status == 1) {
                document.getElementById("printStatus").innerHTML = "Status: 'under produktion'";
                document.getElementById("pbStart").innerHTML = "Produktions Start: " + oldPb.pStartDato;
            }else if(oldPb.status == 2) {
                document.getElementById("printStatus").innerHTML = "Status: 'afsluttet'";
                document.getElementById("pbStart").innerHTML = "Produktions Start: " + getPbDate(pStartDate);
            }
            _callback(true);

        }  else {

                $.ajax({
                    url: "api/pbService/getPB/",
                    data: {pbId: pbId},
                    contentType: "application/JSON",
                    method: "GET",
                    success: function (pb) {
                        console.log(pb);

                        if (status == 0 && oldPb.status == 0 ||status == 1 && oldPb.status == 1 || status == 2 && oldPb.status == 2) {
                            alert("FEJL: Du kan ikke opdatere status til den nuværende status.")
                            _callback(false);
                        } else if (confirm('Vil du ændre status fra ' + pb.status + ' til ' + status + '?')) {

                            if (status == 0) {
                                pb.pStartDato = null;
                                pb.status = 0;
                            }
                            if (status == 1) {
                                if (pb.pStartDato == null) {
                                    pb.pStartDato = getPrintDate();
                                    pb.status = 1;

                                }
                            }
                            if (status == 2) {
                                pb.status = 2;
                            } else pb.status = status;

                            updatePb(pb, 1);
                            _callback(true);

                        } else main.switchPage('HTML/login.html', "Login");
                        _callback(false);

                    },
                });

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
                if (pb.status == 0) {
                    document.getElementById("printStatus").innerHTML = "Status: 'oprettet'";
                    document.getElementById("pbStart").innerHTML = "Produktions Start: " + "-ikke begyndt-";
                }else if (pb.status == 1) {
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
                main.switchPage('HTML/login.html', "Login")
            },
        });
    }

}

/**
 * ----------------------------------------------------------------------------------------
 * ------ Funktionalitet for at appende data til de forskellelige tabeller til print-------
 * ----------------------------------------------------------------------------------------
 */

//Append pb information på printPB.html
function printEmptyTablePBK(print, i, _callback) {
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


    setTimeout(() => {

        _callback();

    }, 10)


}

//Append pbk information på printPB.html
function printTablePBK(print, pbkList, pb, _callback) {
    var a = 0;
    for (let i = 0; i < pbkList.length; i++) {
        getRb(pbkList[i].rbId, (rbIdPrint) => {
            getRaavare(rbIdPrint.raavareId, (raaPrint) => {
                getTolerance(pb, rbIdPrint.raavareId, (tolerance) => {
                    getUser(pbkList[i].oprId, (user) => {
                        printEmptyTablePBK(print, i, () => {
                            var print2 = $(`#printTables`).find(`#tableItems${i}`);
                            var tara = getTara(pbkList[i].tara);
                            var netto = getNetto(pbkList[i].netto);
                            print2.append(`<tr>
                                <td>${rbIdPrint.raavareId}</td>
                                <td>${raaPrint}</td>
                                <td>${rbIdPrint.maengde} kg</td>
                                <td>${tolerance} %</td>
                                <td>${tara}</td>
                                <td>${netto}</td>
                                <td>${pbkList[i].rbId}</td>
                                <td>${user.oprNavn}</td>
                                </tr>`);
                            a++;

                            if (a == pbkList.length) {
                                _callback();
                            }
                        });
                    });

                });
            });
        });



    }
}


//Henter tolerance til printTablePBK
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

//Henter taravægt og tjekker om det er afvejet eller ej
function getTara(tara) {
    var taraStatus;
    if(tara  == 0){
        taraStatus = "Ikke afvejet!";
    } else taraStatus = tara + " kg";
    return taraStatus;
}

//Henter nettovægt og tjekker om det er afvejet eller ej
function getNetto(netto) {
    var nettoStatus;
    if(netto == 0){
        nettoStatus = "Ikke afvejet!";
    } else nettoStatus = netto + " kg";
    return nettoStatus;
}




