/*
Header "On Click" metoder
 */
const logoButton = document.querySelector('#logosection')
const historieButton = document.querySelector('#HistorieButton')
const juletraeerButton = document.querySelector('#JuletraeButton')
const udlejningButton = document.querySelector('#UdlejningButton')
const consultButton = document.querySelector('#ConsulButton')
const selvskovningButton = document.querySelector('#SelvskovButton')
const seldrupButton = document.querySelector('#SeldrupButton')
const aabningstiderButton = document.querySelector('#AabningstiderButton')
const kontaktButton = document.querySelector('#KontaktButton')
const findButton = document.querySelector('#FindButton')

if (logoButton) {
    logoButton.onclick = welcomePage
}
if (historieButton) {
    historieButton.onclick = historieFane
}
if (juletraeerButton) {
    juletraeerButton.onclick = juletraeerFane
}
if (udlejningButton) {
    udlejningButton.onclick = udlejningFane
}
if (consultButton) {
    consultButton.onclick = consultancyFane
}
if (selvskovningButton) {
    selvskovningButton.onclick = selvskovningFane
}
if (seldrupButton) {
    seldrupButton.onclick = seldrupFane
}
if (aabningstiderButton) {
    aabningstiderButton.onclick = aabningstiderFane
}
if (kontaktButton) {
    kontaktButton.onclick = kontaktFane
}
if (findButton) {
    findButton.onclick = findFane
}

function welcomePage () {
    window.location.href = '/'
}
function historieFane () {
    window.location.href = '/historie'
}
function juletraeerFane () {
    window.location.href = '/juletraeer'
}
function udlejningFane() {
    window.location.href = '/udlejning'
}
function consultancyFane() {
    window.location.href = '/consultancy'
}
function selvskovningFane() {
    window.location.href = '/selvskovning'
}
function seldrupFane () {
    window.location.href = '/seldrupgaard'
}
function aabningstiderFane () {
    window.location.href = '/aabningstider'
}
function kontaktFane () {
    window.location.href = '/kontakt'
}
function findFane () {
    window.location.href = '/find'
}