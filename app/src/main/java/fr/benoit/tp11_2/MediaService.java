package fr.benoit.tp11_2;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

public class MediaService extends Service {

    private static final String TAG = "MediaService";

    //media player
    private MediaPlayer player;

    private MediaBinder mBinder;

    public MediaService() {
        mBinder = new MediaBinder();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        //create player
        player = MediaPlayer.create(this, R.raw.jimi);
    }

    public class MediaBinder extends Binder {
        MediaService getService() {
            return MediaService.this;
        }
    }

    public void playSong() {
        player.start();
    }

    public void stopSong(){
        player.pause();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        player.stop();
        player.release();
        return false;
    }
}
