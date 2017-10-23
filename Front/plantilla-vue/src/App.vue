<template>
  <div>
    <div class="w3-bar w3-border w3-black">
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
    </footer>
</div>
</template>

<script>
export default {

  methods: {
      open: function() {
        document.getElementById("mySidebar").style.display = "block";
      },
      close: function() {
        document.getElementById("mySidebar").style.display = "none";
      }
  }


}
</script>
