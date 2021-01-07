package com.autism.figuritas.iu.session;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.autism.figuritas.R;
import com.autism.figuritas.iu.config.InitConfigActivity;

public class SessionActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sesion);

        hideSystemUI();
    }

    /**
     * Init User session
     * @param view
     */
    public void enterSesion(View view)
    {
        //Todo: Ver que onda con esto, el shared prefeferenfces para cada usuario
        Intent intent = new Intent(this, InitConfigActivity.class);
        startActivity(intent);

         /*
        //Check config if is complete
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean config_complete = sharedPreferences.getBoolean("config_complete",false);

        Intent intent = new Intent();

        if(config_complete)
            intent.setClass(this, LevelActivity.class);
        else
            intent.setClass(this, InitConfigActivity.class);

        startActivity(intent);*/

    }

    /**
     * Method for show or hide password text
     * @param view
     */
    public void showPassword(View view)
    {
        EditText txtPassword = findViewById(R.id.editPassword);

        TransformationMethod transformationMethod = txtPassword.getTransformationMethod();

        //Show or hide password
        if(transformationMethod != null)
            txtPassword.setTransformationMethod(null);
        else
            txtPassword.setTransformationMethod(new PasswordTransformationMethod());
    }

    /**
     * Do fullscreen activity
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