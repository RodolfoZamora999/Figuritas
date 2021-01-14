package com.autism.figuritas.iu.components;

import android.widget.TextView;

import androidx.annotation.NonNull;

/**
 * Class to convert a TextView to BonusView
 */
public class BonusView
{
    private TextView textView;

    /**
     * Empty Constructor
     */
    public BonusView()
    {

    }

    /**
     * Constructor with a TextView parameter
     * @param textView
     */
    public BonusView(TextView textView)
    {
        this.textView = textView;
    }

    @NonNull
    public void setTextView(TextView textView)
    {

    }
}
