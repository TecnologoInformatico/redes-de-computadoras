# Seguridad

La seguridad de red apunta a una comunicación “Segura”

Se desea que los mensajes lleguen del emisor al receptor de modo tal que:

- [Confidencialidad](##Confidencialidad)Solo el receptor pueda entender el mensaje
- [Autenticación](##Autenticación) Que el emisor sea quien dice ser
- [Integridad del mensaje](##Integridad-del-mensaje) Que el contenido del mensaje no fue alterado
- [Seguridad operacional](##Seguridad-operacional) Garantías para la posibilidad de comunicación

## Confidencialidad

Sólo el emisor y el receptor deseado deberán comprender el contenido de los mensajes transmitidos.
Los mensajes son cifrados de algún modo para que no puedan ser entendidos por un interceptor.

## Autenticación

Tanto el emisor como el receptor deberán poder confirmar la identidad del otro en el proceso de comunicación.
Confirmar que el otro es de hecho quien dice ser.

## Integridad del mensaje

Aún existiendo una autenticación un mensaje podría ser modificado en el camino por un tercero.
Técnicas similares a las de suma de comprobación son utilizadas para garantizar la integridad del mensaje.

## Seguridad operacional

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

## SSL

## IPsec
