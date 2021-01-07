package com.autism.figuritas.iu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.autism.figuritas.R;
import com.autism.figuritas.iu.session.RegisterActivity;
import com.autism.figuritas.iu.session.SessionActivity;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        hideSystemUI();
    }


    /**
     * Init User Session Activity for login
     * @param view
     */
    public void initSesion(View view)
    {
        Intent intent = new Intent(this, SessionActivity.class);
        startActivity(intent);
    }

    /**
     * Init Register Activity to register a new user
     * @param view
     */
    public void initRegisterUser(View view)
    {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
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