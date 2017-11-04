import Vue from 'vue';
import VueRouter from 'vue-router';
import Index from './Index.vue';
import Actors from './Actors.vue';
import VueResource from 'vue-resource';
require("./style.scss");
import addUser from './addUser.vue';
import grafico from './grafico.vue';
import totalGraph from './totalGraph.vue';
import totalGraph2 from "./totalGraph2.vue";
import nuevoGraph from "./graficoPrueba.vue";

import App from './App.vue';
Vue.use(VueRouter);
Vue.use(VueResource);
const routes = [
  { path: '/index', alias: '/', component: Index},
  { path:'/actors', component: Actors},
  { path: '/newActor', component: addUser },
  { path: '/BarGraph', component: totalGraph},
  { path: '/BarGraph2', component: totalGraph2},
  { path: '/mapa', component: nuevoGraph}
]

// Create the router instance and pass the `routes` option
const router = new VueRouter({
  routes
})

new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
