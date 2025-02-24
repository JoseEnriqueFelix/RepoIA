import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Tree {

    private Queue<Node> queue;
    private Queue<Node> backwardsQueue;
    private Stack<Node> stack;
    private HashSet<String> visitedString;
    private HashSet<String> visitedString2;
    private PriorityQueue<Node> priorityQueue;
    private Node root;

    public Tree(Node root) {
        this.root = root;
    }

    public List<Node> bfs() {
        long startTime = System.currentTimeMillis();
        visitedString = new HashSet<>();
        queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (curr.isfinalState()) {
                long endTime = System.currentTimeMillis();
                List<Node> r = getRoute(curr);
                printData("BFS", visitedString.size(), r, endTime - startTime);
                //printRoute(r);
                return r;
            }
            curr.findSuccessors(visitedString);
            queue.addAll(curr.getChildren());
        }
        System.out.println("No se encontró una solución.");
        System.currentTimeMillis();
        return null;
    }

    public List<Node> dfs() {
        long startTime = System.currentTimeMillis();
        visitedString = new HashSet<>();
        stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            if (curr.isfinalState()) {
                long endTime = System.currentTimeMillis();
                List<Node> r = getRoute(curr);
                printData("DFS", visitedString.size(), r, endTime - startTime);
                //printRoute(r);
                return r;
            }
            curr.findSuccessors(visitedString);
            stack.addAll(curr.getChildren());
        }
        System.out.println("No se encontró una solución.");
        System.currentTimeMillis();
        return null;
    }

    public List<Node> ucs() {
        long startTime = System.currentTimeMillis();
        visitedString = new HashSet<>();
        priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Node::getPriority));
        priorityQueue.add(root);
        while (!priorityQueue.isEmpty()) {
            Node curr = priorityQueue.poll();
            if (curr.isfinalState()) {
                long endTime = System.currentTimeMillis();
                List<Node> r = getRoute(curr);
                printData("UCS", visitedString.size(), r, endTime - startTime);
                //printRoute(r);
                return r;
            }
            curr.findSuccessors(visitedString);
            priorityQueue.addAll(curr.getChildren());
        }
        System.out.println("No se encontró una solución.");
        System.currentTimeMillis();
        return null;
    }

    public List<Node> bidirectionalSearch() {
        long startTime = System.currentTimeMillis();
        visitedString = new HashSet<>();
        visitedString.add(Arrays.deepToString(root.getBoard()));
        queue = new LinkedList<>();
        queue.add(root);
        Node backwardsRoot = new Node(root.getFinalState(), null, 0, root.getBoard());
        backwardsQueue = new LinkedList<>();
        backwardsQueue.add(backwardsRoot);
        visitedString2 = new HashSet<>();
        visitedString2.add(Arrays.deepToString(backwardsRoot.getBoard()));

        HashSet<Node> visitedNode = new HashSet<>();
        HashSet<Node> visitedNode2 = new HashSet<>();

        while (!queue.isEmpty() || !backwardsQueue.isEmpty()) {
            // Busqueda nodo inicial
            if (!queue.isEmpty()) {
                Node currNormal = queue.poll();
                if (visitedString2.contains(Arrays.deepToString(currNormal.getBoard()))) {
                    long endTime = System.currentTimeMillis();
                    List<Node> r = getRouteBidirectional(currNormal, visitedNode, visitedNode2);
                    printData("Bidirectional Search", visitedString.size() + visitedString2.size(), r, endTime - startTime);
                    //printRoute(r);
                    return r;
                }
                currNormal.findSuccessors(visitedString, visitedNode);
                queue.addAll(currNormal.getChildren());
            }
            // Busqueda nodo objetivo
            if (!backwardsQueue.isEmpty()) {
                Node currBackwards = backwardsQueue.poll();
                if (visitedString.contains(Arrays.deepToString(currBackwards.getBoard()))) {
                    long endTime = System.currentTimeMillis();
                    List<Node> r = getRouteBidirectional(currBackwards,visitedNode, visitedNode2);
                    printData("Bidirectional Search", visitedString.size() + visitedString2.size(), r, endTime - startTime);
                    //printRoute(r);
                    return r;
                }
                currBackwards.findSuccessors(visitedString2, visitedNode2);
                backwardsQueue.addAll(currBackwards.getChildren());
            }
        }
        System.out.println("No se encontró una solución.");
        System.currentTimeMillis();
        return null;
    }

    private List<Node> getRoute(Node curr) {
        List<Node> route = new ArrayList<>();
        while (curr != null) {
            route.add(curr);
            curr = curr.getFather();
        }
        Collections.reverse(route);
        return route;
    }

    private List<Node> getRouteBidirectional(Node curr, HashSet<Node> visitedStart, HashSet<Node> visitedDestination) {
        String currBoard = Arrays.deepToString(curr.getBoard());
        Node fromStart = null;
        Node toDestination = null;
        for (Node node : visitedStart) {
            if (Arrays.deepToString(node.getBoard()).equals(currBoard))
                fromStart = node;
        }
        for (Node node : visitedDestination) {
            if (Arrays.deepToString(node.getBoard()).equals(currBoard))
                toDestination = node;
        }
        List<Node> route1 = getRoute(fromStart);
        List<Node> route2 = getRoute(toDestination);
        Collections.reverse(route2);
        if (!route2.isEmpty()) {
            route2.remove(0); 
        }
        route1.addAll(route2);
        return route1;
    }

    public void printRoute(List<Node> route) {
        for (Node node : route) {
            node.printBoard();
            System.out.println();
        }

    }

    private void printData(String title, int visited, List<Node> route, long time) {
        System.out.println("-------------------" + title + "-------------------");
        System.out.println("num. nodos visited: " + visited);
        System.out.println("num. nodos camino: " + route.size());
        System.out.println("Tiempo de ejecución: " + time + " milisegundos");
        System.out.println("-------------------------------------------");
    }
}