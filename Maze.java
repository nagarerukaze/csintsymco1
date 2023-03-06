import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {
    private int n;
    public char[][] maze;
    private int[] start_coordinates = new int[2];
    private int[] goal_coordinates = new int[2];

    public Maze() {
        Scanner sc;
        try {
            sc = new Scanner(new File("maze.txt"));
            this.n = sc.nextInt();
            this.maze = new char[n][n];

            for(int i = 0; i < n; i++) {
                char[] str = sc.next().toCharArray();

                for(int j = 0; j < n; j++) {
                    this.maze[i][j] = str[j];

                    if(str[j] == 'S') {
                        this.start_coordinates[0] = i;
                        this.start_coordinates[1] = j;
                    } else if(str[j] == 'G') {
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

    public int getN() {
        return this.n;
    }

    public int getStartRow() {
        return this.start_coordinates[0];
    }

    public int getStartCol() {
        return this.start_coordinates[1];
    }

    public int getEndRow() {
        return this.goal_coordinates[0];
    }

    public int getEndCol() {
        return this.goal_coordinates[1];
    }
}
