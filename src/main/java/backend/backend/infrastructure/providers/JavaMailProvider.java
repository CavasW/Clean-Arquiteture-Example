package backend.backend.infrastructure.providers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import backend.backend.application.common.interfaces.IMailSender;
import jakarta.mail.internet.MimeMessage;

public class JavaMailProvider implements IMailSender {

    private static final String MAIL_FROM = "60ed2cc11c-c2a2b8+1@inbox.mailtrap.io";

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Override
    public void sendEmail(String subject, String to, String template, Map<String, Object> opts) {
        
        MimeMessage mimeMessage = 
            javaMailSender.createMimeMessage();

        MimeMessageHelper helper = 
            new MimeMessageHelper(mimeMessage);

        Context context = new Context();
        context.setVariables(opts);

        String htmlFile = springTemplateEngine
                    .process(
                        template,
                        context
                    );

        try {

            helper.setSubject(subject);
            helper.setFrom(MAIL_FROM);
            helper.setTo(to);
            helper.setText(htmlFile, true);

            javaMailSender.send(mimeMessage);
            
        } catch (Exception e) {
            throw new RuntimeException("Error Sending Mail");
        }
        
    }
    
}
