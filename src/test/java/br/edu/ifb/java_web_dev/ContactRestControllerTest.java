package br.edu.ifb.java_web_dev;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import br.edu.ifb.java_web_dev.model.Contact;
import br.edu.ifb.java_web_dev.persistence.ContactRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = br.edu.ifb.java_web_dev.ContactsApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ContactRestControllerTest {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private ContactRepository contactRepository;

	@BeforeEach
	public void resetDb() {
		contactRepository.deleteAll();
	}

	@Test
	public void testAddContact() throws IOException, Exception {
		Contact alisson = new Contact("Alisson");
		mvc.perform(post("/contacts/").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(alisson)));

		List<Contact> found = contactRepository.findAll();
		assertThat(found).extracting(Contact::getName).containsOnly("Alisson");
	}

	@Test
	public void testUpdateContact() throws IOException, Exception {
		Contact alisson = createTestContact("Alisson");
		alisson.setName("Alisson Wilker");

		mvc.perform(put("/contacts/{id}", alisson.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.toJson(alisson)));

		List<Contact> found = contactRepository.findAll();
		assertThat(found).extracting(Contact::getName).containsOnly("Alisson Wilker");
	}

	@Test
	public void testDeleteContact() throws IOException, Exception {
		Contact alisson = createTestContact("Alisson");

		mvc.perform(delete("/contacts/{id}", alisson.getId()));

		List<Contact> found = contactRepository.findAll();
		assertEquals(0, found.size());
	}

	@Test
	public void testGetContacts() throws Exception {
		createTestContact("Alisson");
		createTestContact("Alice");

		mvc.perform(get("/contacts/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].name", is("Alisson"))).andExpect(jsonPath("$[1].name", is("Alice")));
	}

	@Test
	public void testGetContact() throws Exception {
		Contact alisson = createTestContact("Alisson");

		mvc.perform(get("/contacts/{id}", alisson.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name", is("Alisson")));

	}

	private Contact createTestContact(String nome) {
		Contact contact = new Contact(nome);
		return contactRepository.saveAndFlush(contact);
	}

}
