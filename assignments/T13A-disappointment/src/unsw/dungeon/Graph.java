package unsw.dungeon;

import java.util.ArrayList;
import javafx.util.Pair;

public class Graph {
    private ArrayList<GraphNode> nodes;

    public Graph(int width, int height) {
        this.nodes = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                nodes.add(new GraphNode(i, j));
            }
        }
    }

    /**
     * Get nodes in graph
     * @return List of nodes
     */
    public ArrayList<GraphNode> getNodes() {
        return nodes;
    }

    /**
     * Get node at particular coordinate
     * @param x x-coordinate of node
     * @param y y-coordinate of node
     * @return Node at given coordinates
     */
    public GraphNode getNode(int x, int y) {
        for (GraphNode n: nodes) {
            if (n.getX() == x && n.getY() == y)
                return n;
        }
        return null;
    }
}