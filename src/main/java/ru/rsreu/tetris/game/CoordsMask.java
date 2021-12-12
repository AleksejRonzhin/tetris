package ru.rsreu.tetris.game;

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
            }
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
            }
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
            }
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
            }
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
            }
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
            }
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
            }
    );

    private final Generation generation;

    CoordsMask(Generation generation) {
        this.generation = generation;
    }

    public Coords[] generateFigure(Coords coords, RotationMode mode) {
        return this.generation.generateFigure(coords, mode);
    }

    private interface Generation {
        Coords[] generateFigure(Coords coords, RotationMode mode);
    }
}
