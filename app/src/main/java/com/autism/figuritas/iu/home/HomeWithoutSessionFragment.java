package com.autism.figuritas.iu.home;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.BlendMode;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.autism.figuritas.R;
import com.autism.figuritas.iu.config.InitConfigActivity;
import com.autism.figuritas.iu.session.RegisterActivity;
import com.autism.figuritas.iu.session.SessionActivity;
import com.autism.figuritas.persistence.preferences.ConstantPreferences;

public class HomeWithoutSessionFragment extends Fragment {
    private final int REQUEST_CODE_ENTER_SESSION = 20;
    private final int REQUEST_CODE_INIT_CONFIG = 40;

    public HomeWithoutSessionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_without_sesion, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        //References
        Button btnInitSession = getActivity().findViewById(R.id.btnIniciarSesionFragment);
        Button btnRegister = getActivity().findViewById(R.id.btnRegistrateFragment);

        //Events click
        btnRegister.setOnClickListener(v ->
                startActivity(new Intent(getContext(), RegisterActivity.class)));

        btnInitSession.setOnClickListener(v ->
                {
                    Intent intent = new Intent();
                    intent.setClass(getContext(), SessionActivity.class);

                    startActivityForResult(intent, REQUEST_CODE_ENTER_SESSION);
                }
        );

        applyColor();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Check for init session
        if (requestCode == REQUEST_CODE_ENTER_SESSION) {
            if (resultCode == Activity.RESULT_OK) {

                //Check if config is complete
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                boolean configComplete = sharedPreferences.getBoolean("config_complete", false);

                if (configComplete)
                    Navigation.findNavController(getView()).navigate(R.id.action_homeWithoutSessionFragment_to_homeSessionFragment);
                else
                    startActivityForResult(new Intent(getContext(), InitConfigActivity.class), REQUEST_CODE_INIT_CONFIG);
            } else if (resultCode == Activity.RESULT_CANCELED) {

                Log.d("TAG", "result canceled on HomeWithoutSessionFragment");
            }
        }

        //Check for initConfig
        if (requestCode == REQUEST_CODE_INIT_CONFIG) {
            Log.d("Config", "REQUEST_CODE_INIT_CONFIG");

            if (resultCode == Activity.RESULT_OK) {
                Navigation.findNavController(getView()).navigate(R.id.action_homeWithoutSessionFragment_to_homeSessionFragment);
            }
        }

    }

    private void applyColor() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final String colorHex = sharedPreferences.getString(ConstantPreferences.CURRENT_COLOR, "#FFFFFF");

        int background_color = 0;
        int title_background = 0;
        int button_background = 0;

        switch (colorHex) {
            case "#FFFFC107":
                background_color = R.color.AMBER_BACKGROUND;
                title_background = R.color.AMBER;
                button_background = R.color.AMBER_ANALOGO;
                break;

            case "#FF00BCD4":
                background_color = R.color.CYAN_BACKGROUND;
                title_background = R.color.CYAN;
                button_background = R.color.CYAN_ANALOGO;
                break;

            case "#FFFF4081":
                background_color = R.color.PINK_BACKGROUND;
                title_background = R.color.PINK;
                button_background = R.color.PINK_ANALOGO;
                break;

            case "#FFFF8000":
                background_color = R.color.ORANGE_BACKGROUND;
                title_background = R.color.ORANGE;
                button_background = R.color.ORANGE_ANALOGO;
                break;

            case "#FFF44336":
                background_color = R.color.RED_BACKGROUND;
                title_background = R.color.RED;
                button_background = R.color.RED_ANALOGO;
                break;

            case "#FFACAF50":
                background_color = R.color.GREEN_BACKGROUND;
                title_background = R.color.GREEN;
                button_background = R.color.GREEN_ANALOGO;
                break;

            case "#FF03A9F4":
                background_color = R.color.LIGHT_BLUE_BACKGROUND;
                title_background = R.color.LIGHT_BLUE;
                button_background = R.color.LIGHT_BLUE_ANALOGO;
                break;

            case "#FFFFFFFF":
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

        //Apply colors to components
        ViewGroup background = getActivity().findViewById(R.id.background);
        background.setBackgroundResource(background_color);

        //Button style
        Drawable drawable = ResourcesCompat.getDrawable(getResources(),
                R.drawable.background_button, getContext().getTheme());
        drawable.setTint(getActivity().getColor(title_background));

        Button btnInitSession = getActivity().findViewById(R.id.btnIniciarSesionFragment);
        Button btnRegister = getActivity().findViewById(R.id.btnRegistrateFragment);
        btnRegister.setBackground(drawable);
        btnInitSession.setBackground(drawable);

    }
}