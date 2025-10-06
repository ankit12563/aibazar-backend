package com.ankitregmi.ecommerce;

import org.springframework.beans.factory.annotation.Value;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class EnvCheck {

    @Value("${okta.oauth2.issuer:NOT_SET}")
    private String issuer;

    @Value("${okta.oauth2.client-id:NOT_SET}")
    private String clientId;

    @Value("${okta.oauth2.audience:NOT_SET}")
    private String audience;

    @PostConstruct
    public void checkIssuer() {
        System.out.println("------------------------------------------------------");
        System.out.println("✅ Environment Variable Check (Auth0 / Okta)");
        System.out.println("✅ OKTA_OAUTH2_ISSUER   = " + issuer);
        System.out.println("✅ OKTA_OAUTH2_CLIENT_ID = " + clientId);
        System.out.println("✅ OKTA_OAUTH2_AUDIENCE  = " + audience);
        System.out.println("------------------------------------------------------");
    }
}
