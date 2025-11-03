# Ejercicio Parcial: Monos en Barranco (Monitores)

> **Monitores.** Algunos monos están intentando cruzar un barranco. Hay una sola cuerda que atraviesa el barranco, y los monos pueden cruzar de forma horizontal. Hasta cinco monos pueden estar colgados de la cuerda al mismo tiempo; si hay más de cinco, la cuerda se romperá y todos morirán. Además, si los monos usan la cuerda, todos lo tienen que hacer en la misma dirección (este u oeste). Si un mono quiere cruzar hacia el oeste, pero en la cuerda hay monos yendo hacia el este, todos caerán y morirán.
>
> Cada mono opera en un hilo separado que ejecuta el siguiente código:
>
> ```c
> typedef enum {EAST, WEST} Destination;
>
> void monkey(int id, Destination dest) {
>   WaitUntilSafeToCross(dest);
>   CrossRavine(id, dest);
>   DoneWithCrossing(dest);
> }
> ```
>
> La variable `id` contiene un número único que identifica a cada mono. `CrossRavine(int monkeyId, Destination d)` es una llamada sincrónica que se proporciona y que devuelve cuando el mono que llega ha llegado a su destino. Se pide implementar las funciones `WaitUntilSafeToCross(Destination d)` y `DoneWithCrossing(Destination d)` utilizando **monitores**.
>
> La implementación debe garantizar que:
>
> a) Un máximo de cinco monos puedan ejecutar `CrossRavine()` al mismo tiempo.
> 
> b) Todos los monos que están ejecutando `CrossRavine()` se dirijan en la misma dirección.
> 
> c) Ningún mono debe esperar innecesariamente. Es decir, si hay monos cruzando en una dirección y llega otro que quiere cruzar en esa dirección, puede hacerlo, a pesar de que hay otros monos esperando del otro lado.