package sweeper;

import java.util.ArrayList;

public class Ranges {
    public static Coord size;
    public static ArrayList<Coord> allCoords;

    static void setSize(Coord _size) {
        size = _size;
        allCoords = new ArrayList<Coord>();
        for (int y = 0; y < size.y; y++) {
            for (int x = 0; x < size.x; x++) {
                allCoords.add(new Coord(x, y));
            }
        }
    }

    public static Coord getSize() {
        return size;
    }

    public static ArrayList<Coord> getAllCoords() {
        return allCoords;
    }
}
