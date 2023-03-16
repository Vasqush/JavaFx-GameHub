package javafx.gamehub.Tetris;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import static javafx.gamehub.Tetris.Tetris.*;

public class Controller {
    // Getting the numbers and the MESH from Tetris
    public static final int MOVE = Tetris.MOVE;
    public static final int SIZE = Tetris.SIZE;
    public static int XMAX = Tetris.XMAX;
    public static int[][] MESH = Tetris.MESH;
    static Tetris tetris = new Tetris();

    public static void startGame() {
        resetGame();
        game = true;
        for ( int[] a : MESH ) {
            Arrays.fill(a, 0);
        }

        Timer fall = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                Platform.runLater(() -> {
                    if ( object.a.getY() == 0 || object.b.getY() == 0 || object.c.getY() == 0
                            || object.d.getY() == 0 )
                        Tetris.top++;
                    else
                        Tetris.top = 0;

                    if ( Tetris.top == 2 ) {
                        // GAME OVER
                        Text over = new Text("GAME OVER");
                        over.setFill(Color.RED);
                        over.setStyle("-fx-font: 70 arial;");
                        over.setY(250);
                        over.setX(60);
                        Tetris.group.getChildren().add(over);
                        Tetris.game = false;
                        return;
                    }

                    if ( Tetris.game ) {
                        tetris.MoveDown(object);
                        Tetris.scoretext.setText("Score: " + score);
                        Tetris.level.setText("Lines: " + linesNo);
                    }

                });
            }
        };
        fall.schedule(task, 0, 300);
    }

    public static void resetGame() {
        group.getChildren().clear();
        for ( int[] mesh : MESH ) {
            Arrays.fill(mesh, 0);
        }
        score = 0;
        linesNo = 0;
    }

    public static void MoveRight(Form form) {
        if ( form.a.getX() + MOVE <= XMAX - SIZE && form.b.getX() + MOVE <= XMAX - SIZE
                && form.c.getX() + MOVE <= XMAX - SIZE && form.d.getX() + MOVE <= XMAX - SIZE ) {
            int moveA = MESH[((int) form.a.getX() / SIZE) + 1][((int) form.a.getY() / SIZE)];
            int moveB = MESH[((int) form.b.getX() / SIZE) + 1][((int) form.b.getY() / SIZE)];
            int moveC = MESH[((int) form.c.getX() / SIZE) + 1][((int) form.c.getY() / SIZE)];
            int moveD = MESH[((int) form.d.getX() / SIZE) + 1][((int) form.d.getY() / SIZE)];
            if ( moveA == 0 && moveA == moveB && moveB == moveC && moveC == moveD ) {
                form.a.setX(form.a.getX() + MOVE);
                form.b.setX(form.b.getX() + MOVE);
                form.c.setX(form.c.getX() + MOVE);
                form.d.setX(form.d.getX() + MOVE);
            }
        }
    }

    public static void MoveLeft(Form form) {
        if ( form.a.getX() - MOVE >= 0 && form.b.getX() - MOVE >= 0 && form.c.getX() - MOVE >= 0
                && form.d.getX() - MOVE >= 0 ) {
            int movea = MESH[((int) form.a.getX() / SIZE) - 1][((int) form.a.getY() / SIZE)];
            int moveb = MESH[((int) form.b.getX() / SIZE) - 1][((int) form.b.getY() / SIZE)];
            int movec = MESH[((int) form.c.getX() / SIZE) - 1][((int) form.c.getY() / SIZE)];
            int moved = MESH[((int) form.d.getX() / SIZE) - 1][((int) form.d.getY() / SIZE)];
            if ( movea == 0 && movea == moveb && moveb == movec && movec == moved ) {
                form.a.setX(form.a.getX() - MOVE);
                form.b.setX(form.b.getX() - MOVE);
                form.c.setX(form.c.getX() - MOVE);
                form.d.setX(form.d.getX() - MOVE);
            }
        }
    }

    public static Form makeRect() {
        int block = (int) (Math.random() * 100);
        String name;
        Rectangle a = new Rectangle(SIZE - 1, SIZE - 1), b = new Rectangle(SIZE - 1, SIZE - 1), c = new Rectangle(SIZE - 1, SIZE - 1),
                d = new Rectangle(SIZE - 1, SIZE - 1);
        if ( block < 15 ) {
            a.setX((XMAX / 2.0) - SIZE);
            b.setX((XMAX / 2.0) - SIZE);
            b.setY(SIZE);
            c.setX((XMAX / 2.0));
            c.setY(SIZE);
            d.setX((XMAX / 2.0) + SIZE);
            d.setY(SIZE);
            name = "j";
        } else if ( block < 30 ) {
            a.setX((XMAX / 2.0) + SIZE);
            b.setX((XMAX / 2.0) - SIZE);
            b.setY(SIZE);
            c.setX((XMAX / 2.0));
            c.setY(SIZE);
            d.setX((XMAX / 2.0) + SIZE);
            d.setY(SIZE);
            name = "l";
        } else if ( block < 45 ) {
            a.setX((XMAX / 2.0) - SIZE);
            b.setX((XMAX / 2.0));
            c.setX((XMAX / 2.0) - SIZE);
            c.setY(SIZE);
            d.setX((XMAX / 2.0));
            d.setY(SIZE);
            name = "o";
        } else if ( block < 60 ) {
            a.setX((XMAX / 2.0) + SIZE);
            b.setX((XMAX / 2.0));
            c.setX((XMAX / 2.0));
            c.setY(SIZE);
            d.setX((XMAX / 2.0) - SIZE);
            d.setY(SIZE);
            name = "s";
        } else if ( block < 75 ) {
            a.setX((XMAX / 2.0) - SIZE);
            b.setX((XMAX / 2.0));
            c.setX((XMAX / 2.0));
            c.setY(SIZE);
            d.setX((XMAX / 2.0) + SIZE);
            name = "t";
        } else if ( block < 90 ) {
            a.setX((XMAX / 2.0) + SIZE);
            b.setX((XMAX / 2.0));
            c.setX((XMAX / 2.0) + SIZE);
            c.setY(SIZE);
            d.setX((XMAX / 2.0) + SIZE + SIZE);
            d.setY(SIZE);
            name = "z";
        } else {
            a.setX((XMAX / 2.0) - SIZE - SIZE);
            b.setX((XMAX / 2.0) - SIZE);
            c.setX((XMAX / 2.0));
            d.setX((XMAX / 2.0) + SIZE);
            name = "i";
        }
        return new Form(a, b, c, d, name);
    }
}