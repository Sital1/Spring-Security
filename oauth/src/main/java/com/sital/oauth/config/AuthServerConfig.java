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
      client-credentials  --> not related to the user
      refresh_token // use this value to obtain the new access token without forcing user to authenticate.
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
                .authorizedGrantTypes("password","refresh_token") // deprecated
                // authorization code grant type
                .and()
                .withClient("client2")
                .secret("secret2")
                .scopes("read")
                .authorizedGrantTypes("authorization_code","refresh_token")
                // authorization_code requires redirect uri
                .redirectUris("http://localhost:9090")
                // client credentials grant type
                .and()
                .withClient("client3")
                .secret("secret3")
                .scopes("read")
                .authorizedGrantTypes("client_credentials");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }
}
