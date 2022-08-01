package DoomEternal.game;

import DoomEternal.functions.GatheredResources;
import DoomEternal.functions.LocationChange;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class PowerUps extends GameObject {
    private final Type type;

    public enum Type {

        // Stat PowerUps
        Health(GatheredResources.getImage("healthDrop")) {
            @Override
            protected void getPowerUp(DoomCharacter doomCharacter) {
                doomCharacter.addHealth(2);
            }
        },
        FireRate(GatheredResources.getImage("fireRateDrop")) {
            @Override
            protected void getPowerUp(DoomCharacter doomCharacter) {
                doomCharacter.addFireRate(1);
            }
        },

        Damage(GatheredResources.getImage("damageDrop")) {
            @Override
            protected void getPowerUp(DoomCharacter doomCharacter) {
                doomCharacter.addDamage(1);
            }
        },
        Armor(GatheredResources.getImage("armorDrop")) {
            @Override
            protected void getPowerUp(DoomCharacter doomCharacter) {
                doomCharacter.addArmor(1);
            }
        },
        Ammo(GatheredResources.getImage("bulletDrop")) {
            @Override
            protected void getPowerUp(DoomCharacter doomCharacter) {
                doomCharacter.addAmmo(20);
            }
        },


        //Weapon PowerUps
        Rocket(GatheredResources.getImage("rocketDrop"), GatheredResources.getImage("rocket")) {
            @Override
            protected void getPowerUp(DoomCharacter doomCharacter) {
                doomCharacter.setWeapon(Weapon.Type.Rocket, this.powerUpImg);
            }
        },
        Mine(GatheredResources.getImage("mineDrop"), GatheredResources.getImage("mine")) {
            @Override
            protected void getPowerUp(DoomCharacter doomCharacter) {
                doomCharacter.setWeapon(Weapon.Type.Mine, this.powerUpImg);
                doomCharacter.setAmmo(25);
            }
        };

        protected final BufferedImage img;
        protected BufferedImage powerUpImg = null;
        protected abstract void getPowerUp(DoomCharacter doomCharacter);


        // stat PowerUps
        Type(BufferedImage img) {
            this.img = img;
        }
        // weapon PowerUps
        Type(BufferedImage img, BufferedImage powerUpImg) {
            this.img = img;
            this.powerUpImg = powerUpImg;
        }

    }
    public PowerUps() {
        this.movement = new LocationChange();
        this.type = this.randomPower();
        this.mainConstructor(this.type.img);
    }


    // Random PowerUps
    private final PowerUps.Type[] allPowerUps = PowerUps.Type.values();
    private final Random getRandomPowerUp = new Random();
    private PowerUps.Type randomPower() {
        return this.allPowerUps[this.getRandomPowerUp.nextInt(this.allPowerUps.length)];
    }
    void getPowerUp(DoomCharacter doomCharacter) {
        this.type.getPowerUp(doomCharacter);
    }




    @Override
    public void update() {
    }
    @Override
    public void objectCollision(GameObject collidingObj) {
        collidingObj.powerUpsCollision(this);
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
