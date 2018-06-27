# Capa de Red

## Introducción

La capa de red, implementa el **servicio de comunicación host a host**.
A diferencia de las capas superiores las cuales se encuentran únicamente en los sistemas terminales, la capa de red se encuentra también en el núcleo de la red.

El host emisor encapsula segmentos en **datagramas**, los routers que se encuentran en la ruta entre ambos hosts examinan los datagramas que reciben.
En el host destinatario, la capa de red recibe de su router más cercano el datagrama y le entrega el segmento que contiene a la capa de transporte.

Entre las principales tareas:

- Se debe comunicar con la capa de transporte
- Descubrir la topología de la red
- Encapsular los datos de la capa de transporte dentro de unidades de datos de la capa de red (datagramas)
- Manejar la conectividad y el ruteo entre hosts y redes
- Se debe comunicar con la capa de enlace

## Redes de circuitos virtuales y de datagramas

Las arquitecturas principales de esta capa son **Las redes de circuitos virtuales** y las **redes de datagramas**. Las redes de circuitos virtuales proveen un servicio de conexión de capa de red, se brinda un camino desde un origen hacia un determinado destino, en el cual los recursos son reservados para garantizar determinado nivel de servicio. Por su parte las redes de datagramas no brindan un servicio de conexión, y los paquetes son reenviados según la dirección de destino, pudiendo atravesar algunos paquetes un camino mientras que otros podrían tomar otra ruta.

## Routers

Un router es un equipo especializado conectado a más de una red. Ejecuta un software que le permito mover datos desde una red a otra. Entre sus funciones principales se encuentran, restringir broadcast, servir de enlace entre distintas redes (default gateway), y estimar la mejor ruta implementando algoritmos de enrutamiento. Así basándose en estos algoritmos de enrutamiento deben poder realizar un reenvío de los datos que reciben.

Los puertos de entrada de un router son el punto de recepción a nivel de bit, y allí se implementa la capa física y la capa de enlace. Una vez recibido un datagrama se necesita utilizar una tabla de reenvío interno que puede estar en la memoria del puerto.

En caso de recibir paquetes más rápido de lo que es posible procesarlos se encolarán en buffers hasta que puedan ser emitidos. La tasa de conmutación es la capacidad de transferir paquetes de los puertos de entrada a los de salida, es deseable que la tasa de conmutación sea igual a la tasa de entrada por enlaces.

De recibirse más paquetes de los que se emiten se procederá a almacenarlos en buffers, lo que generará retardos por encolamiento y posiblemente perdidas de paquetes en caso de producirse un desbordamiento (overflow) en el buffer.

## IP

IP es parte de un conjunto de protocolos de comunicación que proveen identificación global única.

la primer versión implementada en producción, en ARPANET, es **IPv4** y es la que funciona en la mayor parte de los sistemas al día de hoy. Utiliza direcciones de 32 bits (232 = 4.294.967.296) de las cuales muchas son reservadas a propósitos específicos.

- redes LAN
- broadcast
- auto referencia

### IP v6

La gran cantidad de dispositivos conectados a Internet han agotado las reservas de direcciones provistas por la IANA (Internet Assigned Numbers Authority) por lo que se ha impulsado la implementación de su remplazo IP v6. La nueva versión utiliza direcciones de 128 bits, cabecera de largo fijo de 40 bytes, no permite fragmentación y se eliminó el checksum así como las opciones dentro del cabezal, logrando así además de ampliar el número posible de direcciones de forma astronómica, mejorar el rendimiento del protocolo.

Al ser imposible cambiar el protocolo de un momento a otro ya que aún existen muchos equipos incompatibles, se utilizan técnicas de tunelización, en donde los datagramas IPv6 viajan dentro de la carga útil de datagramas IPv6 en los trechos donde no es posible utilizarlo.

### Datagramas

