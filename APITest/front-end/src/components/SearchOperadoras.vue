<template>
  <div>
    <input 
      type="text" 
      v-model="termo" 
      placeholder="Pesquisar operadora..." 
      @input="buscarOperadoras" 
    />
    <ul v-if="operadoras.length">
      <li v-for="operadora in operadoras" :key="operadora.CNPJ">
        {{ operadora.Razao_Social }} - {{ operadora.CNPJ }}
      </li>
    </ul>
    <p v-else>Nenhuma operadora encontrada.</p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      termo: '',  // termo da busca
      operadoras: [],  // resultados da busca
    };
  },
  methods: {
    buscarOperadoras() {
      // Verifica se o termo está vazio antes de fazer a requisição
      if (this.termo.trim() === '') {
        this.operadoras = [];
        return;
      }

      // Requisição para a API
      fetch(`http://localhost:5000/buscar_operadoras?termo=${this.termo}`)
        .then(response => {
          // Verifica se a resposta é válida
          if (!response.ok) {
            throw new Error(`Erro HTTP: ${response.status}`);
          }
          return response.json();
        })
        .then(data => {
          console.log('Dados recebidos:', data);
          if (data.success) {
            this.operadoras = data.data;  // Armazena os resultados da busca
          } else {
            this.operadoras = [];  // Se não for sucesso, limpa os resultados
          }
        })
        .catch(error => {
          console.error('Erro na busca das operadoras:', error);
          this.operadoras = [];  // Limpa os resultados em caso de erro
        });
    }
  }
};
</script>
