package ntut.zxjte9411.hw9;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MediaService extends Service {

    private final String LOG_TAG = "Service";
    private MediaPlayer player;

    public MediaService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG,"onBind");
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Log.d(LOG_TAG,"onCreate");
        player = MediaPlayer.create(this,R.raw.song1);
        player.setLooping(true);
        player.setVolume(1000.0f,1000.0f);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG,"onStartCommand");
        if(!player.isPlaying())player.start();
        else Toast.makeText(this, "音樂播放中", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG,"onDestroy");
        player.stop();
        player.release();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(LOG_TAG,"onUnbind");
        return super.onUnbind(intent);
    }
}
