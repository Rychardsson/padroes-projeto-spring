package one.digitalinnovation.gof.command;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Invoker do padrão <b>Command</b> que executa comandos e mantém
 * um histórico para operações de undo/redo.
 * 
 * @author Rychardsson
 */
@Component
public class CommandInvoker {
    
    private List<Command> historicoComandos = new ArrayList<>();
    private int posicaoAtual = -1;
    
    /**
     * Executa um comando e o adiciona ao histórico
     */
    public void executarComando(Command comando) {
        comando.execute();
        
        // Remove comandos posteriores à posição atual (para redo)
        if (posicaoAtual < historicoComandos.size() - 1) {
            historicoComandos = historicoComandos.subList(0, posicaoAtual + 1);
        }
        
        historicoComandos.add(comando);
        posicaoAtual++;
        
        System.out.println("✅ Comando executado: " + comando.getDescription());
    }
    
    /**
     * Desfaz o último comando executado
     */
    public boolean undo() {
        if (posicaoAtual >= 0) {
            Command comando = historicoComandos.get(posicaoAtual);
            try {
                comando.undo();
                posicaoAtual--;
                System.out.println("↩️ Comando desfeito: " + comando.getDescription());
                return true;
            } catch (UnsupportedOperationException e) {
                System.out.println("❌ Undo não suportado para: " + comando.getDescription());
                return false;
            }
        }
        System.out.println("❌ Nenhum comando para desfazer");
        return false;
    }
    
    /**
     * Refaz o próximo comando no histórico
     */
    public boolean redo() {
        if (posicaoAtual < historicoComandos.size() - 1) {
            posicaoAtual++;
            Command comando = historicoComandos.get(posicaoAtual);
            comando.execute();
            System.out.println("↪️ Comando refeito: " + comando.getDescription());
            return true;
        }
        System.out.println("❌ Nenhum comando para refazer");
        return false;
    }
    
    /**
     * Retorna o histórico de comandos
     */
    public List<String> getHistorico() {
        List<String> historico = new ArrayList<>();
        for (int i = 0; i < historicoComandos.size(); i++) {
            String prefix = (i == posicaoAtual) ? "→ " : "  ";
            historico.add(prefix + historicoComandos.get(i).getDescription());
        }
        return historico;
    }
}
