package es.joseljg.navegacionfragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.joseljg.navegacionfragmentos.clases.Provincia;
import es.joseljg.navegacionfragmentos.clases.ProvinciaViewHolder;
import es.joseljg.navegacionfragmentos.utilidades.ImagenesBlobBitmap;

import static es.joseljg.navegacionfragmentos.clases.ProvinciaViewHolder.EXTRA_OBJETO_PROVINCIA;


public class MostrarDetalleProvinciaActivity extends AppCompatActivity {

    TextView txt_detalle_idprovinciap = null;
    TextView txt_detalle_nombrep = null;
    ImageView img_detalle_provincia = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_detalle_provincia);
        txt_detalle_idprovinciap = (TextView)findViewById(R.id.txt_detalle_idprovinciap);
        txt_detalle_nombrep = (TextView)findViewById(R.id.txt_detalle_nombrep);
        img_detalle_provincia = (ImageView)findViewById(R.id.img_detalle_provincia);
        Intent intent = getIntent();
        if (intent != null) {
            Provincia p = (Provincia) intent.getSerializableExtra(EXTRA_OBJETO_PROVINCIA);
            txt_detalle_idprovinciap.setText("idprovincia: " + String.valueOf(p.getIdprovincia()));
            txt_detalle_nombrep.setText("nombre: " + p.getNombre());
            byte[] byteArray = intent.getByteArrayExtra(ProvinciaViewHolder.EXTRA_IMAGEN_PROVINCIA);
            if (byteArray != null) {
                img_detalle_provincia.setImageBitmap(ImagenesBlobBitmap.bytes_to_bitmap(byteArray));
            }
        }
    }

    public void atras_detalle(View view) {
        finish();
    }
}