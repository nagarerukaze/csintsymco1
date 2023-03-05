import java.util.ArrayList;

public class DFS {
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

        if(maze[x][y] == 'G') { // at goal, start tracing back
            isFound = true;
            explored.add(new Position(x, y));

        }
        else {
            isFound = false;
            explored.add(new Position(x, y));
            maze[x][y] = 'E';

            // move down
            if(x < n - 1) {
                if(maze[x + 1][y] != 'E' && maze[x + 1][y] != '#' && isFound == false) {
                    isFound = Search(maze, n, x + 1, y);
                } 
            }
            
            // move right
            if(y < n - 1) {
                if(maze[x][y + 1] != 'E' && maze[x][y + 1] != '#' && isFound == false) {
                    isFound = Search(maze, n, x, y + 1);
                }
            }

            // move up
            if(x > 0) {
                if(maze[x - 1][y] != 'E' && maze[x - 1][y] != '#' && isFound == false) {
                    isFound = Search(maze, n, x - 1, y);
                } 
            }

            // move left
            if(y > 0) {
                if(maze[x][y - 1] != 'E' && maze[x][y - 1] != '#' && isFound == false) {
                    isFound = Search(maze, n, x, y - 1);
                } 
            }
        }
        
        if(isFound) {
            //edits pathway in 2d array
            maze[x][y] = '-';
            path.add(new Position(x, y));
        }

        return isFound;
    }
}