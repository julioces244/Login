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
 * Created by Julio Cesar on 15/04/2018.
 */

public class InmueblesAdapter extends RecyclerView.Adapter<InmueblesAdapter.ViewHolder> {

    private List<Inmueble> inmuebles;

    private Activity activity;

    public InmueblesAdapter(Activity activity){
        this.inmuebles = new ArrayList<>();
        this.activity = activity;
    }

    public void setInmuebles(List<Inmueble> inmuebles){
        this.inmuebles = inmuebles;
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
    public InmueblesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inmueble, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InmueblesAdapter.ViewHolder viewHolder, int position) {

        final Inmueble inmueble = this.inmuebles.get(position);

        viewHolder.nombreText.setText(inmueble.getDireccion());
        viewHolder.precioText.setText("" + inmueble.getDescripcion());

        String url = ApiService.API_BASE_URL + "/images/" + inmueble.getImagen();
        Picasso.with(viewHolder.itemView.getContext()).load(url).into(viewHolder.fotoImage);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailInmuebleActivity.class);
                intent.putExtra("ID", inmueble.getIdinmueble());
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.inmuebles.size();
    }

}

