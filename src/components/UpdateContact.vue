<template>
  <div>
    <form @submit="updateContact">
      <input type="text" v-model="id" name="id" placeholder="ID do contato..." />
      <input type="text" v-model="name" name="name" placeholder="Nome do contato..." />
      <input type="submit" value="Alterar" />
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      id: "",
      name: ""
    };
  },
  methods: {
    updateContact(e) {
      e.preventDefault();
      const contact = {
        id: this.id,
        name: this.name
      };
      this.$emit("update-contact", contact);
      this.id = "";
      this.name = "";
    }
  }
};
</script>
 
<style/>