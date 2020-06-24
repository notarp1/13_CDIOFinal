/**
 * @Author Christian
 */




/**
 * ------------------------------------------------------------------------------------------
 * - Funktionalitet de første opret/opdater sider (createPBKmain.html) (updatePBKmain.html) -
 * ------------------------------------------------------------------------------------------
 * --------------- Ud fra det indsendte data oprettes tabeller og information ---------------
 * --------------------------- til at styrke brugervenligheden ------------------------------
 * ------------------------------------------------------------------------------------------
 */



//Tilføj produktbatchkomponent (opretProduktbatchkKomp.html)
$("#createPBKfirstPage").submit(function(event) {
    event.preventDefault();
    var pb = $("#pbId").val();
    var pbkRdy = [];
    $.ajax({
        url: "api/pbService/getPB",
        data: {pbId: pb},
        contentType: "application/JSON",
        success: function (product) {



            main.switchPage("HTML/produktBatch/createPBKsecond.html", "Opret produktbatch");
            getSpecificReceptKomps(product.receptId, (sRk) => {

                var printPbk = $(`#pbk-tabel`).find("tbody");
                printPbk.html("");
                var print = $(`#recept-tabel`).find("tbody");
                print.html("");
                var output = $("#createPBK").find("#rbId");
                output.html("");

                getRecept(product.receptId, (recept) =>{

                    $("#createPBK").find("#pbId").val(pb);

                    getElementsBy(pb, recept.receptId, recept.receptNavn);
                    createPBKEssentials(pb, pbkRdy,1, () =>{


                        sRk.forEach(function (sRkElement) {

                            getRbId(sRkElement.raavareId, (raavare) => {
                                getRaavare(sRkElement.raavareId, (rnavn) => {
                                console.log(sRkElement.raavareId);

                                    if(!pbkRdy.includes(sRkElement.raavareId)){
                                        output.append(` <option value="${raavare.rbId}">${raavare.rbId}</option>`);
                                        print.append(`<tr>                   
                                    <td style="color: red">${raavare.rbId}</td>
                                    <td  style="color: red">${rnavn}</td>
                                    <td  style="color: red">${sRkElement.raavareId}</td>
                                    <td  style="color: red">${sRkElement.nomNetto} kg</td>
                                    <td  style="color: red">${sRkElement.tolerance} %</td>                                                  
                                    </tr>`)
                                    } else
                                        print.append(`<tr>                   
                            <td>${raavare.rbId}</td>
                            <td>${rnavn}</td>
                            <td>${sRkElement.raavareId}</td>
                            <td>${sRkElement.nomNetto} kg</td>
                            <td>${sRkElement.tolerance} %</td>                                                  
                            </tr>`);

                                });
                            });
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

$("#updatePBKfirstPage").submit(function(event) {
    event.preventDefault();
    var pb = $("#pbId").val();
    var pbkRdy = [];
    $.ajax({
        url: "api/pbService/getPB",
        data: {pbId: pb},
        contentType: "application/JSON",
        success: function (product) {



            main.switchPage("HTML/produktBatch/updatePBKsecond.html", "Opdater produktbatch");
            getSpecificReceptKomps(product.receptId, (sRk) => {

                var printPbk = $(`#pbk-tabel`).find("tbody");
                printPbk.html("");
                var print = $(`#recept-tabel`).find("tbody");
                print.html("");
                var output = $("#updatePBK").find("#rbId");
                output.html("");

                getRecept(product.receptId, (recept) =>{

                    $("#updatePBK").find("#pbId").val(pb);

                    getElementsBy(pb, recept.receptId, recept.receptNavn);
                    createPBKEssentials(pb, pbkRdy, 1, () =>{


                        sRk.forEach(function (sRkElement) {

                            getRbId(sRkElement.raavareId, (raavare) => {
                                getRaavare(sRkElement.raavareId, (rnavn) => {
                                    console.log(sRkElement.raavareId);

                                    if(!pbkRdy.includes(sRkElement.raavareId)){
                                        print.append(`<tr>                   
                                    <td style="color: red">${raavare.rbId}</td>
                                    <td  style="color: red">${rnavn}</td>
                                    <td  style="color: red">${sRkElement.raavareId}</td>
                                    <td  style="color: red">${sRkElement.nomNetto} kg</td>
                                    <td  style="color: red">${sRkElement.tolerance} %</td>                                                  
                                    </tr>`)
                                    } else
                                        print.append(`<tr>                   
                            <td>${raavare.rbId}</td>
                            <td>${rnavn}</td>
                            <td>${sRkElement.raavareId}</td>
                            <td>${sRkElement.nomNetto} kg</td>
                            <td>${sRkElement.tolerance} %</td>                                                  
                            </tr>`);

                                });
                            });
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

$("#findPBKList").submit(function (event) {

    event.preventDefault();
    var pb = $("#findPBKList").serializeJSON();
    console.log(pb);

    main.switchPage("HTML/produktBatch/listPBK.html", "Produktionsbatch Liste", () => {

        loadPBK(pb);
    })





});


/**
 * ----------------------------------------------------------------------------------------
 * ------------------------ Generel brugervenligheds funktionalitet------------------------
 * ----------------------------------------------------------------------------------------
 */

//Denne callback funktion sikre at pbk listen hentes før receptkomponent listen.
//Dette giver muligheden for at markere hvilke receptkomponenter der mangler at blive afvejet.
function createPBKEssentials(pb, pbkRdy, val, _callback){
    var printPbk = $(`#pbk-tabel`).find("tbody");
    printPbk.html("");
    var i = 0;

    if(val==1){
        var output = $("#updatePBK").find("#rbId");
        output.html("");
    }

    getSpecificPBKList(pb, 0, (pbkList) => {

        if(pbkList.length == 0){
            _callback();
        }
        pbkList.forEach(function (pbkListInside) {
            i++;

            getRb(pbkListInside.rbId, (rb) => {
                pbkRdy.push(rb.raavareId);
                if(val==1) {
                    output.append(` <option value="${pbkListInside.rbId}">${pbkListInside.rbId}</option>`);
                }
                getRaavare(rb.raavareId, (rNavn) => {
                    var tara = getTara(pbkListInside.tara);
                    var netto = getNetto(pbkListInside.netto);


                    printPbk.append(`<tr>
                                <td>${pbkListInside.rbId}</td>
                                <td>${rNavn}</td>                             
                                <td>${rb.raavareId}</td>                               
                                <td>${tara}</td>
                                <td>${netto}</td>
                                
                                 </tr>`);

                });
            });
            if(i == pbkList.length){
                _callback();
            }

        });

    });
}

//Bruges til at hente data om pbId, recept og receptnavn in opret/opdater produktbatch
function getElementsBy(pbId, receptId, receptNavn){
    document.getElementById("visID").innerHTML = "Produkt ID: " + pbId;
    document.getElementById("recept").innerHTML = "Recept: " + receptId;
    document.getElementById("receptNavn").innerHTML = "Produkt: " + receptNavn;

}

//Benyttes til at udskrive hvor mange komponenter er afvejet. Hvis eksempelvis nettovægten mangler, vil produkbatchen være tilføjet, men ikke afvejet!
function getSpecificPBKList(pbId, status, _callback) {
    var notWeighted = 0;
    $.ajax({
        url: "api/pbService/getPBKList/" + pbId,
        contentType: "application/JSON",
        success: function (products) {
            if(status == 1) {
                products.forEach(function (products) {
                    if (products.netto == 0 || products.tara == 0) {
                        notWeighted++;
                    }

                });
                _callback(products.length - notWeighted);
            }
            else _callback(products);
        },
        error: function (XHR) {
            console.log(XHR);
        },
    });
}



/**
 * -------------------------------------------------------------------------------------------
 * ------ Funktionalitet for de diverse 'submit' knapper, tilhørende formene på: -------------
 * -------------------------------------------------------------------------------------------
 * ---- createPB.html / createPBKsecond.html / updatePBKsecond.html / updatePBstatus.html ----
 * -------------------------------------------------------------------------------------------
 */
//Tilføj produktbatch   (createPB.html)
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
            loadPrintPB(pb, 0, (callback) => {

                window.print();
            });

        },
        error: function(XHR) {
            console.log(pb);
            console.log(XHR);
            alert("Fejl: " + XHR.responseText);
        },
    });
});

//Tilføj produktbatchkomponent (opretProduktbatchkKomp.html)
$("#createPBK").submit(function(event) {
    event.preventDefault();
    var print = $("#createPBK").find("#goPrint").is(":checked");
    $.ajax({
        url: "api/pbService/createPBK",
        data: JSON.stringify($("#createPBK").serializeJSON()),
        contentType: "application/JSON",
        method: "POST",
        success: function (data) {
            alert(data);
            if(print) {
                var pb = $("#createPBK").serializeJSON();
                $.ajax({
                    url: "api/pbService/getPBKList/" + pb.pbId,

                    contentType: "application/JSON",
                    method: "GET",
                    success: function (pbkListe) {
                            loadPrintPB(pb, 0, () => {

                            $("#pbPrint-tabel").remove();

                            loadPrintPBK(pbkListe, pb,  () => {
                                window.print();
                            });

                        });
                    },
                    error: function (XHR) {
                        console.log(XHR);
                        alert("Fejl: " + XHR.responseText);
                    },
                });
            } else main.switchPage('HTML/produktBatch/createPBKmain.html', "Opret produktbatch");

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
            main.switchPage("HTML/produktBatch/listPBK.html", "Se PBK liste")

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
    var upStatus = $("#findPB").find("#upStatus").is(":checked");
    var pb = $("#findPB").serializeJSON();
    var update = (upStatus ? 1 : 0);

    $.ajax({
        url: "api/pbService/getPBKList/" + pb.pbId,
        contentType: "application/JSON",
        method: "GET",
        success: function (pbkListe) {
            console.log(pbkListe)
            loadPrintPB(pb, update, (isSuccesfull) => {
                console.log(isSuccesfull)
                $("#pbPrint-tabel").remove();

                loadPrintPBK(pbkListe, pb, () => {
                    if(isSuccesfull){
                        window.print();
                    }// else {main.switchPage("HTML/produktBatch/updatePBstatus.html");}
                })
            });
        },
        error: function(XHR) {
            console.log(XHR);
            alert("Fejl: " + XHR.responseText);
        },
    });
});



/**
 * ----------------------------------------------------------------------------------------
 * ------------------ Load produktbatch og produktbatch-komponent lister ------------------
 * ----------------------------------------------------------------------------------------
 */

//Load PB's ind i tabel (listPB.html)
function loadPB() {
    var table = $("#pb-tabel").find("tbody");
    table.html("");
    $.ajax({
        url: "api/pbService/getPBList",
        contentType: "application/JSON",
        success: function (products) {
            products.forEach(function (products) {
                getSpecificReceptKomps(products.receptId, (receptKomp) => {
                    getSpecificPBKList(products.pbId, 1, (produktKomp) => {

                        getRecept(products.receptId, (recept) => {
                            table.append(`<tr>
                        <td>${products.pbId}</td>
                        <td>${products.receptId}</td>
                        <td>${recept.receptNavn}</td>
                        <td>${products.status}</td>
                        <td>${products.date}</td>
                        <td>${products.pStartDato}</td>
                        <td>${produktKomp} / ${receptKomp.length}</td>
                         
                       <td><button onclick="confirmDeletePB(${products.pbId})">slet</button></td>
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
}


//Load PBK's ind i tabel (listPBK.html / specificPB)
function loadPBK(pb) {

    var table = $("#pbk-tabel").find("tbody");
    table.html("");
    $.ajax({
        url: "api/pbService/getPBKList/" + pb.pbId,
        contentType: "application/JSON",
        success: function (pbkList) {
            console.log(pbkList);
            pbkList.forEach(function (pbkList) {
                getRb(pbkList.rbId, (rb) => {
                    getRaavare(rb.raavareId, (rnavn) => {
                        var tara = getTara(pbkList.tara);
                        var netto = getNetto(pbkList.netto);
                        table.append(`<tr>
                            <td>${pbkList.pbId}</td>
                            <td>${pbkList.rbId}</td>
                            <td>${rnavn}</td>
                            <td>${tara}</td>
                            <td>${netto}</td>
                            <td>${pbkList.oprId}</td>
                           <td><button onclick="confirmDeletePBK(${pbkList.pbId}, ${pbkList.rbId})">slet</button></td>
                        </tr>`);
                    });
                });
            });
        },
        error: function (XHR) {
            console.log(XHR);
            alert("Fejl:" + XHR.responseText);
        },
    });
}

/**
 * ----------------------------------------------------------------------------------------
 * ---------------------- Slet Produktbatch og Prodtukbatch-komponent ----------------------
 * ----------------------------------------------------------------------------------------
 */

// Slet PB (listPB.html)
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
                        main.switchPage('HTML/produktBatch/listPB.html', "Se PB Liste");
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

// Slet PBK (listPBK.html)
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
                        main.switchPage('HTML/produktBatch/listPBKmain.html', "Se PBK Liste")
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



