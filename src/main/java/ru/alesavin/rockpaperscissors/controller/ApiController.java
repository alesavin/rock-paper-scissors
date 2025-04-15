package ru.alesavin.rockpaperscissors.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.alesavin.rockpaperscissors.model.*;
import ru.alesavin.rockpaperscissors.model.impl.*;

import java.time.Duration;
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
    public Mono<Void> hello(@CookieValue(name = "JSESSION", required = false) final String cookie,
                            ServerHttpResponse response) {
        if (cookie == null) {
            ResponseCookie newCookie =
                    ResponseCookie.from("JSESSION", UUID.randomUUID().toString())
                            .secure(true)
                            .build();
            response.addCookie(newCookie);
        }
        return Mono.empty();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/play")
    public Mono<Round> play(@CookieValue(name = "JSESSION", required = false) final String cookie,
                            @RequestBody final RoundRequest request) {
        if (cookie == null)
            throw new SecurityException();
        return Mono.just(engine.play(cookie, request));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/statistics", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Round> statistics(@CookieValue(name = "JSESSION", required = false) final String cookie) {
        if (cookie == null)
            throw new SecurityException();
        return Flux.interval(Duration.ofSeconds(1)).map(l -> new Round(
                new RoundRequest("a", "b" + l),
                Shape.ROCK,
                Shape.SCISSORS,
                Outcome.DRAW
        )).take(5);
//        return Flux.fromArray(engine.statistics(cookie));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/play")
    public Mono<Void> clear(@CookieValue(name = "JSESSION", required = false) final String cookie) {
        if (cookie == null)
            throw new SecurityException();
        engine.clear(cookie);
        return Mono.empty();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/global-statistics")
    public Mono<GlobalStatistics> globalStatistics() {
        return Mono.just(engine.globalStatistics());
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
