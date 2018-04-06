# Flujo de datos

## Bandwidth

(Ancho de banda)

Máxima cantidad de datos que es posible transferir a travez de una conexión en determinado tiempo.

## Conmutación de Circuitos

## Conmutación de Paquetes

## Throughput

Actual número de bits que es posible hacer fluír a traves de una conexión de red en determinado periodo de tiempo.

    Throughput <= Bandwidth

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
