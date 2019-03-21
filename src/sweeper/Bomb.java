package sweeper;

class Bomb {

    private Matrix bombMap;
    private int totalBomb;

    Bomb(int totalBomb) {
        this.totalBomb = totalBomb;
        fixBombsCount();
    }

    void start() {
        bombMap = new Matrix(Box.ZERO);
        for (int i = 0; i < totalBomb; i++) {
            placeBomb();
        }
    }

    //Получаем бомбу по координатам
    Box get(Coord coord) {
        return bombMap.get(coord);
    }

     int getTotalBombs() {
        return totalBomb;
    }

    //Запрещает устанавливать бомб больше, чем количество клеток поля / 2
    private void fixBombsCount() {
        int maxBombs = (Ranges.getSize().x * Ranges.getSize().y) / 2;
        if (totalBomb > maxBombs)
            totalBomb = maxBombs;
    }

    //Устанавливает бомбы
    private void placeBomb() {
        while (true) {
            Coord coord = Ranges.getRandomCoord();
            //проверка, чтобы исключить усановку нескольких бомб в одинаковое место
            if (Box.BOMB == bombMap.get(coord))
                continue;
            bombMap.set(coord, Box.BOMB);
            incrNumberAroundBomb(coord);
            break;
        }
    }

    //Увеличивает номера около бомбы
    private void incrNumberAroundBomb(Coord coord) {
        for (Coord around : Ranges.getCoordsAround(coord)) {
            if (Box.BOMB != bombMap.get(around)) {
                bombMap.set(around, bombMap.get(around).getNextNumberBox());
            }
        }
    }
}
