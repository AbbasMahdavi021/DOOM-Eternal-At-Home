package DoomEternal.functions;

import DoomEternal.game.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class PlayerControl implements KeyListener {

    private final Player player;
    private final HashMap<Integer, PlayerKeySet> controls;


    public PlayerControl(Player obj, HashMap<Integer, PlayerKeySet> controls) {
        this.player = obj;
        this.controls = controls;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (this.controls.get(e.getKeyCode()) == PlayerKeySet.up) {
            this.player.toggleUpPressed();
        }
        if (this.controls.get(e.getKeyCode()) == PlayerKeySet.down) {
            this.player.toggleDownPressed();
        }

        if (this.controls.get(e.getKeyCode()) == PlayerKeySet.left) {
            this.player.toggleLeftPressed();
        }
        if (this.controls.get(e.getKeyCode()) == PlayerKeySet.right) {
            this.player.toggleRightPressed();
        }

        if (this.controls.get(e.getKeyCode()) == PlayerKeySet.action) {
            this.player.toggleActionPressed();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (this.controls.get(e.getKeyCode()) == PlayerKeySet.up) {
            this.player.unToggleUpPressed();
        }
        if (this.controls.get(e.getKeyCode()) == PlayerKeySet.down) {
            this.player.unToggleDownPressed();
        }
        if (this.controls.get(e.getKeyCode()) == PlayerKeySet.left) {
            this.player.unToggleLeftPressed();
        }
        if (this.controls.get(e.getKeyCode()) == PlayerKeySet.right) {
            this.player.unToggleRightPressed();
        }
        if (this.controls.get(e.getKeyCode()) == PlayerKeySet.action) {
            this.player.unToggleActionPressed();
        }
    }
}
