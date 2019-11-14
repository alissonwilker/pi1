<template>
  <div class="pessoaFisica-item">
    <p>
      {{ pessoaFisica.id }}
      {{ pessoaFisica.cpf }}
      {{ pessoaFisica.nome }}
      <button
        @click="$emit('del-pessoaFisica', pessoaFisica.id)"
        class="del"
      >x</button>
    </p>
  </div>
</template>

<script>
export default {
  props: ["pessoaFisica"]
};
</script>

<style scoped>
.pessoaFisica-item {
  color: red;
}
</style>