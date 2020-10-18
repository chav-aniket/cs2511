package unsw.dungeon.entities;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Graph;
import unsw.dungeon.GraphNode;

import java.util.HashSet;
import java.util.Iterator;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Iterator;

public class AStar {
    private Dungeon dungeon;
    private Graph graph;
    private Enemy enemy;
    private HashMap<GraphNode, GraphNode> path;

    public AStar(Enemy enemy, Dungeon dungeon) {
        this.dungeon = dungeon;
        this.enemy = enemy;
        this.graph = dungeon.getGraph();
        path = new HashMap<>();
    }

    /**
     * Set distances in dungeon to infinity
     * @return Hashmap of cells and distances
     */
    public HashMap<GraphNode, Double> initialiseDistances() {
        HashMap<GraphNode, Double> newMap = new HashMap<>();
        for (GraphNode n: dungeon.getGraph().getNodes()) {
            newMap.put(n, Double.POSITIVE_INFINITY);
        }
        return newMap;
    }

    /**
     * Find route for enemy to take in dungeon
     * @param enemyX x-coordinate of enemy
     * @param enemyY y-coordinate of enemy
     * @param destX x-coordinate of destination
     * @param destY y-coordinate of destination
     */
    public void findRoute(int enemyX, int enemyY, int destX, int destY) {
        GraphNode start = graph.getNode(enemyX, enemyY);
        GraphNode end = graph.getNode(destX, destY);
        HashMap<GraphNode, GraphNode> newPath = new HashMap<GraphNode, GraphNode>();
        HashSet<GraphNode> visited = new HashSet<GraphNode>();
        HashMap<GraphNode, Double> distances = initialiseDistances();

        PriorityQueue<GraphNode> open = new PriorityQueue<GraphNode>();
        open.add(start);
        start.setDistanceToStart(0);
        distances.put(start, (double) 0);
        GraphNode curr;

        while (!open.isEmpty()) {
            curr = open.remove();

            if (!visited.contains(curr)) {
                visited.add(curr);

                if (curr.equals(end)) {
                    path = newPath;
                    return;
                }

                ArrayList<GraphNode> neighbours = getNeighbours(curr);
                for (GraphNode neighbour: neighbours) {
                    if (!visited.contains(neighbour)) {
                        if (neighbour == null)
                            continue;
                        double predictedDistance = neighbour.calcDistance(end);

                        double neighbourDistance = 1;
                        double totalDistance = curr.getDistanceToStart() + predictedDistance + neighbourDistance;

                        if (totalDistance < distances.get(neighbour)) {
                            distances.put(neighbour, totalDistance);
                            neighbour.setDistanceToStart(totalDistance);

                            newPath.put(neighbour, curr);
                            open.add(neighbour);
                        }
                    }
                }
            }
        }
    }

    /**
     * Find the furthest point from the player
     * @param enemyX x-coordinate of enemy
     * @param enemyY y-coordinate of enemy
     * @param playerX x-coordinate of player
     * @param playerY y-coordinate of player
     * @return Furthest cell
     */
    public GraphNode findFurthest(int enemyX, int enemyY, int playerX, int playerY) {
        GraphNode start = graph.getNode(enemyX, enemyY);
        GraphNode end = graph.getNode(playerX, playerY);
        HashSet<GraphNode> visited = new HashSet<GraphNode>();
        HashMap<GraphNode, Double> distances = initialiseDistances();

        PriorityQueue<GraphNode> open = new PriorityQueue<GraphNode>();
        open.add(start);
        start.setDistanceToStart(0);
        distances.put(start, (double) 0);
        GraphNode curr = null;

        while (!open.isEmpty()) {
            curr = open.remove();

            if (!visited.contains(curr)) {
                visited.add(curr);

                ArrayList<GraphNode> neighbours = getNeighbours(curr);
                for (GraphNode neighbour: neighbours) {
                    if (!visited.contains(neighbour)) {
                        if (neighbour == null)
                            continue;
                        double predictedDistance = neighbour.calcDistance(end);

                        if (predictedDistance > curr.getPredictedDistance()) {
                            distances.put(neighbour, predictedDistance);
                            neighbour.setPredictedDistance(predictedDistance);
                            neighbour.setDistanceToStart(predictedDistance);
                            open.add(neighbour);
                        }
                    }
                }
            }
        }
        double maxDist = 0, currDist = 0;
        GraphNode maxDistNode = end;
        for (GraphNode n: distances.keySet()) {
            currDist = distances.get(n);
            if (currDist > maxDist) {
                maxDist = currDist;
                maxDistNode = n;
            }
        }
        //System.out.println("Furthest Square: " + maxDistNode.getX() + " " + maxDistNode.getY());
        return maxDistNode;
    }

    /**
     * Get neighbouring cells
     * @param curr Current cell
     * @return List of neighbouring cells
     */
    private ArrayList<GraphNode> getNeighbours(GraphNode curr) {
        ArrayList<GraphNode> neighbours = new ArrayList<>();
        Graph graph = dungeon.getGraph();
        int x = curr.getX();
        int y = curr.getY();

        if (y < dungeon.getHeight() - 1 && (enemy.collisionHandler(x, y + 1)))
            neighbours.add(graph.getNode(x, y + 1));

        if (y > 0 && (enemy.collisionHandler(x, y -1)))
            neighbours.add(graph.getNode(x, y - 1));

        if (x < dungeon.getWidth() && (enemy.collisionHandler(x + 1, y)))
            neighbours.add(graph.getNode(x + 1, y));

        if (x > 0 && (enemy.collisionHandler(x - 1, y)))
            neighbours.add(graph.getNode(x - 1, y));
        
        return neighbours;
    }

    /**
     * Move enemy to next cell
     * @param enemyX x-coordinate of enemy
     * @param enemyY y-coordinate of enemy
     * @param destX x-coordinate of destination
     * @param destY y-coordinate of destination
     */
    public void nextStep(int enemyX, int enemyY, int destX, int destY) {
        if (path.size() == 0) return;
        GraphNode start = graph.getNode(enemyX, enemyY);
        GraphNode end = graph.getNode(destX, destY);
        GraphNode curr = end;
        GraphNode prev = curr;
        while (curr != null && !curr.equals(start)) {
            prev = curr;
            curr = path.get(curr);
        }
        int range = (prev.getX() - start.getX()) + (prev.getX() - start.getY());
        if (range != -1 && range != 1) {
            // System.out.println("rerouting");
            findRoute(enemyX, enemyY, destX, destY);
        }
        if (prev != null)
            enemy.move(prev.getX(), prev.getY());
    }

}