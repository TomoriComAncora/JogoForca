package lucas.curso.jogoforca.utils;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import lucas.curso.jogoforca.R;

public class musicaGeral extends Service {
    private static MediaPlayer mp;

    public void onCreate(){
        super.onCreate();
        mp = MediaPlayer.create(this, R.raw.bluebird);
        mp.setLooping(true);
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        mp.start();
        return START_STICKY;
    }

    public void onDestroy(){
        mp.stop();
        mp.release();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
