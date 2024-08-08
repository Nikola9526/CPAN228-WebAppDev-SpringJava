package com.humber.Week11_SpringProfiles.configs;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
// to make a BEAN
@Component
@Profile("prod")
public class ProdDBConfig implements DBConfig {
    @Override
    public void setupDBConnection() {
        System.out.println("Setting up DB connection for Prod environment!");
    }
}
