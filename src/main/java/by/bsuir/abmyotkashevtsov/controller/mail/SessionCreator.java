package by.bsuir.abmyotkashevtsov.controller.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * Mail session configuration.
 */
public class SessionCreator {
    private String smtpHost;
    private String smtpPort;
    private String userName;
    private String userPassword;
    private Properties sessionProperties;

    public SessionCreator(Properties configProperties) {
        smtpHost = configProperties.getProperty("mail.smtp.host");
        smtpPort = configProperties.getProperty("mail.smtp.port");
        userName = configProperties.getProperty("mail.user.name");
        userPassword = configProperties.getProperty("mail.user.password");

        sessionProperties = new Properties();
        sessionProperties.put("mail.smtp.user", userName);
        sessionProperties.put("mail.smtp.host", smtpHost);
        sessionProperties.put("mail.smtp.port", smtpPort);
        sessionProperties.put("mail.smtp.debug", "true");
        sessionProperties.put("mail.smtp.auth", "true");
        sessionProperties.put("mail.smtp.socketFactory.port", smtpPort);
        sessionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        sessionProperties.put("mail.smtp.socketFactory.fallback", "false");
    }

    public Session createSession() {
        return Session.getInstance(sessionProperties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, userPassword);
            }
        });
    }
}
