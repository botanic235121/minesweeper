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
        if (gameOver()) return;
        openBox(coord);
        checkWinner();
    }

    public void pressRightButton(Coord coord) {
        if (gameOver()) return;
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
                setOpenedToClosedBoxesAroundNumber(coord);
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
    private void openBombs(Coord bombed) {
        state = GameState.BOMBED;
        flag.setBombedToBox(bombed);
        // Открывает все бомбы при проигрыше
        for (Coord coord : Ranges.getAllCoords()) {
            if (bomb.get(coord) == Box.BOMB) {
                flag.setOpenedToClosedBox(coord);
            } else
                flag.setNoBombToFlagedSafeBox(coord);
        }
    }

    // открывает соседние с номером клетки, если число установленных флагов равно этому номеру
    private void setOpenedToClosedBoxesAroundNumber(Coord coord) {
        if (flag.get(coord) != Box.BOMB) {
            if (flag.getCountOfFlagedBoxesAround(coord) == bomb.get(coord).getNumber()) {
                for (Coord around : Ranges.getCoordsAround(coord)) {
                    if (flag.get(around) == Box.CLOSED)
                        openBox(around);
                }

            }
        }

    }

    // проверяет не проиграна ли игра
    private boolean gameOver() {
        if (state == GameState.PLAYED) {
            return false;
        } else {
            return true;
        }
    }
}
