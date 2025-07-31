package one.digitalinnovation.gof.decorator;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.service.ClienteService;

/**
 * Decorator abstrato do padrão <b>Decorator</b> para adicionar funcionalidades
 * extras aos serviços de cliente sem modificar a implementação original.
 * 
 * @author Rychardsson
 */
public abstract class ClienteServiceDecorator implements ClienteService {
    
    protected ClienteService clienteService;
    
    public ClienteServiceDecorator(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    
    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteService.buscarTodos();
    }
    
    @Override
    public Cliente buscarPorId(Long id) {
        return clienteService.buscarPorId(id);
    }
    
    @Override
    public void inserir(Cliente cliente) {
        clienteService.inserir(cliente);
    }
    
    @Override
    public void atualizar(Long id, Cliente cliente) {
        clienteService.atualizar(id, cliente);
    }
    
    @Override
    public void deletar(Long id) {
        clienteService.deletar(id);
    }
}
