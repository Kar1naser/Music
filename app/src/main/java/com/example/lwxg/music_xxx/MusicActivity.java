package com.example.lwxg.music_xxx;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;

public class MusicActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, MediaPlayer.OnCompletionListener {



    private int play_state = 1;
    private Context context;
    private String[] musicName = {"1.mp3", "2.mp3", "3.mp3"};
    private ListView home_list;
    private Button music_last;
    private Button music_start;
    private Button music_next;
    private MediaPlayer mediaPlayer;
    private AssetManager assetManager;
    int i = 0;
    boolean zt = true;
    private Button music_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        initView();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(this);
        assetManager = getAssets();
        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, musicName);
        home_list.setAdapter(adapter);
        home_list.setOnItemClickListener(this);
        try {
            AssetFileDescriptor afd = assetManager.openFd(musicName[i]);
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();
            zt = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initView() {
        context = this;
        home_list = (ListView) findViewById(R.id.home_list);
        music_last = (Button) findViewById(R.id.music_last);
        music_start = (Button) findViewById(R.id.music_start);
        music_next = (Button) findViewById(R.id.music_next);
        music_last.setOnClickListener(this);
        music_start.setOnClickListener(this);
        music_next.setOnClickListener(this);
        music_state = (Button) findViewById(R.id.music_state);
        music_state.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.music_last:
                i--;
                if (i == -1)
                    i = 2;
                playMusic();
                break;
            case R.id.music_start:
                if (zt) {
                    zt = !zt;
                    mediaPlayer.stop();
                } else {
                    zt = !zt;
                    try {
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.start();
                }
                break;
            case R.id.music_next:
                i++;
                if (i == 3)
                    i = 0;
                playMusic();
                break;
            case R.id.music_state:
                play_state++;
                if (play_state == 1)
                    music_state.setText("全部循环");
                else if (play_state == 2)
                    music_state.setText("单曲循环");
                else if (play_state == 3)
                    music_state.setText("随机播放");
                if (play_state == 4)
                    play_state = 1;
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            default:
                break;
            case 0:
                i = 0;
                playMusic();
                break;
            case 1:
                i = 1;
                playMusic();
                break;
            case 2:
                i = 2;
                playMusic();
                break;
        }
    }

    private void playMusic() {
        mediaPlayer.reset();
        try {
            AssetFileDescriptor afd = assetManager.openFd(musicName[i]);
//            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();
            zt = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (play_state == 1)
            next();
        else if (play_state == 2)
            circulation();
        else if (play_state == 3)
            stochastic();
    }

    private void stochastic() {
        i = (int) (Math.random() * 2 + 1);
        playMusic();
    }

    private void circulation() {
        playMusic();
    }

    private void next() {
        i++;
        if (i == 3)
            i = 0;
        playMusic();
    }
}
