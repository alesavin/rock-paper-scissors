package ru.alesavin.rockpaperscissors.model.impl;

import ru.alesavin.rockpaperscissors.model.Outcome;
import ru.alesavin.rockpaperscissors.model.Shape;

import javax.annotation.Nullable;

/**
 * TODO
 *
 * @author alesavin
 */
public class Solver {

    private Solver() {
    }

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
