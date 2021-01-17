package com.autism.figuritas.iu.levels.level_2;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.autism.figuritas.R;

public class Level_2 extends Fragment
{

    public Level_2()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_delete, container, false);
    }
}