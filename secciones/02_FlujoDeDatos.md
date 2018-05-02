# Flujo de datos

## Bandwidth

(Ancho de banda)

Máxima cantidad de datos que es posible transferir a travez de una conexión en determinado tiempo.

## Conmutación de Circuitos

La conmutación de circuitos realizan una reserva de recursos de extremo a extremo, por el tiempo en el que dura la sesión entre los sistemas terminales. Esto garantiza los parametros de calidad.

Se realiza una división del ancho de banda en secciones fijas, lo que se puede realizar dividiendo por frecuencia (FDM Frequency Division Multiplexing) o por tiempo (TDM, Time Division Multiplexing).

No se comparten recursos, si no se utilizan se desperdician.

## Conmutación de Paquetes

En la conmutación de paquetes se realiza una división en paquetes del flujo de datos.
Se intercambian mensajes, estos se transmiten a través de cada enlace a la velocidad de transmisión máxima del enlace utilizando el ancho de banda disponible. Los recursos se comparten permitiendo que más usuarios utilicen la red al mismo tiempo, por lo que la demanda puede exceder la disponibilidad, en muchos casos será necesario implementar medidas para el control de la congestión.

La forma en la que se intercambian datos en internet frecuentemente suele ser en forma de ráfagas, por lo que esta estrategia suele ser una buena opción. De todos modos es necesario utilizar protocolos para asegurar la transferencia de datos y controlar posibles congestiones.

## Throughput

Actual número de bits que es posible hacer fluír a traves de una conexión de red en determinado periodo de tiempo.

    Throughput <= Bandwidth

La tasa de transferencia será siempre menor o igual que el ancho de banda.

## Retardos y pérdidas

- Procesamiento en el nodo
- Encolamiento
- Transmisión
- propagación

### Resumen de fórmulas

Sea:

- R = ancho de banda del enlace
- L = Longitud del paquete
- a = promedio de arribos
- d = longitud del enlace físico
- s = velocidad de propagación

Tiempo de:

    en cola = La/R
    transmisión = L/R
    propagación = d/s

Producto ancho de banda-retardo:

    R * s

Retardo en cola ~= Intensidad del tráfico

En caso de que la intensidad del tráfico en el nodo supere la capacidad del enlace se perderán paquetes.
