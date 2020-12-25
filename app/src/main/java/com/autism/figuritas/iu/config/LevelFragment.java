package com.autism.figuritas.iu.config;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.autism.figuritas.R;

/**
 * Fragmento para la configuración del nivel
 */
public class LevelFragment extends Fragment
{
    public LevelFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_level, container, false);
    }

}