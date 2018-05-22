package pe.edu.tecsup.login;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import pe.edu.tecsup.login.Class.Historial;
import pe.edu.tecsup.login.Interface.ApiService;
import pe.edu.tecsup.login.Interface.ApiServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Julio Cesar on 20/05/2018.
 */

public class HistorialesAdapter extends RecyclerView.Adapter<HistorialesAdapter.ViewHolder> {

    private List<Historial> historiales;

    public HistorialesAdapter(){
        this.historiales = new ArrayList<>();
    }

    public void setHistoriales(List<Historial> historiales){
        this.historiales = historiales;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView fotoImage;
        public TextView nombreText;
        public TextView precioText;

        public ViewHolder(View itemView) {
            super(itemView);
            fotoImage = (ImageView) itemView.findViewById(R.id.foto_image);
            nombreText = (TextView) itemView.findViewById(R.id.nombre_text);
            precioText = (TextView) itemView.findViewById(R.id.precio_text);
        }
    }

    @Override
    public HistorialesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_historial, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HistorialesAdapter.ViewHolder viewHolder, int position) {

        Historial historial = this.historiales.get(position);

        viewHolder.nombreText.setText(historial.getInmueble_idinmueble());
        viewHolder.precioText.setText(historial.getUsuarios_idusuarios());

        //String url = ApiService.API_BASE_URL + "/images/" + producto.getImagen();
        //Picasso.with(viewHolder.itemView.getContext()).load(url).into(viewHolder.fotoImage);

    }

    @Override
    public int getItemCount() {
        return this.historiales.size();
    }

}

