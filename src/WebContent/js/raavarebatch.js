/**
 * @author Zahra
 */

//Tilføj råvarebatch
    console.log("Document ready!");
    $("#createRB").submit(function(event) {
        console.log("Posting!");
        event.preventDefault();
        var rb_json = $("#createRB").serializeJSON();
        console.log(rb_json);
        $.ajax({
            url: "api/rbService/createRB",
            data: JSON.stringify(rb_json),
            contentType: "application/JSON",
            method: "POST",
            success: function (data) {
                console.log(data);
                alert(data);

            },
            error: function(XHR) {
                console.log(XHR);
                alert("Fejl: " + XHR.responseText);
            },
        });
    });

