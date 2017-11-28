<template>

<div class="w3-container w3-center">
    <h1> Artistas con sus respectivas valoraciones</h1>
    <br><br>

  <div  id="chartdiv"></div>

</div>

</template>


<script>
  export default{
    data(){
      return{
        datos:  []
      }
    },
    mounted:function(){
      this.$http.get('http://localhost:2323/backend-tbd/artistas')
    .then(response=>{
       // get body data
      this.datos = response.body;
     console.log('datos',this.datos);

      this.algo();
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
    });



    },
    methods:{
      algo:function(){
        console.log("aqui vienedssad ", this.datos);
           var chart = AmCharts.makeChart("chartdiv", {
          "type": "serial",
          "theme": "light",
          "rotate": true,
          "marginBottom": 50,
          "dataProvider": this.datos/*[{
            "age": "85+",
            "male": -0.1,
            "female": 0.3
          }, {
            "age": "80-54",
            "male": -0.2,
            "female": 0.3
          }, {
            "age": "75-79",
            "male": -0.3,
            "female": 0.6
          }, {
            "age": "70-74",
            "male": -0.5,
            "female": 0.8
          }, {
            "age": "65-69",
            "male": -0.8,
            "female": 1.0
          }, {
            "age": "60-64",
            "male": -1.1,
            "female": 1.3
          }, {
            "age": "55-59",
            "male": -1.7,
            "female": 1.9
          }, {
            "age": "50-54",
            "male": -2.2,
            "female": 2.5
          }, {
            "age": "45-49",
            "male": -2.8,
            "female": 3.0
          }, {
            "age": "40-44",
            "male": -3.4,
            "female": 3.6
          }, {
            "age": "35-39",
            "male": -4.2,
            "female": 4.1
          }, {
            "age": "30-34",
            "male": -5.2,
            "female": 4.8
          }, {
            "age": "25-29",
            "male": -5.6,
            "female": 5.1
          }, {
            "age": "20-24",
            "male": -5.1,
            "female": 5.1
          }, {
            "age": "15-19",
            "male": -3.8,
            "female": 3.8
          }, {
            "age": "10-14",
            "male": -3.2,
            "female": 3.4
          }, {
            "age": "5-9",
            "male": -4.4,
            "female": 4.1
          }, {
            "age": "0-4",
            "male": -5.0,
            "female": 4.8
          }]*/,
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
