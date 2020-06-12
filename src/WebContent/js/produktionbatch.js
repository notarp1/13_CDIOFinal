/**
 * @Author Christian
 */




function switchPage(page) {
    $("body").load(page);

}




//Tilføj produktbatch
$("#createPB").submit(function(event) {
    event.preventDefault();
    $.ajax({
        url: "api/pbService/createPB",
        data: JSON.stringify($("#createPB").serializeJSON()),
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


//!!!!!IKKE I BRUG

function loadUsers() {
    var table = $("#tabel1").find("tbody");
    table.html("");
    $.ajax({
        url: "../api/userAdmin/getUsers",
        contentType: "application/JSON",
        success: function (users) {
            console.log(users);
            users.forEach(function(user) {
                console.log(user);
                table.append(`<tr>
                    <td>${user.userId}</td>
                    <td>${user.userName}</td>
                    <td>${user.cpr}</td>
                    <td>${user.ini}</td>
                    <td>${user.password}</td>
                    <td><button onclick="confirmDeleteUser(${user.userId})">Slet bruger?</button></td>
                </tr>`);
            })
        },
        error: function(XHR) {
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


//!!!!!IKKE I BRUG
$("#deleteUser").submit(function(event) {
    event.preventDefault();
    confirmDeleteUser($("#userID").val());
});

//!!!!!IKKE I BRUG
function confirmDeleteUser(ID) {
    $.ajax({
        url: "../api/userAdmin/getUser",
        data: {userID: ID},
        contentType: "application/JSON",
        method: "GET",
        success: function (user) {
            console.log(user);
            if (confirm('Are you sure you want to delete user: '+user.userName+'?')) {
                $.ajax({
                    url: "../api/userAdmin/delete",
                    data: JSON.stringify({userID: ID}),
                    contentType: "application/JSON",
                    method: "POST",
                    success: function (data) {
                        console.log(data);
                        alert(data.responseText);
                        switchPage("menu.html");
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




