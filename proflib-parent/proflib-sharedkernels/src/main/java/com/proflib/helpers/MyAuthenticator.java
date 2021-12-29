package com.proflib.helpers;

import jakarta.inject.Inject;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAuthenticator extends Authenticator
{

    @Inject
    @ConfigProperty (name="mail.user")
    String smtpmailuser;
    @Inject
    @ConfigProperty (name="mail.password")
    String smtpmailpassword;

    static final Logger log = LoggerFactory.getLogger(MyAuthenticator.class);
    private PasswordAuthentication emailAuthentication;

    public MyAuthenticator()
    {
        emailAuthentication = new PasswordAuthentication(smtpmailuser, smtpmailpassword);

    }
    @Override
    public PasswordAuthentication getPasswordAuthentication()
    {
        return emailAuthentication;
    }

}
