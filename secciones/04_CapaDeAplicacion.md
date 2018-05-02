# Capa de Aplicación

La capa de aplicación esta formada por programas que se ejecutan en sistemas terminales, hosts, conectados en red.

Una aplicación de red consta de parejas de procesos que se envían mensajes entre sí por medio de la red.

    Ejemplo: Un navegador Web Mozilla Firefox comunicándose con un servidor web Apache.

Por lo general esta interacción se realiza de modo en que un host realiza una petición y utiliza un servicio brindado por otro host, donde al que realiza la petición (inicia la comunicación), se le llamará cliente y al que brinda el servicio (espera la comunicación de un proceso cliente), servidor. En el ejemplo anterior el navegador funciona como programa cliente y el servidor Apache tal como su nombre lo indica, de servidor.

Los procesos envían y reciben mensajes a través de sockets, lo cual se puede pensar como una puerta de comunicación. El proceso que envía datos deja mensajes en el socket y confía en el servicio brindado por las capas inferiores para que el mensaje llegue al proceso destino.

Cada host puede ser identificado por una dirección IP, pero para identificar a los procesos, ya que pueden ser muchos funcionando en un mismo host, es necesario otro identificador. Para esto se utilizan números que se asocian a los procesos, los cuales se denominan puertos.

Para que los mensajes sean entendidos por el servidor que los recibe, y correctamente respondidos, debe existir un protocolo, donde se definirán tipos de mensajes, campos y parámetros que deben ser enviados, así como el modo de hacerlo.

Entre estos protocolos pueden ser de dominio público o propietarios. Entre los de dominio público se encuentran los definidos en los RFC (Request For Comments), HTTP, SMTP, FTP, SSH, etc.

## Arquitecturas

El desarrollador de una aplicación debe pensar el mejor modo en que los hosts se comunicarán y que rol cumplirán. Uno de los grandes enfoques que se pueden tener en cuenta son las arquitecturas, en las que en Internet predominan: cliente servidor y P2P.

### Cliente servidor

En la arquitectura cliente servidor, el servidor es un equipo de alta disponibilidad, que aguarda por solicitudes de los clientes en una dirección IP fija. Por su parte los clientes pueden estar activos de forma intermitente. Normalmente un sólo host no es suficiente para responder a la solicitud de todos los clientes, así mismo no serían posible brindar la misma calidad de servicio para todos los rincones geográficos, para esto se suele implementar una granja de servidores (cluster), para crear un servidor virtual de alta capacidad, y se realiza una replicación en distintos puntos para mejorar la velocidad de acceso y la disponibilidad del servicio.

Por su parte los clientes se encuentran activos de forma intermitente, no tienen necesariamente IP fija y utilizan variado hardware y sistemas operativos, así como en el caso de la Wold Wide Web, pueden utilizar distintas aplicaciones para entablar la comunicación. Los clientes no se comunican de forma directa entre sí, sino que siempre lo harán a través del servidor.

Se debe notar que un sistema de gran porte con este tipo de arquitectura puede llegar a ser sumamente costoso, tanto desde el hardware, el ancho de banda y el mantenimiento a realizar. Actualmente existen servicios de máquinas virtuales en la nube donde se puede escalar una aplicación con gran facilidad y reduciendo costos, lo cual suele denominarse cloud service (servicio de nube).

Ejemplos de servicios en la nube:

