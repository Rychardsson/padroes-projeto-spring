package one.digitalinnovation.gof.template.impl;

import org.springframework.stereotype.Component;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.template.ClienteProcessor;

/**
 * Implementação concreta do padrão <b>Template Method</b> para
 * processamento de clientes padrão.
 * 
 * @author Rychardsson
 */
@Component
public class ClientePadraoProcessor extends ClienteProcessor {
    
    @Override
    protected void processarCore(Cliente cliente) {
        System.out.println("🤝 Processamento padrão para: " + cliente.getNome());
        System.out.println("  - Cadastro realizado");
        System.out.println("  - Email de boas-vindas enviado");
        System.out.println("  - Cliente adicionado ao sistema de CRM");
    }
    
    @Override
    protected void postProcessarCliente(Cliente cliente) {
        super.postProcessarCliente(cliente);
        System.out.println("📨 Enviando manual do cliente por email");
    }
}
