class Graf {
    #graf
    #gennemsnitsGraf

    constructor() {
    }

    totalAntalAnkomneITidsrum(ankomneArray, starttid, sluttid) {
        let ialt = 0
        for (let i = starttid; i < sluttid; i++) {
            ialt += ankomneArray[i]
        }
        return ialt
    }

    beregnProcentAnkomneITidsrum(ankomneArray, starttid, sluttid, ialt) {
        let yValues = []
        for (let i = starttid; i < sluttid; i++) {
            yValues.push(ankomneArray[i] / ialt * 100)
        }
        return yValues
    }

    lavNuvaerendeTimeRoed(barColors, starttid, sluttid) {
        let today = new Date();
        let currentHour = today.getHours();

        for (let i = starttid; i < sluttid; i++) {
            if (currentHour === i) {
                barColors[i-starttid] = "red"
            }
        }

    }

    lavGraf(ankomneArray) {
        const myChart = document.querySelector('#myChart')
        
        let ialt = this.totalAntalAnkomneITidsrum(ankomneArray, 10, 16)
        let xValues = ["10-11", "11-12", "12-13", "13-14", "14-15", "15-16"];
        let yValues = this.beregnProcentAnkomneITidsrum(ankomneArray, 10, 16, ialt)

        let barColors = ["green", "green", "green", "green", "green", "green"];
        this.lavNuvaerendeTimeRoed(barColors, 10, 16)

        if (typeof this.#graf != "undefined") {
            this.#graf.destroy()
        }

        this.#graf = new Chart(myChart, {
            type: "bar",
            data: {
                labels: xValues,
                datasets: [{
                    type: "line",
                    fill: false,
                    lineTension: 0,
                    backgroundColor: "rgba(0,0,255,1.0)",
                    borderColor: "rgba(0,0,255,0.1)",
                    data: [15,20,10,15,20,20]
                }, {
                    backgroundColor: barColors,
                    data: yValues
                }
                ]
            },
            options: {
                events: [],
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true,
                            min: 0,
                            max: 100
                        }
                    }]
                },
                legend: {display: false},
                title: {
                    display: true,
                    text: "Travlhed på given dato",
                    fontSize: 15
                }
            }
        });

    }

}

export default Graf;