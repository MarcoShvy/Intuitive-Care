<template>
  <div id="app" class="container">
    <h1>Buscar Operadoras</h1>
    <input v-model="termo" @input="buscarOperadoras" placeholder="Digite um termo..." />
    <ul>
      <li v-for="operadora in operadoras" :key="operadora.CNPJ">
        {{ operadora.Razao_Social }} - {{ operadora.CNPJ }}
      </li>
    </ul>
  </div>
</template>

<script>
import { ref } from 'vue';

export default {
  setup() {
    const termo = ref('');
    const operadoras = ref([]);

    const buscarOperadoras = async () => {
      if (termo.value.length < 3) {
        operadoras.value = [];
        return;
      }

      try {
        const response = await fetch(`http://localhost:5000/buscar_operadoras?termo=${termo.value}`);
        const data = await response.json();
        if (data.success) {
          operadoras.value = data.data.map(op => ({
            Razao_Social: op.Razao_Social,
            CNPJ: op.CNPJ
          }));
        } else {
          operadoras.value = [];
        }
      } catch (error) {
        console.error('Erro na busca das operadoras:', error);
        operadoras.value = [];
      }
    };

    return { termo, operadoras, buscarOperadoras };
  }
};
</script>

<style>
.container {
  max-width: 600px;
  margin: 0 auto;
  text-align: center;
  padding: 20px;
}
input {
  width: 100%;
  padding: 10px;
  margin: 10px 0;
}
ul {
  list-style: none;
  padding: 0;
}
li {
  padding: 5px;
  border-bottom: 1px solid #ccc;
}
</style>