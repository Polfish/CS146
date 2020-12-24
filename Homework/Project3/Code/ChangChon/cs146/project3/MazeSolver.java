package Homework.Project3.Code.ChangChon.cs146.project3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Solves the given maze using depth-first search and breadth-first search. Also, uses the solved
 * maze to find the shortest path from the starting cell to the last cell.
 */
public class MazeSolver {

    // Represents the last cell of the maze, which is also the cell that both algorithm should
    // finish on.
    private Cell lastCell;
    // This represents the maze and is essentially like an adjacency matrix.
    private Cell[][] maze;
    // The length of the shortest path.
    private int shortestPathLength;
    // The number of visited cells visited by an algorithm.
    private int visitedCells;
    // Stores the parent of each node visited by both algorithm.
    private Cell[] parents;

    /**
     * Initializes the maze solver and finds the last cell using the given maze.
     *
     * @param maze a perfect maze
     */
    public MazeSolver(Cell[][] maze) {
        lastCell = maze[maze.length - 1][maze.length - 1];
        this.maze = maze;
    }

    /**
     * Solves a maze using a non-recursive implementation of depth-first search.
     *
     * @return a solved maze using depth-first search
     */
    public Cell[][] dfsSolve() {
        // This is used to track the number of visited cells.
        visitedCells = 0;
        // This is used by the algorithm to visit all cells until it finds the last cell. Also,
        // it allows a non-recursive implementation to work.
        Stack<Cell> cellStack = new Stack<>();
        Cell currentCell = maze[0][0];
        cellStack.push(currentCell);
        currentCell.setData(String.valueOf(visitedCells));
        // An array to hold the parent of each cell initialized as an array equal to the total
        // number of cells. Used to find the shortest path.
        parents = new Cell[maze.length * maze.length];

        // The algorithm should stop when it finds the last cell.
        while (currentCell != lastCell) {
            currentCell = cellStack.pop();

            // This if statement allows the algorithm to backtrack if it reaches a dead end.
            if (!currentCell.getVisited()) {
                currentCell.setData(String.valueOf(visitedCells++));
                currentCell.setVisited(true);
                for (Cell neighbor : currentCell.getConnectedNeighbors()) {
                    if (!neighbor.getVisited()) {
                        cellStack.push(neighbor);
                        // Sets the parent of the neighbor to the current cell, which means that
                        // multiple cells could have the same parent.
                        parents[neighbor.getPosition()] = currentCell;
                    }
                }
            }
        }

        return maze;
    }

    /**
     * Solves a maze using a non-recursive implementation of breadth-first search.
     *
     * @return a solved maze using breadth-first search
     */
    public Cell[][] bfsSolve() {
        // This is used to track the number of visited cells.
        visitedCells = 0;
        // Used by the algorithm to visit all the cells until it finds the last cell. Using a queue
        // makes the algorithm go through all the neighbors before going to a different cell.
        Queue<Cell> cellQueue = new LinkedList<>();
        Cell currentCell = maze[0][0];
        cellQueue.add(currentCell);
        currentCell.setData(String.valueOf(visitedCells));
        // An array to hold the parent of each cell initialized as an array equal to the total
        // number of cells. Used to find the shortest path.
        parents = new Cell[maze.length * maze.length];

        // The algorithm should stop when it finds the last cell.
        while (currentCell != lastCell) {
            currentCell = cellQueue.poll();

            // This if statement allows the algorithm to backtrack if it reaches a dead end.
            if (!currentCell.getVisited()) {
                currentCell.setData(String.valueOf(visitedCells++));
                currentCell.setVisited(true);
                for (Cell neighbor : currentCell.getConnectedNeighbors()) {
                    if (!neighbor.getVisited()) {
                        cellQueue.add(neighbor);
                        // Sets the parent of the neighbor to the current cell, which means that
                        // multiple cells could have the same parent.
                        parents[neighbor.getPosition()] = currentCell;
                    }
                }
            }
        }

        return maze;
    }

    /**
     * Finds the shortest path from the starting cell to the last cell mainly utilizing the array of
     * parents.
     *
     * @return a string representation of the shortest path that is basically the index of each cell
     */
    public String findShortestPath() {
        // Counts the shortest path length
        shortestPathLength = 0;
        String shortestPath = "";
        // Start backwards because otherwise it will visit cells that were visited by the algorithm,
        // but are not part of the shortest path. This also means that some cells will be skipped.
        Cell currentCell = lastCell;

        // Used to print in reverse because it starts from the last cell.
        Stack<Cell> shortestPathStack = new Stack<>();

        // Should end when the current cell is null, which means that current cell is the starting
        // cell because the starting cell has no parent.
        while (currentCell != null) {
            shortestPathStack.push(currentCell);
            currentCell.setData("#");
            // Sets the current cell's visited boolean to true. Used to find if a cell was visited
            // by depth-first search or breadth-first search, but is not in the shortest path.
            currentCell.setVisited(true);
            // Sets the current cell to the parent of the current cell.
            currentCell = parents[currentCell.getPosition()];
            shortestPathLength++;
        }

        // Used to find if a cell was visited by depth-first search or breadth-first search, but
        // is not in the shortest path.
        for (int i = 0; i < parents.length; i++) {
            Cell parent = parents[i];
            Cell child;

            // If a cell was visited by depth-first search or breadth-first search, but is not
            // in the shortest path, then its data is set to " ".
            if (parent != null && !(child = maze[i / maze.length][i % maze.length]).getVisited()) {
                child.setData(" ");
            }
        }

        // Prints out the shortest path using the stack. This is where getXYPosition() gets used.
        while (shortestPathStack.size() != 0) {
            Cell cell = shortestPathStack.pop();
            int[] currentCellXYPosition = cell.getXYPosition();
            shortestPath += "(" + currentCellXYPosition[0] + "," + currentCellXYPosition[1] + ") ";
        }

        return shortestPath;
    }

    /**
     * Gets the length of the shortest path.
     *
     * @return length of the shortest path
     */
    public int getShortestPathLength() {
        return shortestPathLength;
    }

    /**
     * Gets the number of cells visited by the algorithms.
     *
     * @return the number of cells visited by the algorithms.
     */
    public int getVisitedCells() {
        // If the first cell and the last cell of the maze are equal, then it means that the size
        // of the maze is 1, so visited cells should be 1.
        if (maze[0][0] == maze[maze.length - 1][maze.length - 1]) {
            return 1;
        }

        return visitedCells;
    }
}
