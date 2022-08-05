package DoomEternal.functions;

import DoomEternal.game.GameObject;
import DoomEternal.game.PowerUps;
import DoomEternal.game.DoomCharacter;
import DoomEternal.game.Weapon;
import DoomEternal.game.Wall;

public interface Collision {

    void objectCollision(GameObject collidingObj);
    void tankCollision(DoomCharacter collidingDoomCharacter);
    void wallCollision(Wall collidingWall);
    void weaponCollision(Weapon collidingWeapon);
    void powerUpsCollision(PowerUps collidingPowerUps);

}