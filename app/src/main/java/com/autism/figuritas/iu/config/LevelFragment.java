package com.autism.figuritas.iu.config;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.fragment.app.Fragment;

import com.autism.figuritas.MyDatabaseApplication;
import com.autism.figuritas.R;
import com.autism.figuritas.persistence.database.Configuration;
import com.autism.figuritas.persistence.database.DataBase;
import com.autism.figuritas.persistence.preferences.ConstantPreferences;

/**
 * Fragmento para la configuración del nivel
 */
public class LevelFragment extends Fragment
{
    private int level;

    private Bundle bundleConfig;

    private View lastView;

    private ViewGroup viewGroup;

    private Button btnFacil;
    private Button btnNormal;
    private Button btnDificil;
    private Button btnSiguiente;

    private TextView txtTitle;

    public LevelFragment()
    {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Check arguments
        if(getArguments() != null)
            bundleConfig = getArguments();
        else
            bundleConfig = new Bundle();
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

        //References
        this.btnFacil = getActivity().findViewById(R.id.btnFacil);
        this.btnNormal = getActivity().findViewById(R.id.btnNormal);
        this.btnDificil = getActivity().findViewById(R.id.btnDificil);
        this.btnSiguiente = getActivity().findViewById(R.id.btnNextLevel);
        this.viewGroup = getActivity().findViewById(R.id.viewGroupLevel);
        this.txtTitle = getActivity().findViewById(R.id.txtTitleLevel);

        //Event for click
        this.btnFacil.setOnClickListener(view -> springAnimation(view, 0));
        this.btnNormal.setOnClickListener(view -> springAnimation(view, 1));
        this.btnDificil.setOnClickListener(view -> springAnimation(view, 2));
        this.btnSiguiente.setOnClickListener(view -> saveConfig());

        String color = bundleConfig.getString("color");
        changeColors(color);
    }

    private void springAnimation(View view, int level)
    {

        SpringAnimation springAnimationX = new SpringAnimation(view, DynamicAnimation.SCALE_X, 0.6f);
        SpringAnimation springAnimationY = new SpringAnimation(view, DynamicAnimation.SCALE_Y, 0.6f);

        springAnimationX.start();
        springAnimationY.start();

        if(lastView != null)
        {
            SpringAnimation springAnimationLastX = new SpringAnimation(lastView, DynamicAnimation.SCALE_X, 1f);
            SpringAnimation springAnimationLastY = new SpringAnimation(lastView, DynamicAnimation.SCALE_Y, 1f);


            springAnimationLastX.start();
            springAnimationLastY.start();
        }

        this.lastView = view;
        this.level = level;
    }


    /**
     * Method for change colors IU
     * @param colorHex
     */
    private void changeColors(String colorHex)
    {
        int background_color = 0;
        int title_background = 0;
        int button_background = 0;

        switch (colorHex)
        {
            case "#FFC107":
                background_color = R.color.AMBER_BACKGROUND;
                title_background = R.color.AMBER;
                button_background = R.color.AMBER_ANALOGO;
                break;

            case "#00BCD4":
                background_color = R.color.CYAN_BACKGROUND;
                title_background = R.color.CYAN;
                button_background = R.color.CYAN_ANALOGO;
                break;

            case "#FF4081":
                background_color = R.color.PINK_BACKGROUND;
                title_background = R.color.PINK;
                button_background = R.color.PINK_ANALOGO;
                break;

            case "#FF8000":
                background_color = R.color.ORANGE_BACKGROUND;
                title_background = R.color.ORANGE;
                button_background = R.color.ORANGE_ANALOGO;
                break;

            case "#F44336":
                background_color = R.color.RED_BACKGROUND;
                title_background = R.color.RED;
                button_background = R.color.RED_ANALOGO;
                break;

            case "#ACAF50":
                background_color = R.color.GREEN_BACKGROUND;
                title_background = R.color.GREEN;
                button_background = R.color.GREEN_ANALOGO;
                break;

            case "#03A9F4":
                background_color = R.color.LIGHT_BLUE_BACKGROUND;
                title_background = R.color.LIGHT_BLUE;
                button_background = R.color.LIGHT_BLUE_ANALOGO;
                break;

            case "#FFFFFF":
                background_color = R.color.WHITE_BACKGROUND;
                title_background = R.color.WHITE_COMPLEMENTATION;
                button_background = R.color.WHITE_ANALOGO;
                break;

            default:
                background_color = R.color.design_default_color_background;
                title_background = R.color.design_default_color_background;
                button_background = R.color.design_default_color_background;
                break;
        }

        txtTitle.setBackgroundResource(title_background);
        viewGroup.setBackgroundResource(background_color);
        btnSiguiente.setBackgroundResource(button_background);
    }

    //Save config in Database
    private void saveConfig()
    {
        if(bundleConfig == null)
            return;

        //Recuperate data of user
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        long idConfig = sharedPreferences.getLong(ConstantPreferences.CURRENT_USER, 0);
        String color = bundleConfig.getString("color", "#FFFFFF");
        boolean music = bundleConfig.getBoolean("music", false);
        boolean sound = bundleConfig.getBoolean("sounds", false);

        //Create config object
        final Configuration configuration = new Configuration();
        configuration.id_config = idConfig;
        configuration.color = color;
        configuration.musica = music;
        configuration.sonido = sound;
        configuration.dificultad = (byte)level;

        //Complete config
        sharedPreferences.edit().putBoolean(ConstantPreferences.CONFIG_COMPLETE, true).apply();

        //Update configuration on database
        DataBase dataBase = ((MyDatabaseApplication)getActivity().getApplication()).getDataBase();
        dataBase.getQueryExecutor().execute(()->
        {
            //Insert first config for user
            dataBase.getDAO().updateConfig(configuration);

            getActivity().runOnUiThread(()-> {
                Toast toast = Toast.makeText(getContext(), R.string.configuracion_guardada, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 120, 100);
                toast.show();
            });
        });

        //Close Config Activity
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

}