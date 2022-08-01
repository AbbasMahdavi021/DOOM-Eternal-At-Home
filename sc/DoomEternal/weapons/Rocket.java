package DoomEternal.weapons;

import DoomEternal.game.DoomCharacter;
import DoomEternal.game.Wall;
import DoomEternal.game.Weapon;
import DoomEternal.functions.LocationChange;
import java.awt.image.BufferedImage;

public class Rocket extends Weapon {

    public Rocket(BufferedImage img, int damage, DoomCharacter self) {
        this.movement = new LocationChange();
        this.mainConstructor(img);
        this.isSelf = self;
        this.damage += damage;
        this.initWeapon();
    }
    protected void initWeapon() {
        this.bulletSpeed = 16.0f;
        this.hitPoints = 5;
    }


    @Override
    public void tankCollision(DoomCharacter collidingDoomCharacter) {
        if (collidingDoomCharacter != this.isSelf) {
            collidingDoomCharacter.takeDamage(this.damage);
            this.destroy();
        }
    }
    @Override
    public void wallCollision(Wall collidingWall) {
        if (collidingWall.isBreakable()) {
            collidingWall.takeDamage(this.damage);
            this.takeDamage();
        } else {
            this.destroy();
        }
    }
}
