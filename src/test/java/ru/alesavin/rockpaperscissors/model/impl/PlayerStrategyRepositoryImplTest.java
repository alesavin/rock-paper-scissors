package ru.alesavin.rockpaperscissors.model.impl;

import org.junit.Test;
import ru.alesavin.rockpaperscissors.model.PlayerStrategyRepository;
import ru.alesavin.rockpaperscissors.model.Shape;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Test for PlayerStrategyRepositoryImpl
 *
 * @author alesavin
 */
public class PlayerStrategyRepositoryImplTest {

    private PlayerStrategyRepository repository = new PlayerStrategyRepositoryImpl(
            new RandomPlayerStrategy(),
            new StaticPlayerStrategy(Shape.ROCK)
    );

    @Test
    public void testResolve() {
        assertThat(repository.resolve("random")).isExactlyInstanceOf(RandomPlayerStrategy.class);
        assertThat(repository.resolve("static:ROCK")).isExactlyInstanceOf(StaticPlayerStrategy.class);
    }

    @Test
    public void testNull() {
        assertThat(repository.resolve("aaaaaa")).isNull();
        assertThat(repository.resolve("")).isNull();
        assertThat(repository.resolve(null)).isNull();
    }
}
