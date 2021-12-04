package com.autism.figuritas.iu.session;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
}