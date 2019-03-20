import sweeper.Box;
import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JavaSweeper extends JFrame {

    private Game game;
    private JPanel panel;
    private final int COLS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 10;
    private final int IMAGE_SIZE = 50;

    public static void main(String[] args) {
        new JavaSweeper();
    }

    private JavaSweeper() {
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setImages();
        initPanel();
        initFrame();
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            //прорисовывает элементы
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coord coord : Ranges.getAllCoords()) {
                    g.drawImage((Image) game.getBox(coord).image, coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE, this);
                }
            }
        };
        //обработчик событий мыши
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x, y);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    game.pressLeftButton(coord);
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    game.pressRightButton(coord);
                }
                panel.repaint();

            }
        });

        //устанавливает размеры panel
        panel.setPreferredSize(new Dimension(Ranges.getSize().x * IMAGE_SIZE, Ranges.getSize().y * IMAGE_SIZE));
        //добавляет panel во frame
        add(panel);
    }

    private void initFrame() {
        //останавливает выполнение программы при закрытие окна
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Sweeper");
        //размещает по центру экрана
        setLocationRelativeTo(null);
        //запрещает изменение размеров окна
        setResizable(false);
        setVisible(true);
        setIconImage(getImage("icon"));
        //задает размеры окна игры, чтобы в него поместились все компоненты
        pack();
    }

    private void setImages() {
        for (Box box : Box.values()) {
            box.image = getImage(box.name().toLowerCase());
        }
    }

    private Image getImage(String name) {
        String fileName = "img/" + name + ".png";
        //добаляем картинки в каталог res, затем маркируем его как Resource root
        ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
        return icon.getImage();
    }
}
