package com.autism.figuritas.iu.levels.level_4;

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

public class Level_4 extends AbstractLevel
{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_level_4, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        //Very important this
        View fragmentCurrent = getView();

        ImageView imgCircle = fragmentCurrent.findViewById(R.id.imgCircle);
        ImageView imgSquare = fragmentCurrent.findViewById(R.id.imgSquare);
        ImageView imgTriangle = fragmentCurrent.findViewById(R.id.imgTriangle);
        ImageView imgPentagon = fragmentCurrent.findViewById(R.id.imgPentagon);
        ImageView imgStar = fragmentCurrent.findViewById(R.id.imgStar);
        ImageView imgCircleSlot = fragmentCurrent.findViewById(R.id.imgCircleSlot);
        ImageView imgSquareSlot = fragmentCurrent.findViewById(R.id.imgSquareSlot);
        ImageView imgTriangleSlot = fragmentCurrent.findViewById(R.id.imgTriangleSlot);
        ImageView imgPentagonSlot = fragmentCurrent.findViewById(R.id.imgPentagonSlot);
        ImageView imgStarSlot = fragmentCurrent.findViewById(R.id.imgStarSlot);
        ImageButton btnClose = fragmentCurrent.findViewById(R.id.btnExit);
        ImageButton btnReboot = fragmentCurrent.findViewById(R.id.btnReboot);
        ImageView imgTimer = fragmentCurrent.findViewById(R.id.imgTimer);
        ImageView imgBonus = fragmentCurrent.findViewById(R.id.imgBonus);
        TextView txtTimer = fragmentCurrent.findViewById(R.id.txtTimer);
        TextView txtBonus = fragmentCurrent.findViewById(R.id.txtBonus);

        //Set number shape count
        setShapeCount(5);

        //Set textView for Chronometer
        setTextViewToChronometerView(txtTimer);

        //Set components in BonusView
        setTextViewToBonusView(txtBonus);
        setImageViewToBonusView(imgBonus);


        //Tag for images
        imgCircle.setTag("circle");
        imgSquare.setTag("square");
        imgTriangle.setTag("triangle");
        imgPentagon.setTag("pentagon");
        imgStar.setTag("star");
        imgCircleSlot.setTag("circle");
        imgSquareSlot.setTag("square");
        imgTriangleSlot.setTag("triangle");
        imgPentagonSlot.setTag("pentagon");
        imgStarSlot.setTag("star");

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
        imgCircle.setOnClickListener(view -> playFigureSong(AbstractLevel.FIGURES.CIRCLE));
        imgSquare.setOnClickListener(view -> playFigureSong(AbstractLevel.FIGURES.SQUARE));
        imgTriangle.setOnClickListener(view -> playFigureSong(FIGURES.TRIANGLE));
        imgPentagon.setOnClickListener(view -> playFigureSong(FIGURES.PENTAGON));
        imgStar.setOnClickListener(view -> playFigureSong(FIGURES.STAR));

        //DragEvent for Figures
        DragImplementation drag = new DragImplementation();
        drag.setDragSuccessfulListener(this);
        imgCircleSlot.setOnDragListener(drag);
        imgSquareSlot.setOnDragListener(drag);
        imgTriangleSlot.setOnDragListener(drag);
        imgPentagonSlot.setOnDragListener(drag);
        imgStarSlot.setOnDragListener(drag);

        imgCircle.setOnLongClickListener(view ->{ DragImplementation.startDrag(view); return true;});
        imgSquare.setOnLongClickListener(view -> { DragImplementation.startDrag(view); return true;});
        imgTriangle.setOnLongClickListener(view -> { DragImplementation.startDrag(view); return true;});
        imgPentagon.setOnLongClickListener(view -> { DragImplementation.startDrag(view); return true;});
        imgStar.setOnLongClickListener(view -> { DragImplementation.startDrag(view); return true;});

    }

    @Override
    public void terminatedLevel(int totalShapes)
    {
        //Stop Time of ChronometerView
        pauseChronometerView();

        Bundle bundleData = getDataFinishLevel();
        bundleData.putInt("next_level", -1);

        StatisticsLevelDialog statisticsLevelDialog =  new StatisticsLevelDialog();
        statisticsLevelDialog.setArguments(bundleData);
        statisticsLevelDialog.show(getActivity().getSupportFragmentManager(), "fragment_level_3");
    }
}
