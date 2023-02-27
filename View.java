import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class View extends JFrame{
    private Cell cell = new Cell();
    private Maze maze = new Maze();
    private int exploredIndex = 0;
    //JButton button;

    public View() {
        setTitle("Maze Bot");
        setSize(500, 500);
        // validate();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DFS.Search(maze.maze, maze.n, maze.start_coordinates[0], maze.start_coordinates[1]);

        // TODO: Place a button user can click to start the search
        // button = new JButton();

        // TODO: Output maze with highlighted path, and circle going through all explored states
        // The animation skips the ones we already explored
        // If no path found: Add a pop up that says "No solution found!"
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.translate(50,50);

        // Maze
        for(int i = 0; i < maze.n; i++) {
            for(int j = 0; j < maze.n; j++) {
                Color color;
                
                if(maze.maze[i][j] == cell.WALL) {
                    color = Color.BLACK;
                } else if(i == maze.goal_coordinates[0] && j == maze.goal_coordinates[1]) {
                    color = Color.GREEN;
                } else if(i == maze.start_coordinates[0] && j == maze.start_coordinates[1]) {
                    color = Color.PINK;
                } else {
                    color = Color.WHITE;
                }
                
                g.setColor(color);
                g.fillRect(50 * j, 50 * i, 50, 50);

                g.setColor(Color.BLACK);
                g.drawRect(50 * j, 50 * i, 50, 50);
            }
        }
        
        // Draw Path
        for(Position p : DFS.path) {
            g.setColor(Color.BLUE);
            g.drawRect(p.col * 50, p.row * 50, 50, 50);
        }

        // Exploring
        g.setColor(Color.RED);
        g.fillRect(DFS.explored.get(exploredIndex).col * 50, DFS.explored.get(exploredIndex).row * 50, 50, 50);
    }

    @Override
    protected void processKeyEvent(KeyEvent key) {
        if(key.getID() != KeyEvent.KEY_PRESSED) {
            return;
        }

        if(key.getKeyCode() == KeyEvent.VK_LEFT) {
            // LEFT ARROW KEY
            if(exploredIndex > 0) {
                exploredIndex -= 1;
            }
        } else if(key.getKeyCode() == KeyEvent.VK_RIGHT) {
            // RIGHT ARROW KEY
            if(exploredIndex < DFS.explored.size() - 1) {
                exploredIndex += 1;
            }
        }
        repaint(); 
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View view = new View();
                view.setVisible(true);
            }
        });
         
    }
    
}
