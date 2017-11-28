<template>
<div class="w3-container">
<div class="w3-card-4" style="width: 70%; margin-left: 150px;margin-top: 40px">
<div class="w3-container w3-teal">
  <h1>Mapa con información de artistas por paises</h1>
  <h5> Fecha y hora desde la construcción del gráfico {{ tiempo.time }}</h5>
</div>
<div class="pull-left">
<div id="mapa" class="pull-left" style="position: relative;width: 1050px; height: 700px;margin-left: 60px;"></div>
</div>
</div>
</div>
</template>


        <script src="http://d3js.org/d3.v3.min.js"></script>
        <script src="http://d3js.org/topojson.v1.min.js"></script>
        <script src="http://datamaps.github.io/scripts/datamaps.usa.min.js"></script>
<script>
    import Datamap from 'datamaps';
    export default{
        data: function(){
            return{
                tiempo: []
            }
        },
      mounted: function(){
        this.$http.get('http://localhost:2323/backend-tbd/time/mapa')
            .then(response=>{
               // get body data
              this.tiempo = response.body; 
             console.log('tiempo',this.tiempo)
            }, response=>{
               // error callback
               console.log('error cargando lista');
            });
        var map = new Datamap({element: document.getElementById('mapa'),
                scope: 'world',
                paises: [],
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
                    return '<div class="hoverinfo">' + '<h6><strong>' + geography.properties.name + '</h6></strong>'  + 'Artista con mayor aprobación es ' + '<strong>' + data.artista + ' </strong>' + 'con ' + data.cantidadPositivos + ' comentarios</div>'
                  },
                  highlightBorderWidth: 3
                },

                fills: {
                'SurAmerica': '#CC4731',
                'CentroAmerica': '#306596',
                'NorteAmerica': 'green',
                defaultFill: 'gray'
              },

                dataUrl: 'http://localhost:2323/backend-tbd/paises/mostrarMapa',
                //dataUrl: 'data.csv',
                dataType: 'json',
              data:{/*
                "CHL": {
                    "fillKey": "SurAmerica",
                    "artista": "Wisin"
                },
                "ARG": {
                    "fillKey": "SurAmerica",
                    "artista": "Becky G"
                },
                "COL": {
                    "fillKey": "SurAmerica",
                    "artista": "Yandel"
                },
                "ECU": {
                    "fillKey": "SurAmerica",
                    "artista": "Yandel"
                },
                "PER": {
                    "fillKey": "SurAmerica",
                    "artista": "Yandel"
                },
                "URY": {
                    "fillKey": "SurAmerica",
                    "artista": "Yandel"
                },
                "PRY": {
                    "fillKey": "SurAmerica",
                    "artista": "Yandel"
                },
                "BOL": {
                    "fillKey": "SurAmerica",
                    "artista": "Yandel"
                },
                "MEX": {
                    "fillKey": "NorteAmerica",
                    "artista": "Yandel"
                },
                "PAN": {
                    "fillKey": "CentroAmerica",
                    "artista": "Yandel"
                },
                "VEN": {
                    "fillKey": "SurAmerica",
                    "artista": "Yandel"
                },
                "CRI": {
                    "fillKey": "CentroAmerica",
                    "artista": "Yandel"
                },
                "HND": {
                    "fillKey": "CentroAmerica",
                    "artista": "Yandel"
                },
                "PRI": {
                    "fillKey": "CentroAmerica",
                    "artista": "Yandel"
                },
                "NIC": {
                    "fillKey": "CentroAmerica",
                    "artista": "Yandel"
                },
                "GTM": {
                    "fillKey": "CentroAmerica",
                    "artista": "Yandel"
                },
                "SLV": {
                    "fillKey": "CentroAmerica",
                    "artista": "Yandel"
                },
                "CUB": {
                    "fillKey": "CentroAmerica",
                    "artista": "Yandel"
                }*/
              }

      });
      }
    }
</script>