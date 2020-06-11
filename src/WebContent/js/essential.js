/**
 * @Author Theis
 */
let main = {
    user: {},
    switchPage: (page, title = "") => {
        $("main").load(page)
        $("title").html(title)
    },
    notify: (msg, type = "danger", sticky = false) => {
        $.gritter.add({
            text: msg,
            class_name: 'gritter-'+type,
            sticky: sticky
        })
    },
    call: ($url, $data, _success, _fail = null, $type = "POST", extra = null) => {
        let conf = {
            url: $url,
            type: $type,
            contentType: "application/JSON",
            data: $data,
        }
        if(extra != null){
            $.each(extra, (key, value) => {
                conf[key] = value
            })
        }
        $.ajax(
            conf
        ).done(d => {
            if(d.status === "success") {
                _success(d);
            } else {
                main.notify(d.message, d.status)
                if(_fail != null)
                    _fail(d)
            }
        }).fail(d => {
            if(DEBUG)
                console.log(d)

            let msg = "Fejl: " + d.status + ", " + d.statusText
            if(d.status === 404)
                msg = "Filen blev ikke fundet på serveren."
            else if(d.status === 403)
                msg = "Du har ikke adgang til at kalde det script."
            else if(d.status === 500)
                msg = "Der skete en fejl på serveren. (500)"

            main.notify(msg)
            if(_fail != null)
                _fail(d)
        })
    },
    login: (role) => {
        main.user.role = role
        main.switchPage("HTML/menu.html", "Menu")
        $("footer").show(0)
    },
    logout: () => {
        main.user.role = ""
        main.switchPage("HTML/login.html", "Menu")
        $("footer").hide(0)
    }
}