package es.joseljg.navegacionfragmentos.clases;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import es.joseljg.navegacionfragmentos.R;


public class ListaCiudadesAdapter extends RecyclerView.Adapter<CiudadViewHolder> {

    private Context c;
    private ArrayList<Ciudad> listaCiudades;
    private LayoutInflater mInflater;
    private int pagina;

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public ListaCiudadesAdapter(Context c, ArrayList<Ciudad> listaciudades) {
        this.c = c;
        this.listaCiudades = listaciudades;
        mInflater = LayoutInflater.from(c);
        this.pagina = 0;
    }

    @NonNull
    @Override
    public CiudadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_recyclerview_ciudad, parent, false);
        return new CiudadViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CiudadViewHolder holder, int position) {
        Ciudad ciudadActual = listaCiudades.get(position);
        holder.txt_rv_nombrec.setText("Ciudad: " + ciudadActual.getNombre());
        holder.txt_rv_habitantesc.setText(String.valueOf("habitantes: " + ciudadActual.getHabitantes()));
        holder.txt_rv_provinciac.setText(String.valueOf("idprovincia: " + ciudadActual.getIdprovincia()));
       if (ciudadActual.getFotoCiudad() != null) {
            holder.img_ciudad.setImageBitmap(ciudadActual.getFotoCiudad());
       }
       else{
           holder.img_ciudad.setImageResource(R.drawable.ciudad1);
       }
    }

    @Override
    public int getItemCount() {
        if (listaCiudades != null) {
            return listaCiudades.size();
        } else {
            return 0;
        }
    }

    public Context getC() {
        return c;
    }

    public void setC(Context c) {
        this.c = c;
    }

    public ArrayList<Ciudad> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(ArrayList<Ciudad> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

}
