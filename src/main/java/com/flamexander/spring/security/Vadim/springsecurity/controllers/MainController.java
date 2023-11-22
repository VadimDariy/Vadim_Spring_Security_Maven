package com.flamexander.spring.security.Vadim.springsecurity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/")
    public String homePage(){                         // домашняя страница
        return "home";
    }

    @GetMapping("/authenticated")                   // аутентифицированный
    public String pageForAuthenticatedUser(){          // страница для аутентифицированного пользователя
        return "secured part of web service";          // защищенная часть веб-сервиса
    }
}


/*       @RestController - содержит в себе две другие аннотации - @Controller и @ResponseBody, где
                   @ResponseBody - указывает, что  возвращаемое значение методом контроллера, должно быть прямо включено
                                   в тело HTTP-ответа.
                   @Controller - указывает, что класс является контроллером (controller). Oбрабатывает HTTP-запросы,
                                 определяет логику обработки запросов и возвращает соответствующие HTTP-ответы.

         @GetMapping - аннотация в Spring для обозначения метода контроллера, обрабатывающего HTTP GET запросы по
                       указанному пути.

         GET-запрос - это метод HTTP-протокола для получения данных с сервера, без изменения его состояния. Используется
                      для запроса информации.
*/