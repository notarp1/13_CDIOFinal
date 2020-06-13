/**
 * @Author Christian
 */



function switchPage(page) {
    $("body").load(page);
}



function getPrintInfo(pbId, receptId, date, status) {

    getPrintDate();
    getPbId(pbId);
    getReceptId(receptId);
    getPbDate(date);
    getPbStatus(status);

    function getPrintDate() {
        n =  new Date();
        y = n.getFullYear();
        m = n.getMonth() + 1;
        d = n.getDate();
        document.getElementById("printDate").innerHTML ="Udskrevet: " +  m + "/" + d + "/" + y;
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
        document.getElementById("pbStart").innerHTML = "Produktions Start: " +  n;

    }



}

function getPbStatus(status) {
    n = status;
    if (status == 0) {
        document.getElementById("printStatus").innerHTML = "Status: 'oprettet'";
    }
    if (status == 1) {
        document.getElementById("printStatus").innerHTML = "Status: 'under produktion'";
    }
    if (status == 2) {
        document.getElementById("printStatus").innerHTML = "Status: 'afsluttet'";
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
    </table>`);

}


function printAppendTable(printPbk, print, times) {
    a = times;
    for(let i = 0; i<a; i++) {
        printAppend(print, i);
        var print2 = $(`#printTables`).find(`#tableItems${i}`);
            print2.append(`<tr>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td>${printPbk.tara}</td>
    <td>${printPbk.netto}</td>
    <td></td>
    <td>${printPbk.rbId}</td>
    <td>${printPbk.oprId}</td>
    </tr>`);
       /* setTimeout(() => {
        }, 10)*/
    }

}


function loadPrintPB(pb){
    $.ajax({
        url: "api/pbService/getPB",
        data: {pbId: pb.pbId},
        contentType: "application/JSON",
        method: "GET",
        success: function (printPb) {
            main.switchPage('HTML/produktBatch/pbPrint.html')

            setTimeout(() => {

                getPrintInfo(printPb.pbId, printPb.receptId, printPb.date, printPb.status);

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

function loadPrintPBK(pbk){
    $.ajax({
        url: "api/pbService/getPBK",
        data: {pbId: pbk.pbId, rbId: pbk.rbId},
        contentType: "application/JSON",
        method: "GET",
        success: function (printPbk) {
            console.log("4");
            setTimeout(() => {

                var print = $("#printTables");
                getPbStatus(1);

                printAppendTable(printPbk, print, 2);


                console.log(print);
            }, 10)

        },
        error: function(XHR) {
            console.log(XHR);
            alert("Fejl: " + XHR.responseText);
        },
    });
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
            console.log(data);
            alert(data);
            loadPrintPB(pb);

        },
        error: function(XHR) {
            console.log(XHR);
            alert("Fejl: " + XHR.responseText);
        },
    });
});

//Tilføj produktbatchkomponent
$("#createPBK").submit(function(event) {
    event.preventDefault();

    var pbk = $("#createPBK").serializeJSON();
    $.ajax({
        url: "api/pbService/createPBK",
        data: JSON.stringify(pbk),
        contentType: "application/JSON",
        method: "POST",
        success: function (data) {
            console.log(data);
            alert(data);
            var pb = $("#createPBK").serializeJSON();
            $.ajax({
                url: "api/pbService/getPBKList",
                data: {pbId: pb.pbId},
                contentType: "application/JSON",
                method: "GET",
                success: function (data2) {
                    console.log(data2);
                    alert(data2);
                    loadPrintPB(pb);

                    setTimeout(() => {
                        $("#pbPrint-tabel").remove();
                        console.log("3");
                        loadPrintPBK(pbk)
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




