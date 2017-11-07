# Plantilla para Vue
## Taller de Base de Datos

Incluye:
* ejemplo básico de [Webpack](https://webpack.github.io/)
* plantilla para [Vue.js](https://vuejs.org/)

### Requisitos previos
 [Node js](https://nodejs.org/es/download/)

### Instalación plantilla Vue

* ir a carpeta `plantilla vue`
* instalar dependencias de node `npm install datamaps --save d3`
* es necesario abrir el archivo plantilla-vue/node-modules/datamaps/dist/datamaps.all.js y modificar la linea 786: "Datamaps.prototype..." por "Datamap.prototype..."
* ejecutar `npm run dev`
* generar distribuible `npm run build`


Nota:
Para los pc que no poseen macOS, deben quitar la dependencia fsevents del package.json de la carpeta Front
Luego ejecutar dentro de la plantilla vue el siguiente comando : " npm i -f " les bajara las dependencias standares de npm sin el fsevents
Finalnente ejecutar npm run dev para correr la aplicacion de frontend
