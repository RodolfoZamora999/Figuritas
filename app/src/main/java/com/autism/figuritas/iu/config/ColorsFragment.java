package com.autism.figuritas.iu.config;

import android.animation.TimeInterpolator;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.autism.figuritas.R;
import com.autism.figuritas.persistence.database.Configuracion;

/**
 * Fragmento para la selección de los colores de la aplicación
 */
public class ColorsFragment extends Fragment
{
    private String color;

    private TextView txtTitle;
    private ViewGroup viewGroup;

    private ImageView imgColor1;
    private ImageView imgColor2;
    private ImageView imgColor3;
    private ImageView imgColor4;
    private ImageView imgColor5;
    private ImageView imgColor6;
    private ImageView imgColor7;
    private ImageView imgColor8;

    private Button btnNext;

    private View lastColor;

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

        //References
        this.imgColor1 = getActivity().findViewById(R.id.color1);
        this.imgColor2 = getActivity().findViewById(R.id.color2);
        this.imgColor3 = getActivity().findViewById(R.id.color3);
        this.imgColor4 = getActivity().findViewById(R.id.color4);
        this.imgColor5 = getActivity().findViewById(R.id.color5);
        this.imgColor6 = getActivity().findViewById(R.id.color6);
        this.imgColor7 = getActivity().findViewById(R.id.color7);
        this.imgColor8 = getActivity().findViewById(R.id.color8);
        this.btnNext = getActivity().findViewById(R.id.btnNextColor);
        this.txtTitle = getActivity().findViewById(R.id.txtTitleColor);
        this.viewGroup = getActivity().findViewById(R.id.viewGroupColor);

        //Insert tag colors
        this.imgColor1.setTag("#FFC107");
        this.imgColor2.setTag("#FF8000");
        this.imgColor3.setTag("#FF4081");
        this.imgColor4.setTag("#FFFFFF");
        this.imgColor5.setTag("#ACAF50");
        this.imgColor6.setTag("#00BCD4");
        this.imgColor7.setTag("#F44336");
        this.imgColor8.setTag("#03A9F4");

        //Events for click
        this.btnNext.setOnClickListener(view -> nextFragment(view));
        this.imgColor1.setOnClickListener(view -> springAnimation(view));
        this.imgColor2.setOnClickListener(view -> springAnimation(view));
        this.imgColor3.setOnClickListener(view -> springAnimation(view));
        this.imgColor4.setOnClickListener(view -> springAnimation(view));
        this.imgColor5.setOnClickListener(view -> springAnimation(view));
        this.imgColor6.setOnClickListener(view -> springAnimation(view));
        this.imgColor7.setOnClickListener(view -> springAnimation(view));
        this.imgColor8.setOnClickListener(view -> springAnimation(view));
    }

    @Override
    public void onResume()
    {
        super.onResume();

        //Animation for color (Alpha)
        TimeInterpolator interpolator = new AccelerateDecelerateInterpolator();

        this.imgColor1.animate().alpha(1).setStartDelay(30).setInterpolator(interpolator).start();
        this.imgColor2.animate().alpha(1).setStartDelay(60).setInterpolator(interpolator).start();
        this.imgColor3.animate().alpha(1).setStartDelay(90).setInterpolator(interpolator).start();
        this.imgColor7.animate().alpha(1).setStartDelay(120).setInterpolator(interpolator).start();
        this.imgColor4.animate().alpha(1).setStartDelay(150).setInterpolator(interpolator).start();
        this.imgColor5.animate().alpha(1).setStartDelay(180).setInterpolator(interpolator).start();
        this.imgColor6.animate().alpha(1).setStartDelay(210).setInterpolator(interpolator).start();
        this.imgColor8.animate().alpha(1).setStartDelay(240).setInterpolator(interpolator).start();
    }

    private void springAnimation(View view)
    {

        SpringAnimation springAnimationX = new SpringAnimation(view, DynamicAnimation.SCALE_X, 0.6f);
        SpringAnimation springAnimationY = new SpringAnimation(view, DynamicAnimation.SCALE_Y, 0.6f);

        springAnimationX.start();
        springAnimationY.start();

        if(lastColor != null)
        {
            SpringAnimation springAnimationLastX = new SpringAnimation(lastColor, DynamicAnimation.SCALE_X, 1f);
            SpringAnimation springAnimationLastY = new SpringAnimation(lastColor, DynamicAnimation.SCALE_Y, 1f);


            springAnimationLastX.start();
            springAnimationLastY.start();
        }

        this.lastColor = view;

        selectColor(view);
    }

    /**
     * Method for change color IU
     * @param view
     */
    private void selectColor(View view)
    {
        int background_color = 0;
        int title_background = 0;
        int button_background = 0;

        switch (view.getTag().toString())
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
        btnNext.setBackgroundResource(button_background);

        color = view.getTag().toString();
    }

    /**
     * Método para pasar al siguiente fragmento de la actividad. Fragment Sound
     * @param view
     */
    private void nextFragment(View view)
    {
        Bundle bundleConfig = new Bundle();
        bundleConfig.putString("color", color);

        Navigation.findNavController(view).navigate(R.id.action_colorsFragment_to_soundFragment, bundleConfig);
    }
}