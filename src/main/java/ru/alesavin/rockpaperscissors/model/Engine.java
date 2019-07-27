package ru.alesavin.rockpaperscissors.model;

/**
 * TODO
 *
 * @author alesavin
 */
public interface Engine {

    Round play(String user, RoundRequest request);

    Round[] statistics(String user);

    void clear(String user); // TODO change cookie there?
}
