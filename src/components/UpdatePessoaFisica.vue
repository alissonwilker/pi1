<template>
  <div>
    <form @submit="updatePessoaFisica">
      <input type="text" v-model="id" name="id" placeholder="ID da pessoa física..." />
      <input type="text" v-model="nome" name="nome" placeholder="Nome da pessoa física..." />
      <input type="submit" value="Alterar" class="btn" />
    </form>
  </div>
</template>

<script>
export default {
  name: "UpdatePessoaFisica",
  data() {
    return {
      id: "",
      nome: ""
    };
  },
  methods: {
    updatePessoaFisica(e) {
      e.preventDefault();
      const pessoaFisica = {
        id: this.id,
        nome: this.nome
      };
      this.$emit("update-pessoafisica", pessoaFisica);
      this.id = "";
      this.nome = "";
    }
  }
};
</script>
 
<style/>