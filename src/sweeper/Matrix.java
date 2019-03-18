package sweeper;

class Matrix {

    //создаем двумерный массив для хранения элементов Box
    private Box[][] matrix;

    Matrix(Box defaultBox) {
        matrix = new Box[Ranges.getSize().x][Ranges.getSize().y];
        //заполняем матрицу переданными элементами
        for (Coord coord : Ranges.getAllCoords()) {
            matrix[coord.x][coord.y] = defaultBox;
        }
    }

    //геттер возвращает не всю матрицу, а элемент Box по его координатам
    Box get(Coord coord) {
        //проверка, чтобы не выйти за пределы массива
        if (Ranges.inRange(coord)) {
            return matrix[coord.x][coord.y];
        } else return null;
    }

    //сеттер задает элемент Box по переданным координатам
    void set(Coord coord, Box box) {
        //проверка, чтобы не выйти за пределы массива
        if (Ranges.inRange(coord)) {
            matrix[coord.x][coord.y] = box;
        }
    }
}
