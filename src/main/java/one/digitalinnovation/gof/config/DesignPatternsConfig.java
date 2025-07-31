package one.digitalinnovation.gof.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import one.digitalinnovation.gof.observer.ClienteSubject;
import one.digitalinnovation.gof.observer.impl.EmailNotificationObserver;
import one.digitalinnovation.gof.observer.impl.LoggingClienteObserver;

/**
 * Configuração dos padrões de projeto para registrar automaticamente
 * os observadores no sistema.
 * 
 * @author Rychardsson
 */
@Configuration
public class DesignPatternsConfig {
    
    @Autowired
    private ClienteSubject clienteSubject;
    
    @Autowired
    private LoggingClienteObserver loggingObserver;
    
    @Autowired
    private EmailNotificationObserver emailObserver;
    
    @PostConstruct
    public void configurarObservadores() {
        // Registra os observadores automaticamente na inicialização
        clienteSubject.addObserver(loggingObserver);
        clienteSubject.addObserver(emailObserver);
        
        System.out.println("🔧 Padrões de projeto configurados:");
        System.out.println("   ✅ Observer Pattern - Observadores registrados");
        System.out.println("   ✅ Singleton Pattern - Gerenciado pelo Spring IoC");
        System.out.println("   ✅ Strategy Pattern - Interface ClienteService");
        System.out.println("   ✅ Facade Pattern - ClienteRestController");
        System.out.println("   ✅ Repository Pattern - Spring Data JPA");
        System.out.println("   ✅ Builder Pattern - ClienteBuilder disponível");
        System.out.println("   ✅ Command Pattern - CommandInvoker disponível");
        System.out.println("   ✅ Factory Pattern - ClienteValidatorFactory");
        System.out.println("   ✅ Decorator Pattern - Service decorators");
        System.out.println("   ✅ Template Method Pattern - ClienteProcessor");
    }
}
