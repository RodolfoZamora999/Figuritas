package com.autism.figuritas.iu.levels;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.fragment.app.Fragment;
import com.autism.figuritas.R;
import com.autism.figuritas.iu.components.BonusView;
import com.autism.figuritas.iu.components.ChronometerView;
import com.autism.figuritas.iu.utilities.DragImplementation;
import com.autism.figuritas.iu.utilities.MonitorLevel;
import com.autism.figuritas.persistence.database.Configuracion;

public abstract class AbstractLevel extends Fragment
        implements DragImplementation.DragSuccessfulListener, BonusView.BonusUpdateListener, MonitorLevel.TerminatedLevelListener
{
    private Configuracion configuration;
    private MediaPlayer mediaPlayerSound;

    private ChronometerView chronometerView;
    private BonusView bonusView;
    private MonitorLevel monitorLevel;

    public AbstractLevel()
    {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Instance ChronometerView
        chronometerView = new ChronometerView();

        //Instance BonusView
        bonusView = new BonusView();
        bonusView.setBonusUpdateListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        //Get configuration from Activity
        configuration = ((LevelActivity)getActivity()).getConfiguration();

        //Autodetect shapes
        monitorLevel = new MonitorLevel();
        monitorLevel.setTerminatedLevelListener(this);
    }

    public void playFigureSong(FIGURES figures) {
        //Check
        if (mediaPlayerSound != null)
            if (mediaPlayerSound.isPlaying())
                return;

        int id_resource = 0;

        switch (figures) {
            case CIRCLE:
                id_resource = R.raw.circle;
                break;
            case SQUARE:
                id_resource = R.raw.square;
                break;
            case TRIANGLE:
                id_resource = R.raw.triangle;
                break;
            case RECTANGLE:
                id_resource = R.raw.rectangle;
                break;
            case STAR:
                id_resource = R.raw.star;
                break;
            case PENTAGON:
                id_resource = R.raw.pentagon;
                break;
        }

        mediaPlayerSound = MediaPlayer.create(getContext(), id_resource);
        mediaPlayerSound.setVolume((configuration.volumeSound * 0.01f),(configuration.volumeSound * 0.01f) );
        mediaPlayerSound.start();
    }

    /**
     * Method than get String time of ChronometerView and stop, reset ChronometerView
     * @return A String time of ChronometerView
     */
    public String finishChronometerViwLevel()
    {
        String time;

        chronometerView.pauseTimerView();
        time = chronometerView.getTextView().getText().toString();
        chronometerView.resetChronometerView();

        return time != null ? time : "00:00";
    }


    /**
     * Method to set total shapes available in the level
     * @param shapeCount Total shapes in the level
     */
    public void setShapeCount(int shapeCount)
    {
        this.monitorLevel.setTotalShapes(shapeCount);
    }


    /**
     * Set a TextView to ChronometerView
     * @param txtTimer A TextView to show timer of TimerView
     */
    public void setTextViewToChronometerView(TextView txtTimer)
    {
        this.chronometerView.setTextView(txtTimer);
    }

    /**
     * Set a TextView to BonusView to show data
     * @param txtBonus A TextView to BonusView
     */
    public void setTextViewToBonusView(TextView txtBonus)
    {
        this.bonusView.setTextView(txtBonus);
    }

    /**
     * Set a ImageView to BonusView to animated
     * @param imgBonus A ImageView to BonusView
     */
    public void setImageViewToBonusView(ImageView imgBonus)
    {
        this.bonusView.setImageView(imgBonus);
    }

    @Override
    public void updateBonus(TextView textView, int bonus, ImageView imageView)
    {
        SpringAnimation animation = new SpringAnimation(imageView, DynamicAnimation.ROTATION_Y, 360);
        animation.setStartValue(0);
        animation.getSpring().setStiffness(SpringForce.STIFFNESS_LOW);
        animation.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY);
        animation.start();
    }

    @Override
    public void dragSuccessful(View view)
    {
        if(bonusView != null)
            bonusView.updateBonus(25);

        if(monitorLevel != null)
            monitorLevel.incrementFiguresComplete();
    }

    @Override
    public void onResume()
    {
        super.onResume();

        if (chronometerView != null)
        {
            if(chronometerView.isPause())
                chronometerView.resumeTimerView();
            else
                chronometerView.startChronometerView();
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();

        if(mediaPlayerSound != null)
            if (mediaPlayerSound.isPlaying())
                mediaPlayerSound.pause();

            if(chronometerView != null)
            chronometerView.pauseTimerView();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        if(mediaPlayerSound != null)
        {
            if(mediaPlayerSound.isPlaying())
                mediaPlayerSound.stop();

            mediaPlayerSound.release();
            mediaPlayerSound = null;
        }

        //Clear ChronometerView
        if(this.chronometerView != null)
            this.chronometerView.resetChronometerView();
    }

    /**
     * Enum for All available
     */
    public static enum FIGURES
    {
        CIRCLE,
        SQUARE,
        TRIANGLE,
        RECTANGLE,
        STAR,
        PENTAGON
    };
}
