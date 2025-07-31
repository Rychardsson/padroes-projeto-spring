package one.digitalinnovation.gof.observer;

import one.digitalinnovation.gof.model.Cliente;

/**
 * Interface do padrão <b>Observer</b> para observar eventos relacionados a clientes.
 * Implementações desta interface serão notificadas quando eventos específicos
 * ocorrerem no sistema.
 * 
 * @author Rychardsson
 */
public interface ClienteObserver {
    
    /**
     * Notifica quando um cliente é criado
     */
    void onClienteCriado(Cliente cliente);
    
    /**
     * Notifica quando um cliente é atualizado
     */
    void onClienteAtualizado(Cliente clienteAntigo, Cliente clienteNovo);
    
    /**
     * Notifica quando um cliente é removido
     */
    void onClienteRemovido(Long clienteId);
}
