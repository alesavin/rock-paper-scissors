package ru.alesavin.rockpaperscissors.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.alesavin.rockpaperscissors.model.*;
import ru.alesavin.rockpaperscissors.model.impl.*;

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

    private EngineWithGlobalStatistics engine = new EngineWithGlobalStatistics(
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
            newCookie.setSecure(true);
            response.addCookie(newCookie);
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

    @RequestMapping(method = RequestMethod.DELETE, value = "/play")
    public void clear(@CookieValue(name = "JSESSION", required = false) final String cookie) {
        if (cookie == null)
            throw new SecurityException();
        engine.clear(cookie);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/global-statistics")
    public GlobalStatistics globalStatistics() {
        return engine.globalStatistics();
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
