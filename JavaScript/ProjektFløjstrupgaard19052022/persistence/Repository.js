import admin from "firebase-admin";
import {key} from "./AccessKey.js";
import fetch from "node-fetch";


class Repository {
    #db

    constructor() {
        admin.initializeApp({
            credential: admin.credential.cert(key)
        });

        this.#db = admin.firestore();
    }

    async createData(year, month, day, time) {
        const data = {
            year: year,
            month: month,
            day: day,
            tid: time,
            ipAdresse: (await fetch("https://api.ipify.org/?format=json").then(res => res.json()).then(res => res.ip)).toString(),
            oprettelse: admin.firestore.FieldValue.serverTimestamp()
        };
        const dataRef = this.#db.collection('datoer').doc();
        dataRef.set(data);
    }

    async createMessage(fornavn, firmaNavn, email, tlfNummer, emne, besked) {
        const data = {
            fornavn: fornavn,
            firmanavn: firmaNavn,
            email: email,
            tlfNummer: tlfNummer,
            emne: emne,
            besked: besked,
            ipAdresse: (await fetch("https://api.ipify.org/?format=json").then(res => res.json()).then(res => res.ip)).toString(),
            oprettelse: admin.firestore.FieldValue.serverTimestamp()
        };
        const dataRef = this.#db.collection('beskeder').doc();
        dataRef.set(data);
    }

    async getDataByDate(year, month, day) {
        let query = this.#db.collection('datoer')
        query = query.where('year', '==', year)
        query = query.where('month', '==', month)
        query = query.where('day', '==', day)
        const querySnapshot = await query.get()
        const docs = querySnapshot.docs;
        return docs
    }

    async getUser(username, password) {
        const users = await this.#db.collection('users')
            .where('username', '==', username)
            .get()
        const docs = users.docs
        if (docs.length > 1) {
            throw new Error('data inconsistency. Multiple users with username ${username}')
        }
        if (docs.length === 1) {
            const data = docs[0].data()
            if (data.username === username && data.password === password) {
                return true
            }
            else {
                throw new Error("Forkert login: prøv igen")
            }
        }
    }

    async getMessageArray () {
        const query = this.#db.collection('beskeder')
        const querySnapshot = await query.get()
        const docs = querySnapshot.docs
        return docs
    }

    async getDataByDate(year, month, day) {
        let query = this.#db.collection('datoer')
        query = query.where('year', '==', year)
        query = query.where('month', '==', month)
        query = query.where('day', '==', day)
        const querySnapshot = await query.get()
        const docs = querySnapshot.docs;
        return docs
    }
}

export default Repository;