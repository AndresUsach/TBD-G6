<template>  


<div class="w3-container w3-center" id="grafico4">
  <h1> Géneros con valoraciones negativas</h1>
    <svg width="960" height="500"></svg>

</div>
  
</template>

<script>
import * as d3 from 'd3';
export default{
  data: function(){
    return {
      data2 : []
    }
  },
  mounted:function(){
    console.log('grafico4.vue');
    // GET /someUrl
    this.$http.get('http://localhost:2323/backend-tbd/generos')

    .then(response=>{
       // get body data
      this.data2 = response.body;
      this.loadGraph(this.data2); 
     console.log('data2',this.data2)
    }, response=>{
       // error callback
       console.log('error cargando lista');
    })
   
    
  },
  methods:{
    loadGraph:function(data){
      var valor= d3.select("#grafico4");
        var svg = valor.select("svg"),
        margin = {top: 10, right: 90, bottom: 100, left: 140},
        width = +svg.attr("width") - margin.left - margin.right,
        height = +svg.attr("height") - margin.top - margin.bottom;

    var x = d3.scaleBand().rangeRound([0, width]).padding(0.45),
        y = d3.scaleLinear().rangeRound([height, 0]);

    var g = svg.append("g")
        .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    

      x.domain(data.map(function(d) { return d.nombre; }));
      y.domain([0, d3.max(data, function(d) { return d.comentariosNegativos; })]);
      //y.domain([0, 1]); // eje y en rangos de 0% hasta el 100%

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
        .data(data)
        .enter().append("rect")
          .attr("class", "bar")
          .attr("x", function(d) { return x(d.nombre); })
          .attr("y", function(d) { return y(d.comentariosNegativos); })
          .attr("width", x.bandwidth())
          .attr("height", function(d) { return height + - y(d.comentariosNegativos); });
    
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
    margin: 30px;
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
    fill: forestgreen;
  }

  .axis--x path {
    display: block;
  }

  .line {
    fill: forestgreen;
    stroke: forestgreen;
    stroke-width: 2px;
  }

  .grid line {
    stroke: lightgrey;
    stroke-opacity: 0.7;
    shape-rendering: crispEdges;
  }

  .grid path {
    stroke-width: 0;
  }

</style>