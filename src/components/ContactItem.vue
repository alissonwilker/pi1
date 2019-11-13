<template>
  <div class="contact-item">
    <p>
      {{ contact.id }}
      {{ contact.name }}
      <button
        @click="$emit('del-contact', contact.id)">x</button>
    </p>
  </div>
</template>

<script>
export default {
  props: ["contact"]
};
</script>

<style scoped>
.contact-item {
  color: red;
}
</style>