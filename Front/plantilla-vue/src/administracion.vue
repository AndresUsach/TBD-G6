<template>
    <div class="w3-container">
        <div class="w3-center">
          <h1>Actualización de artistas</h1>
        </div>
  <div class="w3-card-4" style="width: 70%; margin-left: 150px;">
    <div class="w3-container w3-teal">
      <h2>Nuevo Artista</h2>
    </div>

    <div class="w3-container">
      <p>
      <label class="w3-text-teal"><b>Nombre del artista (Obligatorio):</b></label>
      <input class="w3-input w3-border" type="text" v-model= "nuevoArtista.nombre" style="width: 30%"></p>
      <p>
      <label class="w3-text-teal"><b>Descripción del artista:</b></label>
      <input class="w3-input w3-border" type="text" v-model="nuevoArtista.descripcion"></p>
      <p>
      <select class="w3-select w3-border" name="option" v-model="selectedGen">
        <option value="" disabled selected>Escoge el genero del artista (Obligatorio):</option>
                <option v-for="g in generos">
                    {{ g.nombre }}
                </option>
      </select></p>
      <p>
      <select class="w3-select w3-border" name="option" v-model="selected">
        <option value="" disabled selected>Seleccione el artista a reemplazar (Obligatorio): </option>
                <option v-for="a in artistas">
                  {{ a.nombre }}
                </option>
      </select></p>

      <p>

      <button id="agregarArtista" class="w3-btn w3-blue-grey" v-on:click= "mostrarMensaje" >Actualizar artista </button></p>

      <p>

      <button id="archivarArtistas" class="w3-btn w3-blue-grey" v-on:click= "archivarTodos" >Archivar todos los artistas </button></p>

      <p>

      <button class="w3-btn w3-teal" id="generarRegistro" v-on:click= "cargarArtista" disabled="true" >Reiniciar la captura de opiniones </button></p>
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

                    alert('Ha ocurrido un error');

                    console.log("Falla en la conexion con el servidor");
                  });
            },

            archivarTodos: function(event){
              this.$http.post('http://localhost:2323/backend-tbd/registro/archivar/').then(response=>{
                alert('Todos los artistas han sido archivados');

                console.log("Guardado en el registro el artista eliminado.");

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
                        var idArtista = this.artistas[i].idartista;
                        console.log(idArtista);
                    }
                }

                for(var i = this.generos.length -1; i >= 0 ; i--){
                    if(this.generos[i].nombre == this.selectedGen){
                        var idGenero = this.generos[i].idgenero;
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