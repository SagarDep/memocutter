package com.example.roman.audiocuttertest.io;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Roman on 03.09.2017.
 */

public class AudioDetector extends Thread {

    private Context context;
    private File audioCutterDirectory;
    private AudioDetectorCallback callback;
    
    public AudioDetector(Context context, File audioCutterDirectory, AudioDetectorCallback callback){
        this.context = context;
        this.audioCutterDirectory = audioCutterDirectory;
        this.callback = callback;
    }

    @Override
    public void run(){

        ArrayList<Wrap> audioFiles = new ArrayList<>();

        Log.d("BROWSE","started");

        for(File audioFile : audioCutterDirectory.listFiles()){
            if(audioFile.getName().contains("mp3")){
                Log.d("BROWSE","ABS: "+audioFile.getAbsolutePath());

                MediaPlayer mediaPlayer;
                Uri myUri = Uri.fromFile(audioFile); // initialize Uri here
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(context, myUri);
                    mediaPlayer.prepare();

                    audioFiles.add(new Wrap(audioFile, audioFile.getName(), mediaPlayer));

                } catch (IOException e) {
                    callback.onAudioDetectorFail(audioFile, e);
                    Log.d("BROWSE","FAIL"+e.toString());
                }

            }
        } //endfor

        callback.onAudioDetectorSuccess(audioFiles);
    }

}
