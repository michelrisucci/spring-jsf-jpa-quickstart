package com.risucci.quickstart.context;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Configuration
@Order(2)
public class QuickstartSecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

}