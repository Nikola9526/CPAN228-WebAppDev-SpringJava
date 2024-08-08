package com.humber.Week11_SpringProfiles.configs;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
// to make a BEAN
@Component
@Profile("dev")
public class DevDBConfig implements DBConfig {
    @Override
    public void setupDBConnection() {
        // printing message once endpoint is reached, shows
        System.out.println("Setting up DB connection for DEV environment!");

    }
}
