# Ejercicio 5: Sincronización de Procesos con Semáforos

> Considere los siguientes dos procesos:
> ```
> T_1 = while true do print(A)
> T_2 = while true do print(B)
> ```

> ### Parte a)
>
> Utilizar semáforos para garantizar que en todo momento la cantidad de A y B impresas difiera como máximo en 1.