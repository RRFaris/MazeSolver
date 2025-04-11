/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
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
        ArrayList<MazeCell> reverseSolution = new ArrayList<>();
        MazeCell cell = maze.getEndCell();

        // Gets the solution, but in reverse order
        while (cell.getParent() != null) {
            reverseSolution.add(cell);
            cell = cell.getParent();
        }
        reverseSolution.add(maze.getStartCell());

        // Use a stack to get solution in correct order
        Stack<MazeCell> stack = new Stack<>();
        int size = reverseSolution.size();

        // Add cells to a stack
        for (MazeCell mazeCell : reverseSolution) {
            stack.push(mazeCell);
        }

        ArrayList<MazeCell> newSolution = new ArrayList<>();

        // Pop off cells from the stack to reverse the order
        for (int i = 0; i < size; i++) {
            newSolution.add(stack.pop());
        }

        return newSolution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST (use a stack)
        Stack<MazeCell> explored = new Stack<>();
        int row = maze.getStartCell().getRow();
        int col = maze.getStartCell().getCol();

        // Explores cells in the order NORTH, EAST, SOUTH, WEST

        if (maze.getCell()) {

        }


        return null;
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST (use a queue)

        return null;
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
