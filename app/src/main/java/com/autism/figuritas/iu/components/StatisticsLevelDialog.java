package com.autism.figuritas.iu.components;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.Navigation;
import com.autism.figuritas.R;
import com.autism.figuritas.persistence.preferences.ConstantPreferences;

/**
 * Dialog to show Statics of level to end
 */
public class StatisticsLevelDialog extends DialogFragment
{
    public StatisticsLevelDialog()
    {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getLayoutInflater().inflate(R.layout.statistics_level_dialog, null);

        if(getArguments() != null) {
            TextView txtTime = view.findViewById(R.id.txtTimerDialog);
            TextView txtBonus = view.findViewById(R.id.txtBonusDialog);
            TextView txtShapes = view.findViewById(R.id.txtShapesCountDialog);
            Button btnNext = view.findViewById(R.id.btnNextLevelDialog);

            txtTime.setText(getString(R.string.tiempo_total) + ": " + getArguments().getString("time", "00:00"));
            txtBonus.setText(getString(R.string.bonus_obtenido) + ": " + String.valueOf(getArguments().getInt("bonus", 0)));
            txtShapes.setText(getString(R.string.total_figuras) + ": " + String.valueOf(getArguments().getInt("shape_count", 0)));

            applyColor(view);
            //Next Level fragment
            if (getArguments().getInt("next_level", 0) != 0)
            {
                btnNext.setOnClickListener(view1 ->
                {
                    //Close Dialog
                    dismiss();

                    if(getArguments().getInt("next_level", 0) == -1)
                    {
                        getActivity().finish();
                        return;
                    }

                    Navigation.findNavController(getActivity(), R.id.fragmentHostLevels).
                            navigate(getArguments().getInt("next_level", 0));
                });
            }
        }

        builder.setView(view);

        //Create Dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);

        // Set alertDialog "not focusable" so nav bar still hiding:
        alertDialog.getWindow().
                setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        // Set full-screen mode (immersive sticky):
        alertDialog.getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        return alertDialog;
    }

    private void applyColor(View view) {
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

        Button btnNext = view.findViewById(R.id.btnNextLevelDialog);
        btnNext.setBackground(drawable);

        TextView txtTitle = view.findViewById(R.id.txtTitleDialog);
        txtTitle.setBackgroundResource(title_background);
    }
}
