package fr.benoit.tp11_2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MediaService musicSrv;
    private Intent playIntent;

    private Button playBtn;
    private Button stopBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playBtn = (Button) findViewById(R.id.play_btn);
        stopBtn = (Button) findViewById(R.id.stop_btn);
    }

    private ServiceConnection musicConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MediaService.MediaBinder binder = (MediaService.MediaBinder)service;
            //get service
            musicSrv = binder.getService();
            //pass list

            playBtn.setEnabled(true);
            stopBtn.setEnabled(true);

        }

        @Override
        public void onServiceDisconnected(ComponentName name){
        }
    };

    public void playSong(View v){
        musicSrv.playSong();
    }

    public void stopSong(View v){
        musicSrv.stopSong();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(playIntent==null){
            playIntent = new Intent(this, MediaService.class);
            bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
        }
    }
}
