package com.autism.figuritas.iu.config;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

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

    @Override
    public void onStart()
    {
        super.onStart();

        getActivity().findViewById(R.id.fragmentSound).setOnClickListener(v -> nextFragment(v));
    }

    /**
     * Método para pasar al siguiente fragmento de la actividad. Fragment level
     * @param view
     */
    private void nextFragment(View view)
    {
        Navigation.findNavController(view).navigate(R.id.action_soundFragment_to_levelFragment);
    }
}