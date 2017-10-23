<template>
	 <div class="w3-row">

		  <bar-graph></bar-graph>
		  <div class="w3-center">
		  <bar-graph4></bar-graph4>
		  </div>

	  	 
	</div> 

</template>

<script>
	
import BarGraph from './grafico.vue';
import BarGraph4 from './grafico4.vue';
export default {


components:{
	BarGraph,
	BarGraph4
}

}
</script>
