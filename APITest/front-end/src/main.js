import { createApp } from 'vue';
import App from './App.vue';

const app = createApp(App);

// Certifique-se de que qualquer configuração que você esteja tentando acessar
// está sendo inicializada corretamente
app.mount('#app');
