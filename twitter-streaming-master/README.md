# Streaming

## Para compilar y ejecutar

```
> gradle jar
> java -cp build/libs/twitter-streaming-master-1.0.jar cl.citiaps.twitter.streaming.TwitterStreaming
```

## Importante
* Es necesario tener instalado MongoDB y Gradle.
* **NO OLVIDAR** modificar twitter4j.properties para agregar parámetros de autenticación.
* MongoDB debe estar corriendo antes de ejecutar este programa.
```
> mongod --dbpath "ruta_bd_mongo"
```
