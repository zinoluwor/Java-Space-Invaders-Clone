import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class Lives {

    // Lives properties
    private int width = 50;
    private int height = 25;
    private int lives;
    private Image lifeImg;

    // Constructor
    public Lives() {

        // Set lives and load life image
        lives = 3;
        lifeImg = new javax.swing.ImageIcon(getClass().getResource("./assets/player.png")).getImage();
    }

    // Draw each of the player's lives
    public void drawLives(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < lives; i++) {
            g2d.drawImage(lifeImg, 470 + i * 50, 10, width, height, null);
        }
        if (lives >=2) {
            g2d.drawImage(lifeImg, 470 + 50, 10, width, height, null);
        }
        if (lives >=3) {
            g2d.drawImage(lifeImg, 470 + 100, 10, width, height, null);
        }

    }

    // Decrement player's lives
    public void decrementLife() {
        lives--;
    }

    // call this method to get the number of lives left
    public int getLives() {
        return lives;
    }
    
}
