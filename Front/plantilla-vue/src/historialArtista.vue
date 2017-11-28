<template>	
 	<!-- este grafico corresponde a los comentarios negativos de los artistas -->


<div class="w3-container w3-center"  id="grafico3">
	<h1> Gráfico para: {{ selected }}</h1>
	<select v-model="selected">
		<option v-for="a in artistas">
			{{ a.nombre }}
		</option>
	</select>
	<button id="generar" v-on:click="loadGraph(selected,registros)">Generar gráfico</button>
	<input type="button" value="Reiniciar" onClick="history.go(0)">
	<div id="chartdiv"></div>		


</div>

	
</template>
<script>

import * as d3 from 'd3';
export default{
  data: function(){
    return {
      artistas: [],
      nombreArtistas: [],
      registros: [],
      selected: ''
    }
  },
  mounted:function(){
    // GET /someUrl
    this.$http.get('http://localhost:2323/backend-tbd/artistas')
    .then(response=>{
       // get body data
      this.artistas = response.body;
    }, response=>{
       // error callback
       console.log('error cargando lista');
    });
    this.$http.get('http://localhost:2323/backend-tbd/registro')

    .then(response=>{
       // get body data
      this.registros = response.body; 
     console.log('registros',this.registros)
    }, response=>{
       // error callback
       console.log('error cargando lista');
    });
    for (var i = 0; i < this.artistas.length -1; i++) {
    	this.nombreArtistas.push(this.artistas[i]);
    }
    console.log('algo:',this.nombreArtistas);
   
    
  },
  methods:{
    loadGraph:function(selected, registro){
        document.getElementById("generar").disabled = true;
		var arrayValores = [];
		for(var i = registro.length -1; i >= 0 ; i--){
		    if(registro[i].nombre != selected){
		        registro.splice(i, 1);
		    }
		}
		console.log(registro);
		var chart = AmCharts.makeChart("chartdiv", {
		    "type": "serial",
		    "theme": "light",
		    "legend": {
		        "useGraphSettings": true
		    },
		    "marginTop":0,
		    "marginRight": 80,
		    "dataProvider": registro,
		    "valueAxes": [{
		        "axisAlpha": 0,
		        "position": "left"
		    }],
		    "graphs": [{
		        "id":"g1",
		        "balloonText": "[[category]]<br><b><span style='font-size:14px;'>[[comentariosPositivos]]</span></b>",
		        "bullet": "round",
		        "bulletSize": 8,
		        "lineColor": "#1E90FF",
		        "lineThickness": 2,
		        "negativeLineColor": "#637bb6",
		        "type": "smoothedLine",
		        "valueField": "comentariosPositivos",
		        "title": "Comentarios Positivos"
		    },
		    {
		        "id":"g2",
		        "balloonText": "[[category]]<br><b><span style='font-size:14px;'>[[comentariosNegativos]]</span></b>",
		        "bullet": "round",
		        "bulletSize": 8,
		        "lineColor": "#d1655d",
		        "lineThickness": 2,
		        "negativeLineColor": "#637bb6",
		        "type": "smoothedLine",
		        "valueField": "comentariosNegativos",
		        "title": "Comentarios Negativos"
		    }],
		    "chartScrollbar": {},
		    "chartCursor": {
		        "categoryBalloonDateFormat": "YYYY",
		        "cursorAlpha": 0,
		        "valueLineEnabled":true,
		        "valueLineBalloonEnabled":true,
		        "valueLineAlpha":0.5,
		        "fullWidth":true
		    },
		    "dataDateFormat": "YYYY-MM-DD",
		    "categoryField": "fecha",
		    "categoryAxis": {
		        "minPeriod": "YYYY-MM-DD",
		        "parseDates": false,
		        "minorGridAlpha": 0.1,
		        "minorGridEnabled": true
		    },
		    "export": {
		        "enabled": false
		    }
		});

		chart.addListener("rendered", zoomChart);
		if(chart.zoomChart){
			chart.zoomChart();
		}
		function zoomChart(){
		    chart.zoomToIndexes(Math.round(chart.dataProvider.length * 0.4), Math.round(chart.dataProvider.length * 0.55));
		}
		
    }

  }
}
</script>

<style>

#chartdiv {
  width: 100%;
  height: 500px;
}

</style>