<template>
	<div class= "w3-container w3-row-padding" id="grafo">
	<br><br>
	<h2>Gráfico de relaciones entre usuarios y artistas </h2>
		<div class="w3-center w3-card-4" id="chart"></div>

	</div>
	
</template>

<script>
export default{
	data: function(){
		return{
			data: []
		}
	},
	methods:{
		cargarGrafo: function(json){

			var width = 1000,
		    height = 900

		    var body = d3.select("#grafo");
			var svg = body.select('#chart')
				.append("svg")
			    .attr("width", width)
			    .attr("height", height);

			var force = d3.layout.force()
			    .gravity(0.05)
			    .distance(150)
			    .charge(-100)
			    .size([width, height]);

  force
      .nodes(json.nodes)
      .links(json.links)
      .start();

  var link = svg.selectAll(".link")
      .data(json.links)
    .enter().append("line")
      .attr("class", "link")
      .style("stroke-width", function(d) { return Math.sqrt(d.weight*7); });

  var node = svg.selectAll(".node")
      .data(json.nodes)
    .enter().append("g")
      .attr("class", "node")
      .call(force.drag);

  var color = d3.scale.category20();

  var x = d3.scale.linear()
    .domain([d3.min(json.nodes, d=>d.weight), d3.max(json.nodes, d=>d.weight)])
    .range([10, 100]);

   node.append("circle")
      .attr("r", function(d) { return x(d.weight)})
      .style("fill",function(d,i){
      	if (d.tweet == "nothing"){
      		return color("steelblue");
      	}
      	return color("red");
      });

  node.append("text")
      .attr("dx", 14)
      .attr("dy", ".35em")
      .text(function(d) { return d.userName });

  force.on("tick", function() {
    link.attr("x1", function(d) { return d.source.x; })
        .attr("y1", function(d) { return d.source.y; })
        .attr("x2", function(d) { return d.target.x; })
        .attr("y2", function(d) { return d.target.y; });

    node.attr("transform", function(d) { return "translate(" + d.x  + "," + d.y + ")"; });
  });
		
		}
	},
	mounted: function(){
		this.$http.get('http://localhost:2323/backend-tbd/graph')
	    .then(response=>{
	      this.data = response.body;
	      console.log("wea bacan");
	      console.log(this.data);
	      this.cargarGrafo(this.data);
	    }, response=>{
	      console.log("error de conexion");
	    })
	}
}
</script>

<style>

.link {
  stroke: #ccc;
}

.node text {
  pointer-events: none;
  font: 14px sans-serif;
}
.link {
      stroke: #00FF00FF;
      stroke-opacity: 0.5;
    }

</style>