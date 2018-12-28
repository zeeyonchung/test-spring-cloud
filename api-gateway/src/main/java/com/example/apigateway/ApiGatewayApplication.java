package com.example.apigateway;

import com.example.filter.AuthHeaderFilter;
import com.example.filter.CustomFallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@EnableResourceServer
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	AuthHeaderFilter authHeaderFilter() {
		return new AuthHeaderFilter();
	}

	@Bean
    CustomFallbackProvider customFallbackProvider() {
	    return new CustomFallbackProvider();
    }

	@Bean
	public ResourceServerConfigurerAdapter resourceServerConfigurerAdapter() {
		return new ResourceServerConfigurerAdapter() {
			@Override
			public void configure(HttpSecurity http) throws Exception {
				http.authorizeRequests()
						.antMatchers("/**").permitAll()
						.anyRequest().authenticated();
			}
		};
	}
}

