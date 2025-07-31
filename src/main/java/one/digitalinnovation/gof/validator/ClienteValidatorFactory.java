package one.digitalinnovation.gof.validator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import one.digitalinnovation.gof.validator.impl.CepValidator;
import one.digitalinnovation.gof.validator.impl.NomeValidator;

/**
 * Factory do padrão <b>Factory Method</b> para criação de validadores de cliente.
 * Centraliza a criação de diferentes tipos de validadores.
 * 
 * @author Rychardsson
 */
@Component
public class ClienteValidatorFactory {
    
    public enum ValidatorType {
        NOME, CEP, COMPLETO
    }
    
    private Map<ValidatorType, ClienteValidator> validators = new HashMap<>();
    
    public ClienteValidatorFactory() {
        // Inicializa os validadores disponíveis
        validators.put(ValidatorType.NOME, new NomeValidator());
        validators.put(ValidatorType.CEP, new CepValidator());
        validators.put(ValidatorType.COMPLETO, new CompositeValidator(
            new NomeValidator(), new CepValidator()
        ));
    }
    
    /**
     * Cria um validador do tipo especificado
     */
    public ClienteValidator createValidator(ValidatorType type) {
        ClienteValidator validator = validators.get(type);
        if (validator == null) {
            throw new IllegalArgumentException("Tipo de validador não suportado: " + type);
        }
        return validator;
    }
    
    /**
     * Implementação interna de um validador composto usando o padrão Composite
     */
    private static class CompositeValidator implements ClienteValidator {
        
        private final ClienteValidator[] validators;
        private String errorMessage;
        
        public CompositeValidator(ClienteValidator... validators) {
            this.validators = validators;
        }
        
        @Override
        public boolean isValid(one.digitalinnovation.gof.model.Cliente cliente) {
            for (ClienteValidator validator : validators) {
                if (!validator.isValid(cliente)) {
                    this.errorMessage = validator.getErrorMessage();
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
