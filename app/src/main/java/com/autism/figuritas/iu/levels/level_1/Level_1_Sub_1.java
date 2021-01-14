package com.autism.figuritas.iu.levels.level_1;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.LongDef;
import androidx.annotation.Nullable;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.autism.figuritas.R;
import com.autism.figuritas.iu.components.TimerView;
import com.autism.figuritas.iu.levels.LevelActivity;

public class Level_1_Sub_1 extends Fragment implements TimerView.TimerViewFinishListener
{
    private TimerView timerView;

    private MediaPlayer mediaPlayer;

    private ImageView imgCircle;
    private ImageView imgSquare;
    private ImageView imgCircleSlock;
    private ImageView imgSquareSlock;
    private ImageView imgTimer;

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        //References components
        this.imgCircle = getActivity().findViewById(R.id.imgCircle_1_1);
        this.imgSquare = getActivity().findViewById(R.id.imgSquare_1_1);
        this.imgCircleSlock = getActivity().findViewById(R.id.imgCircleSlock_1_1);
        this.imgSquareSlock = getActivity().findViewById(R.id.imgSquareSlock_1_1);
        this.btnCerrar = getActivity().findViewById(R.id.btnExit);
        this.txtTimer = getActivity().findViewById(R.id.txtTimer);
        this.imgTimer = getActivity().findViewById(R.id.imgTimer);

        //Create instance of TimerView
        this.timerView = new TimerView();
        this.timerView.setTime(0, 15);
        this.timerView.setTimerViewStopListener(this);
        this.timerView.setTextView(txtTimer);

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
    public void finishTimerView(TextView txtTimer, int mintes, int secods)
    {
           getActivity().runOnUiThread(()-> {

               Toast.makeText(getContext(), "¡Tiempo terminado!", Toast.LENGTH_LONG).show();

               SpringAnimation rotation = new SpringAnimation(imgTimer, DynamicAnimation.ROTATION, 360);
               rotation.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);

               rotation.start();
           });
    }

    @Override
    public void onResume()
    {
        super.onResume();

        if (mediaPlayer == null)
        {
            mediaPlayer = ((LevelActivity) getActivity()).getMediaPlayerSingleton();
            mediaPlayer.start();
        }

        if (timerView != null)
        {
            if(timerView.isPause())
                timerView.resumeTimerView();
            else
                timerView.startTimerView();
        }

    }

    @Override
    public void onPause()
    {
        super.onPause();

        if(timerView != null)
            timerView.pauseTimerView();
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

            mediaPlayer = null;
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        if(this.timerView != null)
            this.timerView.stopTimerView();
    }

    /**
     * Method for play image sound
     * @param view
     */
    public void playSound(View view)
    {
        //Todo: Mejorar esta sección de código
        int id_resource = 0;

        if(view.getTag().toString().equals("circle"))
            id_resource = R.raw.circulo;

        else if(view.getTag().toString().equals("square"))
            id_resource = R.raw.cuadrado;


        MediaPlayer.create(getContext(), id_resource).start();
    }
}

