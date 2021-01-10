package com.autism.figuritas.iu.levels;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.autism.figuritas.R;

public class LevelActivity extends AppCompatActivity
{
    private static MediaPlayer mediaPlayerSingle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_level);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        hideSystemUI();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        //Kill MediaPlayer
        if(mediaPlayerSingle != null)
        {
            if(mediaPlayerSingle.isPlaying())
                mediaPlayerSingle.stop();

            mediaPlayerSingle.release();
            mediaPlayerSingle = null;
        }
    }

    /**
     * Do fullscreen activity
     */
    private void hideSystemUI()
    {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }


    //Todo: Moficar en un futuro
    public MediaPlayer getMediaPlayerSingleton()
    {
        if(mediaPlayerSingle != null)
            return  mediaPlayerSingle;

       /* mediaPlayerSingle = new MediaPlayer();
        mediaPlayerSingle.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
        {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer)
            {
                mediaPlayer.start();
            }
        });*/

        mediaPlayerSingle = MediaPlayer.create(this, R.raw.ambient_music);

        return mediaPlayerSingle;
    }

    /**
     * Implementation for drag and drop figures
     */
    public static class DragImplementation implements View.OnDragListener
    {
        @Override
        public boolean onDrag(View view, DragEvent dragEvent)
        {
            int action = dragEvent.getAction();

            if(action == DragEvent.ACTION_DRAG_STARTED)
            {
                String figure_tag = ((ImageView)dragEvent.getLocalState()).getTag().toString();

                if(figure_tag.equals(view.getTag()))
                    return true;
                else
                    return false;
            }

            if(action == DragEvent.ACTION_DROP)
            {
                String tag = dragEvent.getClipData().getItemAt(0).getText().toString();

                if(tag.equals(view.getTag().toString()))
                    return true;
                else
                    return false;
            }

            if(action == DragEvent.ACTION_DRAG_ENDED)
            {
                if(dragEvent.getResult())
                {
                    //Update drawable slock
                    Drawable drawable = ((ImageView)dragEvent.getLocalState()).getDrawable();
                    ((ImageView)view).setImageDrawable(drawable);

                    ((ImageView)dragEvent.getLocalState()).setVisibility(View.INVISIBLE);
                    ((ImageView)dragEvent.getLocalState()).setEnabled(false);
                }

                View view_drag = (View)dragEvent.getLocalState();
                if (view_drag.isEnabled())
                    view_drag.setVisibility(View.VISIBLE);

                return true;
            }

            return false;
        }

        /**
         * Method for start image drag
         * @param view
         */
        public static void startDrag(View view)
        {
            //Invisible view
            view.setVisibility(View.INVISIBLE);

            String name_image = view.getTag().toString();

            ClipData.Item item = new ClipData.Item(name_image);

            ClipData clipData = new ClipData(view.getTag().toString(),
                    new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);

            // View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

            //Start drag
            view.startDrag(clipData, shadowBuilder, view, View.DRAG_FLAG_OPAQUE);
        }
    }
}