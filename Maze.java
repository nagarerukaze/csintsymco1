import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Maze {
    private int n;
    public char[][] maze;
    private int[] start_coordinates = new int[2];
    private int[] goal_coordinates = new int[2];

    public Maze() {
        BufferedReader brReader;

        //try {
            //brReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/resources/" + "maze.txt")));
    
            this.n = 3;//brReader.read();
            this.maze = new char[n][n];

            for(int i = 0; i < n; i++) {
                //char[] str = brReader.readLine().toCharArray();

                for(int j = 0; j < n; j++) {
                    this.maze[i][j] = '.';//str[j];
                    /*
                    if(str[j] == 'S') {
                        this.start_coordinates[0] = i;
                        this.start_coordinates[1] = j;
                    } else if(str[j] == 'G') {
                        this.goal_coordinates[0] = i;
                        this.goal_coordinates[1] = j;
                    }
                    */
                }
            }
            //brReader.close();
        //} catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        //}
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
