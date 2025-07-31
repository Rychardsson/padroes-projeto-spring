package one.digitalinnovation.gof.decorator.impl;

import org.springframework.stereotype.Component;

import one.digitalinnovation.gof.decorator.ClienteServiceDecorator;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.service.ClienteService;
import one.digitalinnovation.gof.validator.ClienteValidator;
import one.digitalinnovation.gof.validator.ClienteValidatorFactory;

/**
 * Decorator concreto que adiciona validação aos serviços de cliente.
 * Implementa o padrão <b>Decorator</b> para adicionar funcionalidade de validação.
 * 
 * @author Rychardsson
 */
@Component
public class ValidatedClienteServiceDecorator extends ClienteServiceDecorator {
    
    private final ClienteValidatorFactory validatorFactory;
    
    public ValidatedClienteServiceDecorator(ClienteService clienteService, 
                                          ClienteValidatorFactory validatorFactory) {
        super(clienteService);
        this.validatorFactory = validatorFactory;
    }
    
    @Override
    public void inserir(Cliente cliente) {
        validarCliente(cliente);
        super.inserir(cliente);
    }
    
    @Override
    public void atualizar(Long id, Cliente cliente) {
        validarCliente(cliente);
        super.atualizar(id, cliente);
    }
    
    private void validarCliente(Cliente cliente) {
        ClienteValidator validator = validatorFactory.createValidator(
            ClienteValidatorFactory.ValidatorType.COMPLETO
        );
        
        if (!validator.isValid(cliente)) {
            throw new IllegalArgumentException("Cliente inválido: " + validator.getErrorMessage());
        }
    }
}
