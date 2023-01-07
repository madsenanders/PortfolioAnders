import Graf from "./Graf.js";

const graf = new Graf()

const tid = document.querySelector('#tid')
const grafKnap = document.querySelector('#grafKnap')

if (grafKnap) {
    grafKnap.onclick = sendDato
}

if (tid) {
    tid.onchange = tidOnChange
}

let today = new Date();
let year = today.getFullYear()
let month = today.getMonth() + 1
if (month < 10) {
    month = "0" + month
}
let day = today.getDate()
if (day < 10) {
    day = "0" + day
}
let hours = today.getHours()
if (hours < 10) {
    hours = "0" + hours
}
let minutes = today.getMinutes()
if (minutes < 10) {
    minutes = "0" + minutes
}

let idag = year + "-" + month + "-" + day + "T" + hours + ":" + minutes

tid.value = idag
tid.min = idag
year += 1
idag = year + "-" + month + "-" + day + "T" + hours + ":" + minutes
tid.max = idag

await tidOnChange()

async function tidOnChange() {
    // format: year-month-dayThours:minutes
    console.log(tid.value)
    let yearMonthDay = tid.value.split("-")
    let year = yearMonthDay[0]
    let month = yearMonthDay[1]
    let dagOgTid = yearMonthDay[2].split("T")
    let day = dagOgTid[0]
    const resp = await fetch(`/getAnkomne?year=${year}&month=${month}&day=${day}`)
    const ankomneArray = await resp.json()
    graf.lavGraf(ankomneArray)
}

async function sendDato() {
    let array = tid.value.split("-")
    let dagOgTid = array[2].split("T")
    let year = array[0]
    let month = array[1]
    let day = dagOgTid[0]
    let time = dagOgTid[1]
    const body = JSON.stringify({year: year, month: month, day: day, time: time})
    const resp = await fetch("/bekraeftDato", {
        method: "POST",
        headers: {
            "content-type": "application/json"
        },
        body: body
    })
    await tidOnChange()
}
