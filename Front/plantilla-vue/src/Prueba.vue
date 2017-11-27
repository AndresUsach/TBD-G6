<template>
	
	<div class="w3-container w3-center"  id="graficoDoble">
		<svg width="960" height="500"></svg>
	</div>
</template>

<script>

import * as d3 from 'd3';
export default{
  data: function(){
    return {
      data2 : [],
      tiempo: []
    }
  },
  mounted:function(){
    // GET /someUrl
    this.$http.get('http://localhost:2323/backend-tbd/artistas')
    .then(response=>{
       // get body data
      this.data2 = response.body;
      this.loadGraph(this.data2);	
     console.log('data2',this.data2)
    }, response=>{
       // error callback
       console.log('error cargando lista');
    });
    this.$http.get('http://localhost:2323/backend-tbd/time/artistas')

    .then(response=>{
       // get body data
      this.tiempo = response.body; 
     console.log('tiempo',this.tiempo)
    }, response=>{
       // error callback
       console.log('error cargando lista');
    })
},
 methods:{
    loadGraph:function(data2){
    	var value= d3.select("#graficoDoble");
    	var svg = value.select("svg"),
	    margin = {top: 20, right: 80, bottom: 30, left: 50},
	    width = svg.attr("width") - margin.left - margin.right,
	    height = svg.attr("height") - margin.top - margin.bottom,
	    g = svg.append("g").attr("transform", "translate(" + margin.left + "," + margin.top + ")");


		var parseTime = d3.timeParse("%Y%m%d");

		var x = d3.scaleTime().range([0, width]),
		    y = d3.scaleLinear().range([height, 0]);


		var line = d3.line()
		    .curve(d3.curveBasis)
		    .x(function(d) { return x(d.nombre); })
		    .y(function(d) { return y(d.comentariosPositivos); });

		var cities = data2.comentariosPositivos.slice(1).map(function(id) {
			    return {
			      id: id,
			      values: data2.map(function(d) {
			        return {data2: d.nombre, comentariosPositivos: d[id]};
			      })
			    };
			  });

		x.domain(d3.extent(data2, function(d) { return d.nombre; }));

		y.domain([
	    d3.min(cities, function(c) { return d3.min(c.values, function(d) { return d.comentariosPositivos; }); }),
	    d3.max(cities, function(c) { return d3.max(c.values, function(d) { return d.comentariosNegativos; }); })
	  ]);




		var x = d3.scaleBand().rangeRound([0, width]).padding(0.45),
		    y = d3.scaleLinear().rangeRound([height, 0]);

		var g = svg.append("g")
		    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

		g.append("g")
      .attr("class", "axis axis--x")
      .attr("transform", "translate(0," + height + ")")
      .text("Artistas")
      .call(d3.axisBottom(x));

  g.append("g")
      .attr("class", "axis axis--y")
      .call(d3.axisLeft(y))
    .append("text")
      .attr("transform", "rotate(-90)")
      .attr("y", 6)
      .attr("dy", "0.71em")
      .attr("fill", "#000")
      .text("Comentarios");

  var city = g.selectAll("Comentarios")
    .data(comentariosPositivos, comentariosNegativos)
    .enter().append("g")
      .attr("class", "Comentarios");

  city.append("path")
      .attr("class", "line")
      .attr("d", function(d) { return line(d.values); })
      .style("stroke", function(d) { return z(d.id); });

  city.append("text")
      .datum(function(d) { return {id: d.id, value: d.values[d.values.length - 1]}; })
      .attr("transform", function(d) { return "translate(" + x(d.value.nombre) + "," + y(d.value.comentariosPositivos) + ")"; })
      .attr("x", 3)
      .attr("dy", "0.35em")
      .style("font", "10px sans-serif");
		
    }

  }
}
</script>

<style>

.axis--x path {
  display: none;
}

.line {
  fill: none;
  stroke: steelblue;
  stroke-width: 1.5px;
}

</style>