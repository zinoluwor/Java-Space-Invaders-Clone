import javax.swing.*;

public class Game {
    public static void main(String[] args) throws Exception {

        // Game variables
        GamePanel gamePanel = new GamePanel();
        int gameWidth = gamePanel.getGameWidth();
        int gameHeight = gamePanel.getGameHeight();

        // game window
        JFrame frame = new JFrame("Space Invaders");
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(gameWidth, gameHeight);
        frame.setLocationRelativeTo(null); // Center the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Game instance
        frame.add(gamePanel);
        frame.pack();
        frame.setVisible(true);
        gamePanel.requestFocus(); 
    }
}