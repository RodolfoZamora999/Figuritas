package com.autism.figuritas.iu.utilities;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

/**
 * Class to monitor when the level is complete (All shapes in their slot)
 */
public class MonitorLevel
{
    private int totalShapes;
    private int completeShapes;

    private TerminatedLevelListener terminatedLevelListener;

    public MonitorLevel()
    {

    }

    /**
     * Method that increment complete shapes (Shape in its slot)
     */
    public void incrementFiguresComplete()
    {
        completeShapes++;

        //Check values
        if(completeShapes == totalShapes)
           terminatedLevelListener.terminatedLevel(totalShapes);
    }

    /**
     * Insert manually total shapes in the level
     * @param totalShapes Total shapes in the level
     */
    public void setTotalShapes(int totalShapes)
    {
        this.totalShapes = totalShapes;
    }

    /**
     * Get total shapes in the level
     * @return Total shapes in the level
     */
    public int getTotalShapes()
    {
        return this.totalShapes;
    }

    /**
     * Insert listener of level finished, when level finished it will be notified
     * @param terminatedLevelListener Object Listener notify
     */
    public void setTerminatedLevelListener(TerminatedLevelListener terminatedLevelListener)
    {
        this.terminatedLevelListener = terminatedLevelListener;
    }

    /**
     * Interface to notify when the level is terminated (All shape are in their slot)
     */
    public static interface TerminatedLevelListener
    {
        /**
         *
         * @param totalShapes Total shape number assembly
         */
        void terminatedLevel(int totalShapes);
    }
}
