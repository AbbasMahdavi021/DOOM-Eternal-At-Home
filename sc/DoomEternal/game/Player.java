package DoomEternal.game;

import java.util.HashMap;
import java.util.LinkedHashMap;

public abstract class Player extends GameObject {
    protected String name;
    protected boolean UpPressed;
    protected boolean DownPressed;
    protected boolean LeftPressed;
    protected boolean RightPressed;
    protected boolean ActionPressed;


    protected LinkedHashMap<String, Integer> stats;
    protected final int maxHP = 20;
    protected final int maxLives = 3;
    protected int currentHP;
    protected int currentLives;
    protected boolean isLoser;



    public void toggleUpPressed() {
        this.UpPressed = true;
    }
    public void toggleDownPressed() {
        this.DownPressed = true;
    }
    public void toggleLeftPressed() {
        this.LeftPressed = true;
    }
    public void toggleRightPressed() {
        this.RightPressed = true;
    }
    public void toggleActionPressed() {
        this.ActionPressed = true;
    }
    public void unToggleUpPressed() {
        this.UpPressed = false;
    }
    public void unToggleDownPressed() {
        this.DownPressed = false;
    }
    public void unToggleLeftPressed() {
        this.LeftPressed = false;
    }
    public void unToggleRightPressed() {
        this.RightPressed = false;
    }
    public void unToggleActionPressed() {
        this.ActionPressed = false;
    }


    public int getMaxLives() {
        return this.maxLives;
    }
    public int getCurrentLives() {
        return this.currentLives;
    }
    public float getCurrentHP() {
        return Math.min(1, (float) this.currentHP / (float) this.maxHP);
    }
    public boolean isLoser() {
        return this.isLoser;
    }
    public abstract Weapon.Type getWeapon();
    public abstract float getCoolDown();
    public abstract HashMap<String, Integer> getStats();

}
