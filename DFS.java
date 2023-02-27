import java.util.ArrayList;

public class DFS {
    static Cell cell = new Cell();
    static ArrayList<Position> explored = new ArrayList<Position>(); 
    static ArrayList<Position> path = new ArrayList<Position>(); 
    /*

        MAZE LEGENDS

        # - wall
        . - space
        G - goal
        S - start
        - - path
        E - explored


    */
    public static boolean Search(char[][] maze, int n, int x, int y) {
        
        boolean isFound;

        if(maze[x][y] == cell.GOAL) { // at goal, start tracing back
            isFound = true;
            explored.add(new Position(x, y));

        }
        else {
            isFound = false;
            explored.add(new Position(x, y));
            maze[x][y] = cell.EXPLORED;
            
            if(x > 0) {
                if(maze[x - 1][y] != cell.EXPLORED && maze[x - 1][y] != cell.WALL && isFound == false) {
                    isFound = Search(maze, n, x - 1, y);
                } 
            }

            if(x < n - 1) {
                if(maze[x + 1][y] != cell.EXPLORED && maze[x + 1][y] != cell.WALL && isFound == false) {
                    isFound = Search(maze, n, x + 1, y);
                } 
            }

            if(y > 0) {
                if(maze[x][y - 1] != cell.EXPLORED && maze[x][y - 1] != cell.WALL && isFound == false) {
                    isFound = Search(maze, n, x, y - 1);
                } 
            }
            
            if(y < n - 1) {
                if(maze[x][y + 1] != cell.EXPLORED && maze[x][y + 1] != cell.WALL && isFound == false) {
                    isFound = Search(maze, n, x, y + 1);
                }
            }
        }
        
        if(isFound) {
            //edits pathway in 2d array
            maze[x][y] = cell.PATH;
            path.add(new Position(x, y));
        }

        return isFound;
    }
}