package com.flamexander.spring.security.Vadim.springsecurity.configs;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity  // включаем веб-безопасность
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/authenticated/**").authenticated()
                //.antMatchers("/admin/**").hasAnyRole("ADVIN", "SUPERADMIN")
                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/");
    }
}

/*       @EnableWebSecurity - включает базовую поддержку безопасности веб-приложения. Применяется к конфигурационному
                              классу, который расширяет WebSecurityConfigurerAdapter, и позволяет настраивать параметры
                              безопасности для веб-приложения.
         WebSecurityConfigurerAdapter - базовый класс в Spring Security для упрощения конфигурации параметров
                                        безопасности веб-приложения.
         configure(HttpSecurity http) - метод, принимающий объект HttpSecurity (интерфейс), предназначенный для
                                        обработки входящих запросов из браузера. Этот объект позволяет программисту
                                        определить логику обработки запросов, используя методы, предоставленные
                                        интерфейсом. Это необходимо для настройки безопасности веб-приложения в Spring
                                        Security.
        authorizeRequests() - Начало конфигурации правил авторизации.
        antMatchers("/authenticated/**").authenticated()  - данная конфигурация устанавливает правило для URL-путей,
                                        начинающихся с "/authenticated/**". Она сообщает клиенту, что для доступа к этим
                                        страницам необходима аутентификация. Таким образом, если клиент хочет получить
                                        доступ к странице, начинающейся с "/authenticated/", ему требуется предоставить
                                        правильные учетные данные (логин и пароль).
         and() - используется для объединения различных частей конфигурации безопасности.
         formLogin() - предоставляет настройки для формы входа (формы аутентификации).
         logout() - начало конфигурации выхода из системы.
         logoutSuccessUrl("/") - указание URL, куда перенаправить после успешного выхода.
         antMatchers("/admin/**").hasAnyRole("ADVIN", "SUPERADMIN") - разрешает доступ к URL, начинающимся с "/admin/",
                                        для пользователей, у которых есть хотя бы одна из ролей "ADVIN" или "SUPERADMIN".
 */









