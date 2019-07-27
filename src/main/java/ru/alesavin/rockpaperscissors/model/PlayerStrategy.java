package ru.alesavin.rockpaperscissors.model;

/**
 * Player strategy
 *
 * @author alesavin
 */
public interface PlayerStrategy {

    String id();
    Shape play();
}
