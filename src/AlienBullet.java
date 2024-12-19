// Bullet.java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.awt.*;
import javax.swing.ImageIcon;

public class AlienBullet {

    // Alien bullet properties
    Alien alien = new Alien();

    int alnBulletX = (alien.alienX + alien.alienWidth * 11 / 25);
    int alnBulletY = alien.alienY;
    int alnBulletWidth = 6;
    int alnBulletHeight = 20;
    int alnBulletSpeed = 10;

    boolean used = false;
    Image alnBulletImg1;
    Image alnBulletImg2;
    Object alienBullet;

    ArrayList<Image> alnBulletImgArray;
    ArrayList<Object > alnBulletArray;

    // Constructor
    public AlienBullet() {
        // Load alien bullet image
        alnBulletImg1 = new ImageIcon(getClass().getResource("./assets/bullet1.png")).getImage();
        alnBulletImg2 = new ImageIcon(getClass().getResource("./assets/bullet2.png")).getImage();

        // Add alien bullet image to array
        alnBulletImgArray = new ArrayList<Image>();    
        alnBulletImgArray.add(alnBulletImg1);
        alnBulletImgArray.add(alnBulletImg2);
        alnBulletArray = new ArrayList<Object >();
    }

    public void createAlienBullet(Alien alien) {
        // Create alien bullet
        if (System.currentTimeMillis() % 2000 < 25) {
            Random random = new Random();
            int randomIndex1 = random.nextInt(alien.alienArray.size());
            int randomIndex2 = random.nextInt(alien.alienArray.size());
            Object alien1 = alien.alienArray.get(randomIndex1);
            Object alien2 = alien.alienArray.get(randomIndex2);
            // Check if alien is alive
            if (alien.alienAlive) {
                Object bullet1 = new Object(alien1.x + alien.alienWidth / 2 - this.alnBulletWidth / 2,
                        alien1.y + alien.alienHeight, this.alnBulletWidth, this.alnBulletHeight, this.alnBulletImg1, this.alnBulletSpeed);

                Object bullet2 = new Object(alien2.x + alien.alienWidth / 2 - this.alnBulletWidth / 2,
                        alien2.y + alien.alienHeight, this.alnBulletWidth, this.alnBulletHeight, this.alnBulletImg1, this.alnBulletSpeed);

                alnBulletArray.add(bullet1);
                alnBulletArray.add(bullet2);
            }
        }
    }

    public void drawAlienBullet(Graphics g) {
        // Draw alien bullet
        for (Object bullet : alnBulletArray) {
            g.drawImage(bullet.img, bullet.x, bullet.y, bullet.width, bullet.height, null);
        }
    }

    public void moveAlienBullet() {
        // Alien Bullet movement
        for (int i = 0; i < alnBulletArray.size(); i++) {
            Object bullet = alnBulletArray.get(i);
            bullet.y += alnBulletSpeed;
        }

        // Remove bullet
        for (int i = 0; i < alnBulletArray.size(); i++) {
            Object bullet = alnBulletArray.get(i);
            if (bullet.y >= 600) {
                alnBulletArray.remove(i);
            }
        }
    }

    public boolean playerCollision(Object obj1, Object obj2) {
        // collision detection logic
        return obj1.x < obj2.x + obj2.width &&
                obj1.x + obj1.width > obj2.x &&
                obj1.y < obj2.y + obj2.height &&
                obj1.y + obj1.height > obj2.y;
    }

    public void collision(Player player, Lives lives) {
        // Check if bullet hits player and remove player and alien bullet
        for (Iterator<Object> iterator = alnBulletArray.iterator(); iterator.hasNext();) {
            Object bullet = iterator.next();
            if (playerCollision(bullet,
            new Object(player.playerX, player.playerY, player.playerWidth, player.playerHeight, player.playerImg, player.playerSpeed))) {
                iterator.remove();
                lives.decrementLife();
            }
        }
    }
}