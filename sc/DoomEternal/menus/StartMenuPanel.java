package DoomEternal.menus;

import DoomEternal.Launcher;
import DoomEternal.functions.GatheredResources;
import DoomEternal.functions.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class StartMenuPanel extends JPanel {

    private final BufferedImage menuBackground;
    private final Launcher lf;

    public StartMenuPanel(Launcher lf) {
        this.lf = lf;
        int random = (int) (Math.random() * 2 + 1);

        if(random == 1){
            menuBackground = GatheredResources.getImage("title1");
        }else {
            menuBackground = GatheredResources.getImage("title2");
        }

        this.setBackground(Color.BLACK);
        this.setLayout(null);

        JButton start = new JButton("START");
        start.setFont(new Font("Courier New", Font.BOLD, 27));
        start.setBackground(new Color(0,204,0));
        start.setBounds(500, 500, 150, 50);
        start.addActionListener((actionEvent -> {
            this.lf.setFrame("DoomEternal");
            new Sound(GatheredResources.getSound("soundTrack")).playSound();
        }));

        JButton exit = new JButton("EXIT");
        exit.setSize(new Dimension(200, 100));
        exit.setFont(new Font("Courier New", Font.BOLD, 27));
        exit.setBackground(new Color(255,0,0));
        exit.setBounds(500, 600, 150, 50);
        exit.addActionListener((actionEvent -> this.lf.closeGame()));

        this.add(start);
        this.add(exit);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.menuBackground, 0, 0, null);
    }
}
