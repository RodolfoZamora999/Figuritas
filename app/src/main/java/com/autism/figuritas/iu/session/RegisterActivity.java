package com.autism.figuritas.iu.session;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.autism.figuritas.MyDatabaseApplication;
import com.autism.figuritas.R;
import com.autism.figuritas.persistence.database.Bonificacion;
import com.autism.figuritas.persistence.database.Configuracion;
import com.autism.figuritas.persistence.database.Historial;
import com.autism.figuritas.persistence.database.Usuario;
import java.util.Calendar;

/**
 * Class for register a new user
 */
public class RegisterActivity extends AppCompatActivity
{
    private Button btnRegister;
    private Button btnAbort;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //References
        this.btnRegister = findViewById(R.id.btnRegistrar);
        this.btnAbort = findViewById(R.id.btnDescartar);

        //Events
        this.btnRegister.setOnClickListener(view-> registerUser(view));
        this.btnAbort.setOnClickListener(view -> abortRegister(view));
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        hideSystemUI();
    }

    /**
     * This method register a new user in Database
     * @param view
     */
    private void registerUser(View view)
    {
        //Generate id for user with time now
        Calendar calendar = Calendar.getInstance();

        int miliSecond =  calendar.get(Calendar.MILLISECOND);
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
        final Configuracion configuracion = new Configuracion();
        configuracion.id_config = id;
        configuracion.dificultad = 1;
        configuracion.musica = true;
        configuracion.sonido = true;
        configuracion.color = "FFFF";

        //Create history object
        final Historial historial = new Historial();
        historial.id_historial = id;
        historial.porcentaje = 0;
        historial.descripcion = "Empty";

        //Create bonus object
        final Bonificacion bonificacion = new Bonificacion();
        bonificacion.id_bonificacion = id;
        bonificacion.bonificacion_acumulada = 0;

        //Todo: Verificar los datos de entrada
        //Create user object
        String name = ((EditText)findViewById(R.id.editNombre)).getText().toString();
        String apell_p = ((EditText)findViewById(R.id.editApellidoP)).getText().toString();
        String apell_m = ((EditText)findViewById(R.id.editApellidoM)).getText().toString();
        byte age = Byte.parseByte(((EditText)findViewById(R.id.editEdad)).getText().toString());
        String email = ((EditText)findViewById(R.id.editEmail)).getText().toString();
        String password = ((EditText)findViewById(R.id.editContrasena)).getText().toString();

        final Usuario usuario = new Usuario();
        usuario.id_usuario = id;
        usuario.nombre = name;
        usuario.apellido_paterno = apell_p;
        usuario.apellido_materno = apell_m;
        usuario.edad = age;
        usuario.email = email;
        usuario.contrasena = password;
        usuario.id_config = configuracion.id_config;
        usuario.id_historial = historial.id_historial;
        usuario.id_bonificacion = bonificacion.id_bonificacion;

        //Insert all objects in database
        final MyDatabaseApplication application = (MyDatabaseApplication)getApplication();

        application.getDataBase().getQueryExecutor().execute(()->
        {
            application.getDataBase().getDAO().insertConfig(configuracion);
            application.getDataBase().getDAO().inserHistory(historial);
            application.getDataBase().getDAO().insertBonus(bonificacion);
            application.getDataBase().getDAO().insertUser(usuario);

            //Show complete insert
            runOnUiThread(()-> {
                Toast.makeText(RegisterActivity.this,  "¡Usuario registrado!", Toast.LENGTH_LONG).show();
            });
        });
    }

    /**
     *This method cancels the registration of a new user, returns to the main activity
     * @param view
     */
    private void abortRegister(View view)
    {
        //Todo: Incluir mensaje de confirmación

        Toast.makeText(this, "Registro cancelado", Toast.LENGTH_LONG).show();

        finish();
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