package ru.alesavin.rockpaperscissors.model.impl;

import org.junit.Test;
import ru.alesavin.rockpaperscissors.model.Outcome;
import ru.alesavin.rockpaperscissors.model.Shape;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * TODO
 *
 * @author alesavin
 */
public class RandomPlayerStrategyTest {

    private RandomPlayerStrategy strategy = new RandomPlayerStrategy();

    @Test
    public void testId() {
        assertThat(strategy.id()).isEqualTo("random");
    }

    @Test
    public void testCase() {
        Set<Shape> set = new HashSet<>();
        int cases = 100;
        while (cases-- > 0) {
            set.add(strategy.play());
        }
        assertThat(set.size()).isEqualTo(3);
    }
}