- [Amazon Web Services (AWS) - Cloud Computing Services](https://aws.amazon.com/es/what-is-aws/)
- [Microsoft Azure - Servicios en la nube](https://azure.microsoft.com/es-es/overview/what-is-azure/)
- [Google Cloud Platform](https://cloud.google.com/docs/?hl=es)

Servicios brindados con esta arquitectura son:

- Correo electrónico
- Páginas web
- Almacenamiento de archivos centralizado

### Peer to Peer (P2P)

Por su parte una red P2P estará conformada por host que funcionarán como cliente y servidor de forma alternativa conectándose directamente entre sí. Este sistema es de muy alta escalabilidad, creciendo su disponibilidad de forma natural a la vez que aumenta la demanda. Por otro lado puede ser muy difícil de administrar y en la mayoría de los casos será necesario contar con algún servidor que facilite la conexión entre los pares y la localización de recursos.

Algunos servicios que utilizan esta arquitectura son:

- bittorrent
- Skype
- bitcoin
- SETI@Home

## HTTP

En la década de 1990 surgió la aplicación World Wide Web que revolucionó el uso de Internet y lo convirtió en un recurso fundamental para el público general.

El protocolo principal es el HTTP en el que se definen las funciones de un programa cliente y uno servidor. El funcionamiento básico es el pedido al servidor de un recurso página web, el servidor procesa la petición y retorna el archivo, y finalmente el navegador interpreta la estructura y lo muestra. Una página web es un contenedor de objetos estructurada en HTML (HyperText Markup Language), la cual puede hacer referencia a archivos multimedia, scripts y otros archivos.

Cada objeto es identificable por una dirección URL (Uniform Resource Locator). Se utiliza el protocolo TCP de la capa de transporte para realizar los pedidos y enviar las respuestas y por defecto los servidores web escuchan mediante el puerto 80.

Los mensajes intercambiados se pueden clasificar como pedidos (request) y respuestas (response).

Ejemplo de request:

```HTTP
GET /tecnoinf HTTP 1.1
Host: www.fing.edu.uy
User-agent: Mozilla/4.0
Connection: close
Accept-language: es
```

Ejemplo de response:

```HTTP
HTTP/1.1 304 Not Modified
Connection: Keep-Alive
Date: Thu, 15 Mar 2018 00:15:42 GMT
ETag: "32d-4de09aea58e40"
Keep-Alive: timeout=5, max=100
Server: Apache
```

Al momento de la realización de las peticiones se utiliza un conjunto de métodos para indicar la acción que se desea realizar para un recurso determinado. Aunque estos también pueden ser sustantivos, estos métodos de solicitud a veces son llamados HTTP verbs.

- GET
- POST
- HEAD
- PUT
- DELETE

El método GET es utilizado para solicitar datos, las peticiones realizadas a través de este método pueden ser cacheadas y almacenadas en el historial del navegador. No es utilizado para el envío de datos sensibles, como credenciales, por el hecho de que los parámetros viajan en la ruta, por otro lado eso mismo lo hace muy útil para ser utilizado en filtros.

Por su parte el método POST, es utilizado para el registro de datos, la información enviada viaja en el cuerpo del mensaje y no tiene limites de tamaño. en este caso las peticiones nunca son cacheadas.

Las peticiones son respondidas por parte del servidor junto a un [código de estado](https://developer.mozilla.org/es/docs/Web/HTTP/Status "Códigos de estado de respuesta HTTP") que indica si el procedimiento fue exitoso o no. Las respuestas se agrupan en cinco clases: respuestas informativas, respuestas satisfactorias, redirecciones, errores de los clientes y errores de los servidores.

### Cookies

Las cookies son datos que el servidor envía junto con la respuesta al navegador, estos datos se enviarán junto con las siguientes peticiones, lo que permite realizar un seguimiento de estado sobre el protocolo HTTP el cual no contiene estado. Por lo general es utilizado para mantener una sesión de un usuario (Autenticación - logged-in), así como para mantener información de acciones realizadas.

Es posible asignarles un tiempo máximo de vida, que expiren al cerrar el navegador o realizar un borrado manual (cerrar sesión) por parte del servidor.

Así mismo cuenta en algunos casos con mala reputación por el uso que se le puede dar de realizar seguimiento en los hábitos de navegación con fines de recopilación de datos para diversos usos. En algunos casos es posible utilizar otros mecanismos para guardar información en el cliente, como el uso de [Web Storage](https://developer.mozilla.org/en-US/docs/Web/API/Web_Storage_API "Web Storage API").

### Caché web

El caché web almacena documentos web para reducir el ancho de banda consumido, la carga de los servidores y el retardo en la descarga.

- privados
  - Funcionan para un único usuario
- compartidos
  - Utilizados por proveedores de servicios e instituciones para ahorrar ancho de banda.
- pasarela
  - Funcionan a cargo del servidor original, pueden implementar una CDN (Content Delivery Network)

Lo que hace viable el uso del un web cache es la existencia de un GET condicional, lo que se logra a través de un campo en la cabecera `if-modified-since` que en caso de que no se haya modificado el objeto desde la fecha pasada se retorna únicamente la cabecera con código 304 cargando el documento previamente almacenado en caché, de lo contrario se envía todo el documento.

Esto puede ser muy útil para ahorrar ancho de banda para proveedores de servicios, tales como instituciones educativas o empresas, por lo que puede ser que cuenten con un servidor proxy que satisfaga las peticiones del cliente sin involucrar el servidor original.

Se puede configurar el navegador para que acceda mediante chaché. El navegador envía todos los pedidos al  caché, si el objeto se encuentra se devuelve del caché Si no se encuentra se obtiene del cliente original, se guarda en el server proxy y se devuelve al usuario.

Otro ejemplo de implementación de este tipo es el caso de los CDN (Content Delivery Network), en el que se cuenta con una copia de los datos de forma distribuida en distintos puntos de la red.

Un cliente accede a los datos desde un nodo cercano, reduciendo así el tiempo de respuesta y la pérdida de información, y reduciendo la carga de los servidores.