<template>
    <div class="w3-container">
        <div class="w3-center">
        <h1>Actualización de artistas y generos</h1>
        <label>Seleccione el artista a reemplazar (Obligatorio): </label>
        <select v-model="selected">
            <option v-for="a in artistas">
                {{ a.nombre }}
            </option>
        </select>
    </div>
    <div class="w3-left" style="margin-left: 100px;">
        <br><br>
        <h4>Nuevos datos:</h4>
        <div>
            <br>
            <label>Nombre del artista (Obligatorio):</label><br>
            <input type="text" v-model= "nuevoArtista.nombre">
          </div>
        <div>
            <br>
            <label>Descripción del artista:</label><br>
            <input type="text" v-model="nuevoArtista.descripcion">
        </div>
        <div>
            <br>
            <label>Escoge el genero del artista (Obligatorio):</label><br>
            <select v-model="selectedGen">
                <option v-for="g in generos">
                    {{ g.nombre }}
                </option>
            </select>
        </div>

          <div>
            <br><br>
            <button id="agregarArtista" v-on:click= "mostrarMensaje" >Actualizar artistas </button>
          </div>
          <div>
            <br><br>
            <label>El siguiente botón es necesario para capturar opiniones de tu nuevo artista</label><br><br>
            <button id="generarRegistro" v-on:click= "cargarArtista" disabled="true" >Reiniciar la captura de opiniones </button>
          </div>

    </div>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                artistas: [],
                generos: [],
                selected: '',
                selectedGen: '',
              nuevoArtista: {
                nombre:'',
                descripcion:'',

              }
            }
        },
        mounted:function(){
            // GET /someUrl
            this.$http.get('http://localhost:2323/backend-tbd/artistas')
            .then(response=>{
               // get body data
              this.artistas = response.body;
             console.log('artistas',this.data2)
            }, response=>{
               // error callback
               console.log('error cargando lista');
            });
            this.$http.get('http://localhost:2323/backend-tbd/generos')

            .then(response=>{
               // get body data
              this.generos = response.body; 
             console.log('generos',this.registros)
            }, response=>{
               // error callback
               console.log('error cargando lista');
            })
           
            
        },
          methods:{

            cargarArtista: function(event){
                this.$http.get('http://localhost:2323/backend-tbd/artistas/restart').then(response=>{

                console.log("Reiniciada la captura de comentarios.");

                document.getElementById("generarRegistro").disabled = true;
                  alert(' Se ha reiniciado la captura de comentarios de los artistas.');

                  },response=>{

                    console.log("Falla en la conexion con el servidor");
                  });
            },

            mostrarMensaje: function(event){
                

              if (this.nuevoArtista.nombre == '' || this.selected == '' || this.selectedGen == '') {
                alert('Asegurese de tener los campos obligatorios con datos');

                
              }
              else{
                document.getElementById("agregarArtista").disabled = true;
                document.getElementById("generarRegistro").disabled = false;

                for(var i = this.artistas.length -1; i >= 0 ; i--){
                    if(this.artistas[i].nombre == this.selected){
                        idArtista = this.artistas[i].idartista;
                        console.log(idArtista);
                    }
                }

                for(var i = this.generos.length -1; i >= 0 ; i--){
                    if(this.generos[i].nombre == this.selectedGen){
                        idGenero = this.generos[i].idgenero;
                        console.log(idGenero);
                    }
                }
                this.$http.post('http://localhost:2323/backend-tbd/registro/archivar/' + idArtista).then(response=>{

                console.log("Guardado en el registro el artista eliminado.");

                  },response=>{

                    console.log("Falla en la conexion con el servidor");
                  });

                this.$http.put('http://localhost:2323/backend-tbd/artistas/' + idArtista + '/' + idGenero + '/1', this.nuevoArtista).then(response=>{

                console.log("Agreago el nuevo artista", this.nuevoArtista);

                  alert(' Su artista ha sido agregado con exito');

                  },response=>{

                    console.log("Falla en la conexion con el servidor");

                  alert(' Hubo falla en la conexion del servidor');
                  })

            }
              }


          }
    }
</script>