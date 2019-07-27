package ru.alesavin.rockpaperscissors.model;

/**
 * TODO
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
}
