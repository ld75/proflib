package com.proflib;

import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

    @Stateless
    @Path("connexion")
    // peut etre mis soit sur la classe soit sur les methodes. Si sur la classe toutes les methodes sont des controllers LINKcontrlkjsqdf
    public class OpenIdLoginController {

        @GET
        @Path("openid") //facultatif
        public String openid() {
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa");
           return "hello.jsp";

        }
    }