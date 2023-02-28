import java.io.File;
import java.util.Scanner;

public class MazeSolver {
    static char[][] maze;
    static int[] goal_coordinates = new int[2];
    static int[] start_coordinates = new int[2];
    static String pathway = "";

    /*

        MAZE LEGENDS

        # - wall
        . - space
        G - goal
        S - start
        - - path
        E - explored


    */

    //aaron's prototype
    public static boolean Search(char[][] maze, int n, int x, int y) {
        
        boolean isFound;

        if(maze[x][y] == 'G') { //if this style is the goal, stop dont look further and start tracing back
            isFound = true;
        }
        else {
            isFound = false;
            maze[x][y] = 'E';
            
            if(x > 0) {
                if(maze[x - 1][y] != 'E' && maze[x - 1][y] != '#' && isFound == false) {
                    isFound = Search(maze, n, x - 1, y);
                } 
            }

            if(x < n - 1) {
                if(maze[x + 1][y] != 'E' && maze[x + 1][y] != '#'  && isFound == false) {
                    isFound = Search(maze, n, x + 1, y);
                } 
            }

            if(y > 0) {
                if(maze[x][y - 1] != 'E' && maze[x][y - 1] != '#' && isFound == false) {
                    isFound = Search(maze, n, x, y - 1);
                } 
            }
            
            if(y < n - 1) {
                if(maze[x][y + 1] != 'E' && maze[x][y + 1] != '#' && isFound == false) {
                    isFound = Search(maze, n, x, y + 1);
                }
            }

             


        }
        
        if(isFound) {
            //edits pathway in 2d array
            maze[x][y] = '-';
            
        }

        return isFound;
    }

    public static void main(String[] args) throws Exception {
        File input = new File("blocked_tc.txt");
        Scanner sc = new Scanner(input);
        int n = sc.nextInt();
        maze = new char[n][n];

        for(int i = 0; i < n; i++) {
            char[] row = sc.next().toCharArray();

            for(int j = 0; j < n; j++) {
                maze[i][j] = row[j];

                if(row[j] == 'S') {
                    start_coordinates[0] = i;
                    start_coordinates[1] = j;
                } else if(row[j] == 'G') {
                    goal_coordinates[0] = i;
                    goal_coordinates[1] = j;
                }
            }
        }
            
        sc.close();

        for(int i = 0; i < n; i++) {  
            for(int j = 0; j < n; j++) {
                System.out.print(maze[i][j]);
            } 
            System.out.println();
        }
        
        System.out.println("START: " + start_coordinates[0] + start_coordinates[1]);
        System.out.println("GOAL: " + goal_coordinates[0] + goal_coordinates[1]);

        System.out.println("\n" + Search(maze, n, start_coordinates[0], start_coordinates[1]));

        for(int i = 0; i < n; i++) {  
            for(int j = 0; j < n; j++) {
                System.out.print(maze[i][j]);
            } 
            System.out.println();
        }


    }
}