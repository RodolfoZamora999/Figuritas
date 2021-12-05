package com.autism.figuritas.iu.session;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.autism.figuritas.MyDatabaseApplication;
import com.autism.figuritas.R;
import com.autism.figuritas.persistence.database.Bonus;
import com.autism.figuritas.persistence.database.Configuration;
import com.autism.figuritas.persistence.database.History;
import com.autism.figuritas.persistence.database.User;
import com.autism.figuritas.persistence.preferences.ConstantPreferences;

import java.util.Calendar;

/**
 * Class for register a new user
 */
public class RegisterActivity extends AppCompatActivity {
    private Button btnRegister;
    private Button btnAbort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //References
        this.btnRegister = findViewById(R.id.btnRegistrar);
        this.btnAbort = findViewById(R.id.btnDescartar);

        //Events
        this.btnRegister.setOnClickListener(view -> {
            registerUser();
            onBackPressed();
        });

        this.btnAbort.setOnClickListener(view -> abortRegister());

        applyColor();
    }

    @Override
    protected void onResume() {
        super.onResume();

        hideSystemUI();
    }

    private void registerUser() {
        //Generate id for user with time now
        Calendar calendar = Calendar.getInstance();

        int miliSecond = calendar.get(Calendar.MILLISECOND);
        int second = calendar.get(Calendar.SECOND);
        int minute = calendar.get(Calendar.MINUTE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        StringBuilder id_generate = new StringBuilder();
        id_generate.append(miliSecond);
        id_generate.append(second);
        id_generate.append(minute);
        id_generate.append(hour);
        id_generate.append(day);
        id_generate.append(month);
        id_generate.append(year);

        String id_result = id_generate.toString();
        long id = Long.parseLong(id_result);

        //Create config object
        final Configuration configuration = new Configuration(); //Duplicate key
        configuration.id_config = id;
        configuration.dificultad = 1;
        configuration.musica = true;
        configuration.sonido = true;
        configuration.color = "FFFF";

        //Create history object
        final History history = new History();
        history.id_historial = id;
        history.porcentaje = 0;
        history.descripcion = "Empty";

        //Create bonus object
        final Bonus bonus = new Bonus();
        bonus.id_bonificacion = id;
        bonus.bonificacion_acumulada = 0;

        //Todo: Verificar los datos de entrada
        //Create user object
        String name = ((EditText) findViewById(R.id.editNombre)).getText().toString();
        String apell_p = ((EditText) findViewById(R.id.editApellidoP)).getText().toString();
        String apell_m = ((EditText) findViewById(R.id.editApellidoM)).getText().toString();
        byte age = Byte.parseByte(((EditText) findViewById(R.id.editEdad)).getText().toString());
        String email = ((EditText) findViewById(R.id.editEmail)).getText().toString();
        String password = ((EditText) findViewById(R.id.editContrasena)).getText().toString();

        final User user = new User();
        user.id_usuario = id;
        user.nombre = name;
        user.apellido_paterno = apell_p;
        user.apellido_materno = apell_m;
        user.edad = age;
        user.email = email;
        user.contrasena = password;
        user.id_config = configuration.id_config;
        user.id_historial = history.id_historial;
        user.id_bonificacion = bonus.id_bonificacion;

        //Insert all objects in database
        final MyDatabaseApplication application = (MyDatabaseApplication) getApplication();

        application.getDataBase().getQueryExecutor().execute(() ->
        {
            application.getDataBase().getDAO().insertConfig(configuration);
            application.getDataBase().getDAO().insertHistory(history);
            application.getDataBase().getDAO().insertBonus(bonus);
            application.getDataBase().getDAO().insertUser(user);

            //Show complete insert
            runOnUiThread(() -> {
                Toast.makeText(RegisterActivity.this, "¡User registrado!", Toast.LENGTH_LONG).show();
            });
        });
    }


    private void abortRegister() {
        //Todo: Incluir mensaje de confirmación

        Toast.makeText(this, "Registro cancelado", Toast.LENGTH_LONG).show();

        finish();
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
        // SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String colorHex = PreferenceManager.getDefaultSharedPreferences(this).
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
        ViewGroup background = findViewById(R.id.background_register);
        background.setBackgroundColor(getColor(background_color));

        Drawable drawable = getDrawable(R.drawable.button_background_circle);
        drawable.setTint(getColor(title_background));

        Button btnRegister = findViewById(R.id.btnRegistrar);
        Button btnIgnore = findViewById(R.id.btnDescartar);

        btnIgnore.setBackground(drawable);
        btnRegister.setBackground(drawable);

        TextView txtTitle = findViewById(R.id.txtTitleRegister);
        txtTitle.setTextColor(getColor(title_background));
    }

}