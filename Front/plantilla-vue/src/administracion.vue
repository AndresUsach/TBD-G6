<template>
    <div class="w3-container">
        <div class="w3-center">
        <h1>Actualización de artistas y generos</h1>
        <label>Seleccione el artista a reemplazar </label>
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
            <label>Nombre del artista:</label>
            <input type="text" v-model= "nuevoArtista.nombre">
          </div>
        <div>
            <br>
            <label>Descripción del artista:</label>
            <input type="text" name="nuevoArtista.descripcion">
        </div>

          <div>
            <br><br>
            <button v-on:click= "mostrarMensaje" >Agregar </button>
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

            mostrarMensaje: function(event){


              if (this.nuevoArtista.nombre == '') {
                alert('Rellene el campo de nombre');
              }
              else{

                this.$http.post('http://localhost:2323/backend-tbd/artistas/0/1', this.nuevoArtista).then(response=>{

                console.log("Conexion exitosa con el servidor");

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