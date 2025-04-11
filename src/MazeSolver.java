/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        Stack<MazeCell> stack = new Stack<>();
        MazeCell cell = maze.getEndCell();
        ArrayList<MazeCell> solution = new ArrayList<>();

        // Gets the solution, but in reverse order
        while (cell.getParent() != null) {
            stack.add(cell);
            cell = cell.getParent();
        }
        stack.add(maze.getStartCell());

        // Pop off cells from the stack to put the solution in order
        while (!stack.isEmpty()) {
            solution.add(stack.pop());
        }

        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        Stack<MazeCell> explored = new Stack<>();
        int currentRow = maze.getStartCell().getRow();
        int currentCol = maze.getStartCell().getCol();
        MazeCell currentCell = maze.getCell(currentRow, currentCol);
        MazeCell nextCell = null;

        // Main loop
        while (currentRow != maze.getEndCell().getRow() || currentCol != maze.getEndCell().getCol()) {
            // Explores cells in the order NORTH, EAST, SOUTH, WEST
            // NORTH
            if (maze.isValidCell(currentRow -1, currentCol)) {
                explored.add(maze.getCell(currentRow - 1, currentCol));
                maze.getCell(currentRow - 1, currentCol).setExplored(true);
            }
            // EAST
            if (maze.isValidCell(currentRow, currentCol + 1)) {
                explored.add(maze.getCell(currentRow, currentCol + 1));
                maze.getCell(currentRow, currentCol + 1).setExplored(true);
            }
            // SOUTH
            if (maze.isValidCell(currentRow + 1, currentCol)) {
                explored.add(maze.getCell(currentRow + 1, currentCol));
                maze.getCell(currentRow + 1, currentCol).setExplored(true);
            }
            // WEST
            if(maze.isValidCell(currentRow, currentCol - 1)) {
                explored.add(maze.getCell(currentRow, currentCol - 1));
                maze.getCell(currentRow, currentCol - 1).setExplored(true);
            }

            nextCell = explored.pop();
            nextCell.setParent(currentCell);
            currentCell = nextCell;
            currentRow = currentCell.getRow();
            currentCol = currentCell.getCol();
        }

        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        Queue<MazeCell> explored = new LinkedList<>();
        int currentRow = maze.getStartCell().getRow();
        int currentCol = maze.getStartCell().getCol();
        MazeCell currentCell = maze.getCell(currentRow, currentCol);
        MazeCell nextCell = null;

        currentCell.setExplored(true);
        explored.add(currentCell);

        // Main loop
        while (currentRow != maze.getEndCell().getRow() || currentCol != maze.getEndCell().getCol()) {
            // Go to the new position in the grid from the top of the queue
            currentCell = explored.remove();

            // Set new Coordinates
            currentRow = currentCell.getRow();
            currentCol = currentCell.getCol();

            // Explores cells in the order NORTH, EAST, SOUTH, WEST
            // NORTH
            if (maze.isValidCell(currentRow -1, currentCol)) {
                explored.add(maze.getCell(currentRow - 1, currentCol));

                // Set parent immediately
                maze.getCell(currentRow - 1, currentCol).setParent(currentCell);

                maze.getCell(currentRow - 1, currentCol).setExplored(true);
            }
            // EAST
            if (maze.isValidCell(currentRow, currentCol + 1)) {
                explored.add(maze.getCell(currentRow, currentCol + 1));

                // Set parent immediately
                maze.getCell(currentRow, currentCol + 1).setParent(currentCell);

                maze.getCell(currentRow, currentCol + 1).setExplored(true);
            }
            // SOUTH
            if (maze.isValidCell(currentRow + 1, currentCol)) {
                explored.add(maze.getCell(currentRow + 1, currentCol));

                // Set parent immediately
                maze.getCell(currentRow + 1, currentCol).setParent(currentCell);

                maze.getCell(currentRow + 1, currentCol).setExplored(true);
            }
            // WEST
            if(maze.isValidCell(currentRow, currentCol - 1)) {
                explored.add(maze.getCell(currentRow, currentCol - 1));

                // Set parent immediately
                maze.getCell(currentRow, currentCol - 1).setParent(currentCell);

                maze.getCell(currentRow, currentCol - 1).setExplored(true);
            }
        }
        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
