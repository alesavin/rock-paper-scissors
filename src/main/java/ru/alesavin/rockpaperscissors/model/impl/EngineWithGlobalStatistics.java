package ru.alesavin.rockpaperscissors.model.impl;

import ru.alesavin.rockpaperscissors.model.*;

/**
 * Engine with global statistics
 *
 * @author alesavin
 */
public class EngineWithGlobalStatistics extends EngineImpl implements WithGlobalStatistics {

    private GlobalStatistics statistics = new GlobalStatistics();

    public EngineWithGlobalStatistics(PlayerStrategyRepository repository) {
        super(repository);
    }

    @Override
    public Round play(String user, RoundRequest request) {
        Round round = super.play(user, request);
        statistics.incRoundsPlayed();
        switch (round.getOutcome()) {
            case WIN_PLAYER1:
                statistics.incWinsForFirstPlayers();
                break;
            case WIN_PLAYER2:
                statistics.incWinsForSecondPlayers();
                break;
            case DRAW:
                statistics.incDraws();
                break;
        }
        return round;
    }

    @Override
    public GlobalStatistics globalStatistics() {
        return statistics;
    }
}
