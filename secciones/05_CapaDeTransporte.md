# Capa de Transporte

La capa de transporte cumple un rol fundamental en la comunicación entre procesos en hosts separados en la red.

La capa de red, sobre la que se encuentra, cumple el rol de proporcionar una comunicación lógica entre hosts, mientras que la de transporte lo hará entre procesos de distintos hosts. Los protocolos de transporte se ejecutan en los sistemas terminales.

En el emisor se generarán segmentos a partir de los mensajes de la aplicación y los pasa a la capa de red. El receptor a partir de los segmentos recibidos, se encargará de proporcionar mensajes a la capa de aplicación al proceso que corresponda.

Los protocolos disponibles desde la capa de transporte a la capa de aplicación en Internet son:

## TCP (Transmission Control Protocol)

- Confiable, orientado a la conexión, entrega en orden.
- control de congestión
- control de flujo
- establecimiento de la conexión

## UDP (User Datagram Protocol)

- No confiable, entrega no ordenada
- No aporta mucho más de lo que ya hace el protocolo IP de la capa de red.

## Servicios no disponibles

- Garantías de retardo
- Garantías de ancho de banda

## Multiplexación y Demultiplexación

Pueden existir muchas aplicaciones corriendo en simultaneo en un mismo host. Ej: HTTP, telnet, FTP, SMTP. Cuando la capa de transporte recibe datos procedentes de la capa de red, tiene que dirigir los datos a el proceso correcto.

Un proceso puede tener uno o más de un socket La capa de transporte no entrega los datos directamente al proceso, sino que los “deposita” en un socket Los sockets tienen un número identificador único. Esta tarea es la que se denomina Desmultiplexación.

En el host emisor la capa debe reunir los fragmentos de datos
desde los sockets y encapsular cada fragmento de datos con determinada información de cabecera. La información de la cabecera (header) es lo que luego se utilizará en el proceso de desmultiplexación. Una vez creado el segmento es pasado a la capa de red.

El formato del identificador varía entre sockets UDP y TCP.

### Sin conexión

Para que un host pueda ser identificado desde un host remoto basta la tupla dirección IP, número de puerto puerto. Así un proceso en el host A, con el puerto UDP 28340, se podría comunicar con el host B al puerto 9876.

La capa de transporte del host A crea un segmento incluyendo los datos
de la aplicación, puerto origen y puerto destino. Se pasa el segmento a la capa de red la cual lo encapsula en un datagrama, haciendo el máximo esfuerzo por entregar el segmento al host receptor. En caso de llegar la capa de transporte del host B examina el número de puerto y entrega el segmento al socket identificado con el puerto.

### Orientada a la conexión

El Socket TCP se identifica por 4 elementos.

- Dirección IP origen
- Número de puerto origen
- Dirección IP destino
- Número de puerto destino

El host destino utiliza los 4 valores para dirigir el segmento al socket apropiado, el host servidor debe soportar varios sockets TCP simultáneos, en este caso se creará un nuevo socket para cada cliente que se conecta, pero las conexiones siempre serán dirigidas al mismo número de puerto.