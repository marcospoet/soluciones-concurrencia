# Ejercicio: Atrapador (Catcher) con Monitores

> Se desea implementar usando monitores un atrapador que permite coordinar varios threads. Un atrapador provee dos operaciones `esperar` y `liberar(N)`. La idea es que cada uno de los threads puede invocar a cualquiera de las operaciones: un thread que invoca a `esperar` se deberá bloquear hasta tanto sea liberado por algún otro thread que invoca a la operación `liberar(N)`. El parámetro `N` indica cuantos threads de los que están bloqueados deberán liberarse.
>
### [Parte (a)](./parteA)
>
> Dar una solución en la que el thread que invoca `liberar(N)` nunca se bloquea. Además sólo se liberan threads si hay N o más threads esperando. En el caso en que haya menos de N threads, ninguno se liberará y deberán continuar esperando.
>
### [Parte (b)](./parteB)
>
> Modificar la solución anterior para hacer que en el caso en que haya menos de N threads esperando, el proceso que invoca a `liberar` se bloquee hasta tanto se puedan liberar N threads (todos los threads que esperan se deben liberar juntos).