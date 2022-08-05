package DoomEternal.game;

import DoomEternal.functions.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public abstract class GameObject implements Collision {

    protected BufferedImage img;
    protected Location position;
    protected LocationChange movement;
    protected float width;
    protected float height;
    protected Rectangle2D.Double hitBox;
    private boolean destroyed = false;



    //Used for tank and Wall
    protected void mainConstructor(float x, float y, float angle, BufferedImage image) {
        this.movement = new LocationChange(x, y, angle);
        this.mainConstructor(image);
    }
    //Used for Weapons, Objects and powerUps
    protected void mainConstructor(BufferedImage img) {
        this.img = img;
        this.width = this.img.getWidth();
        this.height = this.img.getHeight();
        this.position = new Location(this.width / 2, this.height / 2);
        this.hitBox = new Rectangle2D.Double(this.movement.getX(), this.movement.getY(), this.width, this.height);
    }
    protected void create(GameObject obj, Location location, float angle) {
        float x = location.getX() - obj.position.getX();
        float y = location.getY() - obj.position.getY();
        Location spawnAt = new Location(x, y);
        obj.movement.setPosition(spawnAt);
        obj.movement.setAngle(angle);
        obj.hitBox.setRect(x, y, obj.width, obj.height);
        GatheredObjects.spawn(obj);
    }
    protected void destroy() {
        this.destroyed = true;
    }




    //Collision with Walls and Tanks
    protected void Collision(GameObject obj) {
        Rectangle2D rectangle = this.hitBox.createIntersection(obj.hitBox);
        if (rectangle.getWidth() >= rectangle.getHeight()) {
            if (rectangle.getMaxY() >= this.hitBox.getMaxY()) {
                this.movement.moveObj(0, -(float) rectangle.getHeight());
            }
            if (rectangle.getMaxY() >= obj.hitBox.getMaxY()) {
                this.movement.moveObj(0, (float) rectangle.getHeight());
            }
        }
        if (rectangle.getHeight() >= rectangle.getWidth()) {
            if (rectangle.getMaxX() >= this.hitBox.getMaxX()) {
                this.movement.moveObj(-(float) rectangle.getWidth(), 0);
            }
            if (rectangle.getMaxX() >= obj.hitBox.getMaxX()) {
                this.movement.moveObj((float) rectangle.getWidth(), 0);
            }
        }
    }



    public BufferedImage getImg() {
        return this.img;
    }
    public LocationChange getMovement() {
        return this.movement;
    }
    public Location getPosition() {
        return this.position;
    }
    public Rectangle2D.Double getHitBox() {
        return this.hitBox;
    }
    public boolean isDestroyed() {
        return destroyed;
    }




    public abstract void update();
    public void paintComponent(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(this.movement.getX(), this.movement.getY());
        rotation.rotate(Math.toRadians(this.movement.getAngle()), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, rotation, null);
    }
    public abstract void drawImage(Graphics g);
}
