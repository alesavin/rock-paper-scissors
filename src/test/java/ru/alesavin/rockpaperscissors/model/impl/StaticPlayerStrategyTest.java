package ru.alesavin.rockpaperscissors.model.impl;

import org.junit.Test;
import ru.alesavin.rockpaperscissors.model.Shape;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Test for StaticPlayerStrategy
 *
 * @author alesavin
 */
public class StaticPlayerStrategyTest {

    @Test
    public void testCases() {
        for (Shape shape: Shape.values()) {
            StaticPlayerStrategy strategy = new StaticPlayerStrategy(shape);
            assertThat(strategy.id()).isEqualTo("static:" + shape);
            int cases = 100;
            while (cases-- > 0) {
                assertThat(strategy.play()).isEqualTo(shape);
            }
        }
    }
}
