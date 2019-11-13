<template>
  <div>
    <form @submit="addContact">
      <input type="text" v-model="name" name="name" placeholder="Nome do contato..." />
      <input type="submit" value="Adicionar" class="btn" />
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      name: ""
    };
  },
  methods: {
    addContact(e) {
      e.preventDefault();
      const newContact = {
        name: this.name
      };
      this.$emit("add-contact", newContact);
      this.name = "";
    }
  }
};
</script>
 
<style/>