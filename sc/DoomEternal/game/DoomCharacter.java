package DoomEternal.game;

import DoomEternal.functions.GatheredObjects;
import DoomEternal.functions.GatheredResources;
import DoomEternal.functions.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Random;

public class DoomCharacter extends Player {
    private final BufferedImage bulletImg;
    private BufferedImage weaponImg;
    private final Sound bullet = new Sound(GatheredResources.getSound("bullet"));
    private final Sound mine = new Sound(GatheredResources.getSound("mine"));
    private final Sound rocket = new Sound(GatheredResources.getSound("rocket"));
    private final Sound pickDrop = new Sound(GatheredResources.getSound("pickDrop"));
    private final Sound doomHurt = new Sound(GatheredResources.getSound("doomHurt"));
    private final Sound baronHurt = new Sound(GatheredResources.getSound("baronHurt"));

    public int spriteCounter = 0;
    public int spriteNum = 1;
    private boolean t1IsMoving;
    private boolean t1IsShooting;
    private boolean t2IsMoving;
    private boolean t2IsShooting;


    private Weapon.Type currentWeapon;


    private final float rotationSpeed = 1.5f;
    private int move;
    private int fireRate;
    private int damage;
    private int armor;
    private int ammo;
    private float fireCoolDown;
    private float coolDown;


    public DoomCharacter(float x, float y, float angle, BufferedImage playerImg, BufferedImage weaponImg, String name) {
        this.mainConstructor(x, y, angle, playerImg);
        this.name = name;
        this.weaponImg = weaponImg;
        this.bulletImg = weaponImg;
        this.initTank();
    }

    private void initTank() {

        //Default Stats
        this.currentLives = this.maxLives;
        this.currentHP = this.maxHP;
        this.currentWeapon = Weapon.Type.Bullet;

        this.stats = new LinkedHashMap<>();
        this.move = 1;
        this.ammo = 50;
        this.armor = 1;
        this.damage = 1;
        this.fireRate = 1;

        this.coolDown = 150f;
        this.fireCoolDown = this.coolDown;
        this.isLoser = false;
    }

    private void respawn() {
        this.currentHP = this.maxHP;
        this.currentWeapon = Weapon.Type.Bullet;
        this.weaponImg = this.bulletImg;

        this.move = 1;
        this.ammo = 50;

        if (this.fireRate > 2) {
            this.fireRate -= 2;
        }
        if (this.damage > 2) {
            this.damage -= 2;
        }
        if (this.armor > 2) {
            this.armor -= 2;
        }

        // Respawn
        Random respawn = new Random();
        this.movement.setPosition(GatheredObjects.respawnPoints.get(
                respawn.nextInt(GatheredObjects.respawnPoints.size())).getTransform().getPosition());
    }


    // Movement
    private void rotateRight() {
        this.movement.rotate(this.rotationSpeed);
    }
    private void rotateLeft() {
        this.movement.rotate(-this.rotationSpeed);
    }
    private void moveForwards() {
        this.movement.moveTank(this.move);
    }
    private void moveBackwards() {
        this.movement.moveTank(-this.move);
    }



    //Weapon and Ammo
    private void fire() {
        if (this.fireCoolDown >= this.coolDown) {
            Weapon shoot = this.currentWeapon.create(this.weaponImg, this.damage, this);
            this.create(shoot, this.movement.getPosition().add(this.position), this.movement.getAngle());

            //Sound effects based on weapon
            String weapon = currentWeapon.name();
            if("Bullet".equals(weapon)) {
                bullet.playSound();
            }
            if("Rocket".equals(weapon)) {
                rocket.playSound();
            }
            if("Mine".equals(weapon)) {
                mine.playSound();
            }
            this.ammo--;
            this.fireCoolDown = 0;
        }
    }

    //Damage and Health
    public void takeDamage(int damageDealt) {
        this.currentHP -= Math.max(1, damageDealt - this.armor);
        if(Objects.equals(this.name, "Doom")){
            doomHurt.playSound();
        }
        if (Objects.equals(this.name, "Baron")) {
            baronHurt.playSound();
        }
        if (this.currentHP <= 0) {
            this.currentLives--;
            if (this.currentLives <= 0) {
                this.isLoser = true;
                this.destroy();
            } else {
                this.respawn();
            }
        }
    }


    //PowerUps
    public void addHealth(int value) {
        if ((this.currentHP += value) >= this.maxHP) {
            this.currentHP = this.maxHP;
        }
    }
    public void addFireRate(int value) {
        if ((this.fireRate += value) >= 10) {
            this.fireRate = 10;
        }
    }
    public void addDamage(int value) {
        if ((this.damage += value) >= 10) {
            this.damage = 10;
        }
    }
    public void addArmor(int value) {
        if ((this.armor += value) >= 10) {
            this.armor = 10;
        }
    }
    public void addAmmo(int value) {
        if ((this.ammo += value) >= 50) {
            this.ammo = 50;
        }
    }
    public void setWeapon(Weapon.Type newWeapon, BufferedImage sprWeapon) {
        this.currentWeapon = newWeapon;
        this.weaponImg = sprWeapon;
    }

