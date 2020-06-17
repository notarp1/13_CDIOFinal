
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

function raavareList() {
    var getRaaList = $("#getRaaList");
    $.ajax({
        type: "GET",
        url: "api/raaService/getRaaList",
        contentType: "application/JSON",
        success: function (data) {
            console.log(data);
            data.forEach(function (data) {
                console.log(data);
                getRaaList.append(`<tr>
                    <td>${data.raavareId}</td>
                    <td>${data.raavareNavn}</td>
                    <td>${data.leverandoer}</td>
                    <td><button onclick="deleteRecept(${data.raavareId})">slet</button></td>
                </tr>`);
            })

        },
        error: function (XHR) {
            console.log(XHR);
            alert("Fejl: " + XHR.responseText);
        }
    })

}



