package com.autism.figuritas.iu.session;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.autism.figuritas.MyDatabaseApplication;
import com.autism.figuritas.R;
import com.autism.figuritas.persistence.database.Bonus;
import com.autism.figuritas.persistence.database.Configuration;
import com.autism.figuritas.persistence.database.History;
import com.autism.figuritas.persistence.database.User;

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

}