// Bullet.java
import java.util.ArrayList;
import java.awt.*;
import javax.swing.ImageIcon;

public class PlayerBullet {

    // Player bullet properties
    Player player = new Player();

    int plrBulletX = (player.playerX + player.playerWidth * 11 / 25);
    int plrBulletY = player.playerY;
    int plrBulletWidth = 2;
    int plrBulletHeight = 15;
    int plrBulletSpeed = 15;

    boolean used = false;
    Image plrBulletImg;
    Object playerBullet;

    ArrayList<Image> plrBulletImgArray;
    ArrayList<Object> plrBulletArray;

    // Constructor
    public PlayerBullet() {
        // Load player bullet image
        plrBulletImg = new ImageIcon(getClass().getResource("./assets/playerbullet.png")).getImage();
        
    
        plrBulletImgArray = new ArrayList<Image>();    
        plrBulletImgArray.add(plrBulletImg);
        plrBulletArray = new ArrayList<Object>();
    }

    public void createPlayerBullet(Player player) {
        // Create player bullet
        if (plrBulletArray.isEmpty()) {
            Object bullet = new Object(player.playerX + player.playerWidth / 2 - this.plrBulletWidth / 2, player.playerY,
                    this.plrBulletWidth, this.plrBulletHeight, this.plrBulletImg, this.plrBulletSpeed);
            plrBulletArray.add(bullet);
        }
    }

    public void drawPlayerBullet(Graphics g) {
        // Draw player bullet
        for (int i = 0; i < plrBulletArray.size(); i++) {
            Object bullet = plrBulletArray.get(i);
            g.drawImage(bullet.img, bullet.x, bullet.y, bullet.width, bullet.height, null);
        }
    }

    public void movePlayerBullet() {
           // Player Bullet movement
        for (int i = 0; i < plrBulletArray.size(); i++) {
            Object bullet = plrBulletArray.get(i);
            bullet.y -= plrBulletSpeed;
        }

        // Remove bullet if it goes out of bounds
        for (int i = 0; i < plrBulletArray.size(); i++) {
            Object bullet = plrBulletArray.get(i);
            if (bullet.y <= 0) {
                plrBulletArray.remove(i);
            }
        }

    }

    public boolean alienCollision(Object obj1, Object obj2) {
        // collision detection logic
        return obj1.x < obj2.x + obj2.width &&
                obj1.x + obj1.width > obj2.x &&
                obj1.y < obj2.y + obj2.height &&
                obj1.y + obj1.height > obj2.y;
    }

    public void collision(Alien alien, Score score) {
        // Check if bullet hits alien and remove bullet and alien
        for (int i = 0; i < plrBulletArray.size(); i++) {
            Object bullet = plrBulletArray.get(i);
            for (int j = 0; j < alien.alienArray.size(); j++) {
                Object alienObj =  alien.alienArray.get(j);
                if (alienCollision(bullet, alienObj)) {
                    plrBulletArray.remove(i);
                    alien.alienArray.remove(j);
                    alien.decrementAlienCount();;
                    score.incrementScore();
                }
            }
        }

    }

}