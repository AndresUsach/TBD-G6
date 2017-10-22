# Back-End
Aplicacion que indexa los tweets(cada cierto tiempo), calcula y guarda las estadisticas de cada artista y género (cada cierto tiempo).Además, dispone el servicio rest para consultar dichas estadisticas.

## Previo 
* Abrir el script sql (tbd_final.sql) que está dentro de la carpeta en MySql Workbench
* Ejecutar el script y refrescar para verificar si se creó la base de datos "tbd"
* Cambiar el usuario y contraseña para la coneccion a la base de datos MySql (dentro del proyecto en el archivo "application.properties")
* Debe estar corriendo estar en ejecución la base de datos mongo, para ello utilziar el siguiente comando en una terminal:
```
> mongod --dbpath "ruta_bd_mongo"
```


## Ejecutar
* Abrir una consola de comandos dentro de esta carpeta y ejecutar los siguientes comandos:
```
> gradle clean
> gradle build
> gradle bootrun
```

