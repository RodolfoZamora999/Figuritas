package com.autism.figuritas.iu.components;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.Navigation;
import com.autism.figuritas.R;

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

            //Next Level fragment
            if (getArguments().getInt("next_level", 0) != 0)
            {
                btnNext.setOnClickListener(view1 ->
                {
                    //Close Dialog
                    dismiss();

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
}
