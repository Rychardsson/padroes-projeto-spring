package one.digitalinnovation.gof.command.impl;

import one.digitalinnovation.gof.command.Command;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.service.ClienteService;

/**
 * Comando concreto do padrão <b>Command</b> para inserir um cliente.
 * Encapsula a operação de inserção permitindo que seja executada,
 * registrada em log, ou até mesmo desfeita.
 * 
 * @author Rychardsson
 */
public class InserirClienteCommand implements Command {
    
    private final ClienteService clienteService;
    private final Cliente cliente;
    private Long clienteIdInserido;
    
    public InserirClienteCommand(ClienteService clienteService, Cliente cliente) {
        this.clienteService = clienteService;
        this.cliente = cliente;
    }
    
    @Override
    public void execute() {
        clienteService.inserir(cliente);
        this.clienteIdInserido = cliente.getId();
    }
    
    @Override
    public void undo() {
        if (clienteIdInserido != null) {
            clienteService.deletar(clienteIdInserido);
        }
    }
    
    @Override
    public String getDescription() {
        return "Inserir cliente: " + cliente.getNome();
    }
    
    public Cliente getCliente() {
        return cliente;
    }
}
