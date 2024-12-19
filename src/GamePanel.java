import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    // Game dimensions
    int gameWidth = 640;
    int gameHeight = 600;

    boolean gameOver;
    boolean gameStart;

    Timer gameTimer;
    Player player;
    Alien alien;
    AlienBullet alienBullet;
    PlayerBullet playerBullet;
    Lives lives;
    Score score;

    Image playerImg;
    Image alienImg;
    Image alienBulletImg;
    Image playerBulletImg;
    Image livesImg;
    Set<Integer> pressedKeys = new HashSet<>();

    // Constructor
    public GamePanel() {

        // Initialize game elements
        player = new Player();
        alien = new Alien();
        alienBullet = new AlienBullet();
        playerBullet = new PlayerBullet();
        lives = new Lives();
        score = new Score();
        setPreferredSize(new Dimension(gameWidth, gameHeight));
        setFocusable(true);
        addKeyListener(this);
        gameStart = false;
        gameOver = false;

        gameTimer = new Timer(1000 / 60, this);
        alien.createAliens();
        gameTimer.start();
    }

    // Paint game elements
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        setBackground(Color.BLACK);
        drawObjects(g);
    }

    public void drawObjects(Graphics g) {
        if (gameStart == false) {
            // Add logic to display the start screen
            g.setColor(Color.WHITE);
            g.setFont(new Font("Consolas", Font.PLAIN, 20));
            g.setColor(Color.GREEN);
            g.drawString("Press 'Enter' to start", (gameWidth / 2 - 130), (gameHeight / 2 + 50));

            g.setColor(Color.white);
            g.setFont(new Font("Consolas", Font.PLAIN, 50));
            g.drawString("Space Invaders", (gameWidth / 2 - 200), (gameHeight / 2));
        } 
        else if (gameOver == true) {
            // Add logic to display the game over screen
            g.setColor(Color.WHITE);
            g.setFont(new Font("Monospaced", Font.PLAIN, 50));
            g.drawString("Game Over", (gameWidth / 2 - 130), (gameHeight / 2));

            g.setColor(Color.white);
            g.setFont(new Font("Consolas", Font.PLAIN, 20));
            g.drawString("Final score: ", (gameWidth / 2 - 70), (gameHeight / 2 + 40));

            g.drawString(String.valueOf(score.getScore()), (gameWidth / 2) + 70, (gameHeight / 2) + 40);
            g.setColor(Color.GREEN);
            g.drawString("Press 'Enter' to re-start", (gameWidth / 2 - 130), (gameHeight / 2 + 80));
        } 
        else {
            // Add logic to display the game elements
            player.drawPlayer(g);
            alien.drawAliens(g);
            playerBullet.drawPlayerBullet(g);
            alienBullet.drawAlienBullet(g);
            lives.drawLives(g);
            score.drawScore(g);
        }
    }

    public void moveObjects() {
        // Add logic to move game elements
        playerBullet.movePlayerBullet();
        alienBullet.moveAlienBullet();
        alien.alienMove();
    }

    public void checkCollisions() {
        // Add logic to check for collisions
        playerBullet.collision(alien, score);
        alienBullet.collision(player, lives);
        player.collision(alien, lives, this);
    }

    public void checkGameOver() {
        // Add logic to check if the game is over
        if (lives.getLives() == 0) {
            gameOver = true;
        }
    }

    public void newRound() {
        // Add logic to start a new round
        if (alien.alienCount == 0) {
            score.roundEndBonus();
            playerBullet.plrBulletArray.clear();
            alien.alienArray.clear();
            alien.createAliens();
        }
    }

    private void updatePlayerPosition() {
        // Add logic to update player position
       player.updatePlayerPosition(pressedKeys, this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
        // Start game
        if (!gameStart) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                gameStart = true;
                player = new Player();
                alien = new Alien();
                alienBullet = new AlienBullet();
                playerBullet = new PlayerBullet();
                lives = new Lives();
                score = new Score();
                alien.createAliens();
            }
        // restart game
        } else {
            if (e.getKeyCode() == KeyEvent.VK_ENTER && gameOver == true) {
                gameStart = true;
                gameOver = false;
                player = new Player();
                alien = new Alien();
                alienBullet = new AlienBullet();
                playerBullet = new PlayerBullet();
                lives = new Lives();
                score = new Score();
                alien.createAliens();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
        // Player bullet shoot
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            playerBullet.createPlayerBullet(player);
        }     
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Add logic to update game elements
        if (gameStart) {
            alienBullet.createAlienBullet(alien);
            updatePlayerPosition();
            moveObjects();
            checkCollisions();
            checkGameOver();
            newRound();
            repaint();
        }
    }    
    
    public int getGameWidth() {                             // Getter for gameWidth
        return gameWidth;
    }

    public int getGameHeight() {                            // Getter for gameHeight
        return gameHeight;
    }
}
