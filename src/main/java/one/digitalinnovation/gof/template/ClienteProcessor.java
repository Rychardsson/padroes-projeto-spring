package one.digitalinnovation.gof.template;

import one.digitalinnovation.gof.model.Cliente;

/**
 * Classe abstrata que implementa o padrão <b>Template Method</b> para
 * processamento de clientes. Define um algoritmo com passos fixos,
 * mas permite que subclasses implementem passos específicos.
 * 
 * @author Rychardsson
 */
public abstract class ClienteProcessor {
    
    /**
     * Template method que define o algoritmo de processamento
     */
    public final void processarCliente(Cliente cliente) {
        System.out.println("🔄 Iniciando processamento do cliente: " + cliente.getNome());
        
        // Passos fixos do algoritmo
        if (validarCliente(cliente)) {
            preprocessarCliente(cliente);
            processarCore(cliente);
            postProcessarCliente(cliente);
            System.out.println("✅ Cliente processado com sucesso!");
        } else {
            System.out.println("❌ Cliente não passou na validação");
            tratarErroValidacao(cliente);
        }
    }
    
    /**
     * Passo de validação (pode ser sobrescrito)
     */
    protected boolean validarCliente(Cliente cliente) {
        return cliente != null && cliente.getNome() != null && !cliente.getNome().trim().isEmpty();
    }
    
    /**
     * Pré-processamento (pode ser sobrescrito)
     */
    protected void preprocessarCliente(Cliente cliente) {
        System.out.println("📝 Pré-processando cliente: " + cliente.getNome());
    }
    
    /**
     * Processamento principal (deve ser implementado pelas subclasses)
     */
    protected abstract void processarCore(Cliente cliente);
    
    /**
     * Pós-processamento (pode ser sobrescrito)
     */
    protected void postProcessarCliente(Cliente cliente) {
        System.out.println("🏁 Pós-processando cliente: " + cliente.getNome());
    }
    
    /**
     * Tratamento de erro (pode ser sobrescrito)
     */
    protected void tratarErroValidacao(Cliente cliente) {
        System.out.println("⚠️ Erro na validação do cliente");
    }
}
