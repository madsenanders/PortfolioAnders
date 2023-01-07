const navn = document.querySelector('.navn')
const firma = document.querySelector('.firma')
const besked = document.querySelector('.besked')


let list = []
async function makeList() {
    const liste = document.querySelector('.beskedListe')
    const resp = await fetch('/indbakke')
    const indbakke = await resp.json()
    for (let i = 0; i < indbakke.length; i++) {
        let listItem = document.createElement('li')
        listItem.className='listItem'
        listItem.innerHTML = indbakke[i].email
        liste.appendChild(listItem)

    }
    list = indbakke
}
await makeList()

const listItemArray = document.querySelectorAll('.listItem')



for (let i = 0; i < listItemArray.length; i++) {
    let index = 0
    for (let h = 0; h < list.length; h++) {
        if (listItemArray[i].value === list[h].email) {
            break
        }
        index++
    }
    listItemArray[i].onclick = fillmessage(index)
}

function fillmessage (index) {
    navn.innerHTML = `Navn: ${list[index].fornavn}`
    firma.innerHTML = `Firma: ${list[index].firmanavn}`
    besked.innerHTML = `${list[index].besked}`
}
