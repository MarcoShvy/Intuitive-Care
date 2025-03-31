<template>
  <div>
    <h1>Buscar Operadoras</h1>
    <input v-model="termo" placeholder="Digite o termo de busca" @input="buscarOperadoras" />
    <button @click="buscarOperadoras">Buscar</button>

    <div v-if="loading">Carregando...</div>
    <div v-if="error" class="error">{{ error }}</div>

    <div v-if="operadoras.length > 0">
      <ul>
        <li v-for="operadora in operadoras" :key="operadora.CNPJ">
          <strong>{{ operadora.Nome_Fantasia }}</strong><br />
          CNPJ: {{ operadora.CNPJ }}
        </li>
      </ul>
    </div>

    <div v-if="operadoras.length === 0 && !loading">
      <p>Nenhuma operadora encontrada.</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      termo: '',
      operadoras: [],
      loading: false,
      error: ''
    };
  },
  methods: {
    async buscarOperadoras() {
      if (!this.termo) return;

      this.loading = true;
      this.error = '';
      this.operadoras = []; // Limpa antes de nova busca

      try {
        const response = await axios.get('http://127.0.0.1:5000/buscar_operadoras', {
          params: {
            termo: this.termo,
            limite: 10
          }
        });

        console.log("Resposta da API:", response.data); // Verifica a resposta completa

        // Ajuste na extração dos dados
        if (response.data && response.data.success && Array.isArray(response.data.data)) {
          this.operadoras = [...response.data.data]; // Acesso correto aos dados
          console.log("Operadoras carregadas:", this.operadoras);
        } else {
          this.error = 'Erro na resposta da API: estrutura inesperada.';
        }
      } catch (error) {
        console.error('Erro na requisição:', error);
        this.error = 'Erro ao comunicar com a API: ' + error.message;
      } finally {
        this.loading = false;
      }
    }
  }
};
</script>

<style scoped>
.error {
  color: red;
}
</style>
