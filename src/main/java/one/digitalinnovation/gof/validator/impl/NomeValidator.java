package one.digitalinnovation.gof.validator.impl;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.validator.ClienteValidator;

/**
 * Validador que verifica se o nome do cliente é válido.
 * 
 * @author Rychardsson
 */
public class NomeValidator implements ClienteValidator {
    
    private String errorMessage;
    
    @Override
    public boolean isValid(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            errorMessage = "Nome do cliente é obrigatório";
            return false;
        }
        
        if (cliente.getNome().length() < 2) {
            errorMessage = "Nome deve ter pelo menos 2 caracteres";
            return false;
        }
        
        if (cliente.getNome().length() > 100) {
            errorMessage = "Nome deve ter no máximo 100 caracteres";
            return false;
        }
        
        return true;
    }
    
    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
