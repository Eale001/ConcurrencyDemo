package com.eale.spring.tomcat;

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;

/**
 * @Author HLD
 * @Date 2021/7/13 0013
 **/
public class JettyConfig implements WebServerFactoryCustomizer<JettyServletWebServerFactory> {


    @Override
    public void customize(JettyServletWebServerFactory factory) {

    }
}
