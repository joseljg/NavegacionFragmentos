package es.joseljg.navegacionfragmentos.clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.joseljg.navegacionfragmentos.R;


public class ListaProvinciasAdapter  extends RecyclerView.Adapter<ProvinciaViewHolder> {

    private Context c;
    private ArrayList<Provincia> listaProvincias;
    private LayoutInflater mInflater;
    private int pagina;

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public ListaProvinciasAdapter(Context c, ArrayList<Provincia> listaprovincias) {
        this.c = c;
        this.listaProvincias = listaprovincias;
        mInflater = LayoutInflater.from(c);
        this.pagina = 0;
    }

    @NonNull
    @Override
    public ProvinciaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_recyclerview_provincia, parent, false);
        return new ProvinciaViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ProvinciaViewHolder holder, int position) {
        Provincia provinciaActual = listaProvincias.get(position);
        holder.txt_rv_idprovinciap.setText(String.valueOf("idprovincia: " + provinciaActual.getIdprovincia()));
        holder.txt_rv_nombrep.setText("Ciudad: " + provinciaActual.getNombre());
        if (provinciaActual.getFotoProvincia() != null) {
            holder.img_provincia.setImageBitmap(provinciaActual.getFotoProvincia());
        }
        else{
            holder.img_provincia.setImageResource(R.drawable.bandera1);
        }
    }

    @Override
    public int getItemCount() {
        if (listaProvincias != null) {
            return listaProvincias.size();
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

    public ArrayList<Provincia> getListaProvincias() {
        return listaProvincias;
    }

    public void setListaProvincias(ArrayList<Provincia> listaProvincias) {
        this.listaProvincias = listaProvincias;
    }

}
