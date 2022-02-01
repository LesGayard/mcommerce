package com.mproduits.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mes-config")
public class ApplicationPropertiesConfiguration {

    private int limitDeProduits;

    public int getLimitDeProduits() {
        return this.limitDeProduits;
    }

    public void setLimitDeProduits(int limitDeProduits) {
        this.limitDeProduits = limitDeProduits;
    }
}
