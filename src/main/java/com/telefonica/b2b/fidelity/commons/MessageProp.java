package com.telefonica.b2b.fidelity.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = { "classpath:message.properties" })
public class MessageProp {

    @Autowired
    private Environment environment;

    public String obtainDetail(String key) {
	if (environment.containsProperty(key)) {
	    return environment.getProperty(key);
	}
	return environment.getProperty(Constant.GENERIC_CODE);
    }

}
