<template>
    <div class="w3-container  w3-center">    
        <header class="header-blue">Bienvenido a TweetMusic Login</header>
        <h4>Para acceder a nuevas funcionalidades identifiquese </h4>
        <p>username:
        <input type="text" v-model="username"></p>
        <p>contraseña:
        <input type="password" name="pwd" v-model="pass"></p>
        <p><button 
            type="submit" 
            class="btn btn-primary" 
            v-on:click="selectMode">
            Iniciar sesion</button>
        </p>
        <img class="w3-circle w3-margin-right" src="./img/MusicTweet.png"  style="width:200px"/>
    </div>

</template>

<script>
import {router} from './main.js'
export default {
  
  data(){
      return{
        username:'',
        pass:'',
        users:[],
        authuser: false
      }
  },
  mounted:function(){
            // GET /someUrl
            this.$http.get('http://localhost:2323/backend-tbd/usuarios')
            .then(response=>{
               // get body data
              this.users = response.body;
             console.log('usuarios',this.users)
            }, response=>{
               // error callback
               console.log('error cargando lista');
            })
   },
  methods:{
        selectMode:function() {
            if (this.authenticate(this.username,this.pass,this.users)) {
                alert(this.username+",iniciando sesion");
                this.authuser=true;
                router.push('/Select');
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
            alert("nombre de usuario, no registrado");
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
