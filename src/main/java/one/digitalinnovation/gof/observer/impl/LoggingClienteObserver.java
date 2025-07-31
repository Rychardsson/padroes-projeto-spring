package one.digitalinnovation.gof.observer.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.observer.ClienteObserver;

/**
 * Implementação concreta do padrão <b>Observer</b> que registra logs
 * das operações realizadas com clientes.
 * 
 * @author Rychardsson
 */
@Component
public class LoggingClienteObserver implements ClienteObserver {
    
    private static final Logger logger = LoggerFactory.getLogger(LoggingClienteObserver.class);
    
    @Override
    public void onClienteCriado(Cliente cliente) {
        logger.info("Cliente criado: ID={}, Nome={}", cliente.getId(), cliente.getNome());
    }
    
    @Override
    public void onClienteAtualizado(Cliente clienteAntigo, Cliente clienteNovo) {
        logger.info("Cliente atualizado: ID={}, Nome anterior={}, Nome novo={}", 
                   clienteNovo.getId(), clienteAntigo.getNome(), clienteNovo.getNome());
    }
    
    @Override
    public void onClienteRemovido(Long clienteId) {
        logger.info("Cliente removido: ID={}", clienteId);
    }
}
