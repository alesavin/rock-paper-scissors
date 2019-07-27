package ru.alesavin.rockpaperscissors.model.impl;

import org.junit.Test;
import ru.alesavin.rockpaperscissors.model.Outcome;
import ru.alesavin.rockpaperscissors.model.Shape;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Test for Solver
 *
 * @author alesavin
 */
public class SolverTest {

    @Test
    public void testCase() {
        assertThat(Solver.solve(Shape.ROCK, Shape.ROCK)).isEqualTo(Outcome.DRAW);
        assertThat(Solver.solve(Shape.ROCK, Shape.PAPER)).isEqualTo(Outcome.WIN_PLAYER2);
        assertThat(Solver.solve(Shape.ROCK, Shape.SCISSORS)).isEqualTo(Outcome.WIN_PLAYER1);

        assertThat(Solver.solve(Shape.PAPER, Shape.ROCK)).isEqualTo(Outcome.WIN_PLAYER1);
        assertThat(Solver.solve(Shape.PAPER, Shape.PAPER)).isEqualTo(Outcome.DRAW);
        assertThat(Solver.solve(Shape.PAPER, Shape.SCISSORS)).isEqualTo(Outcome.WIN_PLAYER2);

        assertThat(Solver.solve(Shape.SCISSORS, Shape.ROCK)).isEqualTo(Outcome.WIN_PLAYER2);
        assertThat(Solver.solve(Shape.SCISSORS, Shape.PAPER)).isEqualTo(Outcome.WIN_PLAYER1);
        assertThat(Solver.solve(Shape.SCISSORS, Shape.SCISSORS)).isEqualTo(Outcome.DRAW);
    }

    @Test
    public void testNull() {
        assertThat(Solver.solve(null, null)).isNull();
        assertThat(Solver.solve(Shape.PAPER, null)).isNull();
        assertThat(Solver.solve(null, Shape.SCISSORS)).isNull();
    }
}
