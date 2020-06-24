
/**
 * ----------------------------------------------------------------------------------------
 * -------------- Funktionalitet for hvad der skal printes. Dato, tabeller, status osv. ---
 * ----------------------------------------------------------------------------------------
 */

// Henter hvad der skal printes, bruges af getPrintInfo()
function loadPrintPB(pb, update, _callback) {
    $.ajax({
        url: "api/pbService/" + pb.pbId,
        contentType: "application/JSON",
        method: "GET",
        success: function (printPb) {
            main.switchPage('HTML/produktBatch/printPB.html', "PB-print", () => {
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

// Fortæller hvor hvilke tabel PBK info skal appendes til
function loadPrintPBK(pbkList, pb, _callback){
    var print = $("#printTables");
    printTablePBK(print, pbkList, pb, _callback);
}

// Henter hvad der skal printes, bruges af loadPrintPb()
function getPrintInfo(pbId, receptId, date, status, pStartDate, update, oldPb, _callback) {
    getPbDate();
    getPbId(pbId);
    getReceptId(receptId);
    getPbStatus(status, date, pbId, pStartDate, update, oldPb, (isSuccesful) => {
        setTimeout(() => {
            _callback(isSuccesful);
        }, 10)
    });

    // Hent nuværende date
    function getPrintDate() {
        let n = new Date();
        return n.getFullYear() + "-" + ((String)(n.getMonth() + 1)).padStart(2, '0') + "-" + (String)(n.getDate()).padStart(2, '0');
    }

    // Hent nuværende date, indsæt i printPB.html
    function getPbDate() {
        let n = new Date();
        $("#printDate").html("Udskrevet: " + getPrintDate());
    }

    // Return pbId, indsæt pbId på printPB.html
    function getPbId(pbId) {
        $("#printPbId").html("Produktbatch ID: " + pbId);
    }

    // Return receptId, indsæt receptId på printPB.html
    function getReceptId(receptId) {
        $("#printReceptId").html("Recept ID: " + receptId);
    }


}

function getPbStatus(status, date, pbId, pStartDate, update, oldPb, _callback) {
    console.log("Satus" + oldPb.status);
    if (update == 0) {
        if (oldPb.status == 0) {
            $("#printStatus").html("Status: 'oprettet'");
            $("#pbStart").html("Produktions Start: " + "-ikke begyndt-");
        } else if (oldPb.status == 1) {
            $("#printStatus").html("Status: 'under produktion'");
            $("#pbStart").html("Produktions Start: " + oldPb.pStartDato);
        } else if (oldPb.status == 2) {
            $("#printStatus").html("Status: 'afsluttet'");
            $("#pbStart").html("Produktions Start: " + pStartDate);
        }
        _callback(true);
    } else {
        $.ajax({
            url: "api/pbService/" + pbId,
            contentType: "application/JSON",
            method: "GET",
            success: function (pb) {
                console.log("pb: ", pb);

                if (status == oldPb.status) {
                    alert("FEJL: Du kan ikke opdatere status til den nuværende status.")
                    _callback(false);
                } else if (confirm('Vil du ændre status fra ' + pb.status + ' til ' + status + '?')) {
                    if (status <= 0) {
                        pb.pStartDato = null;
                        pb.status = 0;
                    } else if (status == 1) {
                        if (pb.pStartDato == null) {
                            pb.pStartDato = getPrintDate();
                            pb.status = 1;
                        }
                    } else if (status >= 2) {
                        pb.status = 2;
                    }

                    updatePb(pb, 1);
                    _callback(true);
                } else {
                    main.switchPage('HTML/login.html', "Login");
                    _callback(false);
                }
            },
        });
    }
}

function updatePb(pb, select) {
    $.ajax({
        url: "api/pbService",
        data: JSON.stringify(pb),
        contentType: "application/JSON",
        method: "PUT",
        success: function (pbUp) {
            console.log("pbUpdate: ", pbUp);
            if (select == 1) {
                alert(pbUp);
            }
            if (pb.status == 0) {
                document.getElementById("printStatus").innerHTML = "Status: 'oprettet'";
                document.getElementById("pbStart").innerHTML = "Produktions Start: " + "-ikke begyndt-";
            } else if (pb.status == 1) {
                document.getElementById("printStatus").innerHTML = "Status: 'under produktion'";
                document.getElementById("pbStart").innerHTML = "Produktions Start: " + pb.pStartDato;
            } else {
                document.getElementById("printStatus").innerHTML = "Status: 'afsluttet'";
                document.getElementById("pbStart").innerHTML = "Produktions Start: " + pStartDate;
            }
        },
        error: function (XHR) {
            console.log(XHR);
            alert("Fejl: " + XHR.responseText);
            main.switchPage('HTML/menu.html', "Menu")
        },
    });
}


/**
 * ----------------------------------------------------------------------------------------
 * ------ Funktionalitet for at appende data til de forskellelige tabeller til print-------
 * ----------------------------------------------------------------------------------------
 */

// Append pb information på printPB.html
function printEmptyTablePBK(print, i, _callback) {
    print.append(`<table id="pbPrint-tabel">
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
        <tbody id="tableItems${i}">
        </tbody>
    </table>
    <br><br>`);

    setTimeout(() => {
        _callback();
    }, 10)
}

// Append pbk information på printPB.html
//https://stackoverflow.com/questions/18983138/callback-after-all-asynchronous-foreach-callbacks-are-completed
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

                            // Hvis a er lig længden på listen, så er det sidste og der skal laves callback
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


// Henter tolerance til printTablePBK
function getTolerance(pb, raavareId, _callback) {
    $.ajax({
        url: "api/pbService/" + pb.pbId,
        contentType: "application/JSON",
        success: function (data) {
            $.ajax({
                url: "api/recept/komp/" + data.receptId + "/" + raavareId,
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

// Henter taravægt og tjekker om det er afvejet eller ej
function getTara(tara) {
    var taraStatus;
    if (tara  == 0) {
        taraStatus = "Ikke afvejet!";
    } else taraStatus = tara + " kg";
    return taraStatus;
}

// Henter nettovægt og tjekker om det er afvejet eller ej
function getNetto(netto) {
    var nettoStatus;
    if (netto == 0) {
        nettoStatus = "Ikke afvejet!";
    } else nettoStatus = netto + " kg";
    return nettoStatus;
}




