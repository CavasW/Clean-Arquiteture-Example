package backend.backend.config.providers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import backend.backend.application.common.interfaces.IMailSender;
import backend.backend.infrastructure.providers.JavaMailProvider;

@Configuration
public class MailConfiguration {

    @Primary
    @Bean
    public ITemplateResolver templateResolver() {

        ClassLoaderTemplateResolver templateResolver =
            new ClassLoaderTemplateResolver();

        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding("UTF-8");

        return templateResolver;

    }

    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver resolver) {
        
        SpringTemplateEngine templateEngine =
            new SpringTemplateEngine();

        templateEngine.setTemplateResolver(resolver);

        return templateEngine;

    }

    @Bean
    public IMailSender javaMailImplementation() {
        return new JavaMailProvider();
    }

}
