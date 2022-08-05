package DoomEternal.weapons;

import DoomEternal.game.DoomCharacter;
import DoomEternal.game.Weapon;
import DoomEternal.functions.LocationChange;

import java.awt.image.BufferedImage;
public class Bullet extends Weapon {
    public Bullet(BufferedImage img, int damage, DoomCharacter self) {
        this.movement = new LocationChange();
        this.mainConstructor(img);
        this.isSelf = self;
        this.damage += damage;
        this.initWeapon();
    }

    @Override
    protected void initWeapon() {
        this.bulletSpeed = 12.0f;
        this.hitPoints = 1;
    }

}
