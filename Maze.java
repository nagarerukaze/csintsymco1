import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {
    public Cell cell = new Cell();
    public int n;
    public char[][] maze;
    public int[] start_coordinates = new int[2];
    public int[] goal_coordinates = new int[2];

    public Maze() {
        Scanner sc;
        try {
            sc = new Scanner(new File("blocked_tc.txt"));
            this.n = sc.nextInt();
            this.maze = new char[n][n];

            for(int i = 0; i < n; i++) {
                char[] str = sc.next().toCharArray();

                for(int j = 0; j < n; j++) {
                    this.maze[i][j] = str[j];

                    if(str[j] == cell.START) {
                        this.start_coordinates[0] = i;
                        this.start_coordinates[1] = j;
                    } else if(str[j] == cell.GOAL) {
                        this.goal_coordinates[0] = i;
                        this.goal_coordinates[1] = j;
                    }
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
    }

    public void printMaze() {
        for(int i = 0; i < this.n; i++){
            for(int j = 0; j < this.n; j++) {
                System.out.print(this.maze[i][j]);
            }
            System.out.println();
        }
    }
}
