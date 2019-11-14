package spring;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe que representa uma conta bancaria.
 *
 */
@Entity
@Table(name = "tb_contabancaria")
public class ContaBancaria {

    /**
     * Identificador da conta. Representa o numero da instancia de ContaBancaria.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Saldo da conta bancaria.
     */
    private int saldo = 0;

    /**
     * O cliente dono da conta bancaria.
     */
    @ManyToOne
    @JoinColumn(name = "pessoaFisica")
    private PessoaFisica pessoaFisica;

    /**
     * Construtor padrao.
     */
    public ContaBancaria() {}

    /**
     * Construtor que recebe o cliente dono da nova conta bancaria. Tambem configura o id da conta de
     * acordo com o contador de instancias, que eh incrementado por este construtor.
     * 
     * @param pessoaFisica
     *            a pessoa fisica dona da conta bancaria.
     */
    public ContaBancaria(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    /**
     * Recupera o identificador da conta.
     * 
     * @return o id da conta.
     */
    public int getId() {
        return id;
    }

    /**
     * Recupera o cliente dono da conta bancaria.
     * 
     * @return o cliente dono da conta bancaria.
     */
    public PessoaFisica getPessoaFisica() {
        return this.pessoaFisica;
    }

    /**
     * Altera a pessoa fisica da conta bancaria.
     * 
     * @param pessoaFisica a nova pessoa fisica dona da conta bancaria.
     */
    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    /**
     * Recupera o saldo da conta bancaria.
     * 
     * @return o saldo da conta bancaria.
     */
    public int getSaldo() {
        return saldo;
    }

    /**
     * Deposita um valor na conta bancaria, desde que esse valor seja positivo.
     * 
     * @param valor
     *            o valor a ser depositado na conta bancaria. O valor a ser depositado deve ser
     *            positivo.
     * @return o novo saldo da conta bancaria, após o deposito.
     */
    public int depositar(int valor) {
        if (valor > 0) {
            saldo = saldo + valor;
        }
        return saldo;
    }

    /**
     * Saca um valor da conta bancaria, se esse valor for positivo e menor ou igual ao saldo.
     * 
     * @param valor
     *            o valor a ser sacado da conta bancaria. O valor a ser sacado deve ser positivo e menor
     *            ou igual ao saldo da conta bancaria.
     * @return o novo saldo da conta bancaria, apos o saque.
     */
    public int sacar(int valor) {
        if (valor > 0 && valor <= saldo) {
            saldo = saldo - valor;
        }
        return saldo;
    }

}
