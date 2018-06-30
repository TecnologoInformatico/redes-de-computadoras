# Capa de Enlace

Hosts y routers son **nodos**, los canales de comunicación que conectan nodos adyacentes a través de caminos de comunicación son enlaces.

El paquete de capa de enlace, denominado **frame** o **trama**, encapsula un datagrama.

La capa de enlace tiene la tarea de transferir datagramas desde un nodo a otro nodo **adyacente**, a través de un enlace.

Esta capa se implementa en todos los host en el adaptador de red **NIC** *(Network Interface Card)*, y allí se implementa también la capa física.

## Servicios

- Entramado
- Acceso al enlace
  - Acceso al canal si es un medio compartido
  - Direcciones MAC, utilizadas en los encabezados de las tramas

- Entrega confiable:
  - Entre nodos adyacentes
  - Principalmente en enlaces inalámbricos.

- Control de flujo:
  - Acuerdo entre los nodos emisor y receptor

- Corrección de errores:
  - El receptor identifica y corrige errores sin necesidad de retransmisión
- Half-Duplex y Full-Duplex:
  - Posibilidad de transmitir al mismo tiempo o no.

## Entramado (framing)

El comportamiento básico es en el lado emisor el encapsulamiento del datagrama en un frame, agregando encabezado (header) con chequeo de error, control de flujo, entre otros parámetros posibles, y una cola (trailer) dependiendo del protocolo, lo que permite conocer el fin de la transmisión de una trama y en algunos casos es donde se establece el control de errores.

Al llegar al receptor se buscarán errores, se realizará un control del flujo y posiblemente alguna señalización. Se extrae el datagrama y se pasa a la capa superior.

## Errores, detección y corrección

En una transmisión de datos pueden aparecer errores causados por atenuación de señal o ruido en el medio. Dependiendo del enlace utilizado el protocolo deberá tener mayores o menores controles de detección de errores, en el caso de la fibra óptica la atenuación es baja y el ruido prácticamente nulo, mientras que en el espectro magnético ocurre lo contrario.

El receptor deberá detecta errores, el informar al emisor y/o descarta la trama dependerá del protocolo. La detección de errores no es 100% confiable, hay casos en los que el protocolo puede perder algunos errores. En algunos casos además de detectar errores se podrá recuperar de ellos, para esto se utiliza determinada redundancia **EDC** (*Error Detection Correction*), estos bits por lo general protegen determinados campos de cabecera, y mientras mayor sea el campo mejor es la detección y posible corrección, pero a su vez mayor sobrecarga de datos.

## Protocolos de acceso múltiple

La mayor complejidad dentro de los protocolos de acceso al medio, **medium access control** (*MAC*), de la capa de enlace la tienen los protocolos de acceso multiple al medio (*multiple access protocols*), en los cuales existen dos o más transmisiones simultáneas, aquí se puede dar que un nodo reciba varias señales al mismo tiempo, o que se de la simultaneidad en tiempo y frecuencia de dos o más tramas en el mismo medio físico, lo que generará una **colisión**, y por lo tanto perdida de datos.

Así la tarea de los protocolos de acceso múltiple es la de determinar cómo los nodos comparten el canal y cuando cada uno puede transmitir, lo que debe ser realizado utilizando el mismo canal.

Una idealización de un protocolo de acceso múltiple consistiría en que:

- Cuando un nodo quiere transmitir, lo hará a una velocidad R
- Cuando M nodos quieren transmitir, cada uno enviará a una velocidad promedio de R/M
- Completamente descentralizado:
  - No hay un nodo especial para coordinar las transmisiones
  - No hay sincronización de relojes, slots
- Que sea simple

### Tipos de protocolos MAC

Existen diferentes tipos de protocolos MAC, los cual podríamos agrupar en protocolos de, **particionado del canal, acceso aleatorio y toma de turnos**.

Los protocolos de **particionado** del canal se implementan en base a una estratégia estática, se divide el canal en pequeñas piezas, las cuales pueden ser ranuras de tiempo, rangos de frecuencias o determinado código y asignando le a un nodo determinada pieza de forma exclusiva. Este sistema suele ser eficiente y equitativo frente a altas cargas del canal, pero ineficiente en casos de baja demanda, donde se desperdicia ancho de banda.

Un protocolo de acceso aleatorio utiliza la máxima velocidad de un canal sin establecer una previa coordinación entre los nodos. El hecho de que transmitan varios nodos al mismo tiempo puede generar colisiones, para lo que algunos protocolos implementarán sistemas de detección de colisiones y mecanismos para recuperarse de estas.

Algunos ejemplos son el ALOHA, CSMA/CD y CSMA/CA.

#### CSMA

*Carrier Sense Multiple Access*, Acceso Múltiple por Detección. Este protocolo escucha antes de iniciar una transmisión, en caso de detectar que el canal se encuentra libre se emite, en caso contrario difiere la transmisión, se selecciona un tiempo aleatorio antes de volver a escuchar lo que se repite variando el tiempo de forma aleatoria.

En su variante **CSMA/CD**, (*Collision Detection*), se detectan las colisiones, y en ese caso las transmisiones son abortadas. Vale destacar que las colisiones generan que se pierdan datos, por lo tanto desperdicio del canal. Esto es relativamente fácil de implementar en LANs cableadas, como es el caso de la ethernet original, pero sumamente complejo y en ocasiones imposible en LANs inalámbricas, cómo es el caso de IEEE 802.11 (WiFi), aquí lo que se intentará es evitar las colisiones, es evitar las colisiones, **CSMA/CA** (*Collision Avoidance*).

#### Toma de turnos

En el caso de la toma de turnos se encuentran entre otros; El sistema de encuesta, en el que existe un nodo maestro y el resto dispositivos esclavos que esperan que el maestro les habilite a transmitir, no existen colisiones, pero si una sobrecarga por la coordinación, encuesta, habilitación y peticiones. Ej: Bluetooth.

Por otro lado existen protocolos de paso de testigo, en el que no existe ningún maestro, sino que los nodos se intercambian una trama especial que les permite tener el control del canal. Esa trama especial, token, se va pasando al siguiente nodo y así permitirle transmitir.

## Direcciones MAC

Las direcciones MAC cumplen un rol similar a las direcciones IP de la capa de red. Se tratan de números de 6bytes los cuales son grabados en ROM dentro del adaptador de red, en algunos casos configurable por software. Se utiliza para llevar una frame de una interfaz a otra dentro de la misma red, con lo que la comunicación no necesitará de un conmutador de capa de red, router. Las direcciones son administradas por la IEEE, se le otorga a los fabricantes porciones, 3 primeros bytes, permitiéndose administrar los restantes dentro de su dominio a gusto. La dirección MAC es plana, sin jerarquía a diferencia de IP, y portable.

### ARP

(*Address Resolution Protocol*), Protocolo de resolución de direcciones, que permite determinar la dirección MAC de una interfaz a partir de su dirección IP. Cada nodo IP contiene una tabla ARP en la cual mapea direcciones IP con direcciones MAC para los nodos conocidos dentro de la LAN, junto a un tiempo de vida.

Para conocer la dirección MAC de un dispositivo se envía una consulta ARP, ARP query, conteniendo la dirección IP del dispositivo en cuestión, con destino de difusión, *FF-FF-FF-FF-FF-FF*, de este modo todos los dispositivos reciben la consulta y el poseedor de la IP será quién responda.
