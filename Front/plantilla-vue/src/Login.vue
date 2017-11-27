<template>
    <div class="center">    
        <header class="header-blue">Bienvenido a TweetMusic</header>
        <h4>Para acceder a nuevas funcionalidades identifiquese </h4>
        <p>username:
        <input type="text" v-model="username"></p>
        <p>contraseña:
        <input type="password" name="pwd" v-model="pass"></p>
        <p><button 
            type="submit" 
            class="btn btn-primary" 
            v-on:click="signUp">
            Iniciar sesion</button>
        </p>
        <img src="./img/MusicTweet.png"/>
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
  mounted:function(){
    console.log('Index.vue');

    // GET /someUrl
    this.$http.get('http://localhost:2323/backend-tbd/usuarios')
    .then(response=>{
       // get body data
      this.users = response.body;
     console.log('users',this.users)
    }, response=>{
       // error callback
       console.log('error cargando lista');
    })
  },
  methods:{
      signUp:function() {
            if (this.username=='zero') {
                if (this.pass=='123') {
                    alert(this.username+",iniciando sesion");
                    this.authuser=true;
                    router.push('/');
                }
                else{
                    alert("contraseña invalida");
                }
            }
            else{
                alert("nombre de usuario, no registrado");
            }
            
        },
        /*
        signUp:function() {
            if (authenticate(this.username,this.pass,this.users)) {
                getUserData(this.users,this.username);
                alert(this.username+",iniciando sesion");
                router.push('/');
            }   
        },
        */
        authenticate:function(UserName,Pass,Data){
            var totalData= object.keys(messages.Data).lenght;
            for (var i=0;i <totalData;i++){
                if(UserName==message.Data[i].username){
                    if(Pass==message.Data[i].password){
                        return true;
                    }
                    else{
                        alert('Contraseña incorrecta');
                        return false;
                    }
                }
            }
            return false;
        },
        getUserData:function(Data,UserName){
            var totalData= object.keys(messages.Data).lenght;
            for (var i = 0; i < totalData; i++) {
                if(UserName==message.Data[i].username){
                    this.authuser=message.Data[i];
                    break;
                }
            }        
        }
    }        
}
</script>
<style>
.header-blue{
    background: steelblue;
}

</style>
