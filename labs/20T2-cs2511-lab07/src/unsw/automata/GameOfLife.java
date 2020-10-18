/**
 *
 */
package unsw.automata;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Conway's Game of Life on a 10x10 grid.
 *
 * @author Robert Clifton-Everest
 *
 */
public class GameOfLife {
    private BooleanProperty[][] gameBoard;
    private final int boardSize = 10;

    public GameOfLife() {
        gameBoard = createBoard(boardSize);
    }

    public int getSize() {
        return boardSize;
    }
    
    public BooleanProperty cellProperty(int x, int y) {
        return gameBoard[x][y];
    }

    public BooleanProperty[][] createBoard(int size) {
        BooleanProperty[][] newBoard = new BooleanProperty[size][size];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                newBoard[i][j] = new SimpleBooleanProperty(false);
            }
        }
        return newBoard;
    }

    public BooleanProperty[][] copyBoard() {
        BooleanProperty[][] copy = createBoard(boardSize);
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                copy[i][j].set(isAlive(i, j));
            }
        }
        return copy;
    }

    public void resetBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                ensureDead(i, j);
            }
        }
    }

    public void ensureAlive(int x, int y) {
        cellProperty(x, y).set(true);
    }

    public void ensureDead(int x, int y) {
        cellProperty(x, y).set(false);
    }

    public boolean isAlive(int x, int y) {
        return gameBoard[x][y].get();
    }

    public int neighboursAlive(int x, int y) {
        int nAlive = 0;
        for (int i = x - 1; i < x + 2; i++) {
            int xi = (i + boardSize) % boardSize;
            for (int j = y - 1; j < y + 2; j++) {
                int yj = (j + boardSize) % boardSize;

                if (isAlive(xi, yj) && !(xi == x && yj == y))
                    nAlive++;
            }
        }   
        return nAlive;
    }

    public boolean nextState(BooleanProperty[][] board, int x, int y) {
        int n = neighboursAlive(x, y);
        if (board[x][y].get() && (n == 2 || n == 3))
            return true;
        if (!board[x][y].get() && n == 3)
            return true;
        return false;
    }

    public void tick() {
        BooleanProperty[][] refBoard = copyBoard();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (nextState(refBoard, i, j)) {
                    ensureAlive(i, j);
                } else {
                    ensureDead(i, j);
                }
            }
        }
    }

}
