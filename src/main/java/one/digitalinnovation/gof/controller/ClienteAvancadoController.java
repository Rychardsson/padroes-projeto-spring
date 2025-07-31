package one.digitalinnovation.gof.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.gof.command.CommandInvoker;
import one.digitalinnovation.gof.command.impl.AtualizarClienteCommand;
import one.digitalinnovation.gof.command.impl.InserirClienteCommand;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.ClienteBuilder;
import one.digitalinnovation.gof.service.ClienteService;
import one.digitalinnovation.gof.template.impl.ClientePadraoProcessor;
import one.digitalinnovation.gof.template.impl.ClienteVipProcessor;
import one.digitalinnovation.gof.validator.ClienteValidator;
import one.digitalinnovation.gof.validator.ClienteValidatorFactory;

/**
 * Controller avançado que demonstra o uso dos padrões de projeto implementados.
 * Este controller complementa o ClienteRestController básico com funcionalidades
 * que demonstram os padrões <b>Command</b>, <b>Builder</b>, <b>Factory</b>, 
 * <b>Template Method</b> e outros.
 * 
 * @author Rychardsson
 */
@RestController
@RequestMapping("clientes-avancado")
public class ClienteAvancadoController {
    
    @Autowired
    @Qualifier("clienteServiceImpl")
    private ClienteService clienteService;
    
    @Autowired
    private CommandInvoker commandInvoker;
    
    @Autowired
    private ClienteValidatorFactory validatorFactory;
    
    @Autowired
    private ClientePadraoProcessor clientePadraoProcessor;
    
    @Autowired
    private ClienteVipProcessor clienteVipProcessor;
    
    /**
     * Demonstra o padrão Builder para criação de cliente
     */
    @PostMapping("/builder")
    public ResponseEntity<Cliente> criarClienteComBuilder(
            @RequestParam String nome, 
            @RequestParam String cep) {
        
        // Padrão Builder em ação
        Cliente cliente = ClienteBuilder.umCliente()
                .comNome(nome)
                .comCep(cep)
                .build();
        
        clienteService.inserir(cliente);
        return ResponseEntity.ok(cliente);
    }
    
    /**
     * Demonstra o padrão Command para inserção
     */
    @PostMapping("/command")
    public ResponseEntity<String> inserirComCommand(@RequestBody Cliente cliente) {
        // Padrão Command em ação
        InserirClienteCommand command = new InserirClienteCommand(clienteService, cliente);
        commandInvoker.executarComando(command);
        
        return ResponseEntity.ok("Cliente inserido via Command Pattern");
    }
    
    /**
     * Demonstra o padrão Command para atualização
     */
    @PutMapping("/command/{id}")
    public ResponseEntity<String> atualizarComCommand(
            @PathVariable Long id, 
            @RequestBody Cliente cliente) {
        
        // Padrão Command em ação
        AtualizarClienteCommand command = new AtualizarClienteCommand(clienteService, id, cliente);
        commandInvoker.executarComando(command);
        
        return ResponseEntity.ok("Cliente atualizado via Command Pattern");
    }
    
    /**
     * Desfaz o último comando executado
     */
    @PostMapping("/undo")
    public ResponseEntity<String> desfazerUltimoComando() {
        boolean sucesso = commandInvoker.undo();
        String mensagem = sucesso ? "Comando desfeito com sucesso" : "Nenhum comando para desfazer";
        return ResponseEntity.ok(mensagem);
    }
    
    /**
     * Refaz o próximo comando
     */
    @PostMapping("/redo")
    public ResponseEntity<String> refazerComando() {
        boolean sucesso = commandInvoker.redo();
        String mensagem = sucesso ? "Comando refeito com sucesso" : "Nenhum comando para refazer";
        return ResponseEntity.ok(mensagem);
    }
    
    /**
     * Mostra o histórico de comandos
     */
    @GetMapping("/historico")
    public ResponseEntity<List<String>> verHistoricoComandos() {
        return ResponseEntity.ok(commandInvoker.getHistorico());
    }
    
    /**
     * Demonstra o padrão Factory para validação
     */
    @PostMapping("/validar/{tipo}")
    public ResponseEntity<String> validarCliente(
            @PathVariable String tipo, 
            @RequestBody Cliente cliente) {
        
        try {
            // Padrão Factory em ação
            ClienteValidatorFactory.ValidatorType validatorType = 
                ClienteValidatorFactory.ValidatorType.valueOf(tipo.toUpperCase());
            
            ClienteValidator validator = validatorFactory.createValidator(validatorType);
            
            if (validator.isValid(cliente)) {
                return ResponseEntity.ok("Cliente válido segundo validador: " + tipo);
            } else {
                return ResponseEntity.badRequest()
                    .body("Cliente inválido: " + validator.getErrorMessage());
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                .body("Tipo de validador inválido. Use: NOME, CEP ou COMPLETO");
        }
    }
    
    /**
     * Demonstra o padrão Template Method para processamento de cliente padrão
     */
    @PostMapping("/processar/padrao/{id}")
    public ResponseEntity<String> processarClientePadrao(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarPorId(id);
        clientePadraoProcessor.processarCliente(cliente);
        return ResponseEntity.ok("Cliente processado com template padrão");
    }
    
    /**
     * Demonstra o padrão Template Method para processamento de cliente VIP
     */
    @PostMapping("/processar/vip/{id}")
    public ResponseEntity<String> processarClienteVip(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarPorId(id);
        clienteVipProcessor.processarCliente(cliente);
        return ResponseEntity.ok("Cliente processado com template VIP");
    }
}
