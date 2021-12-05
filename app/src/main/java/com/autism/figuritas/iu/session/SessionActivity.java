package com.autism.figuritas.iu.session;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.autism.figuritas.MyDatabaseApplication;
import com.autism.figuritas.R;
import com.autism.figuritas.persistence.database.User;
import com.autism.figuritas.persistence.preferences.ConstantPreferences;

public class SessionActivity extends AppCompatActivity {
    private TextView txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sesion);

        Button enterSession = findViewById(R.id.btnIniciarSesion);
        enterSession.setOnClickListener(view -> logIn());

        this.txtMessage = findViewById(R.id.txtMessage);

        applyColor();
    }

    @Override
    protected void onResume() {
        super.onResume();

        hideSystemUI();
    }

    /**
     * Login session on App
     */
    private void logIn() {
        final String email = ((EditText) findViewById(R.id.editEmailSession)).getText().toString(); //Todo: Check input values
        final String password = ((EditText) findViewById(R.id.editPassword)).getText().toString();

        final MyDatabaseApplication application = (MyDatabaseApplication) getApplication();
        application.getDataBase().getQueryExecutor().execute(() -> {
            User user = application.getDataBase().getDAO().getUserByEmail(email);
            if (user == null) {
                runOnUiThread(() -> {
                    printMessageError("El usuario no existe. Registrate para poder iniciar sesión.");
                });
            }
            else {
                Log.d("Login", "User exist: " + user.nombre);
                if (user.contrasena.equals(password)) {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                    preferences.edit().putLong(ConstantPreferences.CURRENT_USER, user.id_usuario).commit();
                    setResult(RESULT_OK);
                    finish();
                }
                else {
                    runOnUiThread(() -> {
                        printMessageError("Contraseña incorrecta. Vuelva a intentarlo");
                    });
                }
            }
        });
    }

    /**
     * Method for show or hide password text
     * @param view
     */
    public void showPassword(View view) {
        EditText txtPassword = findViewById(R.id.editPassword);

        TransformationMethod transformationMethod = txtPassword.getTransformationMethod();

        //Show or hide password
        if (transformationMethod != null)
            txtPassword.setTransformationMethod(null);
        else
            txtPassword.setTransformationMethod(new PasswordTransformationMethod());
    }

    private void printMessageError(String message) {
        this.txtMessage.setText(message);
        this.txtMessage.setTextColor(Color.rgb(255, 0, 0));
    }

    /**
     * Do fullscreen activity
     */
    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void applyColor() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String colorHex = sharedPreferences.getString(ConstantPreferences.CURRENT_COLOR, "#FFFFFF");


        Log.d("color test", colorHex);

        Log.d("color test", "Result color: " + "#" + Integer.toHexString(getResources().getColor(R.color.AMBER)));


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
        ViewGroup background = findViewById(R.id.background);
        background.setBackgroundResource(background_color);

        Button btnInitSession = findViewById(R.id.btnIniciarSesion);
        TextView txtTitle = findViewById(R.id.txtTitleActivitySesion);

        Drawable drawable = ResourcesCompat.getDrawable(getResources(),
                R.drawable.background_button, getTheme());
        drawable.setTint(getColor(title_background));

        btnInitSession.setBackground(drawable);
        btnInitSession.setTextColor(Color.rgb(255, 255, 255));
       // txtTitle.setTextColor(getColor(title_background));

        ImageView imgBackgroundTitle = findViewById(R.id.imgBackgroundTitle);
        imgBackgroundTitle.setBackgroundColor(getColor(title_background));

    }
}