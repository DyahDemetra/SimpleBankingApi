package ru.bankingservices;

import jakarta.servlet.FilterRegistration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import ru.bankingservices.filter.AuthenticationFilter;
import ru.bankingservices.service.TokenService;

@SpringBootApplication
public class BankingApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(BankingApp.class, args);
    }

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authFilter(TokenService tokenService) {
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthenticationFilter(tokenService));
        registrationBean.addUrlPatterns("/api/accounts/*", "/api/transactions/*");
        return registrationBean;
    }
}
