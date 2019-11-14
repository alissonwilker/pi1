<template>
  <div>
    <form @submit="addPessoaFisica">
      <input type="text" v-model="cpf" name="cpf" placeholder="CPF da pessoa física..." />
      <input type="text" v-model="nome" name="nome" placeholder="Nome da pessoa física..." />
      <input type="submit" value="Adicionar" class="btn" />
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      nome: "",
      cpf: ""
    };
  },
  methods: {
    addPessoaFisica(e) {
      e.preventDefault();
      const newPessoaFisica = {
        nome: this.nome,
        cpf: this.cpf
      };
      this.$emit("add-pessoafisica", newPessoaFisica);
      this.nome = "";
      this.cpf = "";
    }
  }
};
</script>
 
<style/>