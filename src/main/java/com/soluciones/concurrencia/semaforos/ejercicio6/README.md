# Ejercicio 6: Cálculo Concurrente con Semáforos

> Los siguientes procesos cooperan para calcular el valor $N^2$, que es la suma de los primeros $N$ números impares. Los procesos comparten las variables `N` y `N2` inicializadas de la siguiente manera: `N = 50` y `N2 = 0`.
>
> **P1:**
> ```
> while (N > 0) do
>   N = N - 1
> end while
> mostrar(N2)
> ```
>
> **P2:**
> ```
> while (true) do
>   N2 = N2 + 2*N - 1
> end while
> ```
>
> ### Parte a)
>
> Dar una solución que, utilizando semáforos, garantice que se muestra el valor correcto de `N2`.
>
> ### Parte b)
>
> ¿Qué debería cambiar en la solución propuesta en a) si `N2` se calcula como `N2 + 2*N + 1`?