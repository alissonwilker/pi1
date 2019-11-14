package spring;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller REST de entidades <code>ContaBancaria</code>.
 */
@RestController
@RequestMapping("contasBancarias")
@CrossOrigin
public class ContaBancariaRestController {
    /**
     * Referencia para o repositorio de entidades <code>ContaBancaria</code>.
     * 
     */
    @Autowired
    private ContaBancariaRepository cbRepository;

    /**
     * Recupera a lista completa de contas bancarias.
     * 
     * @return a lista de todas as contas bancarias.
     */
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<ContaBancaria> getContasBancarias() {
        return cbRepository.findAll();
    }

    /**
     * Recupera uma conta bancaria pelo ID.
     * 
     * @param id o ID da pconta bancaria a ser recuperada.
     * @return a conta bancaria recuperada ou null, se nao existir.
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Optional<ContaBancaria> getContaBancaria(@PathVariable Integer id) {
        return cbRepository.findById(id);
    }

    /**
     * Adiciona uma conta bancaria no repositorio.
     * 
     * @param contaBancaria a conta bancaria a ser adicionada.
     * 
     * @return a conta bancaria adicionada.
     */
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ContaBancaria addContaBancaria(@RequestBody ContaBancaria contaBancaria) {
        return persistirContaBancaria(contaBancaria);
    }

    /**
     * Altera uma conta bancaria identificada pelo ID.
     * 
     * @param id            o ID da conta bancaria a ser alterada.
     * @param contaBancaria a conta bancaria ser alterada.
     * 
     * @return a conta bancaria alterada.
     * 
     */
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ContaBancaria updateContaBancaria(@PathVariable String id,
            @RequestBody ContaBancaria contaBancaria) {
        return persistirContaBancaria(contaBancaria);
    }

    /**
     * Persiste uma alteracao em uma conta bancaria existente ou persiste uma nova
     * conta bancaria.
     * 
     * @param contaBancaria a conta bancaria a ser alterada ou criada.
     * 
     * @return a conta bancaria alterada.
     */
    private ContaBancaria persistirContaBancaria(ContaBancaria contaBancaria) {
        return cbRepository.save(contaBancaria);
    }

    /**
     * Remove uma conta bancaria identificada pelo ID.
     * 
     * @param id o ID da conta bancaria a ser removida.
     */
    @DeleteMapping(value = "/{id}")
    public void deleteContaBancaria(@PathVariable Integer id) {
        cbRepository.deleteById(id);
    }

}