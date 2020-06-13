/**
 * @author Zahra
 */

//Tilføj råvarebatch
$("#createRB").submit(function(event) {
    event.preventDefault();
    $("#createRB").serializeJSON();
    $.ajax({
        url: "api/rbService/createRB",
        data: JSON.stringify(),
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