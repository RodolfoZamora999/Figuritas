package com.autism.figuritas.iu.home;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.autism.figuritas.R;
import com.autism.figuritas.iu.config.ConfigActivity;
import com.autism.figuritas.iu.levels.LevelActivity;
import com.autism.figuritas.persistence.preferences.ConstantPreferences;


public class HomeSessionFragment extends Fragment {
    private Button btnInit;
    private Button btnConfig;

    public HomeSessionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_sesion, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        //References
        this.btnInit = getActivity().findViewById(R.id.btnIniciarGame);
        this.btnConfig = getActivity().findViewById(R.id.btnConfiguracionFH);

        //Click envent
        this.btnInit.setOnClickListener(view -> initLevel(view));
        this.btnConfig.setOnClickListener(view -> initConfig(view));

        applyColor();
    }

    /**
     * Methor for init level or init config
     *
     * @param view
     */
    private void initLevel(View view) {
        Intent intent = new Intent();
        intent.setClass(getContext(), LevelActivity.class);

        startActivity(intent);
    }

    private void initConfig(View view) {
        Intent intent = new Intent();
        intent.setClass(getContext(), ConfigActivity.class);

        startActivity(intent);
    }

    private void applyColor() {
        // SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String colorHex = PreferenceManager.getDefaultSharedPreferences(getContext()).
                getString(ConstantPreferences.CURRENT_COLOR, "#FFFFFFFF");

        int background_color = 0;
        int title_background = 0;
        int button_background = 0;

        switch (colorHex) {
            case "#FFFFC107":
                Log.d("color", "Amber section now!");
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
                Log.d("color", "Use default color");
                background_color = R.color.design_default_color_background;
                title_background = R.color.design_default_color_background;
                button_background = R.color.design_default_color_background;
                break;
        }

        //Apply colors to components
        Drawable drawable = getActivity().getDrawable(R.drawable.button_background_circle);
        drawable.setTint(getActivity().getColor(title_background));

        Button btnInit = getActivity().findViewById(R.id.btnIniciarGame);
        Button btnConfig = getActivity().findViewById(R.id.btnConfiguracionFH);

        btnInit.setBackground(drawable);
        btnConfig.setBackground(drawable);
    }
}