package DoomEternal.weapons;

import DoomEternal.game.DoomCharacter;
import DoomEternal.game.Weapon;
import DoomEternal.functions.LocationChange;
import java.awt.image.BufferedImage;

public class Mine extends Weapon {
    public Mine(BufferedImage img, int damage, DoomCharacter self) {
        this.movement = new LocationChange();
        this.mainConstructor(img);
        this.isSelf = self;
        this.damage += damage;
        this.initWeapon();
    }
    @Override
    protected void initWeapon() {
        //zero speed so that bullet remains still, as a land mine
        this.bulletSpeed = 0;
        this.hitPoints = 1;
    }
}
