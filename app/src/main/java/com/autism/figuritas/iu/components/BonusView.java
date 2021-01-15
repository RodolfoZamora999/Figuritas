package com.autism.figuritas.iu.components;

import android.media.Image;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Class to convert a TextView to BonusView
 */
public class BonusView
{
    private int bonus;

    private TextView textView;
    private ImageView imageView;

    private BonusUpdateListener bonusUpdateListener;

    /**
     * Empty Constructor
     */
    public BonusView()
    {

    }

    /**
     * Constructor with a TextView parameter
     * @param textView The textView to update Bonus accumulated
     */
    public BonusView(@NonNull TextView textView)
    {
        this.textView = textView;
    }

    /**
     * Method to insert a TexView to show Bonus
     * @param textView The textView to update Bonus accumulated
     */
    public void setTextView(@NonNull TextView textView)
    {
        this.textView = textView;
    }

    /**
     * Method to insert an ImageView to animated
     * @param imageView ImageView to animated
     */
    public void setImageView(@NonNull ImageView imageView)
    {
        this.imageView = imageView;
    }

    /**
     * Method to return a TexView current
     * @return The current textView
     */
    @Nullable
    public TextView getTextView()
    {
        return this.textView;
    }

    /**
     * Method to return a ImageView current
     * @return The current ImageView
     */
    public ImageView getImageView()
    {
        return this.imageView;
    }

    /**
     * Method to accumulate bonus current
     * @param accumulateBonus The bonus accumulate to current bonus
     */
    public void updateBonus(int accumulateBonus)
    {
        //Accumulate
        this.bonus += accumulateBonus;

        if(textView != null)
            textView.setText(String.valueOf(bonus));

        //Notify interface
        if(this.bonusUpdateListener != null)
            bonusUpdateListener.updateBonus(textView, bonus, imageView);
    }

    /**
     * Method to return accumulate current bonus
     * @return Value of bonus
     */
    public int getBonus()
    {
        return this.bonus;
    }

    /**
     * Method to set BonusUpdateListener
     * @param bonusUpdateListener Object to notify
     */
    public void setBonusUpdateListener(@NonNull BonusUpdateListener bonusUpdateListener)
    {
        this.bonusUpdateListener = bonusUpdateListener;
    }

    /**
     *Interface to notify when BonusView is updated
     */
    public static interface BonusUpdateListener
    {
        void updateBonus(TextView textView, int bonus, @Nullable ImageView imageView);
    }
}
