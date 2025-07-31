package one.digitalinnovation.gof.command;

/**
 * Interface do padrão <b>Command</b> que define a estrutura básica
 * de um comando executável. Permite encapsular operações como objetos,
 * facilitando a implementação de funcionalidades como undo/redo, logging, etc.
 * 
 * @author Rychardsson
 */
public interface Command {
    
    /**
     * Executa o comando
     */
    void execute();
    
    /**
     * Desfaz o comando (opcional)
     */
    default void undo() {
        throw new UnsupportedOperationException("Undo não implementado para este comando");
    }
    
    /**
     * Retorna uma descrição do comando
     */
    String getDescription();
}
