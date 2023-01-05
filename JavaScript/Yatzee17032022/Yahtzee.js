const rolls = [1, 2, 3, 4, 5, 6]
const images = ["Die1.png", "Die2.png", "Die3.png", "Die4.png", "Die5.png", "Die6.png"]
const imageslock = ["Die1Lock.png", "Die2Lock.png", "Die3Lock.png", "Die4Lock.png", "Die5Lock.png", "Die6Lock.png"]

let terning1 = document.querySelector("#terning1>img");
let terning2 = document.querySelector("#terning2>img");
let terning3 = document.querySelector("#terning3>img");
let terning4 = document.querySelector("#terning4>img");
let terning5 = document.querySelector("#terning5>img");

let button = document.querySelector("#buttonRoll");


function randomNumber() {
return rolls[Math.floor(Math.random()*rolls.length)];
}

let rolled = []; // array af numre der er rullet
let locked = [false, false, false, false, false]; // array af om terninger er låste



let turn = 0;
var turnhtml = document.getElementById("tur");

function saveRandomNumbers() {
    if (turn == 0) {
    for (let i = 0; i < 5; i++) {
        rolled.push(randomNumber());
    }
    turn++;
    turnhtml.innerHTML = turn
    updatefields()
    } else if (turn != 3){
        for (let i = 0; i < 5; i++) {
            if (locked[i] == false) {
                rolled.splice(i, 1, randomNumber())
            }
        }
        turn++
        turnhtml.innerHTML = turn
        updatefields()
    }

}
function changeImages() {

    saveRandomNumbers();
    if (locked[0] == false) {
        terning1.src = images[rolled[0] - 1]
    }
    if (locked[1] == false) {
        terning2.src = images[rolled[1] - 1]
    }
    if (locked[2] == false) {
        terning3.src = images[rolled[2] - 1]
    }
    if (locked[3] == false) {
        terning4.src = images[rolled[3] - 1]
    }
    if (locked[4] == false) {
        terning5.src = images[rolled[4] - 1]
    }
}

function changeTerning1() {
    if (locked[0] == false) {
        terning1.src = imageslock[rolled[0] - 1]
        locked[0] = true
    } else {
        terning1.src = images[rolled[0] - 1]
        locked[0] = false
    }
}
function changeTerning2() {
    if (locked[1] == false) {
        terning2.src = imageslock[rolled[1] - 1]
        locked[1] = true
    } else {
        terning2.src = images[rolled[1] - 1]
        locked[1] = false
    }
}
function changeTerning3() {
    if (locked[2] == false) {
        terning3.src = imageslock[rolled[2] - 1]
        locked[2] = true
    } else {
        terning3.src = images[rolled[2] - 1]
        locked[2] = false
    }
}
function changeTerning4() {
    if (locked[3] == false) {
        terning4.src = imageslock[rolled[3] - 1]
        locked[3] = true
    } else {
        terning4.src = images[rolled[3] - 1]
        locked[3] = false
    }
}
function changeTerning5() {
    if (locked[4] == false) {
        terning5.src = imageslock[rolled[4] - 1]
        locked[4] = true
    } else {
        terning5.src = images[rolled[4] - 1]
        locked[4] = false
    }
}
function resetTerninger() {
    for (i = 0; i<locked.length; i++) {
        locked[i] = false;
    }
    terning1.src = "Die0.png"
    terning2.src = "Die0.png"
    terning3.src = "Die0.png"
    terning4.src = "Die0.png"
    terning5.src = "Die0.png"

}

var btnClick = document.getElementById("buttonRoll");
btnClick.addEventListener("click", changeImages, false);

var terning1Click = document.getElementById("terning1");
terning1Click.addEventListener("click", changeTerning1, false);

var terning2Click = document.getElementById("terning2");
terning2Click.addEventListener("click", changeTerning2, false);

var terning3Click = document.getElementById("terning3");
terning3Click.addEventListener("click", changeTerning3, false);

var terning4Click = document.getElementById("terning4");
terning4Click.addEventListener("click", changeTerning4, false);

var terning5Click = document.getElementById("terning5");
terning5Click.addEventListener("click", changeTerning5, false);

// felter logik
let total = 0;
let sum = 0;
let bonusacquired = false;


