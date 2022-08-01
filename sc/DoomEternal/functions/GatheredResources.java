package DoomEternal.functions;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GatheredResources {
    private static final Map<String, BufferedImage> images = new HashMap<>();
    private static final Map<String, Clip> sounds = new HashMap<>();


    public static BufferedImage getImage(String key) {
        return GatheredResources.images.get(key);
    }
    public static Clip getSound(String key) {
        return GatheredResources.sounds.get(key);
    }


    public static void initImages() {
        try {
            //Game Images
            GatheredResources.images.put("icon", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("icon.png"))));
            GatheredResources.images.put("background", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("bg1.png"))));
            GatheredResources.images.put("title1", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("title1.png"))));
            GatheredResources.images.put("title2", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("title2.png"))));
            GatheredResources.images.put("doomWin1", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("doomWin1.png"))));
            GatheredResources.images.put("doomWin2", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("doomWin2.png"))));
            GatheredResources.images.put("baronWin1", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("baronWin1.png"))));
            GatheredResources.images.put("baronWin2", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("baronWin2.png"))));


            //Player Images
            GatheredResources.images.put("doom", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Player/doom.png"))));
            GatheredResources.images.put("doom0", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Player/doom0.png"))));
            GatheredResources.images.put("doom1", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Player/doom1.png"))));
            GatheredResources.images.put("doom2", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Player/doom2.png"))));
            GatheredResources.images.put("doom3", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Player/doom3.png"))));
            GatheredResources.images.put("doom4", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Player/doom4.png"))));
            GatheredResources.images.put("doomShoot", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Player/doomShoot.png"))));

            GatheredResources.images.put("baron", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Player/baron.png"))));
            GatheredResources.images.put("baron0", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Player/baron0.png"))));
            GatheredResources.images.put("baron1", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Player/baron1.png"))));
            GatheredResources.images.put("baron2", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Player/baron2.png"))));
            GatheredResources.images.put("baron3", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Player/baron3.png"))));
            GatheredResources.images.put("baron4", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Player/baron4.png"))));
            GatheredResources.images.put("baronShoot", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Player/baronShoot.png"))));

            //Hud Images
            GatheredResources.images.put("fullHeart", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Hud/fHeart.png"))));
            GatheredResources.images.put("emptyHeart", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Hud/eHeart.png"))));
            GatheredResources.images.put("statBullet", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Hud/bullet.png"))));
            GatheredResources.images.put("statArmor", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Hud/armor.png"))));
            GatheredResources.images.put("statDamage", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Hud/damage.png"))));
            GatheredResources.images.put("statFireRate", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Hud/fireRate.png"))));
            GatheredResources.images.put("key1", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Hud/key3.png"))));
            GatheredResources.images.put("key2", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Hud/key4.png"))));
            GatheredResources.images.put("logo", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Hud/Logo.png"))));
            GatheredResources.images.put("bar", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Hud/bar.png"))));

            //Walls Images
            GatheredResources.images.put("bWall", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("wallB.jpg"))));
            GatheredResources.images.put("uWall", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("wallU.jpg"))));

            //PowerUps Images
            GatheredResources.images.put("healthDrop", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Drops/healthDrop.png"))));
            GatheredResources.images.put("fireRateDrop", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Drops/fireRateDrop.png"))));
            GatheredResources.images.put("damageDrop", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Drops/damageDrop.png"))));
            GatheredResources.images.put("armorDrop", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Drops/armorDrop.png"))));
            GatheredResources.images.put("bulletDrop", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Drops/bulletDrop.png"))));
            GatheredResources.images.put("rocketDrop", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Drops/rocketDrop.png"))));
            GatheredResources.images.put("mineDrop", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Drops/mineDrop.png"))));

            //Weapon Images
            GatheredResources.images.put("bullet1", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("bullet1.png"))));
            GatheredResources.images.put("bullet2", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("bullet2.png"))));
            GatheredResources.images.put("rocket", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("rocket.png"))));
            GatheredResources.images.put("mine", ImageIO.read(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("mine.png"))));

        } catch (IOException e) {
            System.err.println("Could not retrieve image resources!");
            e.printStackTrace();
            System.exit(-2);
        }
    }

    public static void initSounds() {
        try {
            AudioInputStream as;
            Clip clip;

            as = AudioSystem.getAudioInputStream(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Sounds/sound_track.wav")));
            clip = AudioSystem.getClip();
            clip.open(as);
            GatheredResources.sounds.put("soundTrack", clip);

            as = AudioSystem.getAudioInputStream(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Sounds/menu_track.wav")));
            clip = AudioSystem.getClip();
            clip.open(as);
            GatheredResources.sounds.put("menuTrack", clip);

            as = AudioSystem.getAudioInputStream(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Sounds/bullet.wav")));
            clip = AudioSystem.getClip();
            clip.open(as);
            GatheredResources.sounds.put("bullet", clip);

            as = AudioSystem.getAudioInputStream(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Sounds/rocket.wav")));
            clip = AudioSystem.getClip();
            clip.open(as);
            GatheredResources.sounds.put("rocket", clip);

            as = AudioSystem.getAudioInputStream(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Sounds/mine.wav")));
            clip = AudioSystem.getClip();
            clip.open(as);
            GatheredResources.sounds.put("mine", clip);

            as = AudioSystem.getAudioInputStream(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Sounds/pickDrop.wav")));
            clip = AudioSystem.getClip();
            clip.open(as);
            GatheredResources.sounds.put("pickDrop", clip);

            as = AudioSystem.getAudioInputStream(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Sounds/doomHurt.wav")));
            clip = AudioSystem.getClip();
            clip.open(as);
            GatheredResources.sounds.put("doomHurt", clip);

            as = AudioSystem.getAudioInputStream(Objects.requireNonNull(GatheredResources.class.getClassLoader().getResource("Sounds/baronHurt.wav")));
            clip = AudioSystem.getClip();
            clip.open(as);
            GatheredResources.sounds.put("baronHurt", clip);

        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            System.err.println("Could not retrieve sound resources!");
            e.printStackTrace();
            System.exit(-2);
        }
    }
}
