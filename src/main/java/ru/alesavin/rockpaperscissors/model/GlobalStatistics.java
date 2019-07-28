package ru.alesavin.rockpaperscissors.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 *
 * @author alesavin
 */
public class GlobalStatistics {

    private AtomicInteger roundsPlayed = new AtomicInteger();
    private AtomicInteger winsForFirstPlayers = new AtomicInteger();
    private AtomicInteger winsForSecondPlayers = new AtomicInteger();
    private AtomicInteger draws = new AtomicInteger();

    public void incRoundsPlayed() {
        roundsPlayed.incrementAndGet();
    }
    public void incWinsForFirstPlayers() {
        winsForFirstPlayers.incrementAndGet();
    }
    public void incWinsForSecondPlayers() {
        winsForSecondPlayers.incrementAndGet();
    }
    public void incDraws() {
        draws.incrementAndGet();
    }

    public int getRoundsPlayed() {
        return roundsPlayed.intValue();
    }

    public int getWinsForFirstPlayers() {
        return winsForFirstPlayers.intValue();
    }

    public int getWinsForSecondPlayers() {
        return winsForSecondPlayers.intValue();
    }

    public int getDraws() {
        return draws.intValue();
    }
}