    public void setAmmo(int value) {
        this.ammo = value;
    }


    @Override
    public Weapon.Type getWeapon() {
        return this.currentWeapon;
    }
    @Override
    public float getCoolDown() {
        return this.fireCoolDown / this.coolDown;
    }

    //Store Stats to call for HUD
    @Override
    public HashMap<String, Integer> getStats() {
        this.stats.put("Ammo", this.ammo);
        this.stats.put("Armor", this.armor);
        this.stats.put("Damage", this.damage);
        this.stats.put("Fire Rate", this.fireRate);
        return this.stats;
    }


    @Override
    public void update() {
        this.hitBox.setRect(this.movement.getX(), this.movement.getY(), this.width, this.height);

        // CoolDown
        if (this.fireCoolDown < this.coolDown) {
            this.fireCoolDown += this.fireRate;
        }

        //Used for Animation
        if(Objects.equals(this.name, "Doom")){
            // Movement
            if (this.UpPressed) {
                this.moveForwards();
                this.t1IsMoving = true;
                this.t1IsShooting = false;
                animForward();
            }else {
                this.t1IsMoving = false;
            }

            if (this.DownPressed) {
                this.moveBackwards();
                this.t1IsMoving = true;
                this.t1IsShooting = false;
                animBack();
            }

            // Rotation
            if (this.LeftPressed) {
                this.rotateLeft();
            }
            if (this.RightPressed) {
                this.rotateRight();
            }

            // Weapon
            if (this.ActionPressed && this.ammo > 0) {
                this.fire();
                t1IsShooting = true;
            }
        }

        if(Objects.equals(this.name, "Baron")){
            // Movement
            if (this.UpPressed) {
                this.moveForwards();
                this.t2IsMoving = true;
                this.t2IsShooting = false;
                animForward();
            }else {
                this.t2IsMoving = false;
            }

            if (this.DownPressed) {
                this.moveBackwards();
                this.t2IsMoving = true;
                this.t2IsShooting = false;
                animBack();
            }

            // Rotation
            if (this.LeftPressed) {
                this.rotateLeft();
            }
            if (this.RightPressed) {
                this.rotateRight();
            }

            // Weapon
            if (this.ActionPressed && this.ammo > 0) {
                this.fire();
                t2IsShooting = true;
            }
        }
    }

    //rotate through images to create animation
    public void anim(){
        if (spriteNum == 1) {
            spriteNum = 2;
        }else if (spriteNum == 2){
            spriteNum = 3;
        }else if (spriteNum == 3){
            spriteNum = 4;
        }else if (spriteNum == 4){
            spriteNum = 1;
        }
        spriteCounter = 0;
    }
    public void animForward() {
        spriteCounter++;
        if(spriteCounter > 30) {
            anim();
        }
    }

    //walk backwards slower
    public void animBack() {
        spriteCounter++;
        if(spriteCounter > 40) {
            anim();
        }
    }



    @Override
    public void objectCollision(GameObject collidingObj) {
        collidingObj.tankCollision(this);
    }
    @Override
    public void tankCollision(DoomCharacter collidingDoomCharacter) {
        collidingDoomCharacter.Collision(this);
    }
    @Override
    public void wallCollision(Wall collidingWall) {
        this.Collision(collidingWall);
    }
    @Override
    public void weaponCollision(Weapon collidingWeapon) {
    }
    @Override
    public void powerUpsCollision(PowerUps collidingPowerUps) {
        pickDrop.playSound();
        collidingPowerUps.getPowerUp(this);
        collidingPowerUps.destroy();
    }



    @Override
    public void drawImage(Graphics g) {
        if (this.t1IsMoving){
            if (spriteNum == 1) {
                img = GatheredResources.getImage("doom1");
            }
            if (spriteNum == 2) {
                img = GatheredResources.getImage("doom2");
            }
            if (spriteNum == 3) {
                img = GatheredResources.getImage("doom3");
            }
            if (spriteNum == 4) {
                img = GatheredResources.getImage("doom4");
            }
        }else {
            if ("Doom".equals(this.name)){
                img = GatheredResources.getImage("doom0");
            }
        }
        if (t1IsShooting){
            img = GatheredResources.getImage("doomShoot");
        }


        if (this.t2IsMoving){
            if (spriteNum == 1) {
                img = GatheredResources.getImage("baron1");
            }
            if (spriteNum == 2) {
                img = GatheredResources.getImage("baron2");
            }
            if (spriteNum == 3) {
                img = GatheredResources.getImage("baron3");
            }
            if (spriteNum == 4) {
                img = GatheredResources.getImage("baron4");
            }
        }else {
            if ("Baron".equals(this.name)){
                img = GatheredResources.getImage("baron0");
            }
        }
        if (t2IsShooting){
            img = GatheredResources.getImage("baronShoot");
        }

        //HealthBar
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor((this.getCurrentHP() > 0.5) ? Color.GREEN : (this.getCurrentHP() > 0.25) ? Color.YELLOW : Color.RED);

    }
}
