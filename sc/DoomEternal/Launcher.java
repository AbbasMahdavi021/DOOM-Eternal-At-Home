package DoomEternal;

import DoomEternal.functions.GatheredResources;
import DoomEternal.functions.Sound;
import DoomEternal.game.GameWorld;
import DoomEternal.menus.p2WinsPanel;
import DoomEternal.menus.p1WinsPanel;
import DoomEternal.menus.StartMenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class Launcher extends JFrame {
    private JPanel mainPanel;
    private GameWorld gamePanel;
    private final JFrame jf;
    private CardLayout cl;

    public Launcher() {
        GatheredResources.initSounds();
        GatheredResources.initImages();

        this.jf = new JFrame();
        this.jf.setTitle("Doom Eternal on a Budget");
        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Image icon = GatheredResources.getImage("icon");
        this.jf.setIconImage(icon);
    }

    public void initUIComponents() {
        this.mainPanel = new JPanel();
        JPanel startPanel = new StartMenuPanel(this);
        this.gamePanel = new GameWorld(this);
        this.gamePanel.InitializeGame();
        JPanel p1WinsPanel = new p1WinsPanel(this);
        JPanel p2WinsPanel = new p2WinsPanel(this);
        cl = new CardLayout();
        this.mainPanel.setLayout(cl);
        this.mainPanel.add(startPanel, "start");
        this.mainPanel.add(gamePanel, "DoomEternal");
        this.mainPanel.add(p1WinsPanel, "p1Wins");
        this.mainPanel.add(p2WinsPanel, "p2Wins");
        this.jf.add(mainPanel);
        this.jf.setResizable(false);
        this.setFrame("start");
    }

    public void setFrame(String type) {
        Sound menuTrack = new Sound(GatheredResources.getSound("menuTrack"));

        this.jf.setVisible(false);
        switch (type) {
            case "start" -> {
                this.jf.setSize(GameConstants.START_MENU_SCREEN_WIDTH, GameConstants.START_MENU_SCREEN_HEIGHT);
                menuTrack.playSound();
            }
            case "DoomEternal" -> {
                this.jf.setSize(GameConstants.GAME_SCREEN_WIDTH, GameConstants.GAME_SCREEN_HEIGHT);
                (new Thread(this.gamePanel)).start();
                menuTrack.stopSound();
            }
            case "p1Wins", "p2Wins" ->
                this.jf.setSize(GameConstants.END_MENU_SCREEN_WIDTH, GameConstants.END_MENU_SCREEN_HEIGHT);

        }
        this.cl.show(mainPanel, type);
        this.jf.setVisible(true);
    }

    public void closeGame() {
        this.jf.dispatchEvent(new WindowEvent(this.jf, WindowEvent.WINDOW_CLOSING));
    }

    public static void main(String[] args) {
        (new Launcher()).initUIComponents();
    }
}