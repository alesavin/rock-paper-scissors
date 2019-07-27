package ru.alesavin.rockpaperscissors.model.impl;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;
import org.springframework.util.LinkedMultiValueMap;
import ru.alesavin.rockpaperscissors.model.*;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * TODO
 *
 * @author alesavin
 */
public class EngineImpl implements Engine {

    private PlayerStrategyRepository repository;
    private ListMultimap<String,Round> results =
            MultimapBuilder.linkedHashKeys().arrayListValues().build();

    public EngineImpl(PlayerStrategyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Round play(String user, RoundRequest request) {
        Shape p1 = repository.resolve(request.getStrategyIdPlayer1()).play();
        Shape p2 = repository.resolve(request.getStrategyIdPlayer2()).play();
        Outcome outcome = Solver.solve(p1, p2);
        Round round = new Round(request, p1, p2, outcome);
        results.put(user, round);
        return round;
    }

    @Override
    public Round[] statistics(String user) {
        List<Round> list = results.get(user);
        if (list == null) list = Collections.emptyList();
        return list.toArray(new Round[0]);
    }

    @Override
    public void clear(String user) {
        throw new RuntimeException("Clear not implemented");
    }
}
