package DoomEternal.game;

import DoomEternal.*;
import DoomEternal.functions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class GameWorld extends JPanel implements Runnable {
    private final Launcher lf;
    private BufferedImage world;
    private HUD HUD;
    private String map;
    private BufferedReader mapReader;
    private BufferedImage background = null;

    private DoomCharacter DoomGuy;
    private DoomCharacter BaronOfHell;
    private POV POV1;
    private POV POV2;

    private HashMap<Integer, PlayerKeySet> controls1;
    private HashMap<Integer, PlayerKeySet> controls2;



    public GameWorld(Launcher lf) {
        this.setFocusable(true);
        this.lf = lf;
    }

    @Override
    public void run() {
        try {
            this.InitializeGame();
            while (true) {
                this.DoomGuy.update();
                this.BaronOfHell.update();
                this.update();
                this.repaint();

                Thread.sleep(1000 / 144);

                if(this.BaronOfHell.isLoser){
                    lf.setFrame("p1Wins");
                    return;
                } else if (this.DoomGuy.isLoser) {
                    lf.setFrame("p2Wins");
                    return;
                }
            }


        } catch (InterruptedException e) {
            System.out.println("Game Crashed!");
        }
    }

    public void InitializeGame() {
        GatheredObjects.initGameObjects();
        this.setControls();
        this.InitializeMap(this.map);
    }

    public void InitializeMap(String map) {
        this.map = map;
        this.background = GatheredResources.getImage("background");

        try {
            InputStreamReader gameMap = new InputStreamReader(
                    Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("Maps/map1.csv")));
            this.mapReader = new BufferedReader(gameMap);
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Could not find map File");
        }

        // Parsing map
        ArrayList<ArrayList<String>> bluePrint = new ArrayList<>();
        try {
            String size;
            while ((size = this.mapReader.readLine()) != null) {
                if (size.isEmpty()) {
                    continue;
                }
                bluePrint.add(new ArrayList<>(Arrays.asList(size.split(","))));
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        // Map size
        int mapWidth = bluePrint.get(0).size();
        int mapHeight = bluePrint.size();
        this.world = new BufferedImage(mapWidth * 32, mapHeight * 32, BufferedImage.TYPE_INT_RGB);
        this.HUD = new HUD(this.world);

        // Generate entire map
        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                switch (bluePrint.get(y).get(x)) {
                    case ("0"):  //empty space
                        continue;

                    case ("B"):     //breakable wall
                        BufferedImage breakable = GatheredResources.getImage("bWall");
                        Wall bWall = new Wall(x * 32, y * 32, 0, breakable, true);
                        GatheredObjects.spawn(bWall);
                        break;

                    case ("U"):     //unbreakable wall
                        BufferedImage unbreakableWall = GatheredResources.getImage("uWall");
                        Wall uWall = new Wall(x * 32, y * 32, 0, unbreakableWall, false);
                        GatheredObjects.spawn(uWall);
                        break;

                    case ("R"): //respawn points when a player dies
                        Respawn respawn = new Respawn(x * 32, y * 32, 0);
                        GatheredObjects.respawnPoints.add(respawn);
                        break;

                    case ("1"):     // Player 1
                        BufferedImage p1 = GatheredResources.getImage("doom");
                        BufferedImage p1Bullet = GatheredResources.getImage("bullet1");
                        this.DoomGuy = new DoomCharacter(x * 32, y * 32, 90f, p1, p1Bullet, "Doom");
                        PlayerControl p1Controller = new PlayerControl(DoomGuy, this.controls2);
                        this.addKeyListener(p1Controller);
                        this.POV1 = new POV(DoomGuy);
                        this.HUD.setPlayerInfo(0, DoomGuy);
                        GatheredObjects.spawn(DoomGuy);
                        break;

                    case ("2"):     // Player 2
                        BufferedImage p2 = GatheredResources.getImage("baron");
                        BufferedImage p2Bullet = GatheredResources.getImage("bullet2");
                        this.BaronOfHell = new DoomCharacter(x * 32, y * 32, 270f, p2, p2Bullet, "Baron");
                        PlayerControl p2Controller = new PlayerControl(BaronOfHell, this.controls1);
                        this.addKeyListener(p2Controller);
                        this.POV2 = new POV(BaronOfHell);
                        this.HUD.setPlayerInfo(1, BaronOfHell);
                        GatheredObjects.spawn(BaronOfHell);
                        break;

                    default:
                        break;
                }
            }
        }
    }

    private void setControls() {
        this.controls1 = new HashMap<>();
        this.controls2 = new HashMap<>();

        // Set GameObjects.Player 1 controls
        this.controls1.put(KeyEvent.VK_UP, PlayerKeySet.up);
        this.controls1.put(KeyEvent.VK_DOWN, PlayerKeySet.down);
        this.controls1.put(KeyEvent.VK_LEFT, PlayerKeySet.left);
        this.controls1.put(KeyEvent.VK_RIGHT, PlayerKeySet.right);
        this.controls1.put(KeyEvent.VK_ENTER, PlayerKeySet.action);

        // Set GameObjects.Player 2 controls
        this.controls2.put(KeyEvent.VK_W, PlayerKeySet.up);
        this.controls2.put(KeyEvent.VK_S, PlayerKeySet.down);
        this.controls2.put(KeyEvent.VK_A, PlayerKeySet.left);
        this.controls2.put(KeyEvent.VK_D, PlayerKeySet.right);
        this.controls2.put(KeyEvent.VK_SPACE, PlayerKeySet.action);
    }

    private void update() {
        //Update In-game objects
        for (int i = 0; i < GatheredObjects.objCount(); ) {
            GameObject obj = GatheredObjects.getGameObject(i);
            obj.update();
            if (obj.isDestroyed()) {
                GatheredObjects.destroy(obj);
            } else {
                for (int j = 0; j < GatheredObjects.objCount(); j++) {
                    GameObject collidingObj = GatheredObjects.getGameObject(j);
                    if (obj == collidingObj) {
                        continue;
                    }
                    if (obj.getHitBox().intersects(collidingObj.getHitBox())) {
                        obj.objectCollision(collidingObj);
                    }
                }
                i++;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Graphics2D buffer = this.world.createGraphics();

        buffer.clearRect(0, 0, this.world.getWidth(), this.world.getHeight());
        super.paintComponent(g2);
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Scale background to match world size
        for (int i = 0; i < this.world.getWidth(); i += this.background.getWidth()) {
            for (int j = 0; j < this.world.getHeight(); j += this.background.getHeight()) {
                buffer.drawImage(this.background, i, j, null);
            }
        }

        // Draw every object in GameObjects
        for (int i = 0; i < GatheredObjects.objCount(); i++) {
            GameObject obj = GatheredObjects.getGameObject(i);
            obj.drawImage(buffer);
            obj.paintComponent(buffer);
        }

        // Draw POV and mm
        this.POV1.drawPOV(this.world);
        this.POV2.drawPOV(this.world);
        try {
            this.HUD.drawHUD(this.world);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        g2.drawImage(this.POV1.getPOV(), 0, 0, null);
        g2.drawImage(this.POV2.getPOV(), GameConstants.GAME_SCREEN_WIDTH / 2, 0, null);

        g2.drawImage(this.HUD.getP1Stats(), 0, GameConstants.GAME_SCREEN_HEIGHT -
                (GameConstants.GAME_SCREEN_HEIGHT / 3), null);

        g2.drawImage(this.HUD.getP2Stats(), (GameConstants.GAME_SCREEN_WIDTH / 2) +
                (this.HUD.getMinimapWidth() /2), GameConstants.GAME_SCREEN_HEIGHT -
                (GameConstants.GAME_SCREEN_HEIGHT / 3), null);

        g2.drawImage(this.HUD.getMinimap(), (GameConstants.GAME_SCREEN_WIDTH / 2) -
                (this.HUD.getMinimapWidth() / 2), GameConstants.GAME_SCREEN_HEIGHT -
                (GameConstants.GAME_SCREEN_HEIGHT / 3), null);
    }
}