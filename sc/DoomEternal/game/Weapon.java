package DoomEternal.game;

import DoomEternal.weapons.Mine;
import DoomEternal.weapons.Bullet;
import DoomEternal.weapons.Rocket;

import java.awt.*;
import java.awt.image.BufferedImage;
public abstract class Weapon extends GameObject {

    protected DoomCharacter isSelf;
    protected int damage;
    protected float bulletSpeed;
    protected int hitPoints;
    protected abstract void initWeapon();

    enum Type {
        // Bullet
        Bullet {
            @Override
            public Weapon create(BufferedImage img, int damage, DoomCharacter self) {
                return new Bullet(img, damage, self);
            }
        },
        // Rocket
        Rocket {
            @Override
            public Weapon create(BufferedImage img, int damage, DoomCharacter self) {
                return new Rocket(img, damage, self);
            }
        },
        // Mine
        Mine {
            @Override
            public Weapon create(BufferedImage img, int damage, DoomCharacter self) {
                return new Mine(img, damage, self);
            }
        };
        public abstract Weapon create(BufferedImage img, int damage, DoomCharacter self);
    }



    public void takeDamage() {
        this.hitPoints--;
        if (this.hitPoints <= 0) {
            this.destroy();
        }
    }
    @Override
    public void update() {
        this.hitBox.setRect(this.movement.getX(), this.movement.getY(), this.width, this.height);
        this.movement.moveTank(this.bulletSpeed);
    }
    @Override
    public void objectCollision(GameObject collidingObj) {
        collidingObj.weaponCollision(this);
    }
    @Override
    public void tankCollision(DoomCharacter collidingDoomCharacter) {
        if (collidingDoomCharacter != this.isSelf) {
            collidingDoomCharacter.takeDamage(this.damage);
            this.takeDamage();
        }
    }
    @Override
    public void wallCollision(Wall collidingWall) {
        if (collidingWall.isBreakable()) {
            collidingWall.takeDamage(this.damage);
        }
        this.takeDamage();
    }
    @Override
    public void weaponCollision(Weapon collidingWeapon) {
        if (collidingWeapon.isSelf != this.isSelf) {
            collidingWeapon.takeDamage();
        }
    }
    @Override
    public void powerUpsCollision(PowerUps collidingPowerUps) {

    }
    @Override
    public void drawImage(Graphics g) {
    }

}
