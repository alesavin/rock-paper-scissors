package ru.alesavin.rockpaperscissors.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.alesavin.rockpaperscissors.model.*;
import ru.alesavin.rockpaperscissors.model.impl.EngineImpl;
import ru.alesavin.rockpaperscissors.model.impl.PlayerStrategyRepositoryImpl;
import ru.alesavin.rockpaperscissors.model.impl.RandomPlayerStrategy;
import ru.alesavin.rockpaperscissors.model.impl.StaticPlayerStrategy;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

/**
 * Provides HTTP API for the storage unit
 *
 * @author alesavin
 */
@RestController
public class ApiController {

    private Engine engine = new EngineImpl(
        new PlayerStrategyRepositoryImpl(
           new RandomPlayerStrategy(),
           new StaticPlayerStrategy(Shape.ROCK)
        )
    );

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public void hello(@CookieValue(name = "JSESSION", required = false) final String cookie,
                      HttpServletResponse response) {
        if (cookie == null) {
            Cookie newCookie = new Cookie("JSESSION", UUID.randomUUID().toString());
            System.out.println("/hello issue [" + newCookie.toString() + "]");
            response.addCookie(newCookie);
        } else {
            System.out.println("/hello have JSESSION [" + cookie + "]");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/play")
    public Round play(@CookieValue(name = "JSESSION", required = false) final String cookie,
                      @RequestBody final RoundRequest request) {
        if (cookie == null)
            throw new SecurityException();
        return engine.play(cookie, request);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/statistics")
    public Round[] statistics(@CookieValue(name = "JSESSION", required = false) final String cookie) {
        if (cookie == null)
            throw new SecurityException();
        return engine.statistics(cookie);
    }

    @ExceptionHandler(SecurityException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handle(SecurityException e) {
        return Optional.ofNullable(e.getMessage()).orElse("");
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handle(NoSuchElementException e) {
        return Optional.ofNullable(e.getMessage()).orElse("");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handle(IllegalArgumentException e) {
        return Optional.ofNullable(e.getMessage()).orElse("");
    }
}
