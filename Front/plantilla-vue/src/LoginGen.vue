<template>
    <div class="w3-container  w3-center">    
        <header class="header-blue">Bienvenido a TweetMusic Login</header>
        <h4>Para acceder a nuevas funcionalidades identifiquese </h4>
        <p>Username:
        <input type="text" v-model="username"></p>
        <p>Password:
        <input type="password" name="pwd" v-model="pass"></p>
        <p><button 
            type="submit" 
            class="btn btn-primary" 
            v-on:click="signUp">
            Iniciar sesion</button>
        </p>
        <img class="w3-circle w3-margin-right" src="./img/MusicTweet.png"  style="width:200px"/>
    </div>

</template>

<<script>
import {router} from   './main.js'
export default {
  
  data(){
      return{
        username:'',
        pass:'',
        users:[],
        authuser: false
      }
  },
  created(){
    console.log('Index.vue');
    // GET /someUrl
    this.$http.get('http://localhost:2323/backend-tbd/usuarios').then(function(data){
        this.users = data.body;
        console.log('users',this.users);;
    })
  },
  methods:{
      signUp:function() {
            if (this.authenticate(this.username,this.pass,this.users)) {
                alert(this.username+", iniciando sesión...");
                this.authuser=true;
                router.push('/newGenre');
            }
        },
        authenticate:function(UserName,Pass,Data){
            for (var i in Data){
                if(UserName==Data[i].username){
                    if(Pass==Data[i].password){
                        return true;
                    }
                    else{
                        alert('Contraseña incorrecta');
                        return false;
                    }
                }
            }
            alert("Nombre de usuario no registrado");
            return false;
        }
    }        
}
</script>
<style>
.header-blue{
    background: steelblue;
}

</style>
