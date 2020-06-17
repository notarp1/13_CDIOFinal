
/**
 *@Author Christine
 */


$("createRaa").submit(function (event){
    event.preventDefault();
    $.ajax({
        url: "api/raaService/createRaa",
        data: JSON.stringify($("#createRaa").serializeJSON),
        contentType: "application/JSON",
        method: "POST",
        success: function (data) {
            console.log(data);
            alert(data)
        },
        error: function(XHR) {
            console.log(XHR);
            alert("Fejl: " + XHR.responseText);

        },
    });
});


$("updateRaa").submit(function (event){
    event.preventDefault();
    $.ajax({
        url: "api/raaService/updateRaa",
        data: JSON.stringify($("#updateRaa").serializeJSON),
        method: "PUT",
        success: function (data) {
            console.log(data);
            alert(data)
        },
        error: function(XHR) {
            console.log(XHR);
            alert("Fejl: " + XHR.responseText);
        },
    });
});





