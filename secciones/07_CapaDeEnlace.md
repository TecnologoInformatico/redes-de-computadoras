# Capa de Enlace

Hosts y routers son **nodos**, los canales de comunicación que conectan nodos adyacentes a través de caminos de comunicación son enlaces.

El paquete de capa de enlace, denominado **frame** o **trama**, encapsula un datagrama.

La capa de enlace tiene la tarea de transferir datagramas desde un nodo a otro nodo **adyacente**, a través de un enlace.

Esta capa se implementa en todos los host en el adaptador de red **NIC** *(Network Interface Card)*, y allí se implementa también la capa física.

## Entramado (framing)

El comportamiento básico es en el lado emisor el encapulamiento del datagrama en un frame, agregando encabezado (header) con chequeo de error, control de flujo, entre otros parámetros posibles, y una cola (trailer) dependiendo del protocolo, lo que permite conocer el fin de la transmisión de una trama y en algunos casos es donde se establece el control de errores.

Al llegar al receptor se buscarán errores, se realizará un control del flujo y posiblemente alguna señalización. Se extrae el datagrama y se pasa a la capa superior.

## Detección de errores

En una transmisión de datos pueden aparecer errores causados por atenuación de señal o ruido en el medio. Dependiendo del enlace utilizado el protocolo deberá tener mayores o menores controles de detección de errores, en el caso de la fibra óptica la atenuación es baja y el ruido prácticamente nulo, mientras que en el espectro magnético ocurre lo contrario.

El receptor deberá detecta errores, el informar al emisor y/o descarta la trama dependerá del protocolo. La detección de errores no es 100% confiable, hay casos en los que el protocolo puede perder algunos errores. En algunos casos además de detectar errores se podrá recuperar de ellos, para esto se utiliza determinada redundancia **EDC** (*Error Detection Correction*), estos bits por lo general protegen determinados campos de cabecera, y mientras mayor sea el campo mejor es la detección y posible corrección, pero a su vez mayor sobrecarga de datos.

## Protocolos de acceso múltiple

La mayor complejidad dentro de los protocolos de la capa de enlace la tienen los protocolos de acceso multiple al medio, en los cuales existen dos o más transmisiones simultáneas, aquí se puede dar que un nodo reciba varias señales al mismo tiempo, o que se de la simultaneidad en tiempo y frecuencia de dos o más tramas en el mismo medio físico, lo que generará una **colisión**, y por lo tanto perdida de datos.

Así la tarea de los protocolos de acceso múltiple es la de determinar cómo los nodos comparten el canal y cuando cada uno puede transmitir, para lo que debe realizarse utilizando el mismo canal.

Una idealización de un protocolo de acceso múltiple sería:

- Cuando un nodo quiere transmitir, lo hará a una velocidad R
- Cuando M nodos quieren transmitir, cada uno enviará a una velocidad promedio de R/M
- Completamente descentralizado:
  - No hay un nodo especial para coordinar las transmisiones
  - No hay sincronización de relojes, slots
- Simple

## Servicios

Acceso al enlace:

- Acceso al canal si es un medio compartido
- Direcciones MAC, utilizadas en los encabezados de las tramas

Entrega confiable:

- Entre nodos adyacentes
- Principalmente en enlaces inalámbricos.

Control de flujo:

- Acuerdo entre los nodos emisor y receptor


Corrección de errores:

- El receptor identifica y corrige errores sin necesidad de retransmisión

Half-Duplex y Full-Duplex:

- Posibilidad de transmitir al mismo tiempo o no.