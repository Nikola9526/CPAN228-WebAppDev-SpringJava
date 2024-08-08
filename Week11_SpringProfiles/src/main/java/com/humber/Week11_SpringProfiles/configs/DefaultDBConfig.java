package com.humber.Week11_SpringProfiles.configs;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
// to make a BEAN
@Component
//@Primary // it will choose the default one
@Profile("default")
public class DefaultDBConfig implements DBConfig {
// need to show implement method from DBConfig
    @Override
    public void setupDBConnection() {
        System.out.println("Setting up connection for default DB!");


    }
}
