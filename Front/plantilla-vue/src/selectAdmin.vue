<template>
    <div class="w3-container  w3-center">    
        <header class="header-blue">Bienvenido a TweetMusic Login</header>
        <h4>¿Que desea administrar? </h4>
        <p><button 
            type="submit" 
            class="btn btn-primary" 
            v-on:click="AdminArtista">
            Artistas</button>
            <button 
            type="submit" 
            class="btn btn-primary" 
            v-on:click="AdminGenero">
            Genero</button>
        </p>
        <img class="w3-circle w3-margin-right" src="./img/MusicTweet.png"  style="width:200px"/>
    </div>

</template>

<script>
import {router} from './main.js'
export default {
  
  data(){
      return{

      }
  },
  methods:{
        AdminArtista:function() {
            router.push('/admin');
        },
        AdminGenero:function() {
            router.push('/newGenre');
        },
    }        
}
</script>
<style>
.header-blue{
    background: steelblue;
}

</style>