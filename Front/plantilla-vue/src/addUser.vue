<template>

<div class= "center">
  
  <div>
    <h2>{{title}}</h2> 
  </div>

  <div>
    <label>Nombre:</label>
    <input type="text" v-model= "actor.firstName">
  </div>

  <div>
    <label>Apellido:</label>
    <input type="text" v-model= "actor.lastName">
  </div>

  <div>
    <button v-on:click= "mostrarMensaje" >Agregar </button>
  </div>

<pre>{{
$data | json
}}</pre>

</div>
</template>



<script>

  export default {
  data(){
    //declaracion de variables
    return{
      title:'Nuevo Actor',
      actor: {
        firstName:'',
        lastName: ''
      }

      
    }
  },
  // funciones
  methods:{

    mostrarMensaje: function(event){


      if (this.actor.firstName == '' || this.actor.lastName == '') {
        alert('rellene los campos');
      }
      else{

        this.$http.post('http://localhost:8081/sakila-spring-backend/actors', this.actor).then(response=>{

        console.log("Conexion exitosa con el servidor");

      },response=>{

        console.log("Falla en la conexion con el servidor");
      })

      alert(' Su actor ha sido agregado con exito');

    }
      }


  }



}




</script>