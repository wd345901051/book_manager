package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import javax.annotation.Resource;

@EnableAuthorizationServer
@Configuration
public class OAuth2Configuration extends AuthorizationServerConfigurerAdapter {
    @Resource
    private AuthenticationManager manager;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // 验证方法

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory() // 信息保存在内存中
                .withClient("web") // 客户端名称
                .secret(encoder.encode("654321")) // 密钥
                .autoApprove(false) // 自动审批
                .scopes("book","user","borrow") // 授权范围
                .authorizedGrantTypes("client_credentials","password","implicit","authorization_code","refresh_token");
        // 5中授权模式，还有一种刷新Token模式
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .passwordEncoder(encoder) // 设定编码器
                .allowFormAuthenticationForClients() // 表单验证
                .checkTokenAccess("permitAll()"); // 允许所有的Token查询请求
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(manager); // 配置manager
    }
}
