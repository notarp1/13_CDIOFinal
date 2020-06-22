/**
 * @Author Theis
 */
let main = {
    user: {},
    switchPage: (page, title = "", _callback = null) => {
        $("main").load(page)
        $("title").html(title)

        if(_callback != null){

            setTimeout(() => {

                _callback();

            }, 10)

        }

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
        ).done((d, status, xhr) => {
            console.log(d)
            _success(d);
        }).fail((xhr, status, error) => {
            console.log(xhr)

            let msg = "Fejl: " + xhr.status + ", " + xhr.statusText
            if(xhr.status === 404)
                msg = "Filen blev ikke fundet på serveren."
            else if(xhr.status === 403)
                msg = "Du har ikke adgang til at kalde det script."
            else if(xhr.status === 500)
                msg = "Der skete en fejl på serveren. (500)"

            main.notify(msg)
            if(_fail != null)
                _fail(xhr)
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
    },
    logoutHide: () =>{
        $("footer").hide(0);
    },
    tilbage: () =>{
        main.switchPage(main.back)
    }, back:"",
    tilbageHide: () =>{
        $(".tilbage-button").hide(0);
    },
    tilbageShow: () =>{
        $(".tilbage-button").show(0);
    }
}