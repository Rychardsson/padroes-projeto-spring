package one.digitalinnovation.gof.command.impl;

import one.digitalinnovation.gof.command.Command;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.service.ClienteService;

/**
 * Comando concreto do padrão <b>Command</b> para atualizar um cliente.
 * 
 * @author Rychardsson
 */
public class AtualizarClienteCommand implements Command {
    
    private final ClienteService clienteService;
    private final Long id;
    private final Cliente clienteNovo;
    private Cliente clienteAntigo;
    
    public AtualizarClienteCommand(ClienteService clienteService, Long id, Cliente clienteNovo) {
        this.clienteService = clienteService;
        this.id = id;
        this.clienteNovo = clienteNovo;
    }
    
    @Override
    public void execute() {
        // Salva o estado anterior para possível undo
        this.clienteAntigo = clienteService.buscarPorId(id);
        clienteService.atualizar(id, clienteNovo);
    }
    
    @Override
    public void undo() {
        if (clienteAntigo != null) {
            clienteService.atualizar(id, clienteAntigo);
        }
    }
    
    @Override
    public String getDescription() {
        return "Atualizar cliente ID: " + id + " para " + clienteNovo.getNome();
    }
    
    public Cliente getClienteNovo() {
        return clienteNovo;
    }
    
    public Cliente getClienteAntigo() {
        return clienteAntigo;
    }
}
