import express from 'express'
import session from 'express-session'
class Server {
    #app
    #repository

    constructor(repository) {
        this.#app = express()

        this.#repository = repository
        
       
        this.#app.use(express.json())
        this.#app.use(express.urlencoded({extended: true}))
        this.#app.use(express.static('public'))

        this.#app.set('view engine', "pug")
        this.#app.set('views', "src/server/views")

        /* Login */
        this.#app.use(session({
            secret: "thisismysecrctekeyfhrgfgrfrty84fwir767",
            saveUninitialized: false,
            resave: false
        }));

        this.#app.get('/loginSide', (req, resp) => {
            resp.render('loginSide')
        })

        this.#app.get("/login", (req, resp) => {
            resp.render('login', {loggedIn: req.session?.loggedIn})
        })

        this.#app.post("/login", async (reg, resp) => {
            try {
                const username = reg.body.username
                const password = reg.body.password
                if (await this.#repository.getUser(username, password)) {
                    reg.session.loggedIn = true
                    resp.sendStatus(200)
                } else {
                    resp.status(400)
                    resp.send({error: "Credentials not verified"})
                }
            } catch (e) {
                resp.status(400)
                resp.send({error: e.message})
            }
        })

        this.#app.get("/logout", (req, resp) => {
            if (req.session) {
                req.session.destroy((error) => {
                    if (req.session) {
                        req.session.loggedIn = false
                    }
                })
                resp.render('front')
            }
        })
        /* Login slut */

        this.#app.post("/bekraeftDato", async (req, resp) => {
            try {
                const year = req.body.year
                const month = req.body.month
                const day = req.body.day
                const time = req.body.time

                await repository.createData(year, month, day, time)

                resp.sendStatus(200)

            } catch (e) {
                resp.status(400)
                resp.send({error: e.message})
            }
        })

        this.#app.get("/indbakke", async (req, resp) => {
            try {
                const beskeder = await this.#repository.getMessageArray()
                const indbakke = Array(beskeder.length)
                for (let element of beskeder) {
                    let index = beskeder.indexOf(element)
                    let besked = element.data()
                    indbakke[index] = besked
                }
                if (req.session.loggedIn) {
                    resp.send(indbakke)
                }
            } catch (e) {
                resp.status(400)
                resp.send({error: e.message})
            }
        })

        this.#app.get("/getAnkomne", async (req, resp) => {
            try {
                let year = req.query.year
                let month = req.query.month
                let day = req.query.day
                let ankomne = await repository.getDataByDate(year, month, day)
                const tidsArray = Array(24).fill(0);
                for (let element of ankomne) {
                    let time = element.data().tid.split(":")[0]
                    if (time.charAt(0) === "0") {
                        time = time.charAt(1)
                    }
                    tidsArray[time]++
                }
                resp.send(tidsArray)
            } catch (e) {
                resp.status(400)
                resp.send({error: e.message})
            }
        })

        this.#app.post("/kontakt", async (req, resp) => {
            const fornavn = req.body.fornavn
            const firmaNavn = req.body.Firmanavn
            const email = req.body.Email
            const tlfNummer = req.body.tlfNummer
            const emne = req.body.emne
            const besked = req.body.Besked

            await repository.createMessage(fornavn, firmaNavn, email, tlfNummer, emne, besked)

            resp.render('kontaktFane')
        })

        this.#app.get("/", (req, resp) => {
            if (req.session.loggedIn) {
                resp.render("beskederListe")
            }
            resp.render('front')
        })

        this.#app.get("/historie", (req, resp) => {
            resp.render('historieFane')
        })

        this.#app.get("/juletraeer", (req, resp) => {
            resp.render('juletraeerFane')
        })

        this.#app.get("/udlejning", (req, resp) => {
            resp.render('udlejningFane')
        })

        this.#app.get("/consultancy", (req, resp) => {
            resp.render('consultancyFane')
        })

        this.#app.get("/selvskovning", (req, resp) => {
            resp.render('selvskovningFane')
        })

        this.#app.get("/seldrupgaard", (req, resp) => {
            resp.render('seldrupFane')
        })

        this.#app.get("/aabningstider", (req, resp) => {
            resp.render('aabningstiderFane')
        })

        this.#app.get("/kontakt", (req, resp) => {
            resp.render('kontaktFane')
        })

        this.#app.get("/find", (req, resp) => {
            resp.render('findFane')
        })


        this.#app.get("/beskeder", (req, resp) => {
            if (req.session.loggedIn) {
                resp.render('beskederListe')
            }
        })

        this.#app.get("/beskederTest", (req, resp) => {
            resp.render('beskederListe')
        })

    }

    startServer(port) {
        this.#app.listen(port, () => {
            console.log(`Listening on port ${port}`)
        })
    }
}

export default Server