var felt1 = document.getElementById("1s");
var felt2 = document.getElementById("2s");
var felt3 = document.getElementById("3s");
var felt4 = document.getElementById("4s");
var felt5 = document.getElementById("5s");
var felt6 = document.getElementById("6s");
var felt7 = document.getElementById("onepair");
var felt8 = document.getElementById("twopair");
var felt9 = document.getElementById("threesame");
var felt10 = document.getElementById("foursame");
var felt11 = document.getElementById("fullhouse");
var felt12 = document.getElementById("smallstraight");
var felt13 = document.getElementById("largestraight");
var felt14 = document.getElementById("chance");
var felt15 = document.getElementById("yatzy");
var felt16 = document.getElementById("sum");
var felt17 = document.getElementById("bonus");
var felt18 = document.getElementById("total");


felt1.addEventListener("click", disablefelt1, false);
function disablefelt1 () {
    total = total + ones;
    sum = sum + ones;
    bonus();
    turn = 0;
    turnhtml.innerHTML = turn
    felt18.value = total;
    resetTerninger();
    rolled = []
    resetfields();
    felt1.disabled = true;
}

felt2.addEventListener("click", disablefelt2, false);
function disablefelt2 () {
    total = total + twos;
    sum = sum + twos;
    bonus();
    turn = 0;
    turnhtml.innerHTML = turn
    felt18.value = total;
    resetTerninger();
    rolled = []
    resetfields();
    felt2.disabled = true;
}
felt3.addEventListener("click", disablefelt3, false);
function disablefelt3 () {
    total = total + threes;
    sum = sum + threes;
    bonus();
    turn = 0;
    turnhtml.innerHTML = turn
    felt18.value = total;
    resetTerninger();
    rolled = []
    resetfields();
    felt3.disabled = true;
}
felt4.addEventListener("click", disablefelt4, false);
function disablefelt4 () {
    total = total + fours;
    sum = sum + fours;
    bonus();
    turn = 0;
    turnhtml.innerHTML = turn
    felt18.value = total;
    resetTerninger();
    rolled = []
    resetfields();
    felt4.disabled = true;
}
felt5.addEventListener("click", disablefelt5, false);
function disablefelt5 () {
    total = total + fives;
    sum = sum + fives;
    bonus();
    turn = 0;
    turnhtml.innerHTML = turn
    felt18.value = total;
    resetTerninger();
    rolled = []
    resetfields();
    felt5.disabled = true;
}
felt6.addEventListener("click", disablefelt6, false);
function disablefelt6 () {
    total = total + sixes;
    sum = sum + sixes;
    bonus();
    turn = 0;
    turnhtml.innerHTML = turn
    felt18.value = total;
    resetTerninger();
    rolled = []
    resetfields();
    felt6.disabled = true;
}
felt7.addEventListener("click", disablefelt7, false);
function disablefelt7 () {
    total = total + onepair;
    turn = 0;
    turnhtml.innerHTML = turn
    felt18.value = total;
    resetTerninger();
    rolled = []
    resetfields();
    felt7.disabled = true;
}
felt8.addEventListener("click", disablefelt8, false);
function disablefelt8 () {
    total = total + twopair;
    turn = 0;
    turnhtml.innerHTML = turn
    felt18.value = total;
    resetTerninger();
    rolled = []
    resetfields();
    felt8.disabled = true;
}
felt9.addEventListener("click", disablefelt9, false);
function disablefelt9 () {
    total = total + threesame;
    turn = 0;
    turnhtml.innerHTML = turn
    felt18.value = total;
    resetTerninger();
    rolled = []
    resetfields();
    felt9.disabled = true;
}
felt10.addEventListener("click", disablefelt10, false);
function disablefelt10 () {
    total = total + foursame;
    turn = 0;
    turnhtml.innerHTML = turn
    felt18.value = total;
    resetTerninger();
    rolled = []
    resetfields();
    felt10.disabled = true;
}
felt11.addEventListener("click", disablefelt11, false);
function disablefelt11 () {
    total = total + fullhouse;
    turn = 0;
    turnhtml.innerHTML = turn
    felt18.value = total;
    resetTerninger();
    rolled = []
    resetfields();
    felt11.disabled = true;
}
felt12.addEventListener("click", disablefelt12, false);
function disablefelt12 () {
    total = total + smallstraight;
    turn = 0;
    turnhtml.innerHTML = turn
    felt18.value = total;
    resetTerninger();
    rolled = []
    resetfields();
    felt12.disabled = true;
}
felt13.addEventListener("click", disablefelt13, false);
function disablefelt13 () {
    total = total + largestraight;
    turn = 0;
    turnhtml.innerHTML = turn
    felt18.value = total;
    resetTerninger();
    rolled = []
    resetfields();
    felt13.disabled = true;
}
felt14.addEventListener("click", disablefelt14, false);
function disablefelt14 () {
    total = total + chance;
    turn = 0;
    turnhtml.innerHTML = turn
    felt18.value = total;
    resetTerninger();
    rolled = []
    resetfields();
    felt14.disabled = true;
}
felt15.addEventListener("click", disablefelt15, false);
function disablefelt15 () {
    total = total + yatzy;
    turn = 0;
    turnhtml.innerHTML = turn
    felt18.value = total;
    resetTerninger();
    rolled = []
    resetfields();
    felt15.disabled = true;
}
function bonus() {
    felt16.value = sum;
    if (sum > 62 && bonusacquired == false) {
        bonusacquired = true;
        felt17.value = 50;
        total = total + 50;
    }
}

