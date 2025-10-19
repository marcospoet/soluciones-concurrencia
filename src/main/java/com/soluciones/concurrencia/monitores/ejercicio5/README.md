# Ejercicio 5: El Problema de la Peluquería

> Se desea resolver utilizando monitores el problema de la peluquería en la cual los clientes y los peluqueros son modelados como threads independientes que se sincronizan utilizando un monitor, denominado `pelu`, que tiene las siguientes operaciones:
> * `cortarseElPelo`
> * `empezarCorte`
> * `terminarCorte`
>
> Los threads **peluqueros** se comportan de la siguiente manera:
> ```java
> while(true){
>   pelu.empezarCorte();
>   //cortar
>   pelu.terminarCorte();
> }
> ```
> mientras que los **clientes** invocan a la operación `cortarseElPelo`. El funcionamiento es el esperado para una peluquería. Los clientes llegan y esperan hasta ser atendidos. Solo se van de la peluquería cuando finalizan de cortarles el pelo.

---
### [Parte (a)](./parteA)

> Dar una implementación a este problema utilizando monitores (con la política "signal y continúa" y utilizando **diferentes variables de condición**).

### [Parte (b)](./parteB)

> ¿Cómo se modifica su solución si puede utilizar una **única variable de condición**?
