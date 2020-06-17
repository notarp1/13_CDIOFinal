/**
 * @author Zahra
 */

//Tilføj råvarebatch
    $("#createRB").submit(function(event) {
        event.preventDefault();

        var RB_json = $("#createRB").serializeJSON();
        $.ajax({
            url: "api/rbService/createRB",
            data: JSON.stringify(RB_json),
            contentType: "application/JSON",
            method: "POST",
            success: function (data) {
                console.log(data);
                alert(data);

            },
            error: function(XHR) {
                console.log(RB_json);
                console.log(XHR);
                alert("Fejl: " + XHR.responseText);
            },
        });
    });

//Load og se råvarebatches
    function loadRB() {
        var rbTable =  $("#rb-table").find("tbody");
        rbTable.html("");

        $.ajax({
            url: "api/rbService/getRBList",
            contentType: "application/JSON",

            success: function (raavareListe) {
                console.log(raavareListe);
                raavareListe.forEach(function (raavareListe){
                    rbTable.append(`<tr> <td>${raavareListe.rbId}</td>
                                     <td>${raavareListe.raavareId}</td>
                                     <td>${raavareListe.maengde}</td></tr>`);
                })},
            error: function (fejlbesked) {
                console.log(fejlbesked);
                alert("Fejl: " + fejlbesked.responseText)
            }
        })


//Find specifik RB
        $("#findRB").submit(function(event) {
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
                error: function(XHR) {
                    console.log(XHR);
                    alert("Fejl: " + XHR.responseText);
                },
            });
        });

    }