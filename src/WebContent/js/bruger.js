/**
 * @Auther Theis
 */
let bruger = {
    load: (id = 0) => {
        if (id === 0) {
            let table = $("#bruger-tabel tbody")
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
                        </tr>
                    `)
                })
            }, (xhr) => {}, "GET")
        }
        else {
            //
        }
    },
    edit: () => {
        //
    }
}