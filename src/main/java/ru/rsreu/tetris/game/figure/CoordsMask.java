package ru.rsreu.tetris.game.figure;

import ru.rsreu.tetris.game.Coords;

public enum CoordsMask {
    I_FORM(
            (coords, rotation) -> {
                Coords[] realCoords = new Coords[4];
                int x = coords.getX();
                int y = coords.getY();
                switch (rotation) {
                    case NORMAL, TWO_FLIP -> {
                        realCoords[0] = coords;
                        realCoords[1] = new Coords(x, y - 1);
                        realCoords[2] = new Coords(x, y - 2);
                        realCoords[3] = new Coords(x, y - 3);
                    }
                    case ONE_FLIP, THREE_FLIP -> {
                        realCoords[0] = coords;
                        realCoords[1] = new Coords(x + 1, y);
                        realCoords[2] = new Coords(x + 2, y);
                        realCoords[3] = new Coords(x + 3, y);
                    }
                }
                return realCoords;
            },
            new Coords[]{new Coords(2, 1), new Coords(2, 2), new Coords(2, 3), new Coords(2, 4)}
    ),
    O_FORM(
            (coords, rotation) -> {
                Coords[] realCoords = new Coords[4];
                int x = coords.getX();
                int y = coords.getY();
                realCoords[0] = coords;
                realCoords[1] = new Coords(x, y - 1);
                realCoords[2] = new Coords(x + 1, y - 1);
                realCoords[3] = new Coords(x + 1, y);
                return realCoords;
            },
            new Coords[]{new Coords(2, 2), new Coords(2, 3), new Coords(3, 3), new Coords(3, 2)}
    ),
    T_FORM(
            (coords, rotation) -> {
                Coords[] realCoords = new Coords[4];
                int x = coords.getX();
                int y = coords.getY();
                switch (rotation) {
                    case NORMAL -> {
                        realCoords[0] = coords;
                        realCoords[1] = new Coords(x + 1, y);
                        realCoords[2] = new Coords(x + 1, y - 1);
                        realCoords[3] = new Coords(x + 2, y);
                    }
                    case ONE_FLIP -> {
                        realCoords[0] = new Coords(x, y - 1);
                        realCoords[1] = new Coords(x + 1, y - 1);
                        realCoords[2] = new Coords(x + 1, y - 2);
                        realCoords[3] = new Coords(x + 1, y);
                    }
                    case TWO_FLIP -> {
                        realCoords[0] = new Coords(x, y - 1);
                        realCoords[1] = new Coords(x + 1, y - 1);
                        realCoords[2] = new Coords(x + 1, y);
                        realCoords[3] = new Coords(x + 2, y - 1);
                    }
                    case THREE_FLIP -> {
                        realCoords[0] = coords;
                        realCoords[1] = new Coords(x, y - 1);
                        realCoords[2] = new Coords(x + 1, y - 1);
                        realCoords[3] = new Coords(x, y - 2);
                    }
                }
                return realCoords;
            },
            new Coords[]{new Coords(2, 2), new Coords(3, 2), new Coords(4, 2), new Coords(3, 3)}
    ),
    J_FORM(
            (coords, rotation) -> {
                Coords[] realCoords = new Coords[4];
                int x = coords.getX();
                int y = coords.getY();
                switch (rotation) {
                    case NORMAL -> {
                        realCoords[0] = new Coords(x + 1, y);
                        realCoords[1] = new Coords(x + 1, y - 1);
                        realCoords[2] = new Coords(x + 1, y - 2);
                        realCoords[3] = new Coords(x, y - 2);
                    }
                    case ONE_FLIP -> {
                        realCoords[0] = coords;
                        realCoords[1] = new Coords(x, y - 1);
                        realCoords[2] = new Coords(x + 1, y - 1);
                        realCoords[3] = new Coords(x + 2, y - 1);
                    }
                    case TWO_FLIP -> {
                        realCoords[0] = new Coords(x + 1, y);
                        realCoords[1] = coords;
                        realCoords[2] = new Coords(x, y - 1);
                        realCoords[3] = new Coords(x, y - 2);
                    }
                    case THREE_FLIP -> {
                        realCoords[0] = coords;
                        realCoords[1] = new Coords(x + 1, y);
                        realCoords[2] = new Coords(x + 2, y);
                        realCoords[3] = new Coords(x + 2, y - 1);
                    }
                }
                return realCoords;
            },
            new Coords[]{new Coords(2, 2), new Coords(2, 3), new Coords(2, 4), new Coords(3, 2)}
    ),
    L_FORM(
            (coords, rotation) -> {
                Coords[] realCoords = new Coords[4];
                int x = coords.getX();
                int y = coords.getY();
                switch (rotation) {
                    case NORMAL -> {
                        realCoords[0] = coords;
                        realCoords[1] = new Coords(x, y - 1);
                        realCoords[2] = new Coords(x, y - 2);
                        realCoords[3] = new Coords(x + 1, y - 2);
                    }
                    case ONE_FLIP -> {
                        realCoords[0] = new Coords(x, y - 1);
                        realCoords[1] = coords;
                        realCoords[2] = new Coords(x + 1, y);
                        realCoords[3] = new Coords(x + 2, y);
                    }
                    case TWO_FLIP -> {
                        realCoords[0] = coords;
                        realCoords[1] = new Coords(x + 1, y);
                        realCoords[2] = new Coords(x + 1, y - 1);
                        realCoords[3] = new Coords(x + 1, y - 2);
                    }
                    case THREE_FLIP -> {
                        realCoords[0] = new Coords(x, y - 1);
                        realCoords[1] = new Coords(x + 1, y - 1);
                        realCoords[2] = new Coords(x + 2, y - 1);
                        realCoords[3] = new Coords(x + 2, y);
                    }
                }
                return realCoords;
            },
            new Coords[]{new Coords(2, 2), new Coords(3, 2), new Coords(3, 3), new Coords(3, 4)}
    ),
    S_FORM(
            (coords, rotation) -> {
                Coords[] realCoords = new Coords[4];
                int x = coords.getX();
                int y = coords.getY();
                switch (rotation) {
                    case NORMAL, TWO_FLIP -> {
                        realCoords[0] = coords;
                        realCoords[1] = new Coords(x + 1, y);
                        realCoords[2] = new Coords(x + 1, y - 1);
                        realCoords[3] = new Coords(x + 2, y - 1);
                    }
                    case ONE_FLIP, THREE_FLIP -> {
                        realCoords[0] = new Coords(x, y - 2);
                        realCoords[1] = new Coords(x, y - 1);
                        realCoords[2] = new Coords(x + 1, y - 1);
                        realCoords[3] = new Coords(x + 1, y);
                    }
                }
                return realCoords;
            },
            new Coords[]{new Coords(2, 2), new Coords(3, 2), new Coords(3, 3), new Coords(4, 3)}
    ),
    Z_FORM(
            (coords, rotation) -> {
                Coords[] realCoords = new Coords[4];
                int x = coords.getX();
                int y = coords.getY();
                switch (rotation) {
                    case NORMAL, TWO_FLIP -> {
                        realCoords[0] = new Coords(x, y - 1);
                        realCoords[1] = new Coords(x + 1, y - 1);
                        realCoords[2] = new Coords(x + 1, y);
                        realCoords[3] = new Coords(x + 2, y);
                    }
                    case ONE_FLIP, THREE_FLIP -> {
                        realCoords[0] = coords;
                        realCoords[1] = new Coords(x, y - 1);
                        realCoords[2] = new Coords(x + 1, y - 1);
                        realCoords[3] = new Coords(x + 1, y - 2);
                    }
                }
                return realCoords;
            },
            new Coords[]{new Coords(2, 3), new Coords(3, 3), new Coords(3, 2), new Coords(4, 2)}
    );

    private final Generation generation;
    private final Coords[] view;

    CoordsMask(Generation generation, Coords[] view) {
        this.generation = generation;
        this.view = view;
    }

    public Coords[] generateFigure(Coords coords, RotationMode mode) {
        return this.generation.generateFigure(coords, mode);
    }

    public Coords[] getView() {
        return view;
    }

    private interface Generation {
        Coords[] generateFigure(Coords coords, RotationMode mode);
    }
}
