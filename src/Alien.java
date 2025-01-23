
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

public class Alien extends Object{

    // Alien properties
    int alienX = 50;
    int alienY = 75;
    int alienWidth = 35;
    int alienHeight = 25;
    int alienSpeed = 1;

    boolean alienAlive = true;
    Image alien1Img;
    Image alien2Img;
    Image alien3Img;
    Image alien4Img;

    private ArrayList<Image> alienImgArray;
    ArrayList<Object> alienArray;

    int alienRows = 5;
    int alienCols = 10;
    int alienCount;

    Object alien;

    // Constructor
    protected Alien(){ 

        // Load alien images
        alien1Img = new ImageIcon(getClass().getResource("./assets/alien1_1.png")).getImage();
        alien2Img = new ImageIcon(getClass().getResource("./assets/alien2_1.png")).getImage();
        alien3Img = new ImageIcon(getClass().getResource("./assets/alien3_1.png")).getImage();
        alien4Img = new ImageIcon(getClass().getResource("./assets/alien4_1.png")).getImage();

        alienImgArray = new ArrayList<Image>();
        alienImgArray.add(alien1Img);
        alienImgArray.add(alien2Img);
        alienImgArray.add(alien3Img);
        alienImgArray.add(alien4Img);

        alienArray = new ArrayList<Object>();
     
    }
    // Create aliens
    public void createAliens() {
        Random random = new Random();
        for (int c = 0; c < alienCols; c++) {
            for (int r = 0; r < alienRows; r++) {
                int randomAlien = random.nextInt(alienImgArray.size());
                Object  alien = new Object(alienX + c * (alienWidth + 10),
                        alienY + r * (alienHeight + 10),
                        alienWidth, alienHeight, alienImgArray.get(randomAlien), alienSpeed);
                alienArray.add(alien);
            }
        }
        alienCount = alienArray.size();
    }  
    
    // Draw aliens
    public void drawAliens(Graphics g) {
        for (int i = 0; i < alienArray.size(); i++) {
            Object alien = alienArray.get(i);

            // Draw alien if it is alive
            if (alienAlive) {
                g.drawImage(alien.img, alien.x, alien.y, alien.width, alien.height, null);
            }
        }
    }

        // Alien movement
    public void alienMove() {
        boolean changeDirection = false;
        for (int i = 0; i < alienArray.size(); i++) {
            Object  alien = alienArray.get(i);
            if (alienAlive == true) {
                alien.x += alienSpeed;
    
                // Check if any alien hits the wall
                if (alien.x + alienWidth >= gameWidth || alien.x <= 0) {
                    changeDirection = true;
                }
            }
        }
    
        // Change direction and move down if any alien hits the wall
        if (changeDirection == true) {
            alienSpeed *= -1;
            for (int i = 0; i < alienArray.size(); i++) {
                Object  alien = alienArray.get(i);
                alien.x += alienSpeed * 2;
                alien.y += alienHeight;
            }
        }
    
            // restart if any alien leaves the screen
        for (int i = 0; i < alienArray.size(); i++) {
            Object  alien = alienArray.get(i);
            if (alien.y + alienHeight >= gameHeight) {;
                alienArray.clear();
                createAliens();
            }

            // restart if all aliens are killed
            if (alienCount == 0) {
                alienArray.clear();
                createAliens();
            }  
        }
    }

    // Decrement alien count
    public void decrementAlienCount() { 
        alienCount--;
    }
}
