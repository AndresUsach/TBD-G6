import Vue from 'vue';
import VueRouter from 'vue-router';
import Index from './Index.vue';
import Actors from './Actors.vue';
import VueResource from 'vue-resource';
require("./style.scss");
import addUser from './addUser.vue';
import generoPlus from './grafico.vue';
import artistaPlus from './grafico2.vue';
import artistaMinus from './grafico3.vue';
import generoMinus from './grafico4.vue';
import totalGraph from './totalGraph.vue';
import totalGraph2 from "./totalGraph2.vue";
import mapa from "./mapaLatino.vue";
import grafo from "./grafo.vue";
import Login from './Login.vue';
import historial from "./historialArtista.vue";
import administracion from "./administracion.vue";
import newGenre from "./newGenre.vue";
import Prueba from "./Prueba.vue";
import graficoArtista from "./graficoArtista.vue";
import graficoGenero from "./graficoGenero.vue";
import SelectMode from './selectAdmin.vue';



import App from './App.vue';
Vue.use(VueRouter);
Vue.use(VueResource);
const routes = [
  { path: '/index', alias: '/', component: Index},
  { path:'/actors', component: Actors},
  { path: '/newActor', component: addUser },
  { path: '/generoPositivo', component: generoPlus},
  { path: '/generoNegativo', component: generoMinus},
  { path: '/artistaPositivo', component: artistaPlus},
  { path: '/artistaNegativo', component: artistaMinus},
  { path: '/graph', component: grafo},
  { path: '/mapa', component: mapa},
  { path: '/historial', component: historial},
  { path: '/admin', component: administracion},
  { path:'/login', component: Login},
  { path: '/newGenre', component: newGenre},
  { path: '/grafArtist', component: graficoArtista},
  { path: '/grafGen', component: graficoGenero},
  { path: '/Select', component: SelectMode}
  
]


// Create the router instance and pass the `routes` option
export const router = new VueRouter({
  routes
})

new Vue({
  el: '#app',
  router,
  render: h => h(App)
})

