package pe.edu.tecsup.login;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;



public class CalendarFragment extends Fragment {
    private View parentView;
    private Button publicar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_calendar, container, false);

        publicar = (Button) parentView.findViewById(R.id.button5);
        publicar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Crea el nuevo fragmento y la transacción.
//                Intent editprofile = new Intent(SettingsFragment.this.getActivity(), InmueblesListActivity.class);
//                startActivity(editprofile);

            }
        });

        return parentView;
    }




}
