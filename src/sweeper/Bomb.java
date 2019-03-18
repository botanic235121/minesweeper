package sweeper;

class Bomb {

    private Matrix bombMap;
    private int totalBomb;

    Bomb(int totalBomb) {
        this.totalBomb = totalBomb;
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

    //Устанавливает бомбы
    private void placeBomb() {
        Coord coord = Ranges.getRandomCoord();
        bombMap.set(coord, Box.BOMB);
        incrNumberAroundBomb(coord);
    }
    //Увеличивает номера около бомбы
    private void incrNumberAroundBomb(Coord coord){
        for (Coord around : Ranges.getCoordsAround(coord)) {
            if (Box.BOMB != bombMap.get(around)) {
                bombMap.set(around, bombMap.get(around).getNextNumberBox());
            }
        }
    }
}
