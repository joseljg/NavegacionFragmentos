package es.joseljg.navegacionfragmentos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;


import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

private ImageButton bt_ciudades = null;
private ImageButton bt_provincias = null;
private Fragment principalFragment = null;
private String subtitulo = "";

public static final String EXTRA_FRAGMENTO = "es.joseljg.CiudadViewHolder.principalFragment";
public static final String EXTRA_SUBTITULO = "es.joseljg.CiudadViewHolder.subtitulo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            //Restore the fragment's instance
            leerFragment(getSupportFragmentManager().getFragment(savedInstanceState, EXTRA_FRAGMENTO));
            getSupportActionBar().setSubtitle(savedInstanceState.getString(EXTRA_SUBTITULO));
        }
        else {
            leerFragment(new CiudadesFragment());
            getSupportActionBar().setSubtitle(R.string.listaciudades);
        }
        bt_ciudades = (ImageButton) findViewById(R.id.bt_ciudades);
        bt_provincias = (ImageButton) findViewById(R.id.bt_provincias);


        bt_ciudades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setSubtitle(R.string.listaciudades);
                leerFragment(new CiudadesFragment());
                subtitulo = getString(R.string.listaciudades);
            }
        });
        bt_provincias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setSubtitle(R.string.lista_de_provincias);
                leerFragment(new ProvinciasFragment());
                subtitulo = getString(R.string.lista_de_provincias);
            }
        });
    }

    private void leerFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_principal,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        principalFragment = fragment;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, EXTRA_FRAGMENTO, principalFragment);
        outState.putString(EXTRA_SUBTITULO, subtitulo);
    }
    @Override
    public void onRestoreInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

}