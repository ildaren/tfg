const express = require('express')
const app = express()
const fetch = require("node-fetch")
const cors = require('cors')
const morgan = require("morgan");
const bodyParser = require('body-parser');

var url = require('url');

app.use(cors());
app.use(bodyParser.json());

//Acceso a los datos relacionados con las filiales

//Acceso a los coches disponibles disponibles en una filial y unas fechas determinadas
app.get("/filial/*/oferta*", async (req, res) => {
    console.log("Acceso a los coches filtrados en la filial id=" + req.url.split("/")[2] + " por unas fechas dadas.")
    console.log()
    console.log("---------------------------------------------------------------")
    console.log()
    const response = await fetch("http://localhost:8080/apirest/api" + req.url)
    res.status(response.status).json(await response.json())
})

//Acceso a los datos de un coche en concreto dado su id y el de la filial a la que pertenece
app.get("/filial/*/oferta/*", async (req, res) => {
    console.log("Acceso a los datos del coche id=" + req.url.split("/")[4] + " que se encuentra en la filial id=" + req.url.split("/")[2] + ".")
    console.log()
    console.log("---------------------------------------------------------------")
    console.log()
    const response = await fetch("http://localhost:8080/apirest/api" + req.url)
    res.status(response.status).json(await response.json())
})

//Acceso a los datos de una filial en concreto
app.get("/filial/*", async (req, res) => {
    console.log("Acceso a los datos de la filial " + req.url.split("/")[2] + ".")
    console.log()
    console.log("---------------------------------------------------------------")
    console.log()
    const response = await fetch("http://localhost:8080/apirest/api" + req.url)
    res.status(response.status).json(await response.json())
})

//Acceso a todas las filiales registradas
app.get("/filial", async (req, res) => {
    console.log("Acceso a los datos de todas las filiales registradas.")
    console.log()
    console.log("---------------------------------------------------------------")
    console.log()
    const response = await fetch("http://localhost:8080/apirest/api" + req.url)
    res.status(response.status).json(await response.json())
})




//Acceso a los datos relacionados con los usuarios

//Acceso a los datos de un usuario en concreto
app.get("/usuario/*", async (req, res) => {
    console.log("Acceso a los datos de un usuario id=" + req.url.split("/")[2] + ".")
    console.log()
    console.log("---------------------------------------------------------------")
    console.log()
    const response = await fetch("http://localhost:8080/apirest/api" + req.url)
    res.status(response.status).json(await response.json())
})

//Comprobación de que los datos de un usuario son correctos
app.get("/usuario*", async (req, res) => {
    console.log("Acceso a los datos de un usuario para comprobar su inicio de sesion.")
    console.log()
    console.log("---------------------------------------------------------------")
    console.log()
    const response = await fetch("http://localhost:8080/apirest/api" + req.url)
    res.status(response.status).json(await response.json())
})

//Registro de un nuevo usuario
app.post("/usuario", async (req, res) => {
    console.log("Registro de un nuevo usuario.")
    console.log("Datos:")
    console.log(req.body)
    console.log()
    console.log("---------------------------------------------------------------")
    console.log()
    try {
        const response = await fetch("http://localhost:8080/apirest/api" + req.url, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(req.body),
        })
        res.status(response.status).json(await response.json())
    } catch (error) {
        res.status(500).json(error)
    }
})

//Acceso a los datos de los pagos de un usuario
app.get("/usuario/*/pago", async (req, res) => {
    console.log("Acceso a los pagos de un usuario id=" + req.url.split("/")[2] + ".")
    console.log()
    console.log("---------------------------------------------------------------")
    console.log()
    const response = await fetch("http://localhost:8080/apirest/api" + req.url)
    res.status(response.status).json(await response.json())
})

//Acceso a los datos de un pago de un usuario
app.get("/usuario/*/pago/*", async (req, res) => {
    console.log("Acceso al pago id=" + req.url.split("/")[4] + " de un usuario id=" + req.url.split("/")[2] + ".")
    console.log()
    console.log("---------------------------------------------------------------")
    console.log()
    const response = await fetch("http://localhost:8080/apirest/api" + req.url)
    res.status(response.status).json(await response.json())
})

//Acceso a los datos de una reserva de un usuario
app.get("/usuario/*/reserva/*", async (req, res) => {
    console.log("Acceso a la reserva id=" + req.url.split("/")[4] + " de un usuario id=" + req.url.split("/")[2] + ".")
    console.log()
    console.log("---------------------------------------------------------------")
    console.log()
    const response = await fetch("http://localhost:8080/apirest/api" + req.url)
    res.status(response.status).json(await response.json())
})

//Acceso a los datos de las reservas hechas por un usuario
app.get("/usuario/*/reserva", async (req, res) => {
    console.log("Acceso a las reservas de un usuario id=" + req.url.split("/")[2] + ".")
    console.log()
    console.log("---------------------------------------------------------------")
    console.log()
    const response = await fetch("http://localhost:8080/apirest/api" + req.url)
    res.status(response.status).json(await response.json())
})

//Borrar una reserva de un usuario
app.delete("/usuario/*/reserva/*", async (req, res) => {
    console.log("Eliminacion de una reserva id=" + req.url.split("/")[4] + " de un usuario id=" + req.url.split("/")[2] + ".")
    console.log()
    console.log("---------------------------------------------------------------")
    console.log()
    try {
        const response = await fetch("http://localhost:8080/apirest/api" + req.url, {
            method: "DELETE",
        })
        res.status(response.status).json(await response.json())
    } catch (error) {
        res.status(500).json(error)
    }
})

//Actualizar una reserva de un usuario
app.put("/usuario/*/reserva/*", async (req, res) => {
    console.log("Actualizacion de la reserva id=" + req.url.split("/")[4] + "perteneciente al usuario id=" + req.url.split("/")[2] + ".")
    console.log("Datos:")
    console.log(req.body)
    console.log()
    console.log("---------------------------------------------------------------")
    console.log()
    try {
        const response = await fetch("http://localhost:8080/apirest/api" + req.url, {
            method: "PUT",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(req.body),
        })
        res.status(response.status).json(await response.json())
    } catch (error) {
        res.status(500).json(error)
    }
})

//Añadir una reserva nueva de un usuario
app.post("/usuario/*/reserva", async (req, res) => {
    console.log("Registro de una nueva reserva de un usuario id=" + req.url.split("/")[2] + ".")
    console.log("Datos:")
    console.log(req.body)
    console.log()
    console.log("---------------------------------------------------------------")
    console.log()
    try {
        const response = await fetch("http://localhost:8080/apirest/api" + req.url, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(req.body),
        })
        res.status(response.status).json(await response.json())
    } catch (error) {
        res.status(500).json(error)
    }
})

app.listen(3000, () => {
    console.log("Listening on port 3000")
})