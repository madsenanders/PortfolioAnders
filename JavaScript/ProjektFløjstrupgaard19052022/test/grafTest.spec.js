import Graf from "../public/script/Graf.js";

const graf = new Graf()

describe("test af Graf", () => {
    test("beregning af total ankomne i tidsrum", () => {
        //prepare
        const aabent = 10
        const lukket = 16
        const ankomneArray = Array(24).fill(0); // antal folk i hver time
        ankomneArray[10] = 3 // fra 10-11 kommer der 3
        ankomneArray[11] = 5 // fra 11-12 kommer der 5
        ankomneArray[12] = 5 // fra 12-13 kommer der 5
        ankomneArray[13] = 8 // fra 13-14 kommer der 8
        ankomneArray[14] = 4 // fra 14-15 kommer der 4
        ankomneArray[15] = 2 // fra 15-16 kommer der 2
        
        ankomneArray[8] = 10 // fra 8-9 skal ikke tælles med da det ikke er muligt
                             // at komme når der er lukket (åbent fra 10-16)
        //act
        let result = graf.totalAntalAnkomneITidsrum(ankomneArray, aabent, lukket)
        // Assert
        expect(result).toBe(27) // husk at andre tidsrum end 10-16 ikke tæller med
    })
    test("lav procentarray ud fra ankomneArray i tidsrum", () => {
        //prepare
        const aabent = 10
        const lukket = 16
        const ankomneArray = Array(24).fill(0); // antal folk i hver time
        ankomneArray[10] = 1 // fra 10-11 kommer der 1
        ankomneArray[11] = 2 // fra 11-12 kommer der 2
        ankomneArray[12] = 1 // fra 12-13 kommer der 1
        ankomneArray[13] = 4 // fra 13-14 kommer der 4
        ankomneArray[14] = 1 // fra 14-15 kommer der 1
        ankomneArray[15] = 1 // fra 15-16 kommer der 1
        const ialt = 10

        ankomneArray[8] = 10 // fra 8-9 skal ikke tælles med da det ikke er muligt
                             // at komme når der er lukket (åbent fra 10-16)
        //act
        let result = graf.beregnProcentAnkomneITidsrum(ankomneArray, aabent, lukket, ialt) 
        // Assert
        expect(result).toEqual([10, 20, 10, 40, 10, 10]) // husk at andre tidsrum end 10-16 ikke tæller med
    })
    // lavNuvaerendeTimeRoed(barColors, starttid, sluttid)
    test("lav nuværende time rød", () => {
        //prepare
        const fraTime = 0
        const tilTime = 24
        let barColors = Array(24).fill("green"); // en grøn for hver time i et døgn i dette eksempel
        const nuvaerendeTime = new Date().getHours() // timen på dit ur ekslusiv minutter
        //act
        graf.lavNuvaerendeTimeRoed(barColors, fraTime, tilTime)
        // Assert
        for (let i = 0; i < 24; i++) {
            if (i === nuvaerendeTime) {
                expect(barColors[i]).toBe("red") // den nuværende time skal være red
            } else {
                expect(barColors[i]).toBe("green") // alle andre timer grønne
            }
        }
    })
})
