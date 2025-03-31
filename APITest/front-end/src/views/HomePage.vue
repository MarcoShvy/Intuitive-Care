<template>
  <div>
    <SearchOperadoras />
  </div>
</template>

<script>
import SearchOperadoras from '@/components/SearchOperadoras.vue';

export default {
  name: 'HomePage',  // Renomeie o componente para "HomePage"
  components: {
    SearchOperadoras
  }
};
</script>

<style scoped>
</style>
