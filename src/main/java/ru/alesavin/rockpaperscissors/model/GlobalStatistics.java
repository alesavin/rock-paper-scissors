package ru.alesavin.rockpaperscissors.model;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represent global statistics of game rounds
 *
 * @author alesavin
 */
public class GlobalStatistics {

    private AtomicInteger roundsPlayed = new AtomicInteger();
    private AtomicInteger winsForFirstPlayers = new AtomicInteger();
    private AtomicInteger winsForSecondPlayers = new AtomicInteger();
    private AtomicInteger draws = new AtomicInteger();

    public GlobalStatistics() {
    }

    public GlobalStatistics(int roundsPlayed,
                            int winsForFirstPlayers,
                            int winsForSecondPlayers,
                            int draws) {
        this.roundsPlayed.set(roundsPlayed);
        this.winsForFirstPlayers.set(winsForFirstPlayers);
        this.winsForSecondPlayers.set(winsForSecondPlayers);
        this.draws.set(draws);
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GlobalStatistics that = (GlobalStatistics) o;
        return Objects.equals(roundsPlayed.intValue(), that.roundsPlayed.intValue()) &&
                Objects.equals(winsForFirstPlayers.intValue(), that.winsForFirstPlayers.intValue()) &&
                Objects.equals(winsForSecondPlayers.intValue(), that.winsForSecondPlayers.intValue()) &&
                Objects.equals(draws.intValue(), that.draws.intValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(roundsPlayed, winsForFirstPlayers, winsForSecondPlayers, draws);
    }

    @Override
    public String toString() {
        return "GlobalStatistics{" +
                "roundsPlayed=" + roundsPlayed +
                ", winsForFirstPlayers=" + winsForFirstPlayers +
                ", winsForSecondPlayers=" + winsForSecondPlayers +
                ", draws=" + draws +
                '}';
    }
}
