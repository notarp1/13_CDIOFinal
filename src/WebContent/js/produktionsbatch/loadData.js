
/**
 * ----------------------------------------------------------------------------------------
 * ------------------------ Hent data fra database ind som forslag ------------------------
 * ----------------------------------------------------------------------------------------
 */
//Load pbID automatisk ind som forslag til  på de diverse sider
function loadSuggestionsPB(type)     {
    if (type == 0) {
        var output = $("#createPBK").find("#pbId");
        output.html("");
        var url1 = "api/pbService/list";
    }

    if (type == 1) {
        var output = $("#updatePBK").find("#pbId");
        output.html("");
        var url1 = "api/pbService/PBK/list";
    }
    if (type == 2) {
        var output = $("#findPB").find("#pbId");
        output.html("");
        var url1 = "api/pbService/list";
    }
    if (type == 3) {
        var output = $("#createPBKfirstPage").find("#pbId");
        output.html("");
        var url1 = "api/pbService/list";
    }
    if (type == 4) {
        var output = $("#updatePBKfirstPage").find("#pbId");
        output.html("");
        var url1 = "api/pbService/list";
    }
    if (type == 5) {
        var output = $("#findPBKList").find("#pbId");
        output.html("");
        var url1 = "api/pbService/list";
    }


    $.ajax({
        url: url1,
        contentType: "application/JSON",
        success: function (products) {
            console.log(products);
            var pbArray = [];
            for (let i = 0; i < products.length; i++) {
                if (!pbArray.includes(products[i].pbId)) {
                    output.append(` <option value="${products[i].pbId}">${products[i].pbId}</option>`);
                    pbArray.push(products[i].pbId);
                }
            }

        },
        error: function (XHR) {
            console.log(XHR);
            alert("Fejl:" + XHR.responseText);
        },
    });
}

//Load brugere automatisk ind som forslag til  på de diverse sider
function loadSuggestionsUser(type) {

    if (type == 0) {
        var output = $("#createPBK").find("#oprId");
        output.html("");
    }
    if (type == 1) {
        var output = $("#updatePBK").find("#oprId");
        output.html("");
    }

    $.ajax({
        url: "api/bruger/all",
        contentType: "application/JSON",
        method: "GET",
        success: function (users) {
            console.log(users);

            for (let i = 0; i < users.length; i++) {
                output.append(` <option value="${users[i].oprId}">${users[i].oprNavn}</option>`);
            }

        },
        error: function (XHR) {
            console.log(XHR);
            alert("Fejl:" + XHR.responseText);
        },
    });
}

//Load recepter automatisk ind som forslag til  på de diverse sider
function loadSuggestionsRecepter() {

    var output = $("#createPB").find("#receptId");
    output.html("");

    $.ajax({
        url: "api/recept/list",
        contentType: "application/JSON",
        success: function (recepter) {
            console.log(recepter);

            for (let i = 0; i < recepter.length; i++) {
                output.append(` <option value="${recepter[i].receptId}">${recepter[i].receptNavn}</option>`);
            }

        },
        error: function (XHR) {
            console.log(XHR);
            alert("Fejl:" + XHR.responseText);
        },
    });
}


function getUser(id, _callback) {

    $.ajax({
        url: "api/bruger/" + id,
        contentType: "application/JSON",
        method: "GET",
        success: function (user) {
                console.log(user.oprNavn);
            _callback(user);

        },
        error: function (XHR) {
            console.log(XHR);
            alert("Fejl:" + XHR.responseText);
        },
    });

}