package com.telefonica.b2b.fidelity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class B2bFidelityApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
	SpringApplication.run(B2bFidelityApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	return application.sources(B2bFidelityApplication.class);
    }

}
