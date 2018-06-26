
// Usar npm install body-parser --save
// para poder leer respuestas en el body via POST.

var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var port = process.env.PORT || 8080;

app.use(bodyParser.json())

// rutas
app.post('/api/articulos/nuevo', function(req, res) {
    console.log('req.query', req.query)
    console.log('req.params', req.params)
    console.log('req.body', req.body)
        
    var articuloId = req.body.id;
    var titulo = req.body.titulo;

    res.send('Articulo: ' + titulo + ' id: ' + articuloId);
});

app.get('/api/articulos', function(req, res) {

    console.log('req.query', req.query)
    console.log('req.params', req.params)
    console.log('req.body', req.body)
        
    var articuloId = req.query.id;
    var titulo = req.query.titulo;

    res.send('Articulo: ' + titulo + ' id: ' + articuloId);
});


// Inicia el servidor
app.listen(port);
console.log('El servidor ha iniciado en http://localhost:' + port);