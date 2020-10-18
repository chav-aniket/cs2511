package unsw.dungeon;

import java.lang.Math;

public class GraphNode implements Comparable {

    private final int x, y;
    private double startDistance = Double.POSITIVE_INFINITY;
    private double predictedDistance = Double.POSITIVE_INFINITY;
    
    public GraphNode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get X
     * @return x-coordinate of node
     */
    public int getX() {
        return x;
    }

    /**
     * Get Y
     * @return y-coordinate of node
     */
    public int getY() {
        return y;
    }

    /**
     * Compare distances of two nodes
     * @param o Node of which to be compared
     * @return Distance between start and given node
     */
    public int compareTo(Object o) {
        GraphNode n = (GraphNode) o;
        Double distance = startDistance - n.getDistanceToStart();
        return distance.intValue();
    }

    /**
     * Check if two nodes are equivalent
     * @param o Node of which to be compared
     * @return Value of whether nodes are equivalent
     */
    @Override
    public boolean equals(Object o) {
        GraphNode n = (GraphNode) o;
        if (n != null && n.getX() == getX() && n.getY() == getY())
            return true;
        return false;
    }

    /**
     * Calculate geometric distance between two points
     * @param g Node of which to be used in calculation
     * @return Distance between points
     */
    public int calcDistance(GraphNode g) {
        Double distance = Math.sqrt(Math.pow((g.getY() - getY()), 2) + Math.pow((g.getX() - getX()), 2));
        return distance.intValue();
    }

    /**
     * Set the distance to start point
     * @param val Distance
     */
    public void setDistanceToStart(double val) {
        startDistance = val;
    }

    /**
     * Set the predicted distance
     * @param val Predicted distance
     */
    public void setPredictedDistance(double val) {
        predictedDistance = val;
    }

    /**
     * Get the distance to start point
     * @return Distance
     */
    public double getDistanceToStart() {
        return startDistance;
    }

    /**
     * Get the predicted distance
     * @return Predicted distance
     */
    public double getPredictedDistance() {
        return predictedDistance;
    }

}