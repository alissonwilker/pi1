<template>
  <div>
      <div v-bind:key="pessoaFisica.id" v-for="pessoaFisica in pessoasFisicas">
          <PessoaFisicaItem v-bind:pessoaFisica="pessoaFisica" v-on:del-pessoaFisica="$emit('del-pessoaFisica', pessoaFisica.id)"/>
      </div>
  </div>
</template>

<script>
import PessoaFisicaItem from './PessoaFisicaItem';
export default {
    name: "PessoasFisicas",
    components: {
       PessoaFisicaItem 
    },
    props: ["pessoasFisicas"]
}
</script>

<style/>