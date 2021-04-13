package es.joseljg.navegacionfragmentos.clases;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;
import java.io.ByteArrayOutputStream;

import es.joseljg.navegacionfragmentos.MostrarDetalleCiudadActivity;
import es.joseljg.navegacionfragmentos.R;



public class CiudadViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public static final String EXTRA_OBJETO_CIUDAD = "es.joseljg.CiudadViewHolder.ciudad";
    public static final String EXTRA_IMAGEN_CIUDAD = "es.joseljg.CiudadViewHolder.imagen_ciudad";

    public TextView txt_rv_nombrec = null;
    public TextView txt_rv_habitantesc = null;
    public TextView txt_rv_provinciac = null;
    public ImageView img_ciudad = null;

    final ListaCiudadesAdapter lcAdapter;

    public CiudadViewHolder(@NonNull View itemView, ListaCiudadesAdapter mAdapter) {
        super(itemView);
        txt_rv_nombrec = (TextView)  itemView.findViewById(R.id.txt_rv_nombrec);
        txt_rv_habitantesc = (TextView)  itemView.findViewById(R.id.txt_rv_habitantesc);
        txt_rv_provinciac = (TextView)  itemView.findViewById(R.id.txt_rv_provinciac);
        img_ciudad = (ImageView)  itemView.findViewById(R.id.img_ciudad);
        this.lcAdapter = mAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int mPosition = getLayoutPosition();
        Ciudad ciudad = this.lcAdapter.getListaCiudades().get(mPosition);
        lcAdapter.notifyDataSetChanged();
        Intent intent = new Intent(lcAdapter.getC(), MostrarDetalleCiudadActivity.class);
        Ciudad ciudad_sin_imagen = new Ciudad(ciudad.getIdciudad(), ciudad.getNombre(), ciudad.getHabitantes(), ciudad.getIdprovincia());
        intent.putExtra(EXTRA_OBJETO_CIUDAD, ciudad_sin_imagen);
        Bitmap foto_ciudad_png = ciudad.getFotoCiudad();
        if(foto_ciudad_png != null){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            foto_ciudad_png.compress(Bitmap.CompressFormat.PNG, 0, baos);
            //  foto_ciudad_png.compress(Bitmap.CompressFormat.JPEG, 0, baos);

            byte[] byteArrayfoto = baos.toByteArray();
            intent.putExtra(EXTRA_IMAGEN_CIUDAD, byteArrayfoto);
        }
        lcAdapter.getC().startActivity(intent);
    }
}

