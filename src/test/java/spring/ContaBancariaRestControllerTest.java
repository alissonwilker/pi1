package spring;

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
 * Classe de teste para o controller REST de <code>ContaBancaria</code>.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = spring.JavaWebDevApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ContaBancariaRestControllerTest {
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
	 * Testa a adicao de conta bancaria.
	 */
	@Test
	public void testAddContaBancaria() throws IOException, Exception {
		PessoaFisica alisson = createTestPessoaFisica("Alisson", "053");
		ContaBancaria contaBancaria = new ContaBancaria(alisson);
		mvc.perform(post("/contasBancarias/").contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.toJson(contaBancaria)));

		List<ContaBancaria> contasBancarias = cbRepository.findAll();
		assertEquals(1, contasBancarias.size());
		assertEquals("Alisson", contasBancarias.get(0).getPessoaFisica().getNome());
	}

	/**
	 * Testa a alteracao de conta bancaria.
	 */
	@Test
	public void testUpdateContaBancaria() throws IOException, Exception {
		ContaBancaria contaBancaria = createTestContaBancaria("Alisson", "053");
		PessoaFisica alice = createTestPessoaFisica("Alice", "099");
		contaBancaria.setPessoaFisica(alice);

		mvc.perform(put("/contasBancarias/{id}", contaBancaria.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.toJson(contaBancaria)));

		List<ContaBancaria> contasBancarias = cbRepository.findAll();
		assertEquals(1, contasBancarias.size());
		assertEquals("Alice", contasBancarias.get(0).getPessoaFisica().getNome());
	}

	/**
	 * Testa a remocao de conta bancaria.
	 */
	@Test
	public void testDeleteContaBancaria() throws IOException, Exception {
		ContaBancaria contaBancaria = createTestContaBancaria("Alisson", "053");

		mvc.perform(delete("/contasBancarias/{id}", contaBancaria.getId()));

		List<ContaBancaria> found = cbRepository.findAll();
		assertEquals(0, found.size());
	}

	/**
	 * Testa recuperar todas as contas bancarias.
	 * 
	 * @throws Exception se ocorrer algum erro.
	 */
	@Test
	public void testGetContasBancarias() throws Exception {
		createTestContaBancaria("Alisson", "053");
		createTestContaBancaria("Alice", "099");

		mvc.perform(get("/contasBancarias/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].pessoaFisica.nome", is("Alisson")))
				.andExpect(jsonPath("$[1].pessoaFisica.nome", is("Alice")));
	}

	/**
	 * Testa recuperar uma conta bancaria.
	 * 
	 * @throws Exception se ocorrer algum erro.
	 */
	@Test
	public void testGetContaBancaria() throws Exception {
		ContaBancaria contaBancaria = createTestContaBancaria("Alisson", "053");

		mvc.perform(get("/contasBancarias/{id}", contaBancaria.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.pessoaFisica.nome", is("Alisson")));

	}

	/**
	 * Cria uma conta bancaria de teste.
	 */
	private ContaBancaria createTestContaBancaria(String nome, String cpf) {
		ContaBancaria pf = new ContaBancaria(createTestPessoaFisica(nome, cpf));
		return cbRepository.saveAndFlush(pf);
	}

	/**
	 * Cria uma pessoa fisica de teste.
	 */
	private PessoaFisica createTestPessoaFisica(String nome, String cpf) {
		PessoaFisica pf = new PessoaFisica(nome, cpf);
		return pfRepository.saveAndFlush(pf);
	}

}
