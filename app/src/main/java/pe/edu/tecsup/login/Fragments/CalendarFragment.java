package pe.edu.tecsup.login.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import pe.edu.tecsup.login.Activities.HistorialListActivity;
import pe.edu.tecsup.login.R;


public class CalendarFragment extends Fragment {
    //private View parentView;
    private Button publicar;
    private CardView historial;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

       // publicar = (Button) view.findViewById(R.id.button5);
        historial = view.findViewById(R.id.cardhistorial);

        historial.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Crea el nuevo fragmento y la transacción.
                Intent editprofile = new Intent(getContext(), HistorialListActivity.class);
                startActivity(editprofile);

            }
        });

        return view;
    }




}
