import java.util.ArrayList;
import java.util.List;

public class App {
    private static final byte[][] FINAL_STATE = new byte[][] { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };

    public static void main(String[] args) throws Exception {
        List<byte[][]> initialStates = new ArrayList<>();
        initialStates.add(new byte[][]{{7, 2, 4}, {5, 0, 6}, {8, 3, 1}}); 
        initialStates.add(new byte[][]{{1, 2, 3}, {4, 0, 5}, {6, 7, 8}}); 
        initialStates.add(new byte[][]{{8, 7, 6}, {5, 4, 3}, {2, 1, 0}});  
        initialStates.add(new byte[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}});  
        initialStates.add(new byte[][]{{3, 6, 0}, {1, 8, 4}, {7, 5, 2}});
        initialStates.add(new byte[][]{{4, 1, 2}, {3, 0, 5}, {6, 8, 7}});
        initialStates.add(new byte[][]{{7, 8, 6}, {2, 4, 3}, {5, 0, 1}});
        initialStates.add(new byte[][]{{3, 1, 2}, {5, 4, 6}, {8, 0, 7}});

        for (byte[][] initialState : initialStates) {
            Node initialNode = new Node(initialState, null, 0, FINAL_STATE);
            Tree tree = new Tree(initialNode);
            //tree.bfs();
            //tree.dfs();
            //tree.ucs();
            tree.bidirectionalSearch();
        }

    }
}