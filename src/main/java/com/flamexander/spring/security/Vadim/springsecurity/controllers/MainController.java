package com.flamexander.spring.security.Vadim.springsecurity.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {
    @GetMapping("/")
    public String homePage(){                         // домашняя страница
        return "home";
    }

    @GetMapping("/authenticated")                   // аутентифицированный
    public String pageForAuthenticatedUser(Principal principal){ // страница для аутентифицированного пользователя
       // Authentication a = SecurityContextHolder.getContext().getAuthentication();
        return "secured part of web service: " + principal.getName();          // защищенная часть веб-сервиса
    }
}


/**       @RestController - содержит в себе две другие аннотации - @Controller и @ResponseBody, где
                   @ResponseBody - указывает, что  возвращаемое значение методом контроллера, должно быть прямо включено
                                   в тело HTTP-ответа.
                   @Controller - указывает, что класс является контроллером (controller). Oбрабатывает HTTP-запросы,
                                 определяет логику обработки запросов и возвращает соответствующие HTTP-ответы.

         @GetMapping - аннотация в Spring для обозначения метода контроллера, обрабатывающего HTTP GET запросы по
                       указанному пути.

         GET-запрос - это метод HTTP-протокола для получения данных с сервера, без изменения его состояния. Используется
                      для запроса информации.
*/