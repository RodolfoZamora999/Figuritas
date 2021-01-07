package com.autism.figuritas.iu.config;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.autism.figuritas.R;
import com.autism.figuritas.iu.levels.LevelActivity;

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

    @Override
    public void onStart()
    {
        super.onStart();

        getActivity().findViewById(R.id.fragmentLevel).setOnClickListener(v -> nextFragment(v));
    }

    /**
     * Method to invoke Level Activity
     * @param view
     */
    private void nextFragment(View view)
    {

        //Todo: Modificación pendiente
        //Config complete
        /*SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean status = preferences.edit().putBoolean("config", true).commit();

        if(status)
        {
            Intent intent = new Intent(getContext(), LevelActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getContext(), "Error de almacenamiento...", Toast.LENGTH_LONG).show();
        }
         */

        startActivity(new Intent(getContext(), LevelActivity.class));
    }


}