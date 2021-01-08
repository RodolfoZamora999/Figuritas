package com.autism.figuritas.iu.levels.level_1;

import android.content.ClipData;
import android.content.ClipDescription;;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.autism.figuritas.R;
import com.autism.figuritas.iu.levels.LevelActivity;

public class Level_1_Sub_1 extends Fragment
{
    private ImageView imgCircle;
    private ImageView imgSquare;
    private ImageView imgCircleSlock;
    private ImageView imgSquareSlock;

    public Level_1_Sub_1()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {


        return inflater.inflate(R.layout.fragment_level_1__sub_1, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        //References components
        this.imgCircle = getActivity().findViewById(R.id.imgCircle_1_1);
        this.imgSquare = getActivity().findViewById(R.id.imgSquare_1_1);
        this.imgCircleSlock = getActivity().findViewById(R.id.imgCircleSlock_1_1);
        this.imgSquareSlock = getActivity().findViewById(R.id.imgSquareSlock_1_1);

        //Tag for images
        this.imgCircle.setTag("circle");
        this.imgSquare.setTag("square");
        this.imgCircleSlock.setTag("circle");
        this.imgSquareSlock.setTag("square");

        //Event for init drag
        this.imgCircle.setOnLongClickListener(view ->
        {
            startDrop(view);

            return true;
        });

        this.imgSquare.setOnLongClickListener(view ->
        {
            startDrop(view);

            return true;
        });

        this.imgSquareSlock.setOnDragListener(new LevelActivity.DragImplementation());
        this.imgCircleSlock.setOnDragListener(new LevelActivity.DragImplementation());
    }

    /**
     * Method for start image drop
     * @param view
     */
    private void startDrop(View view)
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

