<template>
  <div>
      <div v-bind:key="contact.id" v-for="contact in contacts">
          <ContactItem v-bind:contact="contact" v-on:del-contact="$emit('del-contact', contact.id)"/>
      </div>
  </div>
</template>

<script>
import ContactItem from './ContactItem.vue';

export default {
    components: {
       ContactItem 
    },
    props: ["contacts"]
}
</script>

<style/>