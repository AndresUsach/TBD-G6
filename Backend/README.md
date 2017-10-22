# Back-End
Aplicacion que indexa los tweets(cada cierto tiempo), calcula y guarda las estadisticas de cada artista y género (cada cierto tiempo).Además, dispone el servicio rest para consultar dichas estadisticas.



## Ejecutar
* Abrir una consola de comandos dentro de esta carpeta y ejecutar los siguientes comandos:
```
> gradle clean
> gradle build
> gradle bootrun
```
* Una alternativa para realizar los tres comandos al mismo tiempo es:
```
> gradle clean build bootrun
```

## Importante 
* Abrir el script sql (tbd_final.sql) MySql Workbench
* Ejecutar el script y refrescar para verificar si se creó la base de datos "tbd"
* Cambiar el usuario y contraseña para la coneccion a la base de datos MySql (dentro del proyecto en el archivo "application.properties")
* MongoDB debe estar corriendo antes de ejecutar este programa.
```
> mongod --dbpath "ruta_bd_mongo"
```
