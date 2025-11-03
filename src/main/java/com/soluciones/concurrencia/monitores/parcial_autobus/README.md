# Ejercicio Parcial: Autobús Sincronizado (Monitores)

> **Monitores.** Un pasajero que llega a la estación de autobuses hace fila en el área de embarque. El autobús llega con N asientos disponibles. El proceso de embarque se desarrolla de la siguiente manera:
>
> Cuando el autobús llega, los pasajeros que ya están en el área de embarque pueden subir, siempre que haya asientos disponibles y que haya pasajeros en la fila. Una vez que no quedan asientos libres o no hay más pasajeros en la fila, el autobús se marcha.
>
> Se consideran dos enfoques alternativos:
>
> a) El conductor del autobús invita a todos los pasajeros en el área de embarque a subir, siempre que haya asientos disponibles y pasajeros en la fila. Si alguna de estas condiciones no se cumple, el conductor se marcha.
>
> b) El conductor invita al primer pasajero a subir si hay un asiento disponible y hay un pasajero en la fila. Luego, el último pasajero que subió invita al siguiente en la fila si hay lugar. Así hasta que no haya asientos disponibles o no haya pasajeros. Si alguna de estas condiciones no se cumple, el conductor se marcha o el último pasajero le pide que se retire.