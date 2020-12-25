package com.autism.figuritas.iu.config;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.autism.figuritas.R;

/**
 * Fragmento de configuración del sonido
 */
public class SoundFragment extends Fragment
{

    public SoundFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_sound, container, false);
    }
}