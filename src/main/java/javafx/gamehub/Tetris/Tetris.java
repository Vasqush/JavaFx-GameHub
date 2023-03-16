package javafx.gamehub.Tetris;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;

public class Tetris extends Application {
    // The variables
    public static final int MOVE = 35;
    public static final int SIZE = 35;
    public static final Pane group = new Pane();
    public static int XMAX = SIZE * 16;
    public static int YMAX = SIZE * 24;
    public static int[][] MESH = new int[XMAX / SIZE][YMAX / SIZE];
    private static final Scene scene = new Scene(group, XMAX + 350, YMAX);
    public static int score = 0;
    public static Form object;
    public static int top = 0;
    public static boolean game = true;
    private static Form nextObj = Controller.makeRect();
    public static int linesNo = 0;
    static Text level = new Text("Lines: ");
    static Text scoretext = new Text("Score: ");

    @Override
    public void start(Stage stage) {
        // Initialize mesh
        for (int[] a : MESH) {
            Arrays.fill(a, 0);
        }

        // Create UI elements
        Line lineHorizontal = new Line(0, 0, XMAX + 5, 0);
        Line line = new Line(XMAX + 5, 0, XMAX + 5, YMAX);
        scoretext.setStyle("-fx-font: 20 arial;");
        scoretext.relocate(XMAX + 150, 50);

        level.setStyle("-fx-font: 20 arial;");
        level.relocate(XMAX + 150, 100);
        level.setFill(Color.GREEN);

        Button quitGame = new Button("Return");
        quitGame.setStyle("-fx-font: 16 arial;");
        quitGame.setPrefWidth(70);
        quitGame.setPrefHeight(30);
        quitGame.relocate(XMAX + 150, 350);
        quitGame.setOnMouseClicked(event -> {
            stage.close();
        });

        Button startGame = new Button("Start");
        startGame.setStyle("-fx-font: 16 arial;");
        startGame.setPrefWidth(70);
        startGame.setPrefHeight(30);
        startGame.relocate(XMAX + 150, 300);
        startGame.setFocusTraversable(false);
        startGame.setOnMouseClicked(event -> {
            // Start game
            Controller.startGame();
            Form a = nextObj;
            group.getChildren().addAll(scoretext, lineHorizontal, line, level, startGame, quitGame, a.a, a.b, a.c, a.d);
            moveOnKeyPress(a);
            object = a;
            nextObj = Controller.makeRect();
        });

        startGame.setFocusTraversable(false);
        quitGame.setFocusTraversable(false);

        // Add UI elements to group
        group.getChildren().addAll(scoretext, lineHorizontal, line, level, startGame, quitGame);

        // Set up scene and show stage
        stage.setScene(scene);
        stage.setTitle("T E T R I S");
        stage.setResizable(false);
        stage.show();
    }
    public Scene getScene() {
        return scene;
    }

