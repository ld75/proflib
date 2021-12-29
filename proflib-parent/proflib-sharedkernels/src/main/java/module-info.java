module proflib.sharedkernels {
    exports com.proflib.helpers;
    requires slf4j.api;
    requires org.apache.commons.io;
    requires jakarta.jakartaee.api;
    requires java.net.http;
    requires javafaker;
    requires microprofile.config.api;
}