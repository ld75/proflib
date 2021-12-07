package com.proflib;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 */
@ApplicationPath("/data")
@ApplicationScoped
public class ProflibRestApplication extends Application {

    public void hello()
    {

    }

}
