package com.autism.figuritas.iu.config;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.autism.figuritas.R;

/**
 * Fragmento para la selección de los colores de la aplicación
 */
public class ColorsFragment extends Fragment
{
    public ColorsFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_colors, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        getActivity().findViewById(R.id.fragmentColor).setOnClickListener(v -> nextFragment(v));
    }

    /**
     * Método para pasar al siguiente fragmento de la actividad. Fragment Sound
     * @param view
     */
    private void nextFragment(View view)
    {
        Navigation.findNavController(view).navigate(R.id.action_colorsFragment_to_soundFragment);
    }
}