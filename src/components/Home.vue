<template>
  <div class="hello">
    <AddPessoaFisica v-on:add-pessoafisica="addPessoaFisica" />
    <UpdatePessoaFisica v-on:update-pessoafisica="updatePessoaFisica" />
    <PessoasFisicas
      v-bind:pessoasFisicas="pessoasFisicas"
      v-on:del-pessoaFisica="deletePessoaFisica"
    />
  </div>
</template>

<script>
import PessoasFisicas from "../components/PessoasFisicas";
import AddPessoaFisica from "../components/AddPessoaFisica";
import UpdatePessoaFisica from "../components/UpdatePessoaFisica";
import axios from "axios";

export default {
  components: {
    AddPessoaFisica,
    UpdatePessoaFisica,
    PessoasFisicas
  },
  data() {
    return {
      pessoasFisicas: []
    };
  },
  methods: {
    deletePessoaFisica(id) {
      axios
        .delete("http://localhost:8181/pessoasFisicas/" + id)
        .then(
            (this.pessoasFisicas = this.pessoasFisicas.filter(
              pessoaFisica => pessoaFisica.id !== id
            )) 
        )
        // eslint-disable-next-line no-console
        .catch(err => console.log(err));
    },
    addPessoaFisica(newPessoaFisica) {
      const { nome, cpf } = newPessoaFisica;
      axios
        .post("http://localhost:8181/pessoasFisicas/", {
          nome,
          cpf
        })
        .then(res => (this.pessoasFisicas = [...this.pessoasFisicas, res.data]))
        // eslint-disable-next-line no-console
        .catch(err => console.log(err));
    },
    updatePessoaFisica(pessoaFisica) {
      const { id, nome } = pessoaFisica;
      axios
        .put("http://localhost:8181/pessoasFisicas/" + id, {
          id,
          nome
        })
        .then(
          (res => this.pessoasFisicas[this.pessoasFisicas.findIndex(pf => pf.id == res.data.id)].nome = res.data.nome)
        )
        // eslint-disable-next-line no-console
        .catch(err => console.log(err));
    }
  },
  created() {
    axios
      .get("http://localhost:8181/pessoasFisicas/")
      .then(res => (this.pessoasFisicas = res.data))
      // eslint-disable-next-line no-console
      .catch(err => console.log(err));
  }
};

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
