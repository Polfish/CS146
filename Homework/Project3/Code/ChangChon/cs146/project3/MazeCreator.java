package Homework.Project3.Code.ChangChon.cs146.project3;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Creates a grid and then uses the grid to generate a perfect using depth-first search
 */
public class MazeCreator {

    // Size will be one side of the maze NOT the total amount of cells.
    private int size;
    // This will represent the maze. This maze is essentially like an adjacency matrix
    // representation.
    private Cell[][] maze;
    // Used to randomly generate a maze.
    private Random gen;

    /**
     * Sets up the creation of the perfect maze
     *
     * @param size size of the maze
     */
    public MazeCreator(int size) {
        this.size = size;
        maze = new Cell[size][size];
        createGrid();
        // Random generator with set seed, so that the randomly generated maze always stays the same.
        gen = new Random(1000000);
    }

    /**
     * Generates a perfect maze using depth-first search. A perfect maze should have only one way
     * from any one cell to another.
     *
     * @return the generated perfect maze
     */
    public Cell[][] generateMaze() {
        // This stack allows the algorithm to backtrack if it hits a dead end.
        Stack<Cell> cellStack = new Stack<>();
        // Choose the starting cell and call it CurrentCell
        Cell currentCell = maze[0][0];
        int visitedCells = 1;
        int totalCells = size * size;

        // Using this while statement allows this algorithm to visit every single cell, and leave
        // no cell's walls all intact.
        while (visitedCells < totalCells) {
            // find all neighbors of CurrentCell with all walls intact.
            ArrayList<Cell> intactNeighbors = currentCell.getIntactNeighbors();

            if (intactNeighbors.size() != 0) {
                // Using random generator to randomly choose a neighbor.
                Cell neighbor = intactNeighbors.get(gen.nextInt(intactNeighbors.size()));
                // Knocking down wall between neighbor and current cell.
                currentCell.knockDownWall(neighbor);
                neighbor.knockDownWall(currentCell);
                cellStack.push(currentCell);
                currentCell = neighbor;
                visitedCells++;
            } else {
                // If the algorithm gets here, then it has hit a dead end and is going to back
                // track.
                currentCell = cellStack.pop();
            }
        }

        return maze;
    }

    /**
     * Creates a maze where none of the cells are connected to each other. Used as a setup for the
     * generateMaze() method.
     */
    public void createGrid() {
        // This variable is used to give the cells their position starting at 0.
        int position = 0;
        // This if statement will catch if the size is 1, which needs a separate specific maze of
        // its own.
        if (size == 1) {
            Cell cell = new Cell(position);
            String[] walls = cell.getWalls();

            cell.setData(" ");
            cell.setXYPosition(0, 0);

            // Setting neighbors
            cell.setNorth(new Cell(-1));
            cell.setSouth(new Cell(-1));
            cell.setWest(new Cell(-1));
            cell.setEast(new Cell(-1));

            // Setting the edges which are the walls
            walls[0] = " ";
            walls[1] = " ";

            maze[0][0] = cell;
        } else {
            // Going through the maze to initialize each cell.
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    Cell cell = new Cell(position++);
                    cell.setData(" ");
                    cell.setXYPosition(i, j);

                    String[] walls = cell.getWalls();

                    if (i == 0 && j == 0) {
                        walls[0] = " ";
                    }

                    if (i == size - 1 && j == size - 1) {
                        walls[1] = " ";
                    }

                    maze[i][j] = cell;
                }
            }

            // Going through the maze once again to set neighbours. The neighbours can't be set
            // in the for loop before this because otherwise a NullPointerException will happen.
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    Cell cell = maze[i][j];

                    // For the first row of cells the neighboring north cells are -1 because they
                    // are outside of the maze. This is similar to the last row of cells where the
                    // neighboring south cells are -1 because they are outside of the maze.
                    if (i == 0) {
                        cell.setNorth(new Cell(-1));
                        cell.setSouth(maze[i + 1][j]);
                    } else if (i == size - 1) {
                        cell.setNorth(maze[i - 1][j]);
                        cell.setSouth(new Cell(-1));
                    } else {
                        cell.setNorth(maze[i - 1][j]);
                        cell.setSouth(maze[i + 1][j]);
                    }

                    // For the first column of cells the neighboring west cells are -1 because they
                    // are outside of the maze. This is similar to the last column of cells where the
                    // neighboring west cells are -1 because they are outside of the maze.
                    if (j == 0) {
                        cell.setWest(new Cell(-1));
                        cell.setEast(maze[i][j + 1]);
                    } else if (j == size - 1) {
                        cell.setWest(maze[i][j - 1]);
                        cell.setEast(new Cell(-1));
                    } else {
                        cell.setWest(maze[i][j - 1]);
                        cell.setEast(maze[i][j + 1]);
                    }

                    maze[i][j] = cell;
                }
            }
        }
    }
}
