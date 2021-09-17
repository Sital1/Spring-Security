package com.sital.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer

/*
    Grant Type -> Flow in which a client gets the token
      authorization code/ pkce
      password --> deprecated
      client-credentials
      refresh_token
      implict -- > depcreated

      Specify who are the clients

 */

public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {


    private AuthenticationManager authenticationManager;

    public AuthServerConfig(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // configures the client
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client1")
                .secret("secret1")
                .scopes("read")
                .authorizedGrantTypes("password") // deprecated
        // authorization code grant type
        .and()
                .withClient("client2")
                .secret("secret2")
                .scopes("read")
                .authorizedGrantTypes("authorization_code")
                // authorization_code requires redirect uri
                .redirectUris("http://localhost:9090");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }
}
