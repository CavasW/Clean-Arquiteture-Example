package backend.backend.application.common.interfaces;

public interface IMailSender {
    
    void sendEmail(String to, String email, String template);

}
