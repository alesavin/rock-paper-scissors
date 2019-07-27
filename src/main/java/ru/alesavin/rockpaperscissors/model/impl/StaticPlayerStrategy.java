package ru.alesavin.rockpaperscissors.model.impl;

import ru.alesavin.rockpaperscissors.model.PlayerStrategy;
import ru.alesavin.rockpaperscissors.model.Shape;

import java.util.Random;

/**
 * TODO
 *
 * @author alesavin
 */
public class StaticPlayerStrategy implements PlayerStrategy {

    private Shape value;

    public StaticPlayerStrategy(Shape value) {
        this.value = value;
    }

    @Override
    public String id() {
        return "static:" + value;
    }

    @Override
    public Shape play() {
        return value;
    }
}
