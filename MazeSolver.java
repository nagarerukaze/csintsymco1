import java.io.File;
import java.util.Scanner;

public class MazeSolver {
    static char[][] maze;
    static int[] goal_coordinates = new int[2];
    static int[] start_coordinates = new int[2];
    static String pathway = "";

    //aaron's prototype
    public static boolean Search(char[][] maze, int n, int x, int y) {
        
        boolean isFound;

        if(maze[x][y] == 'G') {
            isFound = true;
        }
        else {
            if(x == 0) { //if at left edge
                if(maze[x + 1][y] != '#' or maze[x +]) { //check right 
                    Search(maze, n, x + 1, y);
                }
            }
            else {
                if() { //check right 
                    Search(maze, n, x + 1, y);
                }
                if() { //check left 
                    Search(maze, n, x - 1, y);
                }
            }
            

            if(x == n) { //if at right edge

            }

            if(y == 0) { //if at top edge

            }

            if(y == n) { //if at bottom edge

            }
        }

        
        if(isFound) {
            //append coordinates to pathway
            pathway = pathway + x + " " + y "\n";
        }

        return isFound;
    }

    public static void main(String[] args) throws Exception {
        File input = new File("test.txt");
        Scanner sc = new Scanner(input);
        int n = sc.nextInt();
        maze = new char[n][n];

        for(int i = 0; i < n; i++) {
            char[] row = sc.next().toCharArray();

            for(int j = 0; j < n; j++) {
                maze[i][j] = row[j];

                if(row[j] == 'S') {
                    goal_coordinates[0] = i;
                    goal_coordinates[1] = j;
                } else if(row[j] == 'G') {
                    start_coordinates[0] = i;
                    start_coordinates[1] = j;
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
    }
}