package DoomEternal.game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Wall extends GameObject {
    private final boolean isBreakable;
    private int wallHealth;
    
    public Wall(float x, float y, float angle, BufferedImage img, boolean isBreakable) {
        this.mainConstructor(x, y, angle, img);
        this.isBreakable = isBreakable;
        this.initWall();
    }
    private void initWall() {
        this.wallHealth = 1;
    }
    
    public void takeDamage(int damageDealt) {
        this.wallHealth -= damageDealt;

        //if wall breaks, %30 chance of dropping PowerUps
        if (this.wallHealth <= 0) {
            double random = Math.random();
            if (random < 0.30) {
                PowerUps dropPowerUp = new PowerUps();
                this.create(dropPowerUp, this.movement.getPosition().add(this.position), 0);
            }
            this.destroy();
        }
    }

    public boolean isBreakable() {
        return this.isBreakable;
    }


    @Override
    public void update() {
    }
    @Override
    public void objectCollision(GameObject collidingObj) {
        collidingObj.wallCollision(this);
    }
    @Override
    public void tankCollision(DoomCharacter collidingDoomCharacter) {
    }
    @Override
    public void wallCollision(Wall collidingWall) {

    }
    @Override
    public void weaponCollision(Weapon collidingWeapon) {

    }
    @Override
    public void powerUpsCollision(PowerUps collidingPowerUps) {

    }
    @Override
    public void drawImage(Graphics g) {

    }

}
