package com.autism.figuritas.iu;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.autism.figuritas.R;
import com.autism.figuritas.iu.config.InitConfigActivity;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //Ocultar barra
        hideSystemUI();
    }

    /**
     * Método para iniciar sesión del usuario
     * @param view
     */
    public void initSesion(View view)
    {
        //Todo: Configurar esta parte
        startActivity(new Intent(this, InitConfigActivity.class));
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    /**
     * Hacer "fullScreen" la aplicación.
     */
    private void hideSystemUI()
    {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}