    private void moveOnKeyPress(Form form) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case RIGHT -> Controller.MoveRight(form);
                case DOWN -> {
                    MoveDown(form);
                    score++;
                }
                case LEFT -> Controller.MoveLeft(form);
                case UP -> MoveTurn(form);
            }
            event.consume();
        });
    }
    private void RemoveRows() {
        ArrayList<Node> rects = new ArrayList<>();
        ArrayList<Integer> lines = new ArrayList<>();
        ArrayList<Node> newRects = new ArrayList<>();
        int full = 0;
        for ( int i = 0; i < MESH[0].length; i++ ) {
            for ( int[] mesh : MESH ) {
                if ( mesh[i] == 1 )
                    full++;
            }
            if ( full == MESH.length )
                lines.add(i);
            //lines.add(i + lines.size());
            full = 0;
        }
        if ( lines.size() > 0 )
            do {
                for ( Node node : Tetris.group.getChildren() ) {
                    if ( node instanceof Rectangle )
                        rects.add(node);
                }
                score += 50;
                linesNo++;

                for ( Node node : rects ) {
                    Rectangle a = (Rectangle) node;
                    if ( a.getY() == lines.get(0) * SIZE ) {
                        MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 0;
                        Tetris.group.getChildren().remove(node);
                    } else
                        newRects.add(node);
                }

                for ( Node node : newRects ) {
                    Rectangle a = (Rectangle) node;
                    if ( a.getY() < lines.get(0) * SIZE ) {
                        MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 0;
                        a.setY(a.getY() + SIZE);
                    }
                }
                lines.remove(0);
                rects.clear();
                newRects.clear();
                for ( Node node : Tetris.group.getChildren() ) {
                    if ( node instanceof Rectangle )
                        rects.add(node);
                }
                for ( Node node : rects ) {
                    Rectangle a = (Rectangle) node;
                    MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 1;

                }
                rects.clear();
            } while (lines.size() > 0);
    }

    private void MoveTurn(Form form) {
        int f = form.form;
        Rectangle a = form.a;
        Rectangle b = form.b;
        Rectangle c = form.c;
        Rectangle d = form.d;
        switch (form.getName()) {
            case "j":
                if ( f == 1 && cB(a, 1, -1) && cB(c, -1, -1) && cB(d, -2, -2) ) {
                    MoveRight(form.a);
                    MoveDown(form.a);
                    MoveDown(form.c);
                    MoveLeft(form.c);
                    MoveDown(form.d);
                    MoveDown(form.d);
                    MoveLeft(form.d);
                    MoveLeft(form.d);
                    form.changeForm();
                    break;
                }
                if ( f == 2 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, -2, 2) ) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveLeft(form.d);
                    MoveLeft(form.d);
                    MoveUp(form.d);
                    MoveUp(form.d);
                    form.changeForm();
                    break;
                }
                if ( f == 3 && cB(a, -1, 1) && cB(c, 1, 1) && cB(d, 2, 2) ) {
                    MoveLeft(form.a);
                    MoveUp(form.a);
                    MoveUp(form.c);
                    MoveRight(form.c);
                    MoveUp(form.d);
                    MoveUp(form.d);
                    MoveRight(form.d);
                    MoveRight(form.d);
                    form.changeForm();
                    break;
                }
                if ( f == 4 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 2, -2) ) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveRight(form.d);
                    MoveRight(form.d);
                    MoveDown(form.d);
                    MoveDown(form.d);
                    form.changeForm();
                    break;
                }
                break;
            case "l":
                if ( f == 1 && cB(a, 1, -1) && cB(c, 1, 1) && cB(b, 2, 2) ) {
                    MoveRight(form.a);
                    MoveDown(form.a);
                    MoveUp(form.c);
                    MoveRight(form.c);
                    MoveUp(form.b);
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveRight(form.b);
                    form.changeForm();
                    break;
                }
                if ( f == 2 && cB(a, -1, -1) && cB(b, 2, -2) && cB(c, 1, -1) ) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveRight(form.b);
                    MoveRight(form.b);
                    MoveDown(form.b);
                    MoveDown(form.b);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    form.changeForm();
                    break;
                }
                if ( f == 3 && cB(a, -1, 1) && cB(c, -1, -1) && cB(b, -2, -2) ) {
                    MoveLeft(form.a);
                    MoveUp(form.a);
                    MoveDown(form.c);
                    MoveLeft(form.c);
                    MoveDown(form.b);
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveLeft(form.b);
                    form.changeForm();
                    break;
                }
                if ( f == 4 && cB(a, 1, 1) && cB(b, -2, 2) && cB(c, -1, 1) ) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveLeft(form.b);
                    MoveLeft(form.b);
                    MoveUp(form.b);
                    MoveUp(form.b);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    form.changeForm();
                    break;
                }
                break;
            case "o":
                break;
            case "s":
                if ( f == 1 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, 0, 2) ) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveUp(form.d);
                    MoveUp(form.d);
                    form.changeForm();
                    break;
                }
                if ( f == 2 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 0, -2) ) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveDown(form.d);
                    MoveDown(form.d);
                    form.changeForm();
                    break;
                }
                if ( f == 3 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, 0, 2) ) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveUp(form.d);
                    MoveUp(form.d);
                    form.changeForm();
                    break;
                }
                if ( f == 4 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 0, -2) ) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveDown(form.d);
                    MoveDown(form.d);
                    form.changeForm();
                    break;
                }
                break;
            case "t":
                if ( f == 1 && cB(a, 1, 1) && cB(d, -1, -1) && cB(c, -1, 1) ) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveDown(form.d);
                    MoveLeft(form.d);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    form.changeForm();
                    break;
                }
                if ( f == 2 && cB(a, 1, -1) && cB(d, -1, 1) && cB(c, 1, 1) ) {
                    MoveRight(form.a);
                    MoveDown(form.a);
                    MoveLeft(form.d);
                    MoveUp(form.d);
                    MoveUp(form.c);
                    MoveRight(form.c);
                    form.changeForm();
                    break;
                }
                if ( f == 3 && cB(a, -1, -1) && cB(d, 1, 1) && cB(c, 1, -1) ) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveUp(form.d);
                    MoveRight(form.d);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    form.changeForm();
                    break;
                }
                if ( f == 4 && cB(a, -1, 1) && cB(d, 1, -1) && cB(c, -1, -1) ) {
                    MoveLeft(form.a);
                    MoveUp(form.a);
                    MoveRight(form.d);
                    MoveDown(form.d);
                    MoveDown(form.c);
                    MoveLeft(form.c);
                    form.changeForm();
                    break;
                }
                break;
            case "z":
                if ( f == 1 && cB(b, 1, 1) && cB(c, -1, 1) && cB(d, -2, 0) ) {
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveLeft(form.d);
                    MoveLeft(form.d);
                    form.changeForm();
                    break;
                }
                if ( f == 2 && cB(b, -1, -1) && cB(c, 1, -1) && cB(d, 2, 0) ) {
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveRight(form.d);
                    MoveRight(form.d);
                    form.changeForm();
                    break;
                }
                if ( f == 3 && cB(b, 1, 1) && cB(c, -1, 1) && cB(d, -2, 0) ) {
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveLeft(form.d);
                    MoveLeft(form.d);
                    form.changeForm();
                    break;
                }
                if ( f == 4 && cB(b, -1, -1) && cB(c, 1, -1) && cB(d, 2, 0) ) {
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveRight(form.d);
                    MoveRight(form.d);
                    form.changeForm();
                    break;
                }
                break;
            case "i":
                if ( f == 1 && cB(a, 2, 2) && cB(b, 1, 1) && cB(d, -1, -1) ) {
                    MoveUp(form.a);
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.a);
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveDown(form.d);
                    MoveLeft(form.d);
                    form.changeForm();
                    break;
                }
                if ( f == 2 && cB(a, -2, -2) && cB(b, -1, -1) && cB(d, 1, 1) ) {
                    MoveDown(form.a);
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.a);
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveUp(form.d);
                    MoveRight(form.d);
                    form.changeForm();
                    break;
                }
                if ( f == 3 && cB(a, 2, 2) && cB(b, 1, 1) && cB(d, -1, -1) ) {
                    MoveUp(form.a);
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.a);
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveDown(form.d);
                    MoveLeft(form.d);
                    form.changeForm();
                    break;
                }
                if ( f == 4 && cB(a, -2, -2) && cB(b, -1, -1) && cB(d, 1, 1) ) {
                    MoveDown(form.a);
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.a);
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveUp(form.d);
                    MoveRight(form.d);
                    form.changeForm();
                    break;
                }
                break;
        }
    }

    private void MoveDown(Rectangle rect) {
        if ( rect.getY() + MOVE < YMAX )
            rect.setY(rect.getY() + MOVE);

    }

    private void MoveRight(Rectangle rect) {
        if ( rect.getX() + MOVE <= XMAX - SIZE )
            rect.setX(rect.getX() + MOVE);
    }

    private void MoveLeft(Rectangle rect) {
        if ( rect.getX() - MOVE >= 0 )
            rect.setX(rect.getX() - MOVE);
    }

    private void MoveUp(Rectangle rect) {
        if ( rect.getY() - MOVE > 0 )
            rect.setY(rect.getY() - MOVE);
    }

    public void MoveDown(Form form) {
        if ( form.a.getY() == YMAX - SIZE || form.b.getY() == YMAX - SIZE || form.c.getY() == YMAX - SIZE
                || form.d.getY() == YMAX - SIZE || moveA(form) || moveB(form) || moveC(form) || moveD(form) ) {
            MESH[(int) form.a.getX() / SIZE][(int) form.a.getY() / SIZE] = 1;
            MESH[(int) form.b.getX() / SIZE][(int) form.b.getY() / SIZE] = 1;
            MESH[(int) form.c.getX() / SIZE][(int) form.c.getY() / SIZE] = 1;
            MESH[(int) form.d.getX() / SIZE][(int) form.d.getY() / SIZE] = 1;
            RemoveRows();

            Form a = nextObj;
            nextObj = Controller.makeRect();
            object = a;
            group.getChildren().addAll(a.a, a.b, a.c, a.d);
            moveOnKeyPress(a);
        }

        if ( form.a.getY() + MOVE < YMAX && form.b.getY() + MOVE < YMAX && form.c.getY() + MOVE < YMAX
                && form.d.getY() + MOVE < YMAX ) {
            int movea = MESH[(int) form.a.getX() / SIZE][((int) form.a.getY() / SIZE) + 1];
            int moveb = MESH[(int) form.b.getX() / SIZE][((int) form.b.getY() / SIZE) + 1];
            int movec = MESH[(int) form.c.getX() / SIZE][((int) form.c.getY() / SIZE) + 1];
            int moved = MESH[(int) form.d.getX() / SIZE][((int) form.d.getY() / SIZE) + 1];
            if ( movea == 0 && movea == moveb && moveb == movec && movec == moved ) {
                form.a.setY(form.a.getY() + MOVE);
                form.b.setY(form.b.getY() + MOVE);
                form.c.setY(form.c.getY() + MOVE);
                form.d.setY(form.d.getY() + MOVE);
            }
        }
    }

    private boolean moveA(Form form) {
        return (MESH[(int) form.a.getX() / SIZE][((int) form.a.getY() / SIZE) + 1] == 1);
    }

    private boolean moveB(Form form) {
        return (MESH[(int) form.b.getX() / SIZE][((int) form.b.getY() / SIZE) + 1] == 1);
    }

    private boolean moveC(Form form) {
        return (MESH[(int) form.c.getX() / SIZE][((int) form.c.getY() / SIZE) + 1] == 1);
    }

    private boolean moveD(Form form) {
        return (MESH[(int) form.d.getX() / SIZE][((int) form.d.getY() / SIZE) + 1] == 1);
    }

    private boolean cB(Rectangle rect, int x, int y) {
        boolean xb = false;
        boolean yb = false;
        if ( x >= 0 )
            xb = rect.getX() + x * MOVE <= XMAX - SIZE;
        if ( x < 0 )
            xb = rect.getX() + x * MOVE >= 0;
        if ( y >= 0 )
            yb = rect.getY() - y * MOVE > 0;
        if ( y < 0 )
            yb = rect.getY() + y * MOVE < YMAX;
        return xb && yb && MESH[((int) rect.getX() / SIZE) + x][((int) rect.getY() / SIZE) - y] == 0;
    }

    public static void main(String[] args) {
        launch();
    }
}