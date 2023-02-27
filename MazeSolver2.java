import java.io.File;

public class MazeSolver2 {
    public static void main(String[] args) throws Exception {
        File input = new File("maze.txt");
        Maze maze = new Maze(input);
        maze.printMaze();
        
        int row = maze.start_coordinates[0];
        int col = maze.start_coordinates[1];
        
        while(maze.travel(row, col) == true) {
            row++;
        }

        maze.printMaze();
    }
}
