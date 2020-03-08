# Redes de Computadoras

Notas y conceptos sobre redes de computadoras.

## TEMAS

1. [Introducción Redes e Internet](#[Introducción-Redes-e-Internet](secciones/01_Introduccion.md))
2. [Capa de Aplicación](#Capa-de-Aplicación)
3. [Capa de Transporte](#Capa-de-Transporte)
4. [Capa de Red](#Capa-de-Red)
5. [Capa de Enlace](#Capa-de-Enlace)
6. [Full stack](#Full-stack)
7. [Redes inalámbricas](#Redes-inalámbricas)
8. [Seguridad](#Seguridad)

## [Introducción Redes e Internet](secciones/01_Introduccion.md)

`2 clases`

Panorama general de lo que se dará en el curso y conceptos básicos sobre que son las redes de computadoras y que es Internet.

Breve introducción histórica del desarrollo de las redes de computadoras y de Internet.

Ejercicios prácticos:

Discusión sobre lo que es Internet, sus elementos más importantes y ejercicio sobre protocolo.

### [10/03 - Clase 01 - Introducción Redes e Internet 01](diapositivas/01_Introduccion_parte_1.pdf)

Conceptos fundamentales de redes e internet.

### [13/03 - Clase 02 - Introducción Redes e Internet 02](diapositivas/01_Introduccion_parte_2.pdf)

Medios, retardos, modelos de servicios de las capas y seguridad.

-----------------------------------------------------

## Capa de Aplicación

`5 clases`

Aplicaciones de red, ejemplos y arquitecturas.
Comunicación entre procesos por la red, que es un socket.
RFC, Protocolos de la capa bien conocidos, La web y HTTP, Caché.
Capa de presentación y capa de sesión.

Ejercicio práctico:

Programación de socket, inspeccionar transferencia de archivos y cabeceras HTTP mediante el navegador. Utilización de telnet para el dialogo con un equipo remoto.

### 17/03 - Clase 03 - Capa de Aplicación 01

La Web y HTTP.

### 20/03 - Clase 04 - Capa de Aplicación 02

Resumen del tema Web y HTTP.
RFC.
DNS, FTP y protocolos de correo electrónico.

### 24/03 - Clase 05 - Capa de Aplicación 03

Arquitecturas, P2P y otros protocolos distribuidos.

### 27/03 - Clase 06 - Capa de Aplicación 04

Programación de sockets.

### 31/03 - Clase 07 - Capa de Aplicación 05

-Capa de presentación y capa de sesión del modelo OSI-

-----------------------------------------------------

## Capa de Transporte

`4 clases`

Objetivo y responsabilidades de la capa.
Multiplexación y demultiplexación, segmento y puertos.
Protocolos TCP y UDP.

Ejercicio práctico:

Ejemplo de servidor web TCP, análisis de segmentos mediante sniffers, escaneo de puertos.
(wireshark, nmap)

### 03/04 - Clase 08 - Capa de Transporte 01

Introducción a la capa de transporte.

### - Clase 09 - Capa de Transporte 02

Transporte de datos no fiable, UDP.

### - Clase 10 - Capa de Transporte 03

Transporte de datos fiable, diseñando un protocolo propio.

### - Clase 11 - Capa de Transporte 04

Transporte de datos fiable TCP.

-----------------------------------------------------

## Capa de Red

`4 clases`

Objetivo de la capa de red, identificador de red y dispositivos que participan.
Protocolo IP, DHCP, NAT, protocolos de enrutamiento, ICMP.

### - Clase 12 - Capa de Red 01

Introducción, responsabilidades y arquitectura.

### - Clase 13 - Capa de Red 02

Protocolo IP, DHCP, NAT.

### - Clase 14 - Capa de Red 03

Router

### - Clase 15 - Capa de Red 04

Algoritmos de enrutamiento y puesta a punto.

-----------------------------------------------------

## Capa de Enlace

`2 clases`

Servicios de la capa de enlace.
Protocolos de acceso al medio.
Protocolo MAC, Ethernet y ARP.
Dispositivos que participan en la capa, switch, hub.

### - Clase 16 - Capa de Enlace

LAN, Dirección MAC, Ethernet y ARP.

-----------------------------------------------------

## Full stack

`1 clase`

Puesta a punto de todo lo visto hasta el momento siguiendo el camino de una petición desde el cliente hasta el servidor intentando abarcar lo más posible del engranaje de la red.

### - Clase 17 - Full stack, puesta a punto

Puesta a punto y seguimiento de una solicitud de ida y vuelta.

-----------------------------------------------------

## Redes inalámbricas

`1 clase`

Elementos de las redes inalámbricas.
Características y desafíos de este tipo de redes.

### - Clase 18 - Redes inalámbricas y sus protocolos

Redes móviles.
Protocolos WiFi, Bluetooth, WiMAX.

-----------------------------------------------------

## Seguridad

`2 clases`

Conceptos de seguridad en las redes:
Confidencialidad, Autenticación, Integridad del mensaje y seguridad operacional.
Conceptos básicos de Criptografía.
Clave pública, funciones hash y firma digital.
Ataques frecuentes, vulnerabilidades de los sistemas y métodos de protección y contingencia.

### - Clase 19 - Seguridad

Conceptos básicos, y sistemas criptográficos.
SSL / IPsec: Seguridad en la capa de Transporte y de redes.

### - Clase 20 - Seguridad operacional

Privacidad, ataques y estrategias de seguridad.
Firewalls y Detección de intrusos

-----------------------------------------------------

## Secciones

### [Introducción](secciones/01_Introduccion.md)

### [Flujo de Datos](secciones/02_FlujoDeDatos.md)

### [Capas](secciones/03_CapasDeRed.md)

#### [Capa de Aplicación](secciones/04_CapaDeAplicacion.md)

#### [Capa de Transporte](secciones/05_CapaDeTransporte.md)

#### [Capa de Red](secciones/06_CapaDeRed.md)

#### [Capa de Enlace](secciones/07_CapaDeEnlace.md)

#### [Redes inalámbricas](secciones/08_WirelessNetworks.md)

#### [Seguridad](secciones/09_NetworkSecurity.md)

## Herramientas

### Sniffers

Aplicaciones que permiten capturar paquetes de datos en una comunicación.

- Wireshark
- Onmipeek
- Capsa
- Solarwinds
- Ettercap
- Cain y Abel
- Kismet
- TCPdump

## Conceptos varios

- Networking
- Internet
- LAN (Local Area Network)
- WAN (Wide Area Network)
- VLAN (Virtual LAN)
- VPN (Virtual Private Network)
- Router
- Switch
- Firewall
- Modelo OSI

## Protocolos

- TCP
- UDP
- IP
- ARP
- ICMP
