/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reservasoftware;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Dalex
 */
public class Sounds {
     public static void sonidoAdvertencia(){
         sonido("src/Sonidos/advertencia.wav");
     }
     public static void sonidoError(){
         sonido("src/Sonidos/error.wav");
     }
     public static void sonidoOk(){
         sonido("src/Sonidos/ok.wav");
     }

    private static void sonido(String file) {
        try {
            File soundFile = new File(file);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        }
    }
    
}
