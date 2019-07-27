package ru.alesavin.rockpaperscissors.model.impl;

import ru.alesavin.rockpaperscissors.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @author alesavin
 */
public class EngineImpl implements Engine {

    private PlayerStrategyRepository repository;

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
        map.compute(user, (s, rounds) -> {
            if (rounds == null)
                rounds = new ArrayList<>();
            rounds.add(round);
            return rounds;
        });
        return round;
    }

    @Override
    public Round[] statistics(String user) {
        if (user != null) {
            List<Round> list = map.get(user);
            if (list != null)
                return list.toArray(new Round[0]);
        }
        return new Round[0];
    }

    @Override
    public void clear(String user) {
        if (user != null) map.remove(user);
    }
}
