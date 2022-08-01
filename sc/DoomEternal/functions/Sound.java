package DoomEternal.functions;

import javax.sound.sampled.Clip;

public class Sound implements Runnable{

    Clip c;

    public Sound(Clip c){
        this.c = c;
    }

    public void stopSound() {
        if(c.isRunning()){
            c.stop();
        }
    }

    public void playSound(){
        stopSound();
        c.setFramePosition(0);
        c.start();
    }

    @Override
    public void run() {
    }

}
