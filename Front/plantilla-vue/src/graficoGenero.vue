<template>

<div class="w3-container w3-center">
    <h1> Géneros musicales con sus respectivas valoraciones</h1>
    <h5> Fecha y hora desde la construcción del gráfico {{ tiempo.time }}</h5>
    <br>

  <div  id="chartdiv"></div>
  <h5> Total comentarios negativos= {{ todo.negativos }}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    Total comentarios positivos= {{ todo.positivos }} </h5> 
  <h5>Total comentarios emitidos a la fecha= {{todo.total}}</h5>



</div>

</template>


<script>
  export default{
    data(){
      return{
        datos:  [],
        tiempo: [],
        todo: []
      }
    },
    mounted:function(){
      this.$http.get('http://localhost:2323/backend-tbd/generos')
    .then(response=>{
       // get body data
      this.datos = response.body;
     console.log('datos',this.datos);

      this.algo();
    }, response=>{
       // error callback
       console.log('error cargando lista');
    });

    this.$http.get('http://localhost:2323/backend-tbd/time/generos')
    .then(response=>{
       // get body data
      this.tiempo = response.body; 
     console.log('tiempo',this.tiempo)
    }, response=>{
       // error callback
       console.log('error cargando lista');
    });

    this.$http.get('http://localhost:2323/backend-tbd/generos/total')
    .then(response=>{
       // get body data
      this.todo = response.body; 
     console.log('tiempo',this.todo)
    }, response=>{
       // error callback
       console.log('error cargando lista');
    });


    },
    methods:{
      algo:function(){
           var chart = AmCharts.makeChart("chartdiv", {
          "type": "serial",
          "theme": "light",
          "rotate": true,
          "marginBottom": 50,
          "dataProvider": this.datos,
          "startDuration": 1,
          "graphs": [{
            "fillAlphas": 0.7,
            "lineAlpha": 0.4,
            "type": "column",
            "valueField": "comentariosPositivos",
            "title": "Comentarios positivos",
            "labelText": "[[value]]",
            "clustered": false,
            "labelFunction": function(item) {
              return Math.abs(item.values.value);
            },
            "balloonFunction": function(item) {
              return item.category + ": " + Math.abs(item.values.value)+ "%";
            }
          }, {
            "fillAlphas": 0.7,
            "lineAlpha": 0.4,
            "type": "column",
            "valueField": "comentariosNegativos",
            "title": "Comentarios negativos",
            "labelText": "[[value]]" ,
            "clustered": false,
            "labelFunction": function(item) {
              return Math.abs(item.values.value);
            },
            "balloonFunction": function(item) {
              return item.category + ": " + Math.abs(item.values.value)+"%";
            }
          }],
          "categoryField": "nombre",
          "categoryAxis": {
            "gridPosition": "start",
            "gridAlpha": 0.5,
            "axisAlpha": 1
          },
          "valueAxes": [{
            "gridAlpha": 0,
            "ignoreAxisWidth": true,
            "labelFunction": function(value) {
              return Math.abs(value) ;
            },
            "guides": [{
              "value": 0,
              "lineAlpha": 0.2
            }]
          }],
          "balloon": {
            "fixedPosition": true
          },
          "chartCursor": {
            "valueBalloonsEnabled": false,
            "cursorAlpha": 0.15,
            "fullWidth": true
          },
          "allLabels": [{
            "text": "Comentarios Negativos",
            "x": "28%",
            "y": "97%",
            "bold": true,
            "align": "middle"
          }, {
            "text": "Comentarios Positivos",
            "x": "75%",
            "y": "97%",
            "bold": true,
            "align": "middle"
          }],
         "export": {
            "enabled": false
          }

        });
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