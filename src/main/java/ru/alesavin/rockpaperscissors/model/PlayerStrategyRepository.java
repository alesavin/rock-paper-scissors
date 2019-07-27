package ru.alesavin.rockpaperscissors.model;

/**
 * TODO
 *
 * @author alesavin
 */
public interface PlayerStrategyRepository {

    PlayerStrategy resolve(final String id);
}
