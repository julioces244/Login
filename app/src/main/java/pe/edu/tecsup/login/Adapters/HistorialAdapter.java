package pe.edu.tecsup.login.Adapters;

import android.app.Activity;
import android.content.Intent;
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

import pe.edu.tecsup.login.Activities.HistorialListActivity;
import pe.edu.tecsup.login.Class.Historial;
import pe.edu.tecsup.login.Class.Usuario;
import pe.edu.tecsup.login.Interface.ApiService;
import pe.edu.tecsup.login.R;

public class HistorialAdapter extends RecyclerView.Adapter<HistorialAdapter.ViewHolder>{

    private static final String TAG = HistorialListActivity.class.getSimpleName();
    private List<Historial> historial;
    private Activity activity;

    public HistorialAdapter(){
        this.historial = new ArrayList<>();
    }

    public void setHistorial(List<Historial> historial){
        this.historial = historial;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView image_historial;
        public TextView direccion_inmbueble;
        public TextView descripcion_inmbueble;

        public ViewHolder(View itemView) {
            super(itemView);
            image_historial = itemView.findViewById(R.id.image_historial);
            direccion_inmbueble = itemView.findViewById(R.id.direccion_texthistorial);
            descripcion_inmbueble = itemView.findViewById(R.id.descripcion1_texthistorial);
        }
    }

    @Override
    public HistorialAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_historial, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String dire = "";
        String des = "";
        String url = "";

        dire = historial.get(position).getDireccion();
        des = historial.get(position).getDescripcion();
        url = ApiService.API_BASE_URL + "/images/" + historial.get(position).getImagen();

        holder.direccion_inmbueble.setText(dire);
        holder.descripcion_inmbueble.setText(des);
        Picasso.with(holder.itemView.getContext()).load(url).into(holder.image_historial);


    }

    @Override
    public int getItemCount() {
        return this.historial.size();
    }

}
