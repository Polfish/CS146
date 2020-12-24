package Homework.Project3.Code.ChangChon.cs146.project3;

import java.util.ArrayList;

/**
 * This class represents a single cell in a maze, which is basically the same as a vertice or node
 * in a graph.
 */
public class Cell {

    // 0: north, 1: south, 2: west, and 3: east
    private Cell[] neighbors;
    private int position;
    // 0: row and 1: column
    // This is used when printing out the shortest path
    private int[] xyPosition;
    // This String represents the edges and whether or not this cell is connected to their neighbor
    // or not. If they are connected, then the wall between both of them should be " ". If not,
    // then the wall will be "-" for north and south and "|" for west and east.
    // 0: north, 1: south, 2: west, 3: east
    private String[] walls;
    // Whether or not this cell has been visited. Used for depth-first search, breadth-first
    // search, and for finding the shortest path.
    private boolean visited;
    // This variable will be used to track all the visited cells of depth-first search and
    // breadth-first search.
    private String data;

    /**
     * Initialized the cell with a position, with all the walls intact unless its position is -1,
     * and intializes other instance variables
     *
     * @param position
     */
    public Cell(int position) {
        this.position = position;

        // If position is -1, then it shouldn't have any walls because it is outside of the maze.
        // To differentiate it from other cells inside the maze that don't have walls, a blank
        // string ("") is used.
        if (position != -1) {
            walls = new String[]{"-", "-", "|", "|"};
        } else {
            walls = new String[]{"", "", "", ""};
        }

        if (position != -1) {
            xyPosition = new int[]{-1, -1};
        } else {
            xyPosition = new int[2];
        }

        neighbors = new Cell[4];
    }

    /**
     * Gets the neighbors of this cell that are still intact.
     *
     * @return the intact neighbors of this cell
     */
    public ArrayList<Cell> getIntactNeighbors() {
        ArrayList<Cell> intactNeighbors = new ArrayList<>();

        for (Cell neighbor : this.getAllNeighbors()) {
            if (neighbor.allWallsIntact()) {
                intactNeighbors.add(neighbor);
            }

            // Added neighbor.getPosition != -1 because otherwise this function returns a
            // NullPointerException when neighbor is -1. Should also add the last cell, if it
            // hasn't been visited yet.
            if (neighbor.getPosition() != -1 && neighbor.getSouth().getPosition() == -1
                    && neighbor.getEast().getPosition() == -1 && !neighbor.getVisited()) {
                intactNeighbors.add(neighbor);
            }
        }

        return intactNeighbors;
    }

    /**
     * Knocks down the wall between itself and the other cell. Note: you should use the same method
     * for the other cell because this only removes the wall of this cell and not the other cell.
     *
     * @param otherCell the other cell to remove the wall in between
     */
    public void knockDownWall(Cell otherCell) {
        for (int i = 0; i < neighbors.length; i++) {
            if (neighbors[i].getPosition() == otherCell.getPosition()) {
                walls[i] = " ";
            }
        }

        visited = true;
        otherCell.visited = true;
    }

    /**
     * Checks if this cell has all its all walls intact.
     *
     * @return true if all walls intact else false
     */
    public boolean allWallsIntact() {
        for (String wall : walls) {
            if (wall.equals(" ") || position == -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the row and column position of this cell. 0: row and 1: column
     *
     * @return the row and column position of this cell
     */
    public int[] getXYPosition() {
        return xyPosition;
    }

    /**
     * Sets the row and column position of this cell.
     *
     * @param x row
     * @param y column
     */
    public void setXYPosition(int x, int y) {
        xyPosition[0] = x;
        xyPosition[1] = y;
    }

    /**
     * Gets the data of this cell.
     *
     * @return data of this cell
     */
    public String getData() {
        return data;
    }

    /**
     * Sets the data of this cell. Used by MazeSolver to show the order in which cells were
     * visited.
     *
     * @param data data of this cell
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Gets walls of this cell.
     *
     * @return walls of this cell
     */
    public String[] getWalls() {
        return walls;
    }

    /**
     * Gets all neighbors of this cell. 0: north, 1: south, 2: west, and 3: east.
     *
     * @return neighbors as an array
     */
    public Cell[] getAllNeighbors() {
        return neighbors;
    }

    /**
     * Gets connected neighbors, which is any cell that doesn't have a wall between itself and this cell.
     *
     * @return connected neighbors of this cell
     */
    public ArrayList<Cell> getConnectedNeighbors() {
        ArrayList<Cell> neighborsList = new ArrayList<>();
        for (Cell neighbor : neighbors) {
            // Not including -1 because any cell with -1 is outside of the maze
            if (isConnected(neighbor) && neighbor.getPosition() != -1) {
                neighborsList.add(neighbor);
            }
        }
        return neighborsList;
    }

    /**
     * Checks if this cell is connected to the other cell.
     *
     * @param otherCell the other cell to check
     * @return if there is no wall between this cell and other cell, then return true, else false
     */
    public boolean isConnected(Cell otherCell) {
        for (int i = 0; i < neighbors.length; i++) {
            if (neighbors[i] == otherCell && walls[i].equals(" ")) {
                return true;
            }
        }

        return false;
    }

    /**
     * Gets whether this cell has been visited or not.
     *
     * @return if true, then it has been visited, else then it has not
     */
    public boolean getVisited() {
        return visited;
    }

    /**
     * Sets whether this cell has been visited or not.
     *
     * @param visited value to set the visited boolean to
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * Gets the position of this cell.
     *
     * @return position of this cell
     */
    public int getPosition() {
        return position;
    }

    /**
     * Gets the north neighbor of this cell.
     *
     * @return the north neighbor of this cell
     */
    public Cell getNorth() {
        return neighbors[0];
    }

    /**
     * Gets the south neighbor of this cell.
     *
     * @return the south neighbor of this cell
     */
    public Cell getSouth() {
        return neighbors[1];
    }

    /**
     * Gets the west neighbor of this cell.
     *
     * @return the west neighbor of this cell
     */
    public Cell getWest() {
        return neighbors[2];
    }

    /**
     * Gets the east neighbor of this cell.
     *
     * @return the east neighbor of this cell
     */
    public Cell getEast() {
        return neighbors[3];
    }

    /**
     * Sets the north neighbor of this cell.
     *
     * @param north the new value for the north neighbor
     */
    public void setNorth(Cell north) {
        neighbors[0] = north;
    }

    /**
     * Sets the south neighbor of this cell.
     *
     * @param south the new value for the south neighbor
     */
    public void setSouth(Cell south) {
        neighbors[1] = south;
    }

    /**
     * Sets the west neighbor of this cell.
     *
     * @param west the new value for the west neighbor
     */
    public void setWest(Cell west) {
        neighbors[2] = west;
    }

    /**
     * Sets the east neighbor of this cell.
     *
     * @param east the new value for the east neighbor
     */
    public void setEast(Cell east) {
        neighbors[3] = east;
    }
}
