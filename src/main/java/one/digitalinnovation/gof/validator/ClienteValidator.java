package one.digitalinnovation.gof.validator;

import one.digitalinnovation.gof.model.Cliente;

/**
 * Interface para validadores de cliente seguindo o padrão <b>Strategy</b>
 * combinado com <b>Factory</b>.
 * 
 * @author Rychardsson
 */
public interface ClienteValidator {
    
    /**
     * Valida um cliente
     * @param cliente Cliente a ser validado
     * @return true se válido, false caso contrário
     */
    boolean isValid(Cliente cliente);
    
    /**
     * Retorna mensagem de erro da validação
     */
    String getErrorMessage();
}
