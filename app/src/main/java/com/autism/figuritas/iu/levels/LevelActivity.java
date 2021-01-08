package com.autism.figuritas.iu.levels;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.autism.figuritas.R;

public class LevelActivity extends AppCompatActivity
{
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
    }
}