package ru.rsreu.tetris.game;

public enum CoordsMask {
    I_FORM(
            (coords, rotation) -> {
                Coords[] ret = new Coords[4];
                int x = coords.getX();
                int y = coords.getY();
                switch (rotation) {
                    case NORMAL, TWO_FLIP -> {
                        ret[0] = coords;
                        ret[1] = new Coords(x, y - 1);
                        ret[2] = new Coords(x, y - 2);
                        ret[3] = new Coords(x, y - 3);
                    }
                    case ONE_FLIP, THREE_FLIP -> {
                        ret[0] = coords;
                        ret[1] = new Coords(x + 1, y);
                        ret[2] = new Coords(x + 2, y);
                        ret[3] = new Coords(x + 3, y);
                    }
                    default -> {
                    }
                }

                return ret;
            }
    );

    private interface GenerationDelegate{
        Coords[] generateFigure(Coords coords, RotationMode mode);
    }

    private GenerationDelegate forms;

    CoordsMask(GenerationDelegate forms){
        this.forms = forms;
    }

    public Coords[] generateFigure(Coords coords, RotationMode mode){
        return this.forms.generateFigure(coords, mode);
    }
}