let ones
let twos
let threes
let fours
let fives
let sixes
let onepair
let twopair
let threesame
let foursame
let fullhouse
let smallstraight
let largestraight
let chance
let yatzy

// opdatere felter, bliver kaldt når der rulles nye tal
function updatefields() {
    felt1.value = find1s(rolled)
    felt2.value = find2s(rolled)
    felt3.value = find3s(rolled)
    felt4.value = find4s(rolled)
    felt5.value = find5s(rolled)
    felt6.value = find6s(rolled)
    felt7.value = findonepair(rolled)
    felt8.value = findtwopair(rolled)
    felt9.value = findthreesame(rolled)
    felt10.value = findfoursame(rolled)
    felt11.value = findfullhouse(rolled)
    felt12.value = findsmallstraight(rolled)
    felt13.value = findlargestraight(rolled)
    felt14.value = findchance(rolled)
    felt15.value = findyatzy(rolled)
}

function resetfields() {
    felt1.value = ""
    felt2.value = ""
    felt3.value = ""
    felt4.value = ""
    felt5.value = ""
    felt6.value = ""
    felt7.value = ""
    felt8.value = ""
    felt9.value = ""
    felt10.value = ""
    felt11.value = ""
    felt12.value = ""
    felt13.value = ""
    felt14.value = ""
    felt15.value = ""
}
// logik til at finde værdien i hvert felt

function find1s (rolledDice) {
    ones = 0;
    for (i=0; i<rolledDice.length; i++) {
        if (rolledDice[i] == 1) {
            ones = ones + rolledDice[i];
        }
    }
    return ones
}
function find2s (rolledDice) {
    twos = 0;
    for (i=0; i<rolledDice.length; i++) {
        if (rolledDice[i] == 2) {
            twos = twos + rolledDice[i];
        }
    }
    return twos
}
function find3s (rolledDice) {
    threes = 0;
    for (i=0; i<rolledDice.length; i++) {
        if (rolledDice[i] == 3) {
            threes = threes + rolledDice[i];
        }
    }
    return threes
}
function find4s (rolledDice) {
    fours = 0;
    for (i=0; i<rolledDice.length; i++) {
        if (rolledDice[i] == 4) {
            fours = fours + rolledDice[i];
        }
    }
    return fours
}
function find5s (rolledDice) {
    fives = 0;
    for (i=0; i<rolledDice.length; i++) {
        if (rolledDice[i] == 5) {
            fives = fives + rolledDice[i];
        }
    }
    return fives
}
function find6s (rolledDice) {
    sixes = 0;
    for (i=0; i<rolledDice.length; i++) {
        if (rolledDice[i] == 6) {
            sixes = sixes + rolledDice[i];
        }
    }
    return sixes
}

function findonepair (rolledDice) {
    onepair = 0;
    for (i=0; i<rolledDice.length; i++) {
        let findsecond = [...rolledDice]
        findsecond.splice(i, 1)
        for (o=0; o<findsecond.length; o++) {
            if (findsecond[o] == rolledDice[i]) {
                if (onepair < rolledDice[i]) {
                    onepair = rolledDice[i]
                }
            }
        }
    }
    onepair = onepair * 2
    return onepair
}

