package sweeper;

class Flag {
    private Matrix flagMap;
    private int countOfClosedBoxes;

    void start() {
        flagMap = new Matrix(Box.CLOSED);
        //инициализируем количесто закрытых клеток значением равным размеру игрового поля
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
    }

    Box get(Coord coord) {
        return flagMap.get(coord);
    }

    int getCountOfClosedBoxes() {
        return countOfClosedBoxes;
    }

    //Меняет FLAGED на CLOSED и наоборот при нажатии ПКМ
    void toggleFlagedToBox(Coord coord) {
        switch (flagMap.get(coord)) {
            case FLAGED:
                setClosedToBox(coord);
                break;
            case CLOSED:
                setFlagedToBox(coord);
                break;
        }
    }

    // Устанавливает значение CLOSED в переданную клетку
    void setClosedToBox(Coord coord) {
        flagMap.set(coord, Box.CLOSED);
    }

    // Устанавливает значение OPENED в переданную клетку
    void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.OPENED);
        //при открытие клетки уменьшает количесто закрытых на 1
        countOfClosedBoxes--;
    }

    // Устанавливает значение FLAGED в переданную клетку
    void setFlagedToBox(Coord coord) {
        flagMap.set(coord, Box.FLAGED);
    }

    // Устанавливает значение BOMBED в переданную клетку
    void setBombedToBox(Coord coord) {
        flagMap.set(coord, Box.BOMBED);
    }

    // Устанавливает значение OPENED в клетку с бомбой
    void setOpenedToClosedBox(Coord coord) {
        if (flagMap.get(coord) == Box.CLOSED) {
            flagMap.set(coord, Box.OPENED);
        }
    }

    // Устанавливает значение NOBOMB в клетку, помеченную FLAGED, но в которой нет бомбы
    void setNoBombToFlagedSafeBox(Coord coord) {
        if (flagMap.get(coord) == Box.FLAGED) {
            flagMap.set(coord, Box.NOBOMB);
        }
    }

    // Подсчитывает количесто флагов рядом с переданной клеткой
    int getCountOfFlagedBoxesAround(Coord coord) {
        int count = 0;
        for (Coord around : Ranges.getCoordsAround(coord)) {
            if (flagMap.get(around) == Box.FLAGED) {
                count++;
            }
        }
        return count;
    }
}
