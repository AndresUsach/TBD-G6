<template>

<div class= "center">
  
  <div>
    <h2>{{title}}</h2> 
  </div>

  <div>
    <br>
    <label>Nombre del genero:</label>
    <input type="text" v-model= "genero.nombre">
  </div>

  <div>
    <br><br>
    <button v-on:click= "mostrarMensaje" >Agregar </button>
  </div>


</div>
</template>



<script>

  export default {
  data(){
    //declaracion de variables
    return{
      title:'Nuevo Genero',
      genero: {
        nombre:''
      }

      
    }
  },
  // funciones
  methods:{

    mostrarMensaje: function(event){


      if (this.genero.nombre == '') {
        alert('Rellene el campo de nombre');
      }
      else{

        this.$http.post('http://localhost:2323/backend-tbd/generos', this.genero).then(response=>{

        console.log("Conexion exitosa con el servidor");

      },response=>{

        console.log("Falla en la conexion con el servidor");
      })

      alert(' Su genero ha sido agregado con exito');

    }
      }


  }



}

</script>