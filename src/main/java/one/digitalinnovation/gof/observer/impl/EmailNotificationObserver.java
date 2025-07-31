package one.digitalinnovation.gof.observer.impl;

import org.springframework.stereotype.Component;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.observer.ClienteObserver;

/**
 * Implementação concreta do padrão <b>Observer</b> que envia notificações
 * por email (simulado) quando operações são realizadas com clientes.
 * 
 * @author Rychardsson
 */
@Component
public class EmailNotificationObserver implements ClienteObserver {
    
    @Override
    public void onClienteCriado(Cliente cliente) {
        enviarEmail("Novo cliente cadastrado: " + cliente.getNome(), 
                   "admin@empresa.com");
    }
    
    @Override
    public void onClienteAtualizado(Cliente clienteAntigo, Cliente clienteNovo) {
        enviarEmail("Cliente atualizado: " + clienteNovo.getNome(), 
                   "admin@empresa.com");
    }
    
    @Override
    public void onClienteRemovido(Long clienteId) {
        enviarEmail("Cliente removido com ID: " + clienteId, 
                   "admin@empresa.com");
    }
    
    /**
     * Simula o envio de email
     */
    private void enviarEmail(String mensagem, String destinatario) {
        System.out.println("📧 EMAIL ENVIADO para " + destinatario + ": " + mensagem);
    }
}
