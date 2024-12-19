import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Player extends Object{

    // Player properties
    int playerX = 25;
    int playerY = 600 - 50;
    int playerWidth = 50;
    int playerHeight = 25;
    int playerSpeed = 6;

    Image playerImg;
    Set<Integer> pressedKeys = new HashSet<>();

    Object player;

    // Constructor
    protected Player(){ 

        // Load player image
        playerImg = new ImageIcon(getClass().getResource("./assets/player.png")).getImage();

        player = new Object(this.playerX, this.playerY, this.playerWidth, this.playerHeight, this.playerImg, this.playerSpeed);
    }

    // Draw player
    public void drawPlayer(Graphics g) {
        g.drawImage(playerImg, this.playerX, this.playerY, this.playerWidth, this.playerHeight, null);
    }

    // Player movement logic for left
    public void playerMoveLeft() {
        if (this.playerX >= 0) {
            this.playerX -= this.playerSpeed;
        }
    }

    // Player movement logic for right
    public void playerMoveRight(GamePanel gamePanel) {
        if (this.playerX <= gamePanel.gameWidth - this.playerWidth) {
            this.playerX += this.playerSpeed;
        }
    }

    // Update player position when left or right key is pressed
    public void updatePlayerPosition(Set<Integer> pressedKeys,GamePanel gamePanel) {
        switch(pressedKeys.size()) {

            // If left and right keys are pressed, do nothing
            case 1:

            if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
                    playerMoveLeft();
            } 
            else if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
                    playerMoveRight(gamePanel);
            }
            break;

            // If left and right keys are pressed, do nothing
            case 2:

            if (pressedKeys.contains(KeyEvent.VK_LEFT) && pressedKeys.contains(KeyEvent.VK_RIGHT)) {
                    break;
            } 
            else if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
                    playerMoveLeft();
            }
            else if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
                    playerMoveRight(gamePanel);
            }
            break;

            // If no keys are pressed, do nothing
            default:
            break;
        }
    }

    // collisoin detection logic
    public boolean playerCollision(Object obj1, Object obj2) {
        return obj1.x < obj2.x + obj2.width &&
                obj1.x + obj1.width > obj2.x &&
                obj1.y < obj2.y + obj2.height &&
                obj1.y + obj1.height > obj2.y;
    }

    // Check if player collides with alien
    public void collision(Alien alien, Lives lives, GamePanel gamePanel) {
        // Check if bullet hits player and remove player and alien bullet
        for (Iterator<Object> iterator = alien.alienArray.iterator(); iterator.hasNext();) {
            Object bullet = iterator.next();
            if (playerCollision(bullet,
            new Object(this.playerX, this.playerY, this.playerWidth, this.playerHeight, this.playerImg, this.playerSpeed))) {
                gamePanel.gameOver = true;
            }
        }
    }
 }
