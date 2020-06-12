/**
 * @Auther Theis
 */
let bruger = {
    current: {},
    load: (id = 0) => {
        if (id === 0) {
            let table = $("#bruger-tabel tbody")
            table.html("")
            main.call("api/bruger/all", {}, (brugere) => {
                brugere.forEach((bruger) => {
                    table.append(`
                        <tr>
                            <td>${bruger.oprId}</td>
                            <td>${bruger.ini}</td>
                            <td>${bruger.oprNavn}</td>
                            <td>${bruger.cpr}</td>
                            <td>${bruger.roller}</td>
                            <td><button onclick="bruger.edit(${bruger.oprId})">Ret</button></td>
                            <td><button onclick="bruger.delete(${bruger.oprId})">Slet</button></td>
                        </tr>
                    `)
                })
            }, (xhr) => {}, "GET")
        }
        else {
            main.call(`api/bruger/${id}`, {}, (hentet) => {
                bruger.current = hentet
                $("#oprId").val(bruger.current.oprId)
                $("#oprNavn").val(bruger.current.oprNavn)
                $("#ini").val(bruger.current.ini)
                $("#cpr").val(bruger.current.cpr)
                if (bruger.current.roller.includes("Administrator"))
                    $("#Administrator").attr("checked", "checked")
                if (bruger.current.roller.includes("Farmaceut"))
                    $("#Farmaceut").attr("checked", "checked")
                if (bruger.current.roller.includes("Produktionsleder"))
                    $("#Produktionsleder").attr("checked", "checked")
                if (bruger.current.roller.includes("Laborant"))
                    $("#Laborant").attr("checked", "checked")
            }, (xhr) => {}, "GET")
        }
    },
    edit: (id) => {
        main.switchPage("HTML/bruger/edit.html")
        bruger.load(id)
    },
    update: (event) => {
        event.preventDefault()
        let data = $(event.currentTarget).serializeJSON()
        for (let [key, value] of Object.entries(data)) {
            if (!bruger.validate(value)) {
                main.notify("Der er et felt, som ikke er udfyldt!")
                return false
            }
        }
        main.call("api/bruger", JSON.stringify(data), (data) => {
            main.notify(data, "success")
            main.switchPage("HTML/bruger/liste.html")
        }, (xhr) => {
            main.notify(xhr.responseText)
        }, "PUT")
    },
    create: (event) => {
        event.preventDefault()
        let data = $(event.currentTarget).serializeJSON()
        for (let [key, value] of Object.entries(data)) {
            if (!bruger.validate(value)) {
                main.notify("Der er et felt, som ikke er udfyldt!")
                return false
            }
        }
        main.call("api/bruger", JSON.stringify(data), (data) => {
            main.notify(data, "success")
            main.switchPage("HTML/bruger/liste.html")
        }, (xhr) => {
            main.notify(xhr.responseText)
        }, "POST")
    },
    delete: (id) => {
        Swal.fire({
            icon: 'warning',
            title: "Er du sikker?",
            text: `Er du sikker på at du vil slette bruger ${id}`,
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Ja, slet den!'
        }).then((result) => {
            if (result.value) {
                main.call(`api/bruger/${id}`, {}, (data) => {
                    Swal.fire(
                        'Slettet!',
                        'Brugeren blev slettet',
                        'success'
                    ).then(() => {
                        bruger.load()
                    })
                }, (xhr) => {
                    main.notify(xhr.responseText)
                }, "DELETE")
            }
        })
    },
    validate: (val) => {
        if(val == "" || !val || val.length === 0 || val == 0) {
            return false
        } else {
            return val
        }
    },
}