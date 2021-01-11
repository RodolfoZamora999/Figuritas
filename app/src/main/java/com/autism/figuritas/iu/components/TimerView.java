package com.autism.figuritas.iu.components;

import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class to convert a TextView to a TimerView
 * Todo: Todavía queda muchas mejor por hacer en esta clase, pero actualmente se encuentra funcional.
 * Todo: Recuerda corregir bugs, hacer más pruebas y mejorar la pausa
 */
public class TimerView extends TimerTask
{
    private Timer timer;

    private TextView textView;

    private int minutes;
    private int seconds;

    private TimerViewFinishListener timerViewFinishListener;

    private boolean isRunning;
    private boolean isPause;

    private Handler handlerMain;

    /**
     * Empty constructor
     */
    public TimerView()
    {

    }

    /**
     * Constructor with a parameter
     * @param textView View to update
     */
    public TimerView(TextView textView)
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
     * Implementation TimerTask interface
     */
    @Override
    public void run()
    {
        Log.d("PRINT", "Entro timer");


        //Check if time is finish
        if(minutes == 0 && seconds == 0)
        {
            if(timerViewFinishListener != null)
                timerViewFinishListener.finishTimerView(textView, minutes, seconds);

            //Stop timer
            stopTimerView();

            return;
        }

        if(seconds >= 0)
            seconds--;
        else
        {
            minutes--;
            seconds = 59;
        }

        //Format; 01:30
        String formatTime = minutes + ":" + seconds ;

        //Update TextView
        if(textView != null)
            updateTextView(formatTime);
    }

    public void startTimerView()
    {
        this.timer = new Timer();

        if(textView != null)
            this.handlerMain = new Handler(textView.getContext().getMainLooper());

        this.timer.scheduleAtFixedRate(this, 0, 1000);

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
            isRunning = false;
            isPause = true;
        }
    }

    public void resumeTimerView()
    {
       // this.timer.scheduleAtFixedRate(this, 1000, 1000);
        isPause = false;
    }

    /**
     * Method to stop TimerView time
     */
    public void stopTimerView()
    {
        if(this.timer != null)
        {
            timer.cancel();
            timer.purge();
            timer = null;
            isRunning = false;
        }

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

    /**
     * Method to insert only seconds in TimerView
     */
    public void setTime(int seconds)
    {
        this.seconds = seconds;
    }

    /**
     * Method to insert minutes and seconds in TimerView
     * @param minutes
     * @param seconds
     */
    public void setTime(int minutes, int seconds)
    {
        this.minutes = minutes;
        this.seconds = seconds;
    }

    /**
     * Set Listener
     */
    public void setTimerViewStopListener(TimerViewFinishListener timerViewStopListener)
    {
        this.timerViewFinishListener = timerViewStopListener;
    }

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
     *Interface to notify Timer is finished
     */
    public static interface TimerViewFinishListener
    {
        /**Important note: Remember to use main thread to update UI
         * @param txtTimer TextView to show timer
         * @param minutes Minutes finished when TimerView stops
         * @param seconds Seconds finished when TimerView stops
         */
        void finishTimerView(TextView txtTimer, int minutes, int seconds);
    }
}
