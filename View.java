import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class View extends JFrame{
    private Cell cell = new Cell();
    private Maze maze = new Maze();

    private JFrame popup = new JFrame();
    private int exploredIndex = 0;
    //JButton button;

    public View() {
        setTitle("Maze Bot");
        setSize(500, 500);
        // validate();
        Timer timer = new Timer (1000,null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boolean finished = DFS.Search(maze.maze, maze.n, maze.start_coordinates[0], maze.start_coordinates[1]);
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(exploredIndex < DFS.explored.size() - 1) {
                    exploredIndex += 1;
                    repaint();
                }
                else{
                    timer.stop();
                    if (!finished)
                        JOptionPane.showMessageDialog(popup,"This is an impossible maze!","Oh no!",JOptionPane.WARNING_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(popup,"You have reached the goal!","GOAL!",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        timer.start();
        // TODO: Place a button user can click to start the search
        // button = new JButton();

        // TODO: Output maze with highlighted path, and circle going through all explored states
        // The animation skips the ones we already explored
        // If no path found: Add a pop up that says "No solution found!"
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int width = 30;
        g.translate(width+10,width+10);
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
                g.fillRect(width * j, width * i, width, width);

                g.setColor(Color.BLACK);
                g.drawRect(width * j, width * i, width, width);
            }
        }
        
        // Draw Path
        for(int i = 1; i < DFS.path.size()-1; i++) {
            g.setColor(Color.blue);
            g.fillRect(DFS.path.get(i).col * width, DFS.path.get(i).row * width, width, width);
        }

        // Exploring
        g.setColor(Color.orange);
        g.fillOval(DFS.explored.get(exploredIndex).col * width, DFS.explored.get(exploredIndex).row * width, width, width);
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
