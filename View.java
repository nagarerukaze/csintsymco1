import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class View extends JFrame{
    private Cell cell = new Cell();
    private Maze maze = new Maze();
    private int exploredIndex = 0;
    private int exploredCount = 0;
    int mazeWidth = maze.n * 50;
    int mazeHeight = maze.n * 50;
    boolean finished = true;
    Timer timer = new Timer (1000,null);
    public View() {
        setTitle("Maze Bot");
        setSize(mazeWidth, mazeHeight);
        validate();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finished = DFS.Search(maze.maze, maze.n, maze.start_coordinates[0], maze.start_coordinates[1]);
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(exploredIndex < DFS.explored.size() - 1) {
                    exploredIndex += 1;
                    repaint();
                }
                else{
                    repaint();
                    timer.stop();
                    if (!finished)
                        JOptionPane.showMessageDialog(null,"<html>his is an impossible maze!<br/>Total number of states explored: "+exploredCount+"<html>","Oh no!",JOptionPane.WARNING_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(null,"<html>You have reached the goal!<br/>Total number of states explored: "+exploredCount+"<html>","GOAL!",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        // TODO: Place a button user can click to start the search
        Object [] options1 = {"Start the Search"};
        if (JOptionPane.showOptionDialog(null,"Welcome to MazeBot","MAZEBOT",JOptionPane.YES_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options1,options1[0]) == 0){
            timer.start();
        }
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
        if (!timer.isRunning())
            for(int i = 1; i < DFS.path.size()-1; i++) {
                g.setColor(Color.blue);
                g.fillRect(DFS.path.get(i).col * width, DFS.path.get(i).row * width, width, width);
            }

        // Mark Explored
        for(int i = 0; i < exploredCount; i++) {
            g.setColor(Color.ORANGE);
            g.drawRect(DFS.explored.get(i).col * width, DFS.explored.get(i).row * width, width, width);
        }

        // Exploring
        g.setColor(Color.ORANGE);
        g.drawRect(DFS.explored.get(exploredIndex).col * width, DFS.explored.get(exploredIndex).row * width, width, width);
        g.fillOval(DFS.explored.get(exploredIndex).col * width, DFS.explored.get(exploredIndex).row * width, width, width);
        exploredCount++;
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
