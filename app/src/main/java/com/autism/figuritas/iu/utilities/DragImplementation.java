package com.autism.figuritas.iu.utilities;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.drawable.Drawable;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Implementation for drag and drop figures
 * Remember to use tags
 */
public class DragImplementation implements View.OnDragListener
{
    private DragSuccessfulListener dragSuccessfulListener;

    public DragImplementation()
    {

    }

     /**
      * Set a DragSuccessfulListener to notify
     * @param dragSuccessfulListener
     */
    public void setDragSuccessfulListener(DragSuccessfulListener dragSuccessfulListener)
    {
        this.dragSuccessfulListener = dragSuccessfulListener;
    }


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

                if(dragSuccessfulListener != null)
                    dragSuccessfulListener.dragSuccessful(view);
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
     * @param view View drag, remember to use tags
     */
    public static void startDrag(View view)
    {
        //Invisible view
        view.setVisibility(View.INVISIBLE);

        String name_image = view.getTag().toString();

        ClipData.Item item = new ClipData.Item(name_image);

        ClipData clipData = new ClipData(view.getTag().toString(),
                new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);

        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

        //Start drag
        view.startDrag(clipData, shadowBuilder, view, View.DRAG_FLAG_OPAQUE);
    }

    /**
     * Interface to notify when drag is successful
     */
    public static interface DragSuccessfulListener
    {
        void dragSuccessful(View view);
    }
}