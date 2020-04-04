const http = require('http')

const hostname = '127.0.0.1'
const port = 3000
const fs = require('fs')
const url = require('url')

const server = http.createServer((req, res) => {
  let ruta = url.parse(req.url, true)
  console.log(ruta)
  let filename = 'index.html'
  if (ruta.pathname == '/articulos') {
    filename = 'articulos.html'
  }
  fs.readFile(filename, (err, data) => {
    res.statusCode = 200
    res.setHeader('Content-Type', 'text/html')
    res.end(data)
  })
  // res.statusCode = 200;
  // res.setHeader('Content-Type', 'text/plain');
  // res.end('Hello World');
});

server.listen(port, hostname, () => {
  console.log(`Server running at http://${hostname}:${port}/`)
})