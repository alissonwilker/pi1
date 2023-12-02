package spring;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * Classe abstrata que representa um cliente.
 *
 */
@MappedSuperclass
public abstract class Cliente {
    /**
     * O ID unico do cliente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * O nome do cliente.
     */
    @Column
    private String nome; 

    /**
     * Construtor padrao.
     */
    protected Cliente() {}

    /**
     * Construtor que recebe o nome do cliente.
     * 
     * @param nome
     *            o nome do cliente.
     */
    protected Cliente(String nome) {
        this.nome = nome;
    }

    /**
     * Recupera o nome do cliente.
     * 
     * @return o nome do cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Altera o nome do cliente.
     * 
     * @param nome o novo nome do cliente.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Recupera o id do cliente.
     * 
     * @return o id do cliente.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Altera o ID do cliente.
     * 
     * @param id o novo ID do cliente.
     */
    public void setId(Integer id) {
        this.id = id;
    }


}
