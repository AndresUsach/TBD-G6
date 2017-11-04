<template>
<body>


  <!-- Push down content on small screens -->
  <div class="w3-hide-large" style="margin-top:83px"></div>

  <!-- Image header -->
  <div class="w3-display-container w3-container">
    <img src="./img/concierto.jpeg" alt="Jeans" style="width:100%">
    <div class="w3-display-topleft w3-text-white" style="padding:24px 48px">
      <h1 class="w3-jumbo w3-hide-small w3-opacity">MusicTweet</h1>
      <h1 class="w3-jumbo w3-hide-small w3-hover-black">Contamos con X tuits</h1>
    </div>
  </div>

<div class="center">

    <div class="w3-card-4 w3-display-left w3-hover-grayscale" style="margin-left: 40px">
      <a href="#/BarGraph"><img class="w3-image" src="./img/sing-hi.png" alt="Norway" style="width:100%"></a>
      <div class="w3-container w3-center w3-black">
        <p>Estadisticas sobre artistas</p>
      </div>
    </div>

    <div class="w3-card-4 w3-display-middle w3-hover-grayscale">
      <a href="#/BarGraph2"><img class="w3-image" src="./img/genero.png" alt="Norway" style="width:100%"></a>
      <div class="w3-container w3-center w3-black">
        <p>Estadisticas sobre generos musicales</p>
      </div>
    </div>

    <div class="w3-card-4 w3-display-right w3-hover-grayscale" style="margin-right:50px;">
      <img class="w3-image" src="./img/mapa.png" alt="Norway" style="width:100%">
      <div class="w3-container w3-center w3-black">
        <p>Estadisticias nivel sudamericano</p>
      </div>
    </div>

  </div>



</body>


</template>
<script>
export default{
  data(){
    return{
      title:'MusicTweet'
    }
  },
  mounted:function(){
    console.log('Index.vue')
  }
}
</script>
