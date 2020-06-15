/**
 * @author Zahra
 */

//Tilføj råvarebatch
$("#createRB").submit(function(event) {
    event.preventDefault();
    var rb_json = $("#createRB").serializeJSON();
    $.ajax({
        url: "api/rbService/createRB",
        data: rb_json,
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