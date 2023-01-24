package backend.backend.infrastructure.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import backend.backend.application.common.interfaces.IMailSender;
import jakarta.mail.internet.MimeMessage;

public class JavaMailProvider implements IMailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String to, String email, String template) {
        
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {
            
            helper.setTo(to);
            helper.setFrom("no-replay@reiport.travel");
            helper.setText(email);
            helper.setSubject(template);

            javaMailSender.send(mimeMessage);

        } catch (Exception e) {
            throw new IllegalStateException("Failed to send an email");
        }
        
    }
    
}
