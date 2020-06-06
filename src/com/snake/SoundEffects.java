package com.snake;

import sun.audio.*;

import java.io.FileInputStream;


public class SoundEffects {

    public void playMusic(String soundName){
        try{
            FileInputStream fileau = new FileInputStream("com\\snake\\statics\\SoundFiles\\" + soundName + ".wav");
            AudioStream as = new AudioStream(fileau);
            AudioPlayer.player.start(as);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
