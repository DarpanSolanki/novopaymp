package in.novopay.darpan.mymusicplayer;

import android.media.MediaPlayer;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class MainActivity extends ActionBarActivity {

    Button play_button, pause_button, fast_frwd, rew;
    SeekBar seekbar;

    MusicHandler musicHandler = new MusicHandler();


    private MediaPlayer mediaplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaplayer = MediaPlayer.create(this, R.raw.a);
        play_button = (Button) findViewById(R.id.main_play);
        pause_button = (Button) findViewById(R.id.main_pause);
        fast_frwd = (Button) findViewById(R.id.main_fast);
        rew = (Button) findViewById(R.id.main_rew);
        seekbar = (SeekBar) findViewById(R.id.main_seek);

        seekbar.setMax(mediaplayer.getDuration());

        play_button.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               Toast.makeText(MainActivity.this, "Play is clicked", Toast.LENGTH_SHORT).show();

                                               mediaplayer.start();
                                               musicHandler.sendEmptyMessage(MESSAGE_WAKE_UP_AND_SEEK);
                                           }
                                       }

        );


        pause_button.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(MainActivity.this, "Pause is clicked", Toast.LENGTH_SHORT).show();
                                                musicHandler.sendEmptyMessage(MESSAGE_WAKE_UP_AND_SEEK);
                                                mediaplayer.pause();
                                            }
                                        }

        );

        fast_frwd.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             //Toast.makeText(MainActivity.this, "Fast Forwarding", Toast.LENGTH_SHORT).show();
                                             mediaplayer.seekTo(mediaplayer.getCurrentPosition() + 5000);
                                         }
                                     }
        );
        rew.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       Toast.makeText(MainActivity.this, "Rewinding", Toast.LENGTH_SHORT).show();
                                       mediaplayer.seekTo(mediaplayer.getCurrentPosition() - 1000);
                                   }
                               }

        );


        mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(MainActivity.this, "Song Ended", Toast.LENGTH_LONG).show();
            }
        });


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaplayer.seekTo(progress);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static int MESSAGE_WAKE_UP_AND_SEEK = 10;


    class MusicHandler extends android.os.Handler {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MESSAGE_WAKE_UP_AND_SEEK) {

                if (mediaplayer != null) {
                    if(mediaplayer.isPlaying()) {
                        seekbar.setProgress(mediaplayer.getCurrentPosition());
                        sendEmptyMessageDelayed(MESSAGE_WAKE_UP_AND_SEEK, 200);
                    }
                }
            }

            super.handleMessage(msg);
        }
    }


}
