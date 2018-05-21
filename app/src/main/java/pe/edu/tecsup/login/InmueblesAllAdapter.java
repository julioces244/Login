package pe.edu.tecsup.login;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import pe.edu.tecsup.login.Class.Inmueble;
import pe.edu.tecsup.login.Interface.ApiService;

/**
 * Created by Julio Cesar on 28/04/2018.
 */

public class InmueblesAllAdapter extends RecyclerView.Adapter<InmueblesAllAdapter.ViewHolder>{

    private List<Inmueble> inmuebles;

    private Activity activity;

    public InmueblesAllAdapter(Activity activity){
        this.inmuebles = new ArrayList<>();
        this.activity = activity;
    }

    public void setInmuebles(List<Inmueble> inmuebles){
        this.inmuebles = inmuebles;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView fotoImage2;
        public TextView nombreText2;
        public TextView precioText2;

        public ViewHolder(View itemView) {
            super(itemView);
            fotoImage2 = (ImageView) itemView.findViewById(R.id.foto_image);
            nombreText2 = (TextView) itemView.findViewById(R.id.nombre_text);
            precioText2 = (TextView) itemView.findViewById(R.id.precio_text);
        }
    }

    @Override
    public InmueblesAllAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inmueble2, parent, false);
        return new InmueblesAllAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InmueblesAllAdapter.ViewHolder viewHolder, int position) {

        final Inmueble inmueble = this.inmuebles.get(position);

        viewHolder.nombreText2.setText(inmueble.getDireccion());
        viewHolder.precioText2.setText("" + inmueble.getDescripcion());

        String url = ApiService.API_BASE_URL + "/images/" + inmueble.getImagen();
        Picasso.with(viewHolder.itemView.getContext()).load(url).into(viewHolder.fotoImage2);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailInmueblesAllActivity.class);
                intent.putExtra("ID", inmueble.getIdinmueble());
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.inmuebles.size();
    }

    // Busqueda
    public void filterList(ArrayList<Inmueble> filteredList) {
        inmuebles = filteredList;
        notifyDataSetChanged();
    }
}
