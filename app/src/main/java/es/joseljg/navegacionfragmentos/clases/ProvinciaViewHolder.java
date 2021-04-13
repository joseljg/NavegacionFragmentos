package es.joseljg.navegacionfragmentos.clases;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;

import es.joseljg.navegacionfragmentos.MostrarDetalleProvinciaActivity;
import es.joseljg.navegacionfragmentos.R;


public class ProvinciaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public static final String EXTRA_OBJETO_PROVINCIA = "es.joseljg.ProvinciaViewHolder.provincia";
    public static final String EXTRA_IMAGEN_PROVINCIA= "es.joseljg.ProvinciaViewHolder.imagen_provincia";

    public TextView txt_rv_idprovinciap = null;
    public TextView txt_rv_nombrep = null;
    public ImageView img_provincia = null;

    final ListaProvinciasAdapter lcAdapter;

    public ProvinciaViewHolder(@NonNull View itemView, ListaProvinciasAdapter mAdapter) {
        super(itemView);
        txt_rv_idprovinciap = (TextView)  itemView.findViewById(R.id.txt_rv_idprovinciap);
        txt_rv_nombrep = (TextView)  itemView.findViewById(R.id.txt_rv_nombrep);
        img_provincia = (ImageView)  itemView.findViewById(R.id.img_provincia);
        this.lcAdapter = mAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int mPosition = getLayoutPosition();
        Provincia provincia = this.lcAdapter.getListaProvincias().get(mPosition);
        lcAdapter.notifyDataSetChanged();
        Intent intent = new Intent(lcAdapter.getC(), MostrarDetalleProvinciaActivity.class);
        Provincia provincia_sin_imagen = new Provincia(provincia.getIdprovincia(), provincia.getNombre());
        intent.putExtra(EXTRA_OBJETO_PROVINCIA, provincia_sin_imagen);
        Bitmap foto_provincia_png = provincia.getFotoProvincia();
        if(foto_provincia_png != null){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            foto_provincia_png.compress(Bitmap.CompressFormat.PNG, 0, baos);
            //  foto_ciudad_png.compress(Bitmap.CompressFormat.JPEG, 0, baos);

            byte[] byteArrayfoto = baos.toByteArray();
            intent.putExtra(EXTRA_IMAGEN_PROVINCIA, byteArrayfoto);
        }
        lcAdapter.getC().startActivity(intent);


    }
}
