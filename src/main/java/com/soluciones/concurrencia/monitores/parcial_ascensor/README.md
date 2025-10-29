# Ejercicio Parcial: Ascensor Kosher con Monitores

> **Monitores.** En un edificio de dos pisos, hay un ascensor kosher que puede transportar hasta **N** personas. El ascensor alterna continuamente entre la planta baja y el primer piso. Espera a que se llene o hasta que ocurra un timeout, lo que suceda primero, y luego cambia de piso. En ese momento, todas las personas dentro del ascensor descienden y las que están esperando entran. El tiempo que tarda el ascensor en cambiar de piso se puede simular utilizando la función `moverse()`, que representa el paso del tiempo (esta función no libera locks, si el thread que la ejecuta hubiera adquirido alguno).
>
> **a)** Se requiere implementar un simulador de este problema usando monitores en Java, donde cada persona es un hilo (que puede dirigirse a cualquiera de los pisos). Notar que la política debe ser **signal y continua**. Su solución debe ser libre de deadlocks.
>
> **b)** ¿Es su solución libre de inanición?
>
> **c)** Modificar su solución de manera tal que haya usuarios con prioridad, es decir, cuando deben subir al ascensor, comienzan los que tienen prioridad. Los sin prioridad suben luego de los que tienen prioridad. De todas maneras, si un usuario sin prioridad ya está dentro, no sale para cederle el puesto a uno con prioridad que llega luego.