package spring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Classe de teste para o controller REST de <code>PessoaFisica</code>.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = spring.JavaWebDevApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class PessoaFisicaRestControllerTest {
	/**
	 * Mock do Spring.
	 */
	@Autowired
	private MockMvc mvc;

	/**
	 * Referencia para o repositorio de pessoas fisicas.
	 */
	@Autowired
	private PessoaFisicaRepository pfRepository;

	/**
	 * Referencia para o repositorio de contas bancarias.
	 */
	@Autowired
	private ContaBancariaRepository cbRepository;

	/**
	 * Limpa os respositorios.
	 */
	@Before
	public void resetDb() {
		cbRepository.deleteAll();
		pfRepository.deleteAll();
	}

	/**
	 * Testa a adicao de pessoa fisica.
	 */
	@Test
	public void testAddPessoaFisica() throws IOException, Exception {
		PessoaFisica alisson = new PessoaFisica("Alisson", "053");
		mvc.perform(post("/pessoasFisicas/").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(alisson)));

		List<PessoaFisica> found = pfRepository.findAll();
		assertThat(found).extracting(PessoaFisica::getNome).containsOnly("Alisson");
	}

	/**
	 * Testa a alteracao de pessoa fisica.
	 */
	@Test
	public void testUpdatePessoaFisica() throws IOException, Exception {
		PessoaFisica alisson = createTestPessoaFisica("Alisson", "053");
		alisson.setNome("Alisson Wilker");

		mvc.perform(put("/pessoasFisicas/{id}", alisson.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.toJson(alisson)));

		List<PessoaFisica> found = pfRepository.findAll();
		assertThat(found).extracting(PessoaFisica::getNome).containsOnly("Alisson Wilker");
	}

	/**
	 * Testa a remocao de pessoa fisica.
	 */
	@Test
	public void testDeletePessoaFisica() throws IOException, Exception {
		PessoaFisica alisson = createTestPessoaFisica("Alisson", "053");

		mvc.perform(delete("/pessoasFisicas/{id}", alisson.getId()));

		List<PessoaFisica> found = pfRepository.findAll();
		assertEquals(0, found.size());
	}

	/**
	 * Testa recuperar todas as pessoas fisicas.
	 * 
	 * @throws Exception se ocorrer algum erro.
	 */
	@Test
	public void testGetPessoasFisicas() throws Exception {
		createTestPessoaFisica("Alisson", "053");
		createTestPessoaFisica("Alice", "099");

		mvc.perform(get("/pessoasFisicas/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].nome", is("Alisson"))).andExpect(jsonPath("$[1].nome", is("Alice")));
	}

	/**
	 * Testa recuperar uma pessoa fisica.
	 * 
	 * @throws Exception se ocorrer algum erro.
	 */
	@Test
	public void testGetPessoaFisica() throws Exception {
		PessoaFisica alisson = createTestPessoaFisica("Alisson", "053");

		mvc.perform(get("/pessoasFisicas/{id}", alisson.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.nome", is("Alisson")));

	}

	/**
	 * Cria uma pessoa fisica de teste.
	 */
	private PessoaFisica createTestPessoaFisica(String nome, String cpf) {
		PessoaFisica pf = new PessoaFisica(nome, cpf);
		return pfRepository.saveAndFlush(pf);
	}

}
