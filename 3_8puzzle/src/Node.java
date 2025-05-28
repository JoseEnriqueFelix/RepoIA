import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Node {
    private ArrayList<Node> children;
    private byte[][] board;
    private Node father;
    private int priority;
    private byte[][] finalState;

    public Node(byte[][] board, Node father, int priority, byte[][] finalState) {
        this.board = board;
        this.father = father;
        this.priority = priority;
        children = new ArrayList<>();
        this.finalState = finalState;
    }

    public void findSuccessors(HashSet<String> visited) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    if (i != 0) {
                        byte[][] copy = copyBoard(board);
                        int aux = copy[i - 1][j];
                        swap(copy, i, j, i - 1, j);
                        addChildren(copy, visited, aux);
                    }
                    if (i != board.length - 1) {
                        byte[][] copy = copyBoard(board);
                        int aux = copy[i + 1][j];
                        swap(copy, i, j, i + 1, j);
                        addChildren(copy, visited, aux);
                    }
                    if (j != 0) {
                        byte[][] copy = copyBoard(board);
                        int aux = copy[i][j - 1];
                        swap(copy, i, j, i, j - 1);
                        addChildren(copy, visited, aux);
                    }
                    if (j != board[i].length - 1) {
                        byte[][] copy = copyBoard(board);
                        int aux = copy[i][j + 1];
                        swap(copy, i, j, i, j + 1);
                        addChildren(copy, visited, aux);
                    }
                }
            }
        }
    }

    public void findSuccessors(HashSet<String> visited, HashSet<Node> visited2) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    if (i != 0) {
                        byte[][] copy = copyBoard(board);
                        int aux = copy[i - 1][j];
                        swap(copy, i, j, i - 1, j);
                        addChildren(copy, visited, aux, visited2);
                    }
                    if (i != board.length - 1) {
                        byte[][] copy = copyBoard(board);
                        int aux = copy[i + 1][j];
                        swap(copy, i, j, i + 1, j);
                        addChildren(copy, visited, aux, visited2);
                    }
                    if (j != 0) {
                        byte[][] copy = copyBoard(board);
                        int aux = copy[i][j - 1];
                        swap(copy, i, j, i, j - 1);
                        addChildren(copy, visited, aux, visited2);
                    }
                    if (j != board[i].length - 1) {
                        byte[][] copy = copyBoard(board);
                        int aux = copy[i][j + 1];
                        swap(copy, i, j, i, j + 1);
                        addChildren(copy, visited, aux, visited2);
                    }
                }
            }
        }
    }

    public boolean isfinalState() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != finalState[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private byte[][] copyBoard(byte[][] original) {
        byte[][] copy = new byte[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return copy;
    }

    private void swap(byte[][] board, int i1, int j1, int i2, int j2) {
        byte temp = board[i1][j1];
        board[i1][j1] = board[i2][j2];
        board[i2][j2] = temp;
    }

    private void addChildren(byte[][] board, HashSet<String> visited, int aux) {
        String s = Arrays.deepToString(board);
        if (visited.add(s)) {
            Node node = new Node(board, this, aux, this.finalState);
            children.add(node);
        }
    }

    private void addChildren(byte[][] board, HashSet<String> visited, int aux, HashSet<Node> visited2) {
        String s = Arrays.deepToString(board);
        if (visited.add(s)) {
            Node node = new Node(board, this, aux, this.finalState);
            children.add(node);
            visited2.add(node);
        }
    }

    public void printBoard() {
        for (byte[] fila : board) {
            for (byte valor : fila) {
                System.out.print(valor + " ");
            }
            System.out.println();
        }
    }

    public byte[][] getFinalState() {
        return finalState;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public byte[][] getBoard() {
        return board;
    }

    public Node getFather() {
        return father;
    }

    public int getPriority() {
        return priority;
    }
}