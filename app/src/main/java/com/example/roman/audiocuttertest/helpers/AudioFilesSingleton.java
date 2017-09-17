package com.example.roman.audiocuttertest.helpers;

import com.example.roman.audiocuttertest.io.Wrap;

import java.util.ArrayList;

/**
 * Created by Roman on 07.09.2017.
 */

public class AudioFilesSingleton {
    private static ArrayList<Wrap> audioFiles;

    public static void setAudioFiles(ArrayList<Wrap> audioFiles){
            AudioFilesSingleton.audioFiles = audioFiles;
    }

    public static ArrayList<Wrap> getAudioFiles(){
        if(audioFiles == null)
            audioFiles = new ArrayList<>();

        return audioFiles;
    }

    public static void resetAudioFiles(){
        audioFiles = new ArrayList<>();
    }
}