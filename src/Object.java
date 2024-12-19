import java.awt.Image;


public class Object {

    // Game dimensions
    int gameWidth = 640;
    int gameHeight = 600;

    // Object dimensions
    int x;
    int y;
    int width;
    int height;
    Image img;
    int speed;

    // default constructor
    public Object() {
		x = 0;
		y = 0;
		width = 0;
		height = 0;
		speed = 0;

	}
    // constructor
    Object(int x, int y, int width, int height, Image img,int speed){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
        this.speed = speed;
    }
 }
