package com.autism.figuritas.iu.levels.level_1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.autism.figuritas.R;
import com.autism.figuritas.iu.components.BonusView;
import com.autism.figuritas.iu.components.StatisticsLevelDialog;
import com.autism.figuritas.iu.levels.AbstractLevel;
import com.autism.figuritas.iu.utilities.DragImplementation;

public class Level_1 extends AbstractLevel
{
    public Level_1()
    {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_level_1__sub_1, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        ImageView imgCircle = getActivity().findViewById(R.id.imgCircle_1_1);
        ImageView imgSquare = getActivity().findViewById(R.id.imgSquare_1_1);
        ImageView imgCircleSlot = getActivity().findViewById(R.id.imgCircleSlot_1_1);
        ImageView imgSquareSlot = getActivity().findViewById(R.id.imgSquareSlot_1_1);
        ImageButton btnClose = getActivity().findViewById(R.id.btnExit);
        ImageButton btnReboot = getActivity().findViewById(R.id.btnReboot);
        ImageView imgTimer = getActivity().findViewById(R.id.imgTimer);
        ImageView imgBonus = getActivity().findViewById(R.id.imgBonus);

        TextView txtTimer = getActivity().findViewById(R.id.txtTimer);
        TextView txtBonus = getActivity().findViewById(R.id.txtBonus);

        //Set number shape count
        setShapeCount(2);

        //Set textView for Chronometer
        setTextViewToChronometerView(txtTimer);

        //Set components in BonusView
        setTextViewToBonusView(txtBonus);
        setImageViewToBonusView(imgBonus);


        //Tag for images
        imgCircle.setTag("circle");
        imgSquare.setTag("square");
        imgCircleSlot.setTag("circle");
        imgSquareSlot.setTag("square");

        //Event for click event
        btnClose.setOnClickListener(view ->
        {
            getActivity().finish();
        });

        //This is an little test
        btnReboot.setOnClickListener(view ->
        {
            Toast.makeText(getContext(), "Working...", Toast.LENGTH_SHORT).show();
        });

        //Song for figures
        imgCircle.setOnClickListener(view -> playFigureSong(FIGURES.CIRCLE));
        imgSquare.setOnClickListener(view -> playFigureSong(FIGURES.SQUARE));

        //DragEvent for Figures
        DragImplementation drag = new DragImplementation();
        drag.setDragSuccessfulListener(this);
        imgCircleSlot.setOnDragListener(drag);
        imgSquareSlot.setOnDragListener(drag);

        imgCircle.setOnLongClickListener(view ->{ DragImplementation.startDrag(view); return true;});
        imgSquare.setOnLongClickListener(view -> { DragImplementation.startDrag(view); return true;});
    }

    @Override
    public void terminatedLevel(int totalShapes)
    {
        //Finish and get Time of Chronometer
        String time = finishChronometerViwLevel();
        Toast.makeText(getContext(), "Nivel terminado con: " + time,  Toast.LENGTH_LONG).show();

        StatisticsLevelDialog statisticsLevelDialog =  new StatisticsLevelDialog();
        statisticsLevelDialog.show(getActivity().getSupportFragmentManager(), "fragment_level_1");
    }
}
