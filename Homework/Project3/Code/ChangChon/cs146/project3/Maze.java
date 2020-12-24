package Homework.Project3.Code.ChangChon.cs146.project3;

import java.io.IOException;

/**
 * Creates a maze, solves it using depth-first search and breadth-first search, prints out the
 * output, and saves the output into a file.
 */
public class Maze {

    // Used to solve the maze with both depth-first search and breadth-first search
    private MazeSolver ms;
    // Used to save output into a file
    private MazeIO mIO;
    // Used to print out the output and save the output into a file
    private String[] mazes;
    // This represents the maze. Essentially like an adjacency matrix
    private Cell[][] maze;
    // The size of the maze. The maze should be size by size.
    private int size;
    // Used to measure elapsed time.
    private double time;
    // Stores the elapsed times into a String array
    private String[] times;

    /**
     * Sets up the maze to solve it. Also, catches the case when size = 0, which means there is no
     * maze.
     *
     * @param size size of the maze is size by size
     * @throws IOException if file not found
     */
    public Maze(int size) throws IOException {
        this.size = size;

        // This catches the case if size is 0, which means there is no maze and nothing should
        // be initialized except the mazes string array to print out and save the output
        if (size == 0) {
            mazes = new String[]{"No maze! Size = 0\n"};
            System.out.println(mazes[0]);
        } else {
            times = new String[5];
            MazeCreator mc = new MazeCreator(size);
            time = System.nanoTime();
            maze = mc.generateMaze();
            times[0] = "Size: " + size + "\n" + "Time to generate maze (in ns): " +
                    (time = System.nanoTime() - time);
            ms = new MazeSolver(maze);
            mazes = new String[7];
            // Print out generate maze.
            mazes[0] = toString() + "\n";

        }

        mIO = new MazeIO();
    }

    /**
     * Solves the maze. Outputs the unsolved maze, the maze solved by DFS and BFS, the shortest path
     * for each algorithm, which should be the same, and the path, length of path, and number of
     * visited cells for each algorithm.
     *
     * @throws IOException if file not found
     */
    public void solveMazeDFS() {
        // If size is 0 it shouldn't do any of this because a maze with size 0 is nonexistent.
        if (size != 0) {
            // Clear maze because the algorithms utilize the visited boolean
            clearMaze();

            time = System.nanoTime();
            // Print maze after visiting it with depth-first search
            maze = ms.dfsSolve();
            times[1] = "Depth-first search elapsed time (in ns): " +
                    (time = System.nanoTime() - time);
            System.out.println("\nDFS:");
            mazes[1] = "DFS:\n" + toString();
            System.out.println();

            // The maze is cleared again because the method that finds the shortest path uses
            // the visited boolean.
            clearMaze();

            time = System.nanoTime();
            // Print the shortest path after finding it
            // Also, mazes[3] is set before mazes[2] because the method that finds the shortest path
            // actually changes the maze, so it needs to done first.
            mazes[3] = "Path: " + ms.findShortestPath() + "\n";
            times[2] = "Time to find shortest path after depth-first search (in ns): " +
                    (time = System.nanoTime() - time);
            mazes[2] = toString();

            // Print path
            System.out.print(mazes[3]);

            // Print length of path
            String lengthOfDFSPath = "Length of path: " + ms.getShortestPathLength() + "\n";
            mazes[3] += lengthOfDFSPath;
            System.out.print(lengthOfDFSPath);

            // Print number of visited cells
            String visitedDFSCells = "Visited cells: " + ms.getVisitedCells() + "\n";
            mazes[3] += visitedDFSCells;
            System.out.println(visitedDFSCells);
        }
    }

