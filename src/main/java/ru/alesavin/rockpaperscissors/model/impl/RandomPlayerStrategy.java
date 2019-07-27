package ru.alesavin.rockpaperscissors.model.impl;

import ru.alesavin.rockpaperscissors.model.PlayerStrategy;
import ru.alesavin.rockpaperscissors.model.Shape;

import java.util.Random;

/**
 * TODO
 *
 * @author alesavin
 */
public class RandomPlayerStrategy implements PlayerStrategy {

    private Random random = new Random();

    @Override
    public String id() {
        return "random";
    }

    @Override
    public Shape play() {
        return Shape.values()[random.nextInt(Shape.values().length)];
    }
}
