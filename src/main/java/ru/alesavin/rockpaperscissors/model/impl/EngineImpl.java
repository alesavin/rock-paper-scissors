package ru.alesavin.rockpaperscissors.model.impl;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;
import org.springframework.util.LinkedMultiValueMap;
import ru.alesavin.rockpaperscissors.model.*;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * TODO
 *
 * @author alesavin
 */
public class EngineImpl implements Engine {

    private PlayerStrategyRepository repository;
    private ListMultimap<String,Round> results =
            MultimapBuilder.linkedHashKeys().arrayListValues().build();

    private ConcurrentHashMap<String, List<Round>> map = new ConcurrentHashMap<>();

    public EngineImpl(PlayerStrategyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Round play(String user, RoundRequest request) {
        if (user == null)
            throw new IllegalArgumentException("User is null");
        if (request == null)
            throw new IllegalArgumentException("Request is null");
        PlayerStrategy strategy1 = repository.resolve(request.getStrategyIdPlayer1());
        if (strategy1 == null)
            throw new IllegalArgumentException("No strategy " + request.getStrategyIdPlayer1());
        Shape p1 = strategy1.play();
        PlayerStrategy strategy2 = repository.resolve(request.getStrategyIdPlayer2());
        if (strategy2 == null)
            throw new IllegalArgumentException("No strategy " + request.getStrategyIdPlayer2());
        Shape p2 = strategy2.play();
        Outcome outcome = Solver.solve(p1, p2);
        Round round = new Round(request, p1, p2, outcome);
/*
        map.compute(user, new BiFunction<String, List<Round>, List<Round>>() {
            @Override
            public List<Round> apply(String s, List<Round> rounds) {
                return null;
            }
        });
*/
        results.put(user, round);
        return round;
    }

    @Override
    public Round[] statistics(String user) {
        List<Round> list = results.get(user);
        return list == null ? new Round[0] : list.toArray(new Round[0]);
    }

    @Override
    public void clear(String user) {
        results.removeAll(user);
    }
}
