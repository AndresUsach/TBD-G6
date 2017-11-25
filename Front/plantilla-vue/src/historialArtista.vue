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
	<svg width="960" height="500"></svg>


</div>

	
</template>
<script>

import * as d3 from 'd3';
export default{
  data: function(){
    return {
      artistas: [],
      registros: [],
      selected: ''
    }
  },
  mounted:function(){
    console.log('grafico2.vue');
    // GET /someUrl
    this.$http.get('http://localhost:2323/backend-tbd/artistas')
    .then(response=>{
       // get body data
      this.artistas = response.body;
     console.log('data2',this.data2)
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
    })
   
    
  },
  methods:{
    loadGraph:function(selected, registro){
    	/*var svgInicial = document.createElement("svg");
		div.style.width = "100px";
		div.style.height = "100px";
		div.style.background = "red";
		div.style.color = "white";
		div.innerHTML = "Hello";
		document.getElementById("grafico3").appendChild(svgInicial);*/
		document.getElementById("generar").disabled = true;
    	var value= d3.select("#grafico3");
      	var svg = value.select("svg"),
		    margin = {top: 10, right: 90, bottom: 100, left: 140},
		    width = +svg.attr("width") - margin.left - margin.right,
		    height = +svg.attr("height") - margin.top - margin.bottom;

		var x = d3.scaleBand().rangeRound([0, width]).padding(0.45),
		    y = d3.scaleLinear().rangeRound([height, 0]);
		var arrayValores = [];
		for(var i = registro.length -1; i >= 0 ; i--){
		    if(registro[i].nombre != selected){
		        registro.splice(i, 1);
		    }
		}

		

		  x.domain(registro.map(function(d) { if(d.nombre === selected){
		  	arrayValores.push(d.comentariosPositivos);

		  	return d.fecha;
		  	 }
		  	}));
		  y.domain([0, d3.max(arrayValores)]);
		  //y.domain([0, 1]); // eje y en rangos de 0% hasta el 100%

		var g = svg.append("g")
		    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

		  g.append("g")
		      .attr("class", "axis axis--x")
		      .attr("transform", "translate(0,"+ height +")")
		      .call(d3.axisBottom(x));

		  g.append("g")
		      .attr("class", "axis axis--y")
		      .call(d3.axisLeft(y).ticks(10)) // ticks indica la cantidad de indices del eje y
		    .append("text")
		      .attr("transform", "rotate(-90)")
		      .attr("y", 6)
		      .attr("dy", "0.71em")
		      .attr("text-anchor", "end")
		      .text("Frequency");

		  g.selectAll(".bar")
		    .data(registro)
		    .enter().append("rect")
		      .attr("class", "bar")
		      .attr("x", function(d) { return x(d.fecha); })
		      .attr("y", function(d) { return y(d.comentariosPositivos); })
		      .attr("width", x.bandwidth())
		      .attr("height", function(d) { return height + - y(d.comentariosPositivos); });
		
    }

  }
}
</script>
<style> 
	.chart div {
	  font: 10px sans-serif;
	  background-color: forestgreen;
	  text-align: right;
	  padding: 3px;
	  margin: 1px;
	  color: white;
	}

	.chart rect {
	  fill: forestgreen;
	}

	.chart text {
	  fill: white;
	  font: 10px sans-serif;
	  text-anchor: end;
	}


	.bar {
	  fill: forestgreen;
	}

	.bar:hover {
	  fill: firebrick;
	}

	.axis--x path {
	  display: block;
	}

	.line {
	  fill: none;
	  stroke: forestgreen;
	  stroke-width: 0.1px;
	}

	.grid line {
	  stroke: lightgrey;
	  stroke-opacity: 0.2;
	  shape-rendering: crispEdges;
	}

	.grid path {
	  stroke-width: 0;
	}

</style>