package backend.backend.application.common.interfaces;

import java.util.Map;

public interface IMailSender {
    
    void sendEmail(String to, String template, Map<String, Object> opts);

}
