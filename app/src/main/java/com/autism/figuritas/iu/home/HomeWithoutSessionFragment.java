package com.autism.figuritas.iu.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.autism.figuritas.R;
import com.autism.figuritas.iu.session.RegisterActivity;
import com.autism.figuritas.iu.session.SessionActivity;

public class HomeWithoutSessionFragment extends Fragment
{
    private final int REQUEST_CODE = 404;

    private Button btnInitSession;
    private Button btnRegister;

    public HomeWithoutSessionFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_home_without_sesion, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        //References
         btnInitSession = getActivity().findViewById(R.id.btnIniciarSesionFragment);
         btnRegister = getActivity().findViewById(R.id.btnRegistrateFragment);

        //Events click
        btnRegister.setOnClickListener(v->
                startActivity(new Intent(getContext(), RegisterActivity.class)));

        btnInitSession.setOnClickListener(v->
                {
                    Intent intent = new Intent();
                    intent.setClass(getContext(), SessionActivity.class);

                    startActivityForResult(intent, REQUEST_CODE);
                }
        );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

       if(requestCode == REQUEST_CODE)
       {
           if(resultCode == Activity.RESULT_OK)
           {
               Navigation.findNavController(getView()).navigate(R.id.action_homeWithoutSessionFragment_to_homeSessionFragment);
           }
           else if(resultCode == Activity.RESULT_CANCELED)
           {
               Toast.makeText(getContext(), "Error de sesión...", Toast.LENGTH_LONG).show();
           }
       }
    }
}