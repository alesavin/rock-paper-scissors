package ru.alesavin.rockpaperscissors.model.impl;

import org.junit.Test;
import ru.alesavin.rockpaperscissors.model.*;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * TODO
 *
 * @author alesavin
 */
public class EngineImplTest {

    private Engine newEngine() {
        return new EngineImpl(
                new PlayerStrategyRepositoryImpl(
                        new RandomPlayerStrategy(),
                        new StaticPlayerStrategy(Shape.ROCK),
                        new StaticPlayerStrategy(Shape.PAPER)
                )
        );
    }

    @Test
    public void testCase1() {
        Engine engine = newEngine();
        assertThat(engine.statistics("user1")).isEmpty();
        assertThat(engine.statistics("user2")).isEmpty();
        assertThat(engine.play("user1", new RoundRequest("static:ROCK", "static:ROCK")))
                .isEqualTo(new Round(
                        new RoundRequest("static:ROCK", "static:ROCK"),
                        Shape.ROCK,
                        Shape.ROCK,
                        Outcome.DRAW
                ));
        assertThat(engine.play("user1", new RoundRequest("static:ROCK", "static:ROCK")))
                .isEqualTo(new Round(
                        new RoundRequest("static:ROCK", "static:ROCK"),
                        Shape.ROCK,
                        Shape.ROCK,
                        Outcome.DRAW
                ));
        assertThat(engine.play("user1", new RoundRequest("static:ROCK", "static:ROCK")))
                .isEqualTo(new Round(
                        new RoundRequest("static:ROCK", "static:ROCK"),
                        Shape.ROCK,
                        Shape.ROCK,
                        Outcome.DRAW
                ));
        assertThat(engine.statistics("user1")).hasSize(3);
        assertThat(engine.statistics("user2")).isEmpty();

        assertThat(engine.play("user2", new RoundRequest("static:PAPER", "static:ROCK")))
                .isEqualTo(new Round(
                        new RoundRequest("static:PAPER", "static:ROCK"),
                        Shape.PAPER,
                        Shape.ROCK,
                        Outcome.WIN_PLAYER1
                ));
        assertThat(engine.statistics("user1")).hasSize(3);
        assertThat(engine.statistics("user2")).hasSize(1);

        engine.clear("user1");
        assertThat(engine.statistics("user1")).isEmpty();
        assertThat(engine.statistics("user2")).hasSize(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoStrategy1() {
        Engine engine = newEngine();
        engine.play("user1", new RoundRequest("aaaaa", "static:ROCK"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoStrategy2() {
        Engine engine = newEngine();
        engine.play("user1", new RoundRequest("static:ROCK", "aaaaa"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNull1() {
        Engine engine = newEngine();
        engine.play(null, new RoundRequest("static:ROCK", "aaaaa"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNull2() {
        Engine engine = newEngine();
        engine.play("user", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNull3() {
        Engine engine = newEngine();
        engine.play("user", new RoundRequest(null, "aaaaa"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNull4() {
        Engine engine = newEngine();
        engine.play("user", new RoundRequest("static:ROCK", null));
    }

    @Test
    public void testNull5() {
        Engine engine = newEngine();
        assertThat(engine.statistics(null)).isEmpty();
    }

    @Test
    public void testNull6() {
        Engine engine = newEngine();
        engine.clear(null);
    }
}
