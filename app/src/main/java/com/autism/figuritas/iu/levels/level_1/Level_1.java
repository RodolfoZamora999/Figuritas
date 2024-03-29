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
        return inflater.inflate(R.layout.fragment_level_1, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        View fragmentCurrent = getView();

        ImageView imgCircle = fragmentCurrent.findViewById(R.id.imgCircle);
        ImageView imgSquare = fragmentCurrent.findViewById(R.id.imgSquare);
        ImageView imgCircleSlot = fragmentCurrent.findViewById(R.id.imgCircleSlot);
        ImageView imgSquareSlot = fragmentCurrent.findViewById(R.id.imgSquareSlot);
        ImageButton btnClose = fragmentCurrent.findViewById(R.id.btnExit);
        ImageButton btnReboot = fragmentCurrent.findViewById(R.id.btnReboot);
        ImageView imgTimer = fragmentCurrent.findViewById(R.id.imgTimer);
        ImageView imgBonus = fragmentCurrent.findViewById(R.id.imgBonus);
        TextView txtTimer = fragmentCurrent.findViewById(R.id.txtTimer);
        TextView txtBonus = fragmentCurrent.findViewById(R.id.txtBonus);

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
        //Stop Time of ChronometerView
        pauseChronometerView();

        Bundle bundleData = getDataFinishLevel();
        bundleData.putInt("next_level", R.id.action_level_1_to_level_2);

        StatisticsLevelDialog statisticsLevelDialog =  new StatisticsLevelDialog();
        statisticsLevelDialog.setArguments(bundleData);
        statisticsLevelDialog.show(getActivity().getSupportFragmentManager(), "fragment_level_1");
    }
}
