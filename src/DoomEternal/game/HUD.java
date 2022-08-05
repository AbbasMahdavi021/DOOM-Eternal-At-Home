package DoomEternal.game;

import DoomEternal.GameConstants;
import DoomEternal.functions.GatheredResources;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Displays various game information on the screen such as a minimap of the game world.
 */
public class HUD {

    private final Player[] players;
    private final BufferedImage[] playerStats;
    private final BufferedImage minimap;
    private final int minimapWidth;
    private final int HUDHeight;


    public HUD(BufferedImage world) {
        this.players = new Player[2];
        int resolution = 40;

        this.HUDHeight = (GameConstants.GAME_SCREEN_HEIGHT / 3) - resolution;

        this.minimapWidth = (int) (((GameConstants.GAME_SCREEN_HEIGHT / 3) - resolution) * ((float) world.getWidth() / (float) world.getHeight()));
        this.minimap = new BufferedImage(this.minimapWidth, this.HUDHeight, BufferedImage.TYPE_INT_RGB);

        int infoWidth = (GameConstants.GAME_SCREEN_WIDTH / 2) - (this.minimapWidth / 2);

        this.playerStats = new BufferedImage[2];
        this.playerStats[0] = new BufferedImage(infoWidth, this.HUDHeight, BufferedImage.TYPE_INT_RGB);
        this.playerStats[1] = new BufferedImage(infoWidth, this.HUDHeight, BufferedImage.TYPE_INT_RGB);
    }

    public BufferedImage getMinimap() {
        return minimap;
    }
    public int getMinimapWidth() {
        return this.minimapWidth;
    }


    public void setPlayerInfo(int player, Player playerObj) {
        this.players[player] = playerObj;
    }
    public BufferedImage getP1Stats() {
        return this.playerStats[0];
    }
    public BufferedImage getP2Stats() {
        return this.playerStats[1];
    }


    public void drawHUD(BufferedImage world) throws InterruptedException {
        Graphics[] playerGraphics = { this.playerStats[0].createGraphics(), this.playerStats[1].createGraphics() };
        Graphics mm = this.minimap.createGraphics();

        playerGraphics[0].clearRect(0, 0, playerStats[0].getWidth(), playerStats[0].getHeight());
        playerGraphics[1].clearRect(0, 0, playerStats[1].getWidth(), playerStats[1].getHeight());
        mm.clearRect(0, 0, minimap.getWidth(), minimap.getHeight());

        Font font = new Font("Courier New", Font.PLAIN,18);


        // Iterate loop for each player
        for (int i = 0; i < playerGraphics.length; i++) {
            if (!this.players[i].isLoser()) {
                //Player Info
                playerGraphics[i].drawRect(4, 2, this.playerStats[i].getWidth() - 8, this.playerStats[i].getHeight() - 6);
                playerGraphics[i].setFont(font);
                playerGraphics[i].setColor(Color.WHITE);
                playerGraphics[i].drawString(players[i].name, 100, 45);
                playerGraphics[i].drawImage(this.players[i].getImg(), 32, 180,100, 100,Color.black, null);

                //healthBar
                playerGraphics[i].drawImage(GatheredResources.getImage("bar"),5, 5, null);
                playerGraphics[i].setColor((this.players[i].getCurrentHP() > 0.5) ? Color.GREEN : (this.players[i].getCurrentHP() > 0.25) ? Color.YELLOW : Color.RED);
                playerGraphics[i].fillRect(170, 32, (int) (this.players[i].getCurrentHP() * 240), 16);
                playerGraphics[i].setColor(Color.WHITE);
                playerGraphics[i].drawRect(170, 32, 240, 16);

                // Draw lives
                for (int j = 0; j < this.players[i].getMaxLives(); j++) {
                    if (j < this.players[i].getCurrentLives()) {
                        playerGraphics[i].drawImage(GatheredResources.getImage("fullHeart"),160 + (j * 45), 55, null);
                    }
                    else {
                        playerGraphics[i].drawImage(GatheredResources.getImage("emptyHeart"),160 + (j * 45), 55, null);
                    }
                }

                //fireCoolDown
                playerGraphics[i].setColor(Color.ORANGE);
                playerGraphics[i].fillRect(165, 20, (int) (this.players[i].getCoolDown() * 240), 4);
                playerGraphics[i].setColor(Color.RED);
                playerGraphics[i].drawRect(165, 20, 240, 4);

                // Draw stats
                playerGraphics[i].setColor(Color.WHITE);
                int y = 95;
                for (Map.Entry<String, Integer> stat : this.players[i].getStats().entrySet()) {
                    y += 50;
                    playerGraphics[i].drawString(stat.getKey(), 170, y);
                    playerGraphics[i].drawString(" : ", 260, y);
                    for (int x = 0; x < stat.getValue(); x++ ) {
                        if ("Ammo".equals(stat.getKey())) {
                            playerGraphics[i].drawImage(GatheredResources.getImage("statBullet"),280 + (x * 9), y-20, null);
                        } else if ("Armor".equals(stat.getKey())) {
                            playerGraphics[i].drawImage(GatheredResources.getImage("statArmor"),285 + (x * 28), y-30, null);
                        }  else if ("Damage".equals(stat.getKey())) {
                            playerGraphics[i].drawImage(GatheredResources.getImage("statDamage"),285 + (x * 28), y-35, null);
                        } else if ("Fire Rate".equals(stat.getKey())) {
                            playerGraphics[i].drawImage(GatheredResources.getImage("statFireRate"),285 + (x * 20), y-25, null);
                        }
                    }
                }

                playerGraphics[i].drawImage(GatheredResources.getImage("logo"),630 , 170,null);
                playerGraphics[0].drawImage(GatheredResources.getImage("key1"),680 , 5,null);
                playerGraphics[1].drawImage(GatheredResources.getImage("key2"),670 , 5,null);

            }
        }

        // Draw minimap
        mm.drawImage(world, 0, 0, this.minimapWidth, this.HUDHeight, null);

        playerGraphics[0].dispose();
        playerGraphics[1].dispose();
        mm.dispose();
    }

}
