package one.digitalinnovation.gof.validator.impl;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.validator.ClienteValidator;

/**
 * Validador que verifica se o CEP do cliente é válido.
 * 
 * @author Rychardsson
 */
public class CepValidator implements ClienteValidator {
    
    private String errorMessage;
    
    @Override
    public boolean isValid(Cliente cliente) {
        if (cliente.getEndereco() == null || cliente.getEndereco().getCep() == null) {
            errorMessage = "CEP é obrigatório";
            return false;
        }
        
        String cep = cliente.getEndereco().getCep().replaceAll("[^0-9]", "");
        
        if (cep.length() != 8) {
            errorMessage = "CEP deve ter 8 dígitos";
            return false;
        }
        
        // Valida se não é um CEP obviamente inválido
        if (cep.equals("00000000") || cep.equals("11111111")) {
            errorMessage = "CEP inválido";
            return false;
        }
        
        return true;
    }
    
    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
