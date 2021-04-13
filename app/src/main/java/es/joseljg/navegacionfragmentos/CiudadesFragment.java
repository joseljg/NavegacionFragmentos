package es.joseljg.navegacionfragmentos;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import es.joseljg.navegacionfragmentos.clases.Ciudad;
import es.joseljg.navegacionfragmentos.clases.ConfiguracionesGenerales;
import es.joseljg.navegacionfragmentos.clases.ListaCiudadesAdapter;
import es.joseljg.navegacionfragmentos.controladores.CiudadController;
import es.joseljg.navegacionfragmentos.utilidades.PaginationListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CiudadesFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class CiudadesFragment extends Fragment {

    private static final int PETICION1 = 1;
    private RecyclerView mRecyclerView;
    private ListaCiudadesAdapter mAdapter;
    private ArrayList<Ciudad> ciudades;
    private int pagina_actual;
    private int total_registros;
    private int total_paginas;
    private int num_columnas_landscape;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//   private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
 //   private String mParam1;
 //   private String mParam2;
/*
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CiudadesFragment.
     */
    // TODO: Rename and change types and number of parameters
 /*   public static CiudadesFragment newInstance(String param1, String param2) {
        CiudadesFragment fragment = new CiudadesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CiudadesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_ciudades, container, false);
        //-----------------------------------------------------------
        total_registros = CiudadController.obtenerCantidadCiudades();
        Log.i("sql", "total registros -> " + String.valueOf(total_registros));

        total_paginas = (total_registros / ConfiguracionesGenerales.ELEMENTOS_POR_PAGINA) +  1;
        Log.i("sql", "total paginas -> " + String.valueOf(total_paginas));

        pagina_actual=0;
        num_columnas_landscape = 2;
        ciudades = CiudadController.obtenerCiudades(pagina_actual);
        pagina_actual++;
        if(ciudades != null) {
            Log.i("sql", "pagina actual -> " + String.valueOf(pagina_actual));
            Log.i("sql", "ciudades leidas -> " + String.valueOf(this.ciudades.size()));
            mRecyclerView = vista.findViewById(R.id.rv_ciudades);
            mAdapter = new ListaCiudadesAdapter(getActivity(), ciudades);
            mRecyclerView.setAdapter(mAdapter);
            int orientation = 1;
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            } else {
                mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), ConfiguracionesGenerales.LANDSCAPE_NUM_COLUMNAS));
            }

            // paginacion
            mRecyclerView.addOnScrollListener(new PaginationListener((LinearLayoutManager) mRecyclerView.getLayoutManager()) {
                private int ciudades_leidas = 0;
                @Override
                protected void loadMoreItems() {

                    int total_registros_leidos = mRecyclerView.getLayoutManager().getItemCount();
                    if (total_registros_leidos < total_registros) {
                        ArrayList<Ciudad> nuevasCiudades = CiudadController.obtenerCiudades(pagina_actual);
                        ciudades_leidas = nuevasCiudades.size();
                        pagina_actual++;

                        Boolean resultado = mRecyclerView.post(new Runnable()
                        {
                            @Override
                            public void run() {
                                ListaCiudadesAdapter mAdapter1 =(ListaCiudadesAdapter) mRecyclerView.getAdapter();
                                ArrayList<Ciudad> ciudades_rv = mAdapter1.getListaCiudades();
                                ciudades_rv.addAll(nuevasCiudades);
                                mRecyclerView.getAdapter().notifyDataSetChanged();
                            }});
                        Log.i("sql", "siguiente pagina -> " + String.valueOf(pagina_actual));
                        Log.i("sql", "total registros -> " + String.valueOf(total_registros));
                        Log.i("sql", "total registros leidos -> " + String.valueOf(total_registros_leidos));
                        Log.i("sql", "ciudades leidas -> " + String.valueOf(this.ciudades_leidas));

                    }
                    else{
                        ciudades_leidas = 0;
                    }
                }
                @Override
                public boolean isLastPage() {
                    if(pagina_actual > total_paginas -1 ) {
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            });
        }
        else{
            mostrarToast("no se pudo establecer la conexion con la base de datos");
            Log.i("sql", "error en el sql");
        }
        return vista;
    }


    private void mostrarToast(String texto) {
        Toast.makeText(getActivity(),texto, Toast.LENGTH_SHORT).show();
    }

    }