- **Número de versión:** 4 bits donde se especifica la versión, así el router sabrá como identificar el resto del datagrama.
- **Tamaño de la cabecera:** Se puede contener un número variable de opciones por lo que este campo especifica en que posición comienzan los datos. (por lo general la cabecera tiene  un tamaño de 20 bytes.
- **TOS (Tipo de servicio):**

### Direccionamiento

Generalmente un host tiene un único enlace hacia la red, cuando IP desea comunicarse lo hace a través de este enlace. El límite entre el host y el enlace físico se denomina interfaz.

Un router tendrá 2 o más enlaces, ya que su función así lo requiere. Cada interfaz de host y de router debe tener su propia dirección IP. La dirección IP se asocia a una interfaz.

Una dirección IP puede ser obtenida de forma manual, por parte de un administrador, o de forma dinámica, mediante el protocolo **DHCP** (Dynamic Host Configuration Protocol).

- El host manda un mensaje de difusión (broadcast) **DHCPDISCOVER**
- El servidor DHCP responde con mensaje de **DHCPOFFER**
- El host solicita dirección IP mediante un mensaje **DHCP request**
- El servidor DHCP envía la dirección de regreso en un mensaje **DHCP ACK**

Otra información útil brindada por el servidor DHCP será:

- Máscara de subred
- Dirección del gateway predeterminado
- dirección de servidor DNS local

Además es posible realizar una configuración de modo que un host siempre reciba la misma IP, o una dirección IP temporal.

El proveedor de internet no será el encargado de brindar este servicio a una LAN, sino, que brindará una IP la cual será utilizada en conjunto por los host de la subred al mundo exterior. Para lograrlo se utiliza una tabla **NAT** *(Network Address Translation)* en la que se realiza una traducción entre IP origen y número de puerto con un correspondiente NAT.

Para los datagramas Salientes:
Se reemplaza la IP origen y el número de puerto de cada datagrama por la dirección IP NAT y un nuevo número de puerto. Los clientes y servidores remotos responderán con la dirección destino NAT y el número de puerto determinado.

Para los datagramas entrantes:
Se debe reemplazar la dirección NAT y el puerto ahí establecido por el original, guardado en la tabla.

De este modo todos los datagramas que dejan la red local tienen la misma dirección, con diferente número de puerto, mientras que los datagramas que circulan dentro de la red se comportan de forma "normal". Así es posible modificar las direcciones de los dispositivos en la red interna sin notificar al mundo exterior.

Los números de puerto son de 16 bits, por lo que se soportan más de 60.000 conexiones simultaneas con una misma dirección IP. Para muchos resulta una falta al propósito de los puertos, pero por otro lado es una solución momentánea a la limitación de direcciones de IPv4, lo que sería solucionado con la implementación de la versión 6.

#### Subred

Las direcciones IP se dividen de modo en que con una parte se representa a la **subred** (bits más altos), mientras que el resto determinan el host, (bits más bajos). Una sub red puede alcanzarse físicamente sin la intervención de un router, y los dispositivos conectados a ella tienen la misma sección de subred en sus direcciones IP.

La **máscara de subred** se utiliza para delimitar el ámbito de una red y así un host saber si debe enviar paquetes dentro de la red o fuera.

Indica que parte de la IP:

- hace referencia a la red
- y que parte hace referencia al host.

Considerando un rango de direcciones desde 223.0.0.0 hasta 223.255.255.255 siendo parte todas ellas de la misma red se podría expresar:

    255.0.0.0
    223.0.0.0/8
    11111111 00000000 00000000 00000000

### ICMP

Protocolo de Mensajes de Control de Internet, utilizado principalmente para la generación de mensajes de error, o para mensajes de control de infraestructura tales como las solicitudes y respuestas de eco (ping).

Los mensajes se envían como carga útil de IP ICMP se especifica como el protocolo de la capa superior. Los mensajes cuentan con un campo tipo y un campo código y contienen la cabecera y los primeros 8bytes del paquete que produjo el error.

## Algoritmos de enrutamiento

Los algoritmos de enrutamiento son utilizados para determinar las tablas de reenvío de los routers y de ellos depende poder encontrar buenas rutas desde el emisor hasta el receptor a través de la red de routers.

Estos algoritmos se pueden agrupar entre dos tipos, algoritmos de enrutamiento global, en los que se utiliza el conocimiento global y completo de la red entre los que está el algoritmo **LS** (*Link Status*), y algoritmos descentralizados, donde el costo se calcula de forma iterativa y distribuida, como es el caso de **DV** (*Distance Vector*).

### Link Status

En el caso de este algoritmo la topología de la red y los costos de todos los enlaces son conocidos, cada nodo difunde paquete de estado de enlace conteniendo identidades y costes de sus enlaces conectados.

Algoritmo de Dijkstra

- Iterativo: Una iteración por cada nodo en la red
- Obtiene el costo mínimo desde un nodo a los demás

Notación:

- **c(x, y):** costo del enlace. ∞ si los nodos no son vecinos.
- **D(v):** mínimo valor calculado hasta el momento desde el origen al nodo v.
- **p(v):** Nodo predecesor en el camino del origen a v.
- **N’:** Conjunto de nodos cuyo costo mínimo es conocido hasta el momento.

Implementación:

Inicialización:

N’ =  {u}
Para cada nodo v

    Si v es vecino de u
        entonces D(v) = c(u,v)
    Sino D(v) = ∞

Loop

    Encontrar w que no esté en N’ tal que D(w) es mínimo
    Agregar w a N’
    actualizar D(v) para cada v adyacente a w y que no esté en N’ tal que:
    D(v) = min( D(v), D(w) + c(w,v) )

Hasta que todos los nodos estén en N’

### Distance Vector

El algoritmo por vector de distancias es distribuido, de tal modo que cada nodo recibe información de sus vecinos, realiza un cálculo y luego distribuye el resultado a sus vecinos. Esto se repite  hasta que no hay más información que intercambiar.

No es necesario que todos los nodos operen de forma sincronizado, por lo que se dice que es asíncrono.

La ecuación que se caracteríza con este sistema es la de Bellman-Ford.

dx(y) := costo del camino de menor costo entre “x” e “y”

    dx(y) = min { c(x,v) + dv(y) }

Cada iteración local es causada por un cambio en el costo local de un enlace, o un mensaje de actualización de V.D. de un vecino. Así cada nodo notifica cuando se modifica su V.D. y los vecinos notifican a sus propios vecinos en caso de ser necesario. Los nodos esperan cambios en los enlaces o mensajes de sus vecinos, en ese caso se recalculan las estimaciones y si algún vector de distancias cambia, se realiza la notificación.

### Enrutamiento jerárquico

En la práctica los algoritmos de enrutamiento se administran de forma jerárquica, de modo que los administradores de red puedan decidir que algoritmos utilizar, además de resultar inviable aplicar un algoritmo de forma global, calcular las rutas a través de la totalidad de Internet sería imposible, sólo la sobrecarga de trafico para difundir las actualizaciones LS de los routers abarcaría gran parte del ancho de banda, además del retardo de propagación de los datos generaría inconsistencias.

Para resolver estos problemas los routers son organizados en sistemas autónomos, los cuales contaran con un control administrativo independiente, utilizando determinado protocolo de enrutamiento interno (*protocolo Intra-AS*).

Entre los protocolos Intra-AS destacan:

#### RIP

(*Routing Information Protocol*)

- Costos en número de hops (máximo 15)
- Algoritmo DV
- Mensajes sobre UDP

#### OSPF

(*Open shortest path first*)

- Algoritmo Link State
- Se utiliza un inundado de paquetes
- Mensajes sobre IP

#### BGP

Por su parte para el enrutamiento Inter-AS en Internet se utiliza un protocolo común denominado **Border Gateway Protocol** *(BGP)*. Este protocolo debe proveer buenas rutas hacia otras redes basado en la topología de los AS's y la política utilizada. Además permite a una subred comunicar de su existencia al resto de Internet.

Los algoritmos RIP y OSPF mantienen su principal objetivo en la eficiencia y velocidad de las rutas provistas, el BGP agrega un control de trafico dependiendo de determinadas políticas que no son tan importantes en la configuración interna de un mismo AS.