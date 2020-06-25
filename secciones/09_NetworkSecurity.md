# Seguridad

La seguridad de red apunta a una comunicación “Segura”

Se desea que los mensajes lleguen del emisor al receptor de modo tal que:

- [Confidencialidad](##Confidencialidad)Solo el receptor pueda entender el mensaje
- [Autenticación](##Autenticación) Que el emisor sea quien dice ser
- [Integridad del mensaje](##Integridad-del-mensaje) Que el contenido del mensaje no fue alterado
- [Seguridad operacional](##Seguridad-operacional) Garantías para la posibilidad de comunicación

## Características

### Confidencialidad

Sólo el emisor y el receptor deseado deberán comprender el contenido de los mensajes transmitidos.
Los mensajes son cifrados de algún modo para que no puedan ser entendidos por un interceptor.

### Autenticación

Tanto el emisor como el receptor deberán poder confirmar la identidad del otro en el proceso de comunicación.
Confirmar que el otro es de hecho quien dice ser.

### Integridad del mensaje

Aún existiendo una autenticación un mensaje podría ser modificado en el camino por un tercero.
Técnicas similares a las de suma de comprobación son utilizadas para garantizar la integridad del mensaje.

### Seguridad operacional

Es deseable tener ciertas garantías de acceso al sistema de comunicación. En la actualidad es necesario tomar medidas para mantener operativo los servicios, a salvo de posibles ataques de denegación de servicios o intrusos maliciosos.

## Ataques al cifrado

Los ataques a un cifrado se suelen clasificar teniendo en cuenta la información que posee el atacante.

### Ciphertext-only attack

El atacante sólo posee el texto cifrado interceptado.

### Know-plain-text attack

El atacante tiene la certeza de que determinadas palabras se encuentran presentes en el mensaje y puede determinar pares de palabras (texto-plano, cifrado)

### Chosen-plain-text attack

El intruso es capaz de encriptar un mensaje.

## Encriptación

Encriptación de un archivo

`openssl rsautl -encrypt -inkey public_key.pem -pubin -in <decrypted file> -out <encrypted file>`

Desencriptar un archivo

`openssl rsautl -decrypt -inkey private_key.key -in <encrypted file> -out <decrypted file>`

## Correo seguro

### OpenPGP

[OpenPGP](https://www.openpgp.org/) [RFC 4880](https://tools.ietf.org/html/rfc4880) es un protocolo no propietario para la encriptación de correo electrónico utilizando criptografía de claves asimétricas.
Esta basado en el programa gratuito Pretty Good Privacy (PGP) desarrollado por Phil Zimmermann en 1991.

```cs
-----BEGIN PGP SIGNED MESSAGE-----
Hash: SHA1
Bob:
Can I see you tonight?
Passionately yours, Alice
-----BEGIN PGP SIGNATURE-----
Version: PGP for Personal Privacy 5.0
Charset: noconv
yhHJRHhGJGhgg/12EpJ+lo8gE4vB3mqJhFEvZP9t6n7G6m5Gw2
-----END PGP SIGNATURE-----
```

### STARTTLS

(Opportunistic TLS)

Estensión para protocolos de texto plano el cual ofrece utilizar una conexión encriptada (TLS o SSL).

- IMAP y POP3 (RFC 2595)
- SMTP (RFC 3207)

Dialogo de ejemplo del inicio de una sesión segura (RFC 3207 SMTP):

```py
  S: <waits for connection on TCP port 25>
  C: <opens connection>
  S: 220 mail.example.org ESMTP service ready
  C: EHLO client.example.org
  S: 250-mail.example.org offers a warm hug of welcome
  S: 250 STARTTLS
  C: STARTTLS
  S: 220 Go ahead
  C: <starts TLS negotiation>
  C & S: <negotiate a TLS session>
  C & S: <check result of negotiation>
  C: EHLO client.example.org[3]
  . . .
```

El último comando EHLO viaja a traves de una conexión segura.

El handshake inicial es realizado en texto plano, por lo que un atacante que modifique los mensajes (man in the middle attack) haciendo parecer que una conexión TLS no es soportada (STRIPTLS attack), podría llevar a que las credenciales sean enviadas en texto plano, sin ofrecerle información al usuario. Se han registrado casos en que proveedores de internet han realizado este ataque contra sus propios clientes.

los ataques STRIPTLS pueden ser bloqueados requiriendo TLS para todas las conexiones salientes, lo que en muchos casos puede no ser práctico.

## TLS

Transport Layer Security (TLS), y su predecesor, Secure Sockets Layer (SSL), son protocolos criptográficos diseñados para proveer seguridad sobre una red de computadoras en la capa de transporte.
Es utilizado en multiples aplicaciones, Correo electrónico, HTTP, VoIP, etc.

El protocolo TLS esta orientado a proveer privacidad y a garantizar la integridad de los datos, teniendo en cuenta las siguientes propiedades:

- Se negocia el algoritmo de encriptación a utilizar.
- La identidad de las partes puede ser comprobada mediante el uso de clave pública.
  - Puede ser opcional pero por lo general se requiere para alguna de las partes (tipicamente el servidor)
- Un intruso no puede modificar la comunicación durante la negociación sin ser detectado.
- Se utiliza encriptación simétrica para intercambiar la información.
- Las claves son únicas para cada conexión basadas en una clave compartida (negociada al inicio de la sesión)

### TLS handshake

El `handshake` utiliza encriptación asimétrica, una clave pública y una privada. Ya que este método tiene un costo muy elevado, no es utilizado para proveer la encriptación durante toda la conexión.
En un primer paso se utiliza únicamente para intercambiar una `clave compartida`, la cual luego será utilizada para encriptar el resto de la comunicación, con un costo de sobrecarga bastante bajo.

Durante el inicio de la conexión también se define el algorítmo de encriptación, el cliente informa los algoritmos que soporta y la versión TLS con la que cuenta, y si el servidor es compatible selecciona uno de los algoritmos.

Otro evento que se da durante el inicio de la conexión es la autenticación, en la mayor parte de los casos el servidor es quién certifica su identidad, pero en algunos casos puede que se requiera una autenticación por parte del cliente también, esto genera una sobrecarga extra, pero agrega un nivel de seguridad cuando se considera necesario.
Así mismo existe una opción "anónima" lo que puede acarrear ataques de un tercero (Man-in-the-Middle attacks) y opciones de generar la clave compartida mediante el uso de un password.

Cada sesión TLS puede tener su propia configuración, por lo que puede variar mucho de una a otra, dependiendo del cifrado elegido, así como los requerimientos de seguridad (autenticación, cifrado), por lo que esta etapa puede contener más o menos pasos dependiendo de la configuración.

### TLS record

El protocolo `TLS Record` utiliza la clave creada durante el handshake para asegurar la comunicación y validar la integridad de los datos.

- Divide los mensajes salientes en bloques manejables y reensamla los mensajes entrantes.
- Comprime y descomprime los bloques (opcional).
- Aplica el código de autenticación del mensaje, MAC (Message Authentication Code) para los mensajes salientes y verifica los entrantes.
- Encripta mensajes salientes y desencripta los entrantes.

Una vez que el protocolo ha cumplido su función pasa los datos a la capa de transporte TCP.

### Configuración

Cuanta con varios métodos de configuración, lo que los servicios brindados pueden variar dependiendo de la configuración realizada.

TLS fue propuesto como estándar por la IETF (Internet Engineering Task Force), definido por primera vez en 1999 y la última versión a la fecha data del 2018 (TLS 1.3).
Este protocolo esta construído en base a su antecesor SSL (1994-1996), desarrollado para brindar HTTPS al navegador web Netscape.

## IPsec

IPsec es un protocolo encargado de autenticar y encriptar los paquetes enviados mediante el protocolo IP. Es utilizado para generar VPNs (virtual private networks).

Establece autenticación mutua al inicio de una sesión y negociación de claves para el uso durante la sesión. Puede proteger flujos de datos entre hosts (host-to-host), entre gateways (network-to-network), o entre un gateway y un host (network-to-host).

IPsec brinda a nivel de capa de red:

- Autenticación de pares
- Autenticación de origen
- Integridad de datos
- Confidencialidad de datos
- Protección ante ataques de reenvío `replay protection`.

## Enlaces

- [Diffie Hellman in plain english](https://security.stackexchange.com/questions/45963/diffie-hellman-key-exchange-in-plain-english)
