package ru.alesavin.rockpaperscissors.model.impl;

import ru.alesavin.rockpaperscissors.model.Outcome;
import ru.alesavin.rockpaperscissors.model.Shape;

import org.springframework.lang.Nullable;

/**
 * Logic for game
 *
 * @author alesavin
 */
public class Solver {

    private Solver() {
    }

    // TODO simplify it. mod(p1.ordinal - p2.ordinal) == 0 => DRAW, ...
    public static @Nullable Outcome solve(Shape p1, Shape p2) {
        if (p1 != null && p2 != null) {
            switch (p1) {
                case ROCK:
                    switch (p2) {
                        case ROCK:
                            return Outcome.DRAW;
                        case PAPER:
                            return Outcome.WIN_PLAYER2;
                        case SCISSORS:
                            return Outcome.WIN_PLAYER1;
                    }
                case PAPER:
                    switch (p2) {
                        case ROCK:
                            return Outcome.WIN_PLAYER1;
                        case PAPER:
                            return Outcome.DRAW;
                        case SCISSORS:
                            return Outcome.WIN_PLAYER2;
                    }
                case SCISSORS:
                    switch (p2) {
                        case ROCK:
                            return Outcome.WIN_PLAYER2;
                        case PAPER:
                            return Outcome.WIN_PLAYER1;
                        case SCISSORS:
                            return Outcome.DRAW;
                    }
            }
        }
        return null;
    }
}
