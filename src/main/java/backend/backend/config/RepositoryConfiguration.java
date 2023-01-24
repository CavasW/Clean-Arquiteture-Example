package backend.backend.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import backend.backend.application.common.interfaces.IUserRepository;
import backend.backend.infrastructure.presistence.InMemoryUserRepository;

@Configuration
public class RepositoryConfiguration {
    
    @Bean(name = BeanDefinition.SCOPE_SINGLETON)
    public IUserRepository userRepository() {
        return new InMemoryUserRepository();
    }

}
