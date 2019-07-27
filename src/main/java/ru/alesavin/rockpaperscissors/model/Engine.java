package ru.alesavin.rockpaperscissors.model;

/**
 * All stuff work here
 *
 * @author alesavin
 */
public interface Engine {

    Round play(String user, RoundRequest request);

    Round[] statistics(String user);

    void clear(String user);
}
