package one.digitalinnovation.gof.observer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import one.digitalinnovation.gof.model.Cliente;

/**
 * Implementação do padrão <b>Observer</b> (Subject) que gerencia os observadores
 * e notifica sobre eventos relacionados a clientes.
 * 
 * @author Rychardsson
 */
@Component
public class ClienteSubject {
    
    private List<ClienteObserver> observers = new ArrayList<>();
    
    /**
     * Adiciona um observador à lista
     */
    public void addObserver(ClienteObserver observer) {
        observers.add(observer);
    }
    
    /**
     * Remove um observador da lista
     */
    public void removeObserver(ClienteObserver observer) {
        observers.remove(observer);
    }
    
    /**
     * Notifica todos os observadores sobre criação de cliente
     */
    public void notifyClienteCriado(Cliente cliente) {
        for (ClienteObserver observer : observers) {
            observer.onClienteCriado(cliente);
        }
    }
    
    /**
     * Notifica todos os observadores sobre atualização de cliente
     */
    public void notifyClienteAtualizado(Cliente clienteAntigo, Cliente clienteNovo) {
        for (ClienteObserver observer : observers) {
            observer.onClienteAtualizado(clienteAntigo, clienteNovo);
        }
    }
    
    /**
     * Notifica todos os observadores sobre remoção de cliente
     */
    public void notifyClienteRemovido(Long clienteId) {
        for (ClienteObserver observer : observers) {
            observer.onClienteRemovido(clienteId);
        }
    }
}
