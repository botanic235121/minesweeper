package sweeper;

class Flag {
    private Matrix flagMap;

    void start() {
        flagMap = new Matrix(Box.CLOSED);
    }
    Box get(Coord coord) {
        return flagMap.get(coord);
    }
    // Устанавливает поле OPENED в переданную клетку
    void setOpenedToBox(Coord coord){
        flagMap.set(coord, Box.OPENED);
    }
    // Устанавливает поле FLAGED в переданную клетку
    void setFlagedToBox(Coord coord){
        flagMap.set(coord, Box.FLAGED);
    }
}
