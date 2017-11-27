<template>

<div class= "w3-container">

<div class="w3-card-4" style="margin-left: 150px;margin-top: 130px;width: 80%">
    <div class="w3-container w3-teal">
      <h2>Nuevo Genero</h2>
    </div>

    <form class="w3-container">
      <p>
      <label class="w3-text-teal"><b>Nombre del genero (Obligatorio):</b></label>
      <input class="w3-input w3-border" type="text" v-model= "genero.nombre" style="width: 50%"></p>
      <p>
      <button class="w3-btn w3-blue-grey" v-on:click= "mostrarMensaje" >Agregar genero</button></p>
    </form>

    </div>

</div>
</template>



<script>

  export default {
  data(){
    //declaracion de variables
    return{
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