package com.proflib.helpers;

import jakarta.mail.internet.*;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.slf4j.LoggerFactory;

import jakarta.mail.*;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static com.proflib.helpers.Utils.handlingFunctionWrapper;

public class MailSender {
    static final org.slf4j.Logger log = LoggerFactory.getLogger(MailSender.class);

    public void sendTemplateEmail(List<String> to, File pj, String template, Object[] templateVariables, String subject) throws Exception {
        String message = MessageFormat.format(template, templateVariables);
            sendTextEmail(to, subject, message);
    }

    private InternetAddress[] convertTOtoListOfInternetAddress(List<String> to) {
        List<InternetAddress> adressMailAdmin = to.stream().map(handlingFunctionWrapper(adresse -> new InternetAddress(adresse), Exception.class)).collect(Collectors.toList());
        return (InternetAddress[])adressMailAdmin.toArray(new InternetAddress[adressMailAdmin.size()]);
    }

    public void sendTextEmail(List<String> to, String subject, String message) throws Exception
    {
        Message messageAdm = prepareEmailParameters();
        messageAdm.setFrom (new InternetAddress("projet@memoclap.com"));
        InternetAddress[] addressadm = convertTOtoListOfInternetAddress(to);
        messageAdm.setRecipients (Message.RecipientType.TO, addressadm);
        messageAdm.setSubject(MimeUtility.encodeText(subject, "utf-8", null));
        Multipart multipart = new MimeMultipart();
        BodyPart messageBody = new MimeBodyPart();
        log.debug("message original "+ message);
        /*String messageUtf8 = MimeUtility.encodeText(message, "utf-8", null);
        log.debug("message utf8 "+ messageUtf8);*/
        messageBody.setContent(message,"text/html;charset=utf-8");
        multipart.addBodyPart(messageBody);
        messageAdm.setContent(multipart);
        Transport.send (messageAdm);
    }
    private Message prepareEmailParameters() {
        log.info("Prepare send email");
        Config config = ConfigProvider.getConfig();
        Properties props = new Properties();

        String username = config.getValue("mail.user", String.class);
        String password = config.getValue("mail.password", String.class);
        props.put("mail.smtp.host", config.getValue("mail.host", String.class));
        props.put("mail.smtp.user", username);
        props.put("mail.smtp.password", password);
        props.put("mail.user", username);
        props.put("mail.password", password);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", config.getValue("mail.smtp.ssl.trust", String.class));
        props.put ("mail.smtp.auth","true");
        props.setProperty("var thatDoc = document;mail.smtp.submitter", username);
        props.setProperty("mail.smtp.port",  config.getValue("mail.port", String.class));
        Session mailSession = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        mailSession.setDebug(true);
        return new MimeMessage(mailSession);
    }


}
