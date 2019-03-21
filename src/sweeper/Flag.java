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

    // Устанавливает поле CLOSED в переданную клетку
    void setClosedToBox(Coord coord) {
        flagMap.set(coord, Box.CLOSED);
    }

    // Устанавливает поле OPENED в переданную клетку
    void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.OPENED);
        //при открытие клетки уменьшает количесто закрытых на 1
        countOfClosedBoxes--;
    }

    // Устанавливает поле FLAGED в переданную клетку
    void setFlagedToBox(Coord coord) {
        flagMap.set(coord, Box.FLAGED);
    }
}
