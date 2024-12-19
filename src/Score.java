import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Score {

    // Score properties
    private int score;
    private int highScore = 0;

    // Constructor
    public Score() {
        score = 0;
    }

    public void incrementScore() {              // Increment score by 100
        score+=100;
    }

    public void roundEndBonus() {               // Increment score by 500
        score+=500;
    }

    public void updateHighScore() {             // Update high score
        if (score > highScore) {
            highScore = score;
        }
    }

    public int getScore() {                     // Get current score
        return score;
    }

    public int getHighScore() {                 // Get high score
        return highScore;
    }

    public void drawScore(Graphics g) {         // Draw score   
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 20);
    }
}
