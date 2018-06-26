
console.log('Hello World!')



var http = require('http');

http.createServer(function (req, res) {
    res.writeHead(200, {'Content-Type': 'text/plain'});
    
    var texto = require('./funciones')
    console.log(texto())
    var textoPrueba = 'Hello World! ' + texto()
    console.log(textoPrueba)
    res.end(textoPrueba);
}).listen(8080); 


