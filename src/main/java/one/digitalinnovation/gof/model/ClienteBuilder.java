package one.digitalinnovation.gof.model;

/**
 * Implementação do padrão <b>Builder</b> para a construção de objetos Cliente
 * de forma fluente e flexível. Este padrão é útil quando temos objetos complexos
 * com muitos parâmetros opcionais.
 * 
 * @author Rychardsson
 */
public class ClienteBuilder {
    
    private Long id;
    private String nome;
    private Endereco endereco;
    
    public ClienteBuilder() {
        // Construtor vazio para inicialização
    }
    
    public ClienteBuilder comId(Long id) {
        this.id = id;
        return this;
    }
    
    public ClienteBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }
    
    public ClienteBuilder comEndereco(Endereco endereco) {
        this.endereco = endereco;
        return this;
    }
    
    public ClienteBuilder comCep(String cep) {
        if (this.endereco == null) {
            this.endereco = new Endereco();
        }
        this.endereco.setCep(cep);
        return this;
    }
    
    public Cliente build() {
        Cliente cliente = new Cliente();
        cliente.setId(this.id);
        cliente.setNome(this.nome);
        cliente.setEndereco(this.endereco);
        return cliente;
    }
    
    /**
     * Método estático para iniciar a construção de um Cliente
     */
    public static ClienteBuilder umCliente() {
        return new ClienteBuilder();
    }
    
    /**
     * Método de conveniência para criar um cliente apenas com nome
     */
    public static Cliente clienteComNome(String nome) {
        return new ClienteBuilder()
                .comNome(nome)
                .build();
    }
}
