<template>
	 <div class="w3-row">

		  <bar-graph1></bar-graph1>
		  <div class="w3-center">
		  <bar-graph2></bar-graph2>
		  </div>

	  	 
	</div> 

</template>

<script>
	
import BarGraph1 from './grafico2.vue';
import BarGraph2 from './grafico3.vue';
export default {


components:{
	BarGraph1,
	BarGraph2
}

}
</script>
