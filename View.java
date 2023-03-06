import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class View extends JFrame{
    private Maze maze = new Maze();
    private int exploredIndex = 0;
    private int exploredCount = 0;
    
    private int n = maze.getN();
    private boolean finished = true;
    private Timer timer = new Timer (200,null);

    public View() {
        setTitle("Maze Bot");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        finished = DFS.Search(maze.maze, n, maze.getStartRow(), maze.getStartCol());

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
                        JOptionPane.showMessageDialog(null,"<html>This is an impossible maze!<br/>Total number of states explored: "+exploredCount+"<html>","Oh no!",JOptionPane.WARNING_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(null,"<html>You have reached the goal!<br/>Total number of states explored: "+exploredCount+"<html>","GOAL!",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        Object [] options1 = {"Start the Search"};
        if (JOptionPane.showOptionDialog(null,"Welcome to MazeBot","MAZEBOT",JOptionPane.YES_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options1,options1[0]) == 0){
            timer.start();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        double scaleFactor = .3 + (10/n);
        int width = (int)Math.ceil(40 * scaleFactor);
        g.translate(width+30,width+30);
        
        // Maze
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                Color color;
                
                if(maze.maze[i][j] == '#') {
                    color = Color.BLACK;
                } else if(i == maze.getEndRow() && j == maze.getEndCol()) {
                    color = Color.GREEN;
                } else if(i == maze.getStartRow() && j == maze.getStartCol()) {
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
                g.setColor(Color.GREEN);
                g.fillRect(DFS.path.get(i).getCol() * width, DFS.path.get(i).getRow() * width, width, width);
            }

        // Mark Explored
        for(int i = 0; i < exploredCount; i++) {
            g.setColor(Color.RED);
            g.drawRect(DFS.explored.get(i).getCol() * width, DFS.explored.get(i).getRow() * width, width, width);
        }

        // Exploring
        g.setColor(Color.RED);
        g.drawRect(DFS.explored.get(exploredIndex).getCol() * width, DFS.explored.get(exploredIndex).getRow() * width, width, width);
        g.fillOval(DFS.explored.get(exploredIndex).getCol() * width, DFS.explored.get(exploredIndex).getRow() * width, width, width);
        exploredCount++;
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
