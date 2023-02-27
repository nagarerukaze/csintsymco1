import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {
    public Cell cell = new Cell();
    public int n;
    public char[][] maze;
    public int[] start_coordinates;

    public Maze(File input) throws FileNotFoundException {
        Scanner sc = new Scanner(input);
        this.n = sc.nextInt();
        this.maze = new char[n][n];

        for(int i = 0; i < n; i++) {
            char[] str = sc.next().toCharArray();

            for(int j = 0; j < n; j++) {
                maze[i][j] = str[j];

                if(str[j] == cell.START) {
                    start_coordinates[0] = i;
                    start_coordinates[1] = j;
                }
            }
        }
            
        sc.close();
    }

    public boolean isEnd(int row, int col) {
        if(this.maze[row][col] == cell.GOAL) {
            return true;
        }
        return false;
    }

    public boolean travel(int row, int col) {
        if(row < this.n && col < this.n) {
            if(this.maze[row][col] != cell.WALL) {
                this.maze[row][col] = cell.EXPLORED;
                return true;
            }
        }
        return false;
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
