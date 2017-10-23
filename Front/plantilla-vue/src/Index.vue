<template>
<body>

<div class="center">
  <h2>{{title}}</h2>

    <img class="w3-display-middle" src="./img/MusicTweet.png" />

  
</div>


<div class="center">
  <h3>
  <br><br><br><br><br><br>
          Tu música a un tweet ..
        </h3>
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
