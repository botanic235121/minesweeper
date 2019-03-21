package sweeper;

public class Game {

    private Bomb bomb;
    private Flag flag;
    private GameState state;

    public Game(int cols, int rows, int bombs) {
        Ranges.setSize(new Coord(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }

    public void start() {
        bomb.start();
        flag.start();
        state = GameState.PLAYED;
    }

    public GameState getState() {
        return state;
    }

    public Box getBox(Coord coord) {
        if (flag.get(coord) == Box.OPENED) {
            return bomb.get(coord);
        } else {
            return flag.get(coord);
        }
    }

    public void pressLeftButton(Coord coord) {
        openBox(coord);
        checkWinner();
    }

    public void pressRightButton(Coord coord) {
        flag.toggleFlagedToBox(coord);
    }
    //проверка на победу
    private void checkWinner() {
        if (state == GameState.PLAYED) {
            //проверяет чтобы число закрытых клеток равнялось числу бобм
            if (flag.getCountOfClosedBoxes() == bomb.getTotalBombs()) {
                state = GameState.WINNER;
            }
        }
    }


    private void openBox(Coord coord) {
        //проверка состояния верхнего слоя (flagMap)
        switch (flag.get(coord)) {
            case OPENED:
                return;
            case FLAGED:
                return;
            case CLOSED:
                //проверка состояния нижнего слоя (bombMap)
                switch (bomb.get(coord)) {
                    //открывает клетку если под ней ноль
                    case ZERO:
                        openedBoxAround(coord);
                        return;
                    //открывает клетку если под ней бомба
                    case BOMB:
                        openBombs(coord);
                        return;
                    //открывает клетку если под ней любое число
                    default:
                        flag.setOpenedToBox(coord);
                        return;
                }
        }
    }

    //рекурсивно открывает соседние клетки рядом с пустой (zero)
    private void openedBoxAround(Coord coord) {
        flag.setOpenedToBox(coord);
        for (Coord around : Ranges.getCoordsAround(coord)) {
            openBox(around);
        }
    }
    //открывавет все бомбы при проигрыше
    private void openBombs(Coord coord){
        state = GameState.BOMBED;
    }
}
