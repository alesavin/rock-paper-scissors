package ru.alesavin.rockpaperscissors.model;

import java.util.Objects;

/**
 * TODO
 *
 * @author alesavin
 */
public class RoundRequest {

    private String strategyIdPlayer1;
    private String strategyIdPlayer2;

    public RoundRequest(String strategyIdPlayer1, String strategyIdPlayer2) {
        this.strategyIdPlayer1 = strategyIdPlayer1;
        this.strategyIdPlayer2 = strategyIdPlayer2;
    }

    public String getStrategyIdPlayer1() {
        return strategyIdPlayer1;
    }

    public void setStrategyIdPlayer1(String strategyIdPlayer1) {
        this.strategyIdPlayer1 = strategyIdPlayer1;
    }

    public String getStrategyIdPlayer2() {
        return strategyIdPlayer2;
    }

    public void setStrategyIdPlayer2(String strategyIdPlayer2) {
        this.strategyIdPlayer2 = strategyIdPlayer2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoundRequest that = (RoundRequest) o;
        return Objects.equals(strategyIdPlayer1, that.strategyIdPlayer1) &&
                Objects.equals(strategyIdPlayer2, that.strategyIdPlayer2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(strategyIdPlayer1, strategyIdPlayer2);
    }

    @Override
    public String toString() {
        return "RoundRequest{" +
                "strategyIdPlayer1='" + strategyIdPlayer1 + '\'' +
                ", strategyIdPlayer2='" + strategyIdPlayer2 + '\'' +
                '}';
    }
}
