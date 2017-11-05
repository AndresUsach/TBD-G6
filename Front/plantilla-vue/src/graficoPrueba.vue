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
        responsive: true,
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
        },
        geographyConfig: {
          highlightBorderColor: '#bada55',
         popupTemplate: function(geography, data) {
            return '<div class="hoverinfo">' + '<h6><strong>' + geography.properties.name + '</h6></strong>'  + 'Artista más escuchado: ' + '<strong>' + data.artista + ' </strong></div>'
          },
          highlightBorderWidth: 3
        },

        fills: {
        'Sur America': '#CC4731',
        'Centro America': '#306596',
        'Norte America': 'green',
        defaultFill: 'gray'
      },
      data:{
        "CHL": {
            "fillKey": "Sur America",
            "artista": "Wisin"
        },
        "ARG": {
            "fillKey": "Sur America",
            "artista": "Becky G"
        },
        "COL": {
            "fillKey": "Sur America",
            "artista": "Yandel"
        },
        "BRA": {
            "fillKey": "Sur America",
            "artista": "Yandel"
        },
        "ECU": {
            "fillKey": "Sur America",
            "artista": "Yandel"
        },
        "PER": {
            "fillKey": "Sur America",
            "artista": "Yandel"
        },
        "URY": {
            "fillKey": "Sur America",
            "artista": "Yandel"
        },
        "PRY": {
            "fillKey": "Sur America",
            "artista": "Yandel"
        },
        "BOL": {
            "fillKey": "Sur America",
            "artista": "Yandel"
        },
        "MEX": {
            "fillKey": "Norte America",
            "artista": "Yandel"
        },
        "PAN": {
            "fillKey": "Centro America",
            "artista": "Yandel"
        },
        "VEN": {
            "fillKey": "Sur America",
            "artista": "Yandel"
        },
        "CRI": {
            "fillKey": "Centro America",
            "artista": "Yandel"
        },
        "HND": {
            "fillKey": "Centro America",
            "artista": "Yandel"
        },
        "PRI": {
            "fillKey": "Centro America",
            "artista": "Yandel"
        },
        "NIC": {
            "fillKey": "Centro America",
            "artista": "Yandel"
        },
        "GTM": {
            "fillKey": "Centro America",
            "artista": "Yandel"
        },
        "SLV": {
            "fillKey": "Centro America",
            "artista": "Yandel"
        },
        "CUB": {
            "fillKey": "Centro America",
            "artista": "Yandel"
        }
      }

      });
      }
    }
</script>