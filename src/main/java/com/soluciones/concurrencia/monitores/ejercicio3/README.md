# Ejercicio: Barrera con Monitores

> Se desea implementar usando monitores una barrera para coordinar a N threads. Una barrera provee una única operación denominada `esperar`. La idea es que cada uno de los N threads invocarán una vez a la operación `esperar` y el efecto de invocarla es que el thread se bloquea y no puede continuar hasta tanto los restantes threads invoquen a la operación `esperar`.
>
> Por ejemplo, si `mibarrera` es una barrera para coordinar 3 threads, el uso de `mibarrera` en los siguientes threads:
> ```
> thread1: print 'a'; mibarrera.esperar(); print 1
> thread2: print 'b'; mibarrera.esperar(); print 2
> thread3: print 'c'; mibarrera.esperar(); print 3
> ```
> garantiza que todas las letras se mostrarán antes de los números. Dar una implementación para el monitor barrera.