<template>
<div class="container">
<div class="center">
  <h1>Mapa con información de artistas por paises</h1>
</div>
<div class="pull-left">
<div id="mapa" class="pull-left" style="position: relative;width: 1050px; height: 700px;margin-left: 200px;"></div>
</div>
</div>
</template>


    <script src="http://d3js.org/d3.v3.min.js"></script>
    <script src="http://d3js.org/topojson.v1.min.js"></script>
<script>
    import Datamap from 'datamaps';
    export default{
      mounted: function(){
        var map = new Datamap({element: document.getElementById('mapa'),
        scope: 'world',
        height: null, //if not null, datamaps will grab the height of 'element'
        width: null, //if not null, datamaps will grab the width of 'element'
        // Zoom in on Africa
        setProjection: function(element) {
          var projection = d3.geo.equirectangular()
            .center([-80, -10])
            .rotate([4.4, 0])
            .scale(400)
            .translate([element.offsetWidth / 2, element.offsetHeight / 2]);
          var path = d3.geo.path()
            .projection(projection);

          return {path: path, projection: projection};
        }});
      }
    }
</script>