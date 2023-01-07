import Server from "./server/server.js";
import Repository from "../../produkt/persistence/Repository.js";

const PORT = process.env.PORT || 6969
const repository = new Repository()
const server = new Server(repository)
server.startServer(PORT)
