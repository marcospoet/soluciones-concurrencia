# Ejercicio: Secuenciador Ternario con Monitores

> Un secuenciador ternario provee tres operaciones `primero`, `segundo`, `tercero`. Se desea implementar usando monitores al secuenciador ternario para coordinar a threads que pueden invocar a cualquiera de las operaciones. El secuenciador alternará cíclicamente la ejecución de `primero`, luego de `segundo`, y finalmente `tercero`.