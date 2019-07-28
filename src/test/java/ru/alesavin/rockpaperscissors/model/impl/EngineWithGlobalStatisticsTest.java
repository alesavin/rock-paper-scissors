package ru.alesavin.rockpaperscissors.model.impl;

import org.junit.Test;
import ru.alesavin.rockpaperscissors.model.*;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Test for EngineImpl
 *
 * @author alesavin
 */
public class EngineWithGlobalStatisticsTest {

    private EngineWithGlobalStatistics newEngine() {
        return new EngineWithGlobalStatistics(
                new PlayerStrategyRepositoryImpl(
                        new RandomPlayerStrategy(),
                        new StaticPlayerStrategy(Shape.ROCK),
                        new StaticPlayerStrategy(Shape.PAPER)
                )
        );
    }

    @Test
    public void testCase1() {
        EngineWithGlobalStatistics engine = newEngine();
        assertThat(engine.globalStatistics()).isEqualTo(new GlobalStatistics());
        assertThat(engine.play("user1", new RoundRequest("static:ROCK", "static:ROCK")))
                .isEqualTo(new Round(
                        new RoundRequest("static:ROCK", "static:ROCK"),
                        Shape.ROCK,
                        Shape.ROCK,
                        Outcome.DRAW
                ));
        assertThat(engine.globalStatistics()).isEqualTo(new GlobalStatistics(1, 0,0, 1));
        assertThat(engine.play("user1", new RoundRequest("static:ROCK", "static:ROCK")))
                .isEqualTo(new Round(
                        new RoundRequest("static:ROCK", "static:ROCK"),
                        Shape.ROCK,
                        Shape.ROCK,
                        Outcome.DRAW
                ));
        assertThat(engine.globalStatistics()).isEqualTo(new GlobalStatistics(2, 0,0, 2));
        assertThat(engine.play("user1", new RoundRequest("static:PAPER", "static:ROCK")))
                .isEqualTo(new Round(
                        new RoundRequest("static:PAPER", "static:ROCK"),
                        Shape.PAPER,
                        Shape.ROCK,
                        Outcome.WIN_PLAYER1
                ));
        assertThat(engine.play("user1", new RoundRequest("static:ROCK", "static:PAPER")))
                .isEqualTo(new Round(
                        new RoundRequest("static:ROCK", "static:PAPER"),
                        Shape.ROCK,
                        Shape.PAPER,
                        Outcome.WIN_PLAYER2
                ));
        assertThat(engine.globalStatistics()).isEqualTo(new GlobalStatistics(4, 1,1, 2));
        assertThat(engine.play("user2", new RoundRequest("static:PAPER", "static:ROCK")))
                .isEqualTo(new Round(
                        new RoundRequest("static:PAPER", "static:ROCK"),
                        Shape.PAPER,
                        Shape.ROCK,
                        Outcome.WIN_PLAYER1
                ));
        assertThat(engine.play("user2", new RoundRequest("static:ROCK", "static:PAPER")))
                .isEqualTo(new Round(
                        new RoundRequest("static:ROCK", "static:PAPER"),
                        Shape.ROCK,
                        Shape.PAPER,
                        Outcome.WIN_PLAYER2
                ));
        assertThat(engine.globalStatistics()).isEqualTo(new GlobalStatistics(6, 2,2, 2));
    }
}
