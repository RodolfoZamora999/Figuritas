package com.autism.figuritas.iu.home;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.autism.figuritas.R;
import com.autism.figuritas.iu.config.ConfigActivity;
import com.autism.figuritas.iu.levels.LevelActivity;


public class HomeSessionFragment extends Fragment
{
    private Button btnInit;
    private Button btnConfig;

    public HomeSessionFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_home_sesion, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        //References
        this.btnInit = getActivity().findViewById(R.id.btnIniciarGame);
        this.btnConfig = getActivity().findViewById(R.id.btnConfiguracionFH);

        //Click envent
        this.btnInit.setOnClickListener(view -> initLevel(view));
        this.btnConfig.setOnClickListener(view -> initConfig(view));
    }

    /**
     * Methor for init level or init config
     * @param view
     */
    private void initLevel(View view)
    {
        Intent intent = new Intent();
        intent.setClass(getContext(), LevelActivity.class);

        startActivity(intent);
    }

    private void initConfig(View view)
    {
        Intent intent = new Intent();
        intent.setClass(getContext(), ConfigActivity.class);

        startActivity(intent);
    }
}