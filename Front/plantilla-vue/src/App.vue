<template>
  <div>
    <!--<div class="w3-bar w3-border w3-black">
  <a href="#/index" class="w3-bar-item w3-button w3-hover-none w3-text-grey w3-hover-text-white">Home</a>
</div>
       <div class="w3-sidebar w3-bar-block w3-dark-grey w3-animate-left" style="display:none" id="mySidebar">
        <button class="w3-bar-item w3-button w3-large" v-on:click="close">Close &times;</button>
        <a href="#/BarGraph2" class="w3-bar-item w3-button">Top 10 géneros más escuchados</a>
        <a href="#/BarGraph" class="w3-bar-item w3-button">Top 10 artistas más escuchados</a>
      </div>

    <main class="container">
        <div>
            <button class="w3-button w3-white w3-xxlarge" v-on:click="open">&#9776;</button>
        </div>

      <router-view></router-view>
    </main>
    <footer>
      Footer
    </footer>-->

<!-- Top container -->
<div class="w3-bar w3-top w3-black w3-large" style="z-index:4">
  <button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey" v-on:click="open"><i class="fa fa-bars"></i>  Menu</button>
  <span class="w3-bar-item w3-right">Bloo Duckver</span>
</div>

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-collapse w3-white w3-animate-left" style="z-index:3;width:250px;" id="mySidebar"><br>
  <div class="w3-container w3-row">
    <div class="w3-col s4">
      <img class="w3-circle w3-margin-right" src="./img/MusicTweet.png" style="width:200px" />
    </div>
    <div class="w3-col s16 w3-bar">
      <br>
      <span><strong>Bienvenido</strong></span><br>
      <a href="#" class="w3-bar-item w3-button"><i class="fa fa-envelope"></i></a>
      <a href="#" class="w3-bar-item w3-button"><i class="fa fa-user"></i></a>
      <a href="#" class="w3-bar-item w3-button"><i class="fa fa-cog"></i></a>
    </div>
  </div>
  <hr>
  <div class="w3-container">
    <h5>Menú</h5>
  </div>
  <div class="w3-bar-block">
    <a href="#/" id="inicio" class="w3-bar-item w3-button w3-padding tablink item" v-on:click="openCity('inicio')"><i class="fa fa-users fa-fw"></i> Inicio</a>
    <a v-on:click="myAccFunc" href="javascript:void(0)" class="w3-bar-item w3-button w3-padding tablink item" id="myBtnArtist"><i class="fa fa-microphone"></i> 
          Artistas<i class="fa fa-caret-down w3-right"></i>
      </a>
      <div id="demoAcc" class="w3-bar-block w3-hide w3-padding-large w3-medium">
        <a href="#/BarGraph" class="w3-bar-item w3-button" v-on:click="openCity('myBtnArtist')"><i class="fa fa-plus-circle" aria-hidden="true"></i>  Positivos</a>
        <a href="#/BarGraph" class="w3-bar-item w3-button" v-on:click="openCity('myBtnArtist')"><i class="fa fa-minus-circle" aria-hidden="true"></i>  Negativos</a>
      </div>
    <a v-on:click="myAccFunc2" href="javascript:void(0)" class="w3-bar-item w3-button w3-padding tablink item" id="myBtnGenre"><i class="fa fa-music"></i>
        Generos <i class="fa fa-caret-down w3-right"></i>
      </a>
      <div id="demoAcc2" class="w3-bar-block w3-hide w3-padding-large w3-medium">
        <a href="#/BarGraph2" class="w3-bar-item w3-button" v-on:click="openCity('myBtnGenre')"><i class="fa fa-plus-circle" aria-hidden="true"></i>  Positivos</a>
        <a href="#/BarGraph2" class="w3-bar-item w3-button" v-on:click="openCity('myBtnGenre')"><i class="fa fa-minus-circle" aria-hidden="true"></i>  Negativos</a>
      </div>
    <a href="#/mapa" id="Latinoamerica" class="w3-bar-item w3-button w3-padding tablink item" v-on:click="openCity('Latinoamerica')"><i class="fa fa-bullseye fa-fw"></i> Latinoamerica</a>
    <a href="#" id="Noticias" class="w3-bar-item w3-button w3-padding tablink item" v-on:click="openCity('Noticias')"><i class="fa fa-bell fa-fw"></i> Noticias</a>
    <a href="#" id="Settings" class="w3-bar-item w3-button w3-padding tablink item" v-on:click="openCity('Settings')"><i class="fa fa-cog fa-fw"></i> Settings</a><br><br>
      
      
  </div>
</nav>


<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" v-on:click="close" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:230px;margin-top:43px;">


    <router-view></router-view>

  <!-- Footer -->
  <!-- End page content -->
</div>

</div>
</template>

<script>

document.getElementById("myBtnArtist").click();
document.getElementById("myBtnGenre").click();
</script>

<script>
export default {

  methods: {
      myAccFunc: function() {
        var x = document.getElementById("demoAcc");
        if (x.className.indexOf("w3-show") == -1) {
            x.className += " w3-show";
        } else {
            x.className = x.className.replace(" w3-show", "");
        }
      },

      myAccFunc2: function() {
        var x = document.getElementById("demoAcc2");
        if (x.className.indexOf("w3-show") == -1) {
            x.className += " w3-show";
        } else {
            x.className = x.className.replace(" w3-show", "");
        }
      },

      open: function() {
        if (document.getElementById("mySidebar").style.display === 'block') {
            document.getElementById("mySidebar").style.display = 'none';
            document.getElementById("myOverlay").style.display = "none";
        } else {
            document.getElementById("mySidebar").style.display = 'block';
            document.getElementById("myOverlay").style.display = "block";
        }
      },
      close: function() {
        document.getElementById("mySidebar").style.display = "none";
        document.getElementById("myOverlay").style.display = "none";
      },
      openCity: function (evt) {
        var i, x, tablinks;
        x = document.getElementsByClassName("item");
        tablinks = document.getElementsByClassName("tablink");
        for (i = 0; i < x.length; i++) {
            tablinks[i].style = tablinks[i].style.backgroundColor = "none";
        }
        document.getElementById(evt).style.backgroundColor = "SlateGrey";
        //evt.className += " w3-blue";
      }

  }


}
</script>
