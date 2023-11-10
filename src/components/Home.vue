<template>
  <div>
    <AddContact v-on:add-contact="addContact" />
    <UpdateContact v-on:update-contact="updateContact" />
    <Contacts
      v-bind:contacts="contacts"
      v-on:del-contact="deleteContact"
    />
  </div>
</template>

<script>
import Contacts from "./Contacts.vue";
import AddContact from "./AddContact.vue";
import UpdateContact from "./UpdateContact.vue";
import axios from "axios";

export default {
  components: {
    AddContact,
    UpdateContact,
    Contacts
  },
  data() {
    return {
      contacts: []
    };
  },
  methods: {
    deleteContact(id) {
      axios
        .delete("http://localhost:8181/contacts/" + id)
        .then(
            (this.contacts = this.contacts.filter(
              contact => contact.id !== id
            )) 
        )
        // eslint-disable-next-line no-console
        .catch(err => console.log(err));
    },
    addContact(newContact) {
      const { name } = newContact;
      axios
        .post("http://localhost:8181/contacts/", {
          name
        })
        .then(res => (this.contacts = [...this.contacts, res.data]))
        // eslint-disable-next-line no-console
        .catch(err => console.log(err));
    },
    updateContact(contact) {
      const { id, name } = contact;
      axios
        .put("http://localhost:8181/contacts/" + id, {
          id,
          name
        })
        .then(
          (res => this.contacts[this.contacts.findIndex(c => c.id == res.data.id)].name = res.data.name)
        )
        // eslint-disable-next-line no-console
        .catch(err => console.log(err));
    }
  },
  created() {
    axios
      .get("http://localhost:8181/contacts/")
      .then(res => (this.contacts = res.data))
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
