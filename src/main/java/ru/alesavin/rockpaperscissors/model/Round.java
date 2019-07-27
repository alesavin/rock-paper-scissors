package ru.alesavin.rockpaperscissors.model;

import java.util.Objects;

/**
 * Finished game round
 *
 * @author alesavin
 */
public class Round {

    private RoundRequest request;
    private Shape choicePlayer1;
    private Shape choicePlayer2;
    private Outcome outcome;

    public Round(RoundRequest request, Shape choicePlayer1, Shape choicePlayer2, Outcome outcome) {
        this.request = request;
        this.choicePlayer1 = choicePlayer1;
        this.choicePlayer2 = choicePlayer2;
        this.outcome = outcome;
    }

    public RoundRequest getRequest() {
        return request;
    }

    public void setRequest(RoundRequest request) {
        this.request = request;
    }

    public Shape getChoicePlayer1() {
        return choicePlayer1;
    }

    public void setChoicePlayer1(Shape choicePlayer1) {
        this.choicePlayer1 = choicePlayer1;
    }

    public Shape getChoicePlayer2() {
        return choicePlayer2;
    }

    public void setChoicePlayer2(Shape choicePlayer2) {
        this.choicePlayer2 = choicePlayer2;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return Objects.equals(request, round.request) &&
                choicePlayer1 == round.choicePlayer1 &&
                choicePlayer2 == round.choicePlayer2 &&
                outcome == round.outcome;
    }

    @Override
    public int hashCode() {
        return Objects.hash(request, choicePlayer1, choicePlayer2, outcome);
    }

    @Override
    public String toString() {
        return "Round{" +
                "request=" + request +
                ", choicePlayer1=" + choicePlayer1 +
                ", choicePlayer2=" + choicePlayer2 +
                ", outcome=" + outcome +
                '}';
    }
}
