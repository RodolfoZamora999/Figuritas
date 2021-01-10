package com.autism.figuritas.iu.levels.level_1;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.autism.figuritas.R;
import com.autism.figuritas.iu.home.MainActivity;
import com.autism.figuritas.iu.levels.LevelActivity;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Level_1_Sub_1 extends Fragment
{
    private MediaPlayer mediaPlayer;

    private ImageView imgCircle;
    private ImageView imgSquare;
    private ImageView imgCircleSlock;
    private ImageView imgSquareSlock;

    private ImageButton btnCerrar;

    private TextView txtTimer;

    public Level_1_Sub_1()
    {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {


        return inflater.inflate(R.layout.fragment_level_1__sub_1, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        //References components
        this.imgCircle = getActivity().findViewById(R.id.imgCircle_1_1);
        this.imgSquare = getActivity().findViewById(R.id.imgSquare_1_1);
        this.imgCircleSlock = getActivity().findViewById(R.id.imgCircleSlock_1_1);
        this.imgSquareSlock = getActivity().findViewById(R.id.imgSquareSlock_1_1);
        this.btnCerrar = getActivity().findViewById(R.id.btnExit);
        this.txtTimer = getActivity().findViewById(R.id.txtTimer);


        //Tag for images
        this.imgCircle.setTag("circle");
        this.imgSquare.setTag("square");
        this.imgCircleSlock.setTag("circle");
        this.imgSquareSlock.setTag("square");

        //Event for click event
        this.btnCerrar.setOnClickListener(view ->
        {
            getActivity().finish();
        });

        this.imgCircle.setOnClickListener(view -> playSound(view));
        this.imgSquare.setOnClickListener(view -> playSound(view));


        //Event for init drag
        this.imgCircle.setOnLongClickListener(view ->
        {
            LevelActivity.DragImplementation.startDrag(view);

            return true;
        });

        this.imgSquare.setOnLongClickListener(view ->
        {
            LevelActivity.DragImplementation.startDrag(view);

            return true;
        });

        this.imgSquareSlock.setOnDragListener(new LevelActivity.DragImplementation());
        this.imgCircleSlock.setOnDragListener(new LevelActivity.DragImplementation());
    }

    @Override
    public void onResume()
    {
        super.onResume();

        if(mediaPlayer == null)
        {
            mediaPlayer = ((LevelActivity)getActivity()).getMediaPlayerSingleton();
            mediaPlayer.start();
        }

    }

    @Override
    public void onStop()
    {
        super.onStop();

        //Stop mediaPlayer
        if (mediaPlayer != null)
        {
            if(mediaPlayer.isPlaying())
                mediaPlayer.stop();
        }
    }

    /**
     * Method for play image sound
     * @param view
     */
    public void playSound(View view)
    {
        int id_resource = 0;

        if(view.getTag().toString().equals("circle"))
            id_resource = R.raw.circulo;

        else if(view.getTag().toString().equals("square"))
            id_resource = R.raw.cuadrado;


        MediaPlayer.create(getContext(), id_resource).start();

        /*
       try
       {
           AssetFileDescriptor afd = getActivity().getResources().openRawResourceFd(R.raw.circulo);

           if (afd == null)
               return;

           mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());

           afd.close();

           mediaPlayer.prepareAsync();
       }
       catch (IOException ioException)
       {
           Log.d("PRINT", "Error AssetFileDescriptor close");
       }*/
    }


    /*
    public void metodo()
    {
        Timer timer = new Timer();
        timer.schedule(new TimerTask()
        {
            int value = 0;

            @Override
            public void run()
            {
                Log.d("PRINT", "second: " + value);
                value++;

                /*if(value == 60)
                    timer.cancel();
            }
        }, 1000 / 60, 60);
    }*/

}