    /**
     * Solves the maze using breadth-first search.
     */
    public void solveMazeBFS() {
        // If size is 0 it shouldn't do any of this because a maze with size 0 is nonexistent.
        if (size != 0) {
            time = System.nanoTime();
            // Clear maze because the algorithms utilize the visited boolean
            clearMaze();
            maze = ms.bfsSolve();
            times[3] = "Breadth-first search elapsed time (in ns): " +
                    (time = System.nanoTime() - time);
            System.out.println("\nBFS:");
            mazes[4] = "BFS:\n" + toString();
            System.out.println();

            // The maze is cleared again because the method that finds the shortest path uses
            // the visited boolean.
            clearMaze();

            time = System.nanoTime();
            // Print the shortest path after finding it.
            // Also, mazes[6] is set before mazes[5] because the method that finds the shortest path
            // actually changes the maze, so it needs to done first.
            mazes[6] = "Path: " + ms.findShortestPath() + "\n";
            times[4] = "Time to find shortest path after breadth-first search (in ns): " +
                    (time = System.nanoTime() - time);
            mazes[5] = toString();

            // Print path
            System.out.print(mazes[6]);

            // Print length of path
            String lengthOfBFSPath = "Length of path: " + ms.getShortestPathLength() + "\n";
            mazes[6] += lengthOfBFSPath;
            System.out.print(lengthOfBFSPath);

            // Print number of visited cells
            String visitedBFSCells = "Visited cells: " + ms.getVisitedCells() + "\n";
            mazes[6] += visitedBFSCells;
            System.out.println(visitedBFSCells);
        }
    }

    /**
     * Saves the mazes into a file.
     *
     * @param clearFile if true clear the file before saving, else don't clear
     * @throws IOException if file not found
     */
    public void saveMazes(boolean clearFile) throws IOException {

        // Added this if statement because sometimes the file shouldn't be cleared to save
        // all tests.
        if (clearFile) {
            mIO.clearFile();
        }

        // Goes through each maze in the String array and saves it to a file.
        for (String maze : mazes) {
            mIO.saveToFile(maze);
        }

        mIO.closeFile();
    }

    /**
     * Gets the String array of mazes.
     *
     * @return String array of mazes
     */
    public String[] getMazes() {
        return mazes;
    }

    /**
     * Gets the String array of elapsed times.
     *
     * @return String array of elapsed times
     */
    public String[] getTimes() {
        return times;
    }

    /**
     * Sets all the visited booleans in a maze to false.
     */
    public void clearMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                maze[i][j].setVisited(false);
            }
        }
    }

    /**
     * Prints a given maze. North and south walls are "-", and west and east walls are "|". If the
     * cell has no wall, then it is " ".
     *
     * @return a string representation of the maze
     */
    @Override
    public String toString() {
        String mazeString = "";
        int size = maze.length;
        for (int i = 0; i < size; i++) {
            // Counter used to go through each row of cells three times: once for the north walls,
            // second time for the west and east walls, and a third time for the south walls.
            int counter = 1;
            String row = "";
            while (counter <= 3) {
                for (int j = 0; j < size; j++) {
                    Cell currentCell = maze[i][j];
                    String[] walls = currentCell.getWalls();
                    String currentCellData = currentCell.getData();

                    // This will convert numbers greater than 9 into numbers less than or
                    // equal to 9 because the maze cannot fit numbers above 9.
                    if (!currentCell.getData().equals(" ") && !currentCell.getData().equals("#")) {
                        int temp = Integer.parseInt(currentCell.getData());
                        while (temp > 9) {
                            temp -= 10;
                        }
                        currentCellData = String.valueOf(temp);
                    }

                    // Switch statement used to check whether it is on the first, second, or
                    // third pass.
                    switch (counter) {
                        case 1:
                            // There is no else statement because the north walls should be only
                            // printed once. This is because printing out the south walls prints
                            // out the north walls of the next row of cells.
                            if (i == 0 && j == 0) {
                                row += "+" + walls[0] + "+";
                            } else if (i == 0) {
                                row += walls[0] + "+";
                            }
                            break;

                        case 2:
                            // Only for the first column of data are the west walls printed because
                            // the east walls are the same as the west walls for the next column of
                            // cells.
                            if (j == 0) {
                                row += "\n" + walls[2] + currentCellData + walls[3];
                            } else {
                                row += currentCellData + walls[3];
                            }
                            break;

                        case 3:
                            // Only at the first column of data is "\n+" added because it means that
                            // the second pass has finished and the "\n" sends it to a new line.
                            // Also, only the first column needs a second "+" because the "+"
                            // after walls[1] is the same as adding a "+" to the left of walls[1]
                            // if it isn't the first column of data.
                            if (j == 0) {
                                row += "\n+" + walls[1] + "+";
                            } else {
                                row += walls[1] + "+";
                            }
                            break;
                    }
                }
                counter++;
            }
            mazeString += row;
            System.out.print(row);
        }
        System.out.println();

        return mazeString;
    }
}