function findtwopair (rolledDice) {
    let onepair = 0;
    twopair = 0;
    let firstpairfound = false;
    for (i=0; i<rolledDice.length; i++) {
        let findsecond = [...rolledDice]
        findsecond.splice(i, 1)
        for (o=0; o<findsecond.length; o++) {
            if (firstpairfound == false) {
                if (findsecond[o] == rolledDice[i]) {
                    onepair = rolledDice[i]
                    firstpairfound = true;
                }
            } else {
                if (findsecond[o] == rolledDice[i]  && rolledDice[i] != onepair) {
                    twopair = rolledDice[i] + onepair
                }
            }
        }
    }
    twopair = twopair * 2
    return twopair
}

function findthreesame(rolledDice) {
    threesame = 0;
    for (i=0; i<rolledDice.length; i++) {
        let findsecond = [...rolledDice]
        findsecond.splice(i, 1)
        for (o=0; o<findsecond.length; o++) {
            if (findsecond[o] == rolledDice[i]) {
                    let findthird = [...findsecond]
                    findthird.splice(o, 1)
                    for (n=0; n<findthird.length; n++) {
                        if (findthird[n] == findsecond[o]) {
                            threesame = rolledDice[i]
                        }
                    }
            }
        }
    }
    threesame = threesame * 3
    return threesame
}

function findfoursame(rolledDice) {
    foursame = 0;
    for (i=0; i<rolledDice.length; i++) {
        let findsecond = [...rolledDice]
        findsecond.splice(i, 1)
        for (o = 0; o < findsecond.length; o++) {
            if (findsecond[o] == rolledDice[i]) {
                let findthird = [...findsecond]
                findthird.splice(o, 1)
                for (n = 0; n < findthird.length; n++) {
                    if (findthird[n] == findsecond[o]) {
                        let findfourth = [...findthird]
                        findfourth.splice(n, 1)
                        for (b = 0; b < findfourth.length; b++) {
                            if (findfourth[b] == findthird[n]) {
                                foursame = rolledDice[i]
                            }

                        }
                    }
                }
            }
        }
    }
    foursame = foursame * 4
    return foursame
}

function findfullhouse(rolledDice) {
    let threesame = 0;
    let onepair = 0;
    fullhouse = 0;
    let threesamefound = false;
    for (i=0; i<rolledDice.length; i++) {
        let findsecond = [...rolledDice]
        findsecond.splice(i, 1)
        for (o=0; o<findsecond.length; o++) {
            if (findsecond[o] == rolledDice[i]) {
                if (threesamefound == true) {
                    if (findsecond[o] != threesame) {
                        onepair = findsecond[o]
                    }
                } else {
                let findthird = [...findsecond]
                findthird.splice(o, 1)
                for (n=0; n<findthird.length; n++) {
                    if (findthird[n] == findsecond[o]) {
                        threesame = rolledDice[i]
                        threesamefound = true;
                    }
                }
                }
            }
        }
    }
    if (onepair != 0) {
        fullhouse = threesame * 3 + onepair * 2
    }
    return fullhouse
}
function findsmallstraight (rolledDice) {
    smallstraight = 0;
    if (ones != 0) {
        if (twos != 0) {
            if (threes != 0) {
                if (fours != 0) {
                    if (fives != 0) {
                        smallstraight = 15
                    }
                }
            }
        }
    }
    return smallstraight
}

function findlargestraight (rolledDice) {
    largestraight = 0;
    if (twos != 0) {
        if (threes != 0) {
            if (fours != 0) {
                if (fives != 0) {
                    if (sixes != 0) {
                        largestraight = 20
                    }
                }
            }
        }
    }
    return largestraight
}
function findchance (rolledDice) {
    chance = 0;
    for (i=0; i<rolledDice.length; i++) {
        chance = chance + rolledDice[i]
    }
    return chance
}

function findyatzy (rolledDice) {
    yatzy = 0;
    let firstindex = rolledDice[0];
    let allsame = true;
    for (i=1; i<rolledDice.length; i++) {
        if (rolledDice[i] != firstindex) {
            allsame = false;
        }
    }
    if (allsame == true) {
        yatzy = 50;
    }
    return yatzy
}


