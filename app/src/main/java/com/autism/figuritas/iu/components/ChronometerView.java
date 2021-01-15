package com.autism.figuritas.iu.components;

import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.Timer;
import java.util.TimerTask;

/**
 * ChronometerView can convert any TextView To ChronometerView
 */
public class ChronometerView
{
    private Timer timer;

    private TextView textView;
    private ImageView  imageView;

    private int minutes;
    private int seconds;

    private boolean isRunning;
    private boolean isPause;

    private Handler handlerMain;

    private ChronometerView.MyTimerTask timerTask;

    /**
     * Empty constructor
     */
    public ChronometerView()
    {

    }

    /**
     * Constructor with a parameter
     * @param textView View to update
     */
    public ChronometerView(TextView textView)
    {
        this.textView = textView;
    }

    protected void updateTextView(String timeText)
    {
        //Magic!
        //Update TextView from main thread using a Handler
        this.handlerMain.post(()-> textView.setText(timeText));
    }

    /**
     * Method to start TimerView counter
     */
    public void startChronometerView()
    {
        this.timer = new Timer();

        if(textView != null)
            this.handlerMain = new Handler(textView.getContext().getMainLooper());

        this.timerTask = new ChronometerView.MyTimerTask();

        this.timer.scheduleAtFixedRate(timerTask, 0, 1000);

        this.isRunning = true;
    }

    /**
     * Method to pause TimerView time
     */
    public void pauseTimerView()
    {
        if(this.timer != null)
        {
            timer.cancel();
            timer.purge();
            timer = null;
            timerTask.cancel();
            timerTask = null;
            isRunning = false;
            isPause = true;
        }
    }

    public void resumeTimerView()
    {
        //Resume timer
        this.timer = new Timer();
        this.timerTask = new ChronometerView.MyTimerTask();
        this.timer.scheduleAtFixedRate(timerTask, 0, 1000);

        isPause = false;
        isRunning = true;
    }

    /**
     * Method to stop TimerView time, reset all values
     */
    public void resetChronometerView()
    {
        if(this.timer != null)
        {
            timer.cancel();
            timer.purge();
            timerTask.cancel();
            timer = null;
            timerTask = null;
        }

        isRunning = false;
        minutes = 0;
        seconds = 0;

        //Delete reference
        this.textView = null;
    }

    /**
     * Method to return if TimerView is pause now
     * @return
     */
    public boolean isPause()
    {
        return this.isPause;
    }

    /**
     * Method to return if TimerView is running now
     * @return
     */
    public boolean isRunning()
    {
        return this.isRunning;
    }
    @NonNull
    /**
     * Method for set TextView
     * @param textView
     */
    public void setTextView(TextView textView)
    {
        this.textView = textView;
    }

    /**
     * Method for get current TextView
     * @return Current TextView
     */
    public TextView getTextView()
    {
        return this.textView;
    }

    /**
     * Implementation of TimerTask
     */
    private class MyTimerTask extends TimerTask
    {
        @Override
        public void run()
        {
            if(seconds <= 59)
                seconds++;
            else
            {
                minutes++;
                seconds = 0;
            }

            //Format; 01:30
            String formatTime;

            if(minutes < 10)
                formatTime = "0"+minutes+":";
            else
                formatTime = minutes+":";

            if(seconds < 10)
                formatTime += "0"+seconds;
            else
                formatTime += ""+seconds;

            //Update TextView
            if(textView != null)
                updateTextView(formatTime);
        }
    }
}
