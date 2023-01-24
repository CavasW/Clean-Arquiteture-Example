package backend.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import backend.backend.application.common.interfaces.IMailSender;

@Configuration
public class MailConfiguration {
    
    @Bean
    public IMailSender mailSender() {
        return new IMailSender() {

            @Override
            public void sendEmail(String to, String email, String template) {
                System.out.println("Email Sent!");
            }
            
        };
    }

}
