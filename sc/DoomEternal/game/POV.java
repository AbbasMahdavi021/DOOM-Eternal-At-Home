package DoomEternal.game;

import DoomEternal.GameConstants;

import java.awt.image.BufferedImage;
public class POV {
    private static final int WIDTH = (GameConstants.GAME_SCREEN_WIDTH / 2) - 2;
    private static final int HEIGHT = (GameConstants.GAME_SCREEN_HEIGHT * 2 / 3) - 2;

    private final GameObject player;
    private BufferedImage POV;

    public POV(GameObject obj) {
        this.player = obj;
    }

    public void drawPOV(BufferedImage world) {
        float x = this.player.getMovement().getX() + this.player.getPosition().getX() - ((float) WIDTH / 2);
        float y = this.player.getMovement().getY() + this.player.getPosition().getY() - ((float) HEIGHT / 2);

        // Boarder Check
        if (x <= 0) {
            x = 0;
        } else if (x >= world.getWidth() - WIDTH) {
            x = world.getWidth() - WIDTH;
        }
        if (y <= 0) {
            y = 0;
        } else if (y >= world.getHeight() - HEIGHT) {
            y = world.getHeight() - HEIGHT;
        }

        this.POV = world.getSubimage((int) x, (int) y, WIDTH, HEIGHT);
    }
    public BufferedImage getPOV() {
        return this.POV;
    }

}
