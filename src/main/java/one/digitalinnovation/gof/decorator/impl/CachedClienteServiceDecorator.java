package one.digitalinnovation.gof.decorator.impl;

import one.digitalinnovation.gof.decorator.ClienteServiceDecorator;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.service.ClienteService;

/**
 * Decorator concreto que adiciona cache simples aos serviços de cliente.
 * Demonstra o padrão <b>Decorator</b> para adicionar funcionalidade de cache.
 * 
 * @author Rychardsson
 */
public class CachedClienteServiceDecorator extends ClienteServiceDecorator {
    
    private Cliente ultimoClienteBuscado;
    private Long ultimoIdBuscado;
    
    public CachedClienteServiceDecorator(ClienteService clienteService) {
        super(clienteService);
    }
    
    @Override
    public Cliente buscarPorId(Long id) {
        // Cache simples - apenas para demonstração
        if (ultimoIdBuscado != null && ultimoIdBuscado.equals(id) && ultimoClienteBuscado != null) {
            System.out.println("🎯 Cache hit para cliente ID: " + id);
            return ultimoClienteBuscado;
        }
        
        System.out.println("💾 Cache miss para cliente ID: " + id + " - buscando no banco");
        Cliente cliente = super.buscarPorId(id);
        
        // Atualiza o cache
        ultimoIdBuscado = id;
        ultimoClienteBuscado = cliente;
        
        return cliente;
    }
    
    @Override
    public void inserir(Cliente cliente) {
        super.inserir(cliente);
        invalidarCache();
    }
    
    @Override
    public void atualizar(Long id, Cliente cliente) {
        super.atualizar(id, cliente);
        invalidarCache();
    }
    
    @Override
    public void deletar(Long id) {
        super.deletar(id);
        invalidarCache();
    }
    
    private void invalidarCache() {
        ultimoClienteBuscado = null;
        ultimoIdBuscado = null;
        System.out.println("🗑️ Cache invalidado");
    }
}
