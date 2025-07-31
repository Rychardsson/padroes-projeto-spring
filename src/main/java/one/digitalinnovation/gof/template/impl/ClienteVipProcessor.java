package one.digitalinnovation.gof.template.impl;

import org.springframework.stereotype.Component;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.template.ClienteProcessor;

/**
 * Implementação concreta do padrão <b>Template Method</b> para
 * processamento de clientes VIP.
 * 
 * @author Rychardsson
 */
@Component
public class ClienteVipProcessor extends ClienteProcessor {
    
    @Override
    protected boolean validarCliente(Cliente cliente) {
        // Validação adicional para clientes VIP
        boolean validacaoBasica = super.validarCliente(cliente);
        if (!validacaoBasica) {
            return false;
        }
        
        // Clientes VIP devem ter endereço
        return cliente.getEndereco() != null && cliente.getEndereco().getCep() != null;
    }
    
    @Override
    protected void preprocessarCliente(Cliente cliente) {
        super.preprocessarCliente(cliente);
        System.out.println("👑 Cliente VIP detectado - processamento prioritário");
        System.out.println("📧 Enviando notificação para gerente de contas VIP");
    }
    
    @Override
    protected void processarCore(Cliente cliente) {
        System.out.println("💎 Aplicando benefícios VIP para: " + cliente.getNome());
        System.out.println("  - Desconto especial aplicado");
        System.out.println("  - Suporte prioritário ativado");
        System.out.println("  - Gerente dedicado atribuído");
    }
    
    @Override
    protected void postProcessarCliente(Cliente cliente) {
        super.postProcessarCliente(cliente);
        System.out.println("🎁 Enviando kit de boas-vindas VIP");
        System.out.println("📞 Agendando ligação de boas-vindas");
    }
}
