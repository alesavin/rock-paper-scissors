package ru.alesavin.rockpaperscissors.model;

/**
 * Repository for palyer strategies
 *
 * @author alesavin
 */
public interface PlayerStrategyRepository {

    PlayerStrategy resolve(final String id);
}
