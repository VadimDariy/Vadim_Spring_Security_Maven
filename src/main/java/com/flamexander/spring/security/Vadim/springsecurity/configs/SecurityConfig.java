package com.flamexander.spring.security.Vadim.springsecurity.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@EnableWebSecurity  // включаем веб-безопасность
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/authenticated/**").authenticated()
                //.antMatchers("/admin/**").hasAnyRole("ADVIN", "SUPERADMIN")
                .and()
                .formLogin()
                //.loginProcessingUrl("/hellologin")
                .and()
                .logout().logoutSuccessUrl("/");
    }

    // In-Memory
//    @Bean
//    public UserDetailsService users(){
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$12$fSSP1rzTGSjWHqdqF9aYpO3Ke.PDW43lE1QfoqhQWguoGbPxhoahG")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$12$fSSP1rzTGSjWHqdqF9aYpO3Ke.PDW43lE1QfoqhQWguoGbPxhoahG")
//                .roles("USER", "ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    // jdbcAuthentication

    @Bean
    public JdbcUserDetailsManager users(DataSource dataSource){
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2a$12$fSSP1rzTGSjWHqdqF9aYpO3Ke.PDW43lE1QfoqhQWguoGbPxhoahG")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$12$fSSP1rzTGSjWHqdqF9aYpO3Ke.PDW43lE1QfoqhQWguoGbPxhoahG")
                .roles("USER", "ADMIN")
                .build();
        JdbcUserDetailsManager JdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        if(JdbcUserDetailsManager.userExists(user.getUsername())){
            JdbcUserDetailsManager.deleteUser(user.getUsername());
        }
        if(JdbcUserDetailsManager.userExists(admin.getUsername())){
            JdbcUserDetailsManager.deleteUser(admin.getUsername());
        }
        JdbcUserDetailsManager.createUser(user);
        JdbcUserDetailsManager.createUser(admin);
        return JdbcUserDetailsManager;
    }

}

/*
@EnableWebSecurity - включает базовую поддержку безопасности веб-приложения. Применяется к конфигурационному
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

         UserDetailsService users():
         UserDetailsService - предоставляет интерфейс для загрузки информации о пользователях из различных источников
                              данных.
         Метод users() - в конфигурации Spring Security используется для жесткой настройки пользователей в памяти
                         приложения, но это не загружает пользователей в память компьютера.

         @Bean - метод users() аннотирован как @Bean, это означает, что контейнер Spring создаст экземпляр UserDetails,
                 который будет состояние которого будет внедряться по необходимости другими компонентами, для
                 настройки пользовательских данных для аутентификации в Spring Security.

         Настройка бина - означает создание и конфигурирование объекта (бина), который управляется контейнером Spring.

         @Bean - в целом эта аннотация предоставляет собою природу переменной ссылочного типа данных в рамках Spring,
                 которая хранит в себе результат работы метода над которым она прописана.

         UserDetails user = User.builder() - используется для создания объекта UserDetails с использованием паттерна
                                             "Builder". Методы вызываются по цепочке для настройки параметров
                                             пользователя, а build() возвращает окончательный объект.

         password("{bcrypt}$2a$12$fSSP1rzTGSjWHqdqF9aYpO3Ke.PDW43lE1QfoqhQWguoGbPxhoahG") -   представляет собой
                             захешированный пароль с использованием алгоритма BCrypt.
                             Расшифруем его:
                               - "{bcrypt}" - указывает, что пароль захеширован с использованием BCrypt.
                               - $2a$12$ - это префикс, который указывает на версию алгоритма BCrypt и стоимость (12),
                                           которая определяет, насколько затратным будет процесс хеширования (чем выше
                                           значение, тем более затратным).
                               - Далее идет строка из 22 символов, представляющая собой результат хеширования пароля с
                                 использованием BCrypt. Она включает в себя соль, что делает атаки перебором сложными,
                                 даже если пароли совпадают.

         Когда вы сравниваете введенный пароль с хешированным, Spring Security автоматически использует BCrypt для
    проверки соответствия введенного пароля хешу в базе данных.

         build() - метод, завершающий конфигурацию объекта и возвращающий готовый экземпляр. В контексте User.builder(),
                   он создает объект UserDetails.

         return new InMemoryUserDetailsManager(user, admin);
         Этот код возвращает новый объект InMemoryUserDetailsManager, который является реализацией UserDetailsService
         для хранения пользовательских данных в памяти. В данном случае, ему передаются пользователи "user" и "admin".





 */